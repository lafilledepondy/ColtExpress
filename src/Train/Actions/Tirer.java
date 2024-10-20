package Train.Actions;

import Train.ButinType.Butin;
import Train.CaseType.Case;
import Train.Direction;
import Train.PersonneType.Bandit;
import Train.PersonneType.Personnage;

/**
 * La classe Tirer représente une action où un personnage tire sur un bandit pour lui voler un butin.
 * Cette classe fait partie du package Train.Actions.
 */
public class Tirer extends Action {
    //ATTRIBUTS
    Bandit bCible;
    Case cible;
    Direction dirBullet;

    //CONSTRUCTEUR
    /**
     * Constructeur de la classe Tirer.
     *
     * @param Tireur Le personnage qui effectue l'action de tirer (Il peut soit etre un bandit ou un marshall).
     * @param bCible Le bandit cible sur lequel le personnage tire.
     * @param cCible la case sur laquelle se trouve le bandit cible
     */
    public Tirer(Personnage Tireur, Bandit bCible, Case cCible) {
        super(Tireur);
        this.bCible = bCible;
        this.cible = cCible;
    }

    //METHODE
    public Personnage getPersonnage(){
        return personnage;
    }
    public Personnage getBCible(){
        return this.bCible;
    }
    public void setbCible(Bandit bCible){
        this.bCible = bCible;
    }

    public Direction getDirBullet() {
        return dirBullet;
    }

    public void setDirBullet(Direction d){
        this.dirBullet = d;
    }
    public void setCible(Case cible){
        this.cible = cible;
    }
    
    /**
     * Méthode permettant d'exécuter l'action de tirer sur un bandit.
     * Verifier si le tireur est un bandit pour lui enlever une balle (Le marshall a une infinité).
     * S'il existe un bandit sur la case ou le tireur tire :
     *  - Le bandit cible perd un butin aleatoire
     *  - La case cible gagne un butin par terre.
     * Sinon :
     *  - Un message s'affiche indiquant qu'il n'ya pas de bandit
     */
    @Override
    public void executer() {
        //enlever un bullet du bandit qui a tiré ci c'est un bandit
        if(personnage instanceof Bandit) ((Bandit)personnage).enleverBullet();
        if(bCible != null) {
            //enlever un butin aleatoire du bandit cible
            Butin bEnlever = bCible.enleverButinAleat();

            if(bEnlever != null) {
                System.out.println("Butin enlever de " + bCible.getNom() + " !");
            }else{
                System.out.println("Pas de butin enlever de " + bCible.getNom() + ".");
            }
            //Enlever la somme d'argent du bandit cible
            (bCible).enleverSommeArgent(bEnlever);
            //Mettre cette somme d'argent dans la case où se trouve le bandit cible
            this.cible.placerButin(bEnlever);
        }else{
            System.out.println("Pas de bandit dans la case!");
        }

        System.out.println(" ");
    }
}
package Train.PersonneType;

import Train.ButinType.Butin;
import Train.CaseType.Case;

import java.util.ArrayList;
import java.util.Random;

/**
 * La classe abstraite Personne représente une personne du train.
 * Elle est la classe mere à beaucoup de classes qui ajoutent par la suite des fonctionnalités
 * Cette classe fait partie du package Train.PersonneType.
 */
public abstract class Personne {
    //ATTRIBUTS
    private Case position;
    private ArrayList<Butin> butins;

    //CONSTRUCTEUR
    /**
     * Constructeur de la classe Personne.
     *
     * @param pos la case sur laquelle se trouve la personne.
     * Crée une instance de Personnage avec une position et une poche vide qui pourra contenir par la suite des butins.
     */
    public Personne(Case pos){
        this.position = pos;
        butins = new ArrayList<>();
    }

    //METHODES
    /**
     * Récupère la position du personnage
     *
     * @return une case
     */
    public Case getPosition(){
        return position;
    }

    /**
     * Change la position du personnage à celle mis en paramètre
     *
     * @param e une case
     */
    public void setPosition(Case e){
        this.position = e;

        System.out.println("\n" + "Update: perso dans wagon: " + position.getNumWagon());
        if(getPosition().getRoof()){
            System.out.println("Il est sur le toit");
        }else{System.out.println("Il est a l'interieur du wagon");}
    }

    /**
     * Récupère la liste des butins que contient la personne.
     *
     * @return une liste de type ArrayList.
     */
    public ArrayList<Butin> getButins() {
        return this.butins;
    }

    /**
     * Indique si la personne contient des butins.
     *
     * @return un boolean true si la personnne contient des butins,
     *                 et false sinon.
     */
    public boolean ContientButin(){
        return !(getNbButins() == 0);
    }

    /**
     * Donne le nombre de butins que contient la personne.
     *
     * @return un entier.
     */
    public int getNbButins(){
        return this.butins.size();
    }

    /**
     * Ajoute un butin pris en parametre a la liste de butins que contient la personne.
     *
     * @param b un butin a ajouter.
     */
    public void ajouterButin(Butin b){
        this.butins.add(b);
    }

    /**
     * Enlever un butin pris en parametre de la liste de butins que contient la personne.
     *
     * @param b un butin a enlever.
     */
    public void enleverButin(Butin b){
        this.butins.remove(b);
    }

    /**
     * Enleve un butin aleatoire de la liste de butins que contient la personne.
     *
     * @return un Butin qui est celui enlevé si la personne contient des butins,
     *         vide sinon.
     */
    public Butin enleverButinAleat(){
        if(ContientButin()) {
            Random random = new Random();
            int numRand = random.nextInt(getNbButins());
            Butin butin = this.butins.get(numRand);
            this.butins.remove(numRand);
            return butin;
        }
        return null;
    }
}

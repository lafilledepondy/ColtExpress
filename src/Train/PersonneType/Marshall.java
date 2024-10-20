package Train.PersonneType;

import Train.Actions.*;
import Train.CaseType.Case;
import Train.Direction;
import java.util.Random;

/**
 * La classe Marshall représente un type de personnage qui existe dans le train et qui le surveille et protege les passagers.
 * Il est capable de se déplacer à l'intérieur des différents wagons.
 * Cette classe étend la classe Personnage et fait partie du package Train.PersonneType.
 */
public class Marshall extends Personnage {
    //ATTRIBUTS
    Action action;
    final static double NERVOSITE_MARSHALL = 0.3;

    //CONSTRUCTEUR
    /**
     * Constructeur de la classe Marshall.
     *
     * @param pos la case sur laquelle se trouve le marshall.
     * Crée une instance de Bandit avec une position et un nom.
     */
    public Marshall(Case pos) {
        super(pos,"JEAN");
    }
    /*
    public void deplacerGauche(){
        if (this.getPosition().getNumWagon() >= 1) {
            this.setPosition(new Case(this.getPosition().getRoof(),this.getPosition().getNumWagon()-1));
            System.out.println("Le marshall se déplace vers l'arrière du train.");
        }else {
            System.out.println("Le marshall est déjà dans le dernier wagon.");
        }
    }
    public void deplacerDroite(){
        if (this.getPosition().getNumWagon() <= 2) {
            this.setPosition(new Case(this.getPosition().getRoof(),this.getPosition().getNumWagon()+1));
            System.out.println("Le marshall se déplace vers l'arrière du train.");
        }else {
            System.out.println("Le marshall est déjà dans le dernier wagon.");
        }
    }
    public void deplacerAleatoire() {
        Random rand = new Random();
        if (rand.nextDouble() < NERVOSITE_MARSHALL) {
            if (rand.nextDouble() < 0.5) {
                deplacerDroite();
            }else{
                deplacerGauche();
            }
        }
    }
    */

    //METHODES
    /**
     * Execute l'action du déplacement aléatoire.
     */
    public void executerAction(Action a){
        this.action = a;
        action.executer();
    }

    /**
     * Méthode pour déplacer le marshall aléatoirement à gauche ou à droite
     * avec une probabilité de NERVOSITEMARSHALL prédéfinie.
     */
    public void deplacerMarshall(){
        Random rand = new Random();
        if (rand.nextDouble() < NERVOSITE_MARSHALL) {
            Deplacer deplacer = null;
            if (rand.nextDouble() < 0.5) {
                deplacer = new Deplacer(this, Direction.ARRIERE);
            }else{
                deplacer = new Deplacer(this, Direction.ARRIERE);
            }
            executerAction(deplacer);
        }
    }
}
package Train.Actions;

import Train.CaseType.Case;
import Train.Direction;
import Train.PersonneType.Marshall;
import Train.PersonneType.Personnage;

/**
 * La classe Deplacer représente une action où un personnage se déplace dans une direction spécifiée.
 * Cette classe fait partie du package Train.Actions.
 */
public class Deplacer extends Action {
    //ATTRIBUT
    Direction d;

    //CONSTRUCTEUR
    /**
     * Constructeur de la classe Deplacer.
     *
     * @param p La personne effectuant l'action de déplacement.
     * @param d La direction dans laquelle se déplace la personne.
     */
    public Deplacer(Personnage p, Direction d) {
        super(p);
        this.d = d;
    }
    /**
     * Méthode qui verifie si l'attribut personnage est un marshall.
     * @return une chaîne de caractères,
     *          sinon null.
     */
    public String checkIfMarshall(){
        if(personnage instanceof Marshall){
            return "Le marshall ";
        }else{
            return null;
        }
    }
    /**
     * Méthode pour afficher un message pour un deplacement vers la droite.
     */
    public void messageArriere(){
        String s = checkIfMarshall();
        System.out.println( s + personnage.getNom() + " avance au " + personnage.getPosition().getNumWagon() + "° wagons.");
    }
    /**
     * Méthode pour afficher un message pour un deplacement vers la gauche.
     */
    public void messageAvant(){
        String s = checkIfMarshall();
        System.out.println(  s + personnage.getNom() + " recule au " + personnage.getPosition().getNumWagon() + "° wagons.");

    }
    /**
     * Méthode pour afficher un message pour un deplacement vers le bas.
     */
    public void messageBas(){
        String s = checkIfMarshall();
        System.out.println(  s + personnage.getNom() + " descend vers l'intérieur du wagon.");
        System.out.println("Pos de "+ personnage.getNom() +": "+ personnage.getPosition().roofToNum() +
                personnage.getPosition().getNumWagon());
    }
    /**
     * Méthode pour afficher un message pour un deplacement vers le haut.
     */
    public void messageHaut(){
        String s = checkIfMarshall();
        System.out.println(  s + personnage.getNom() + " monte vers le toit du wagon.");
    }

    /**
     * Méthode pour exécuter l'action de déplacement.
     * Verifier qu'on ne dépasse pas le train dans le déplacement si :
     *  - Le personnage n'est pas sur le dernier wagon et qu'il veut aller à Droite.
     *  - Le personnage n'est pas sur le premier wagon et qu'il veut aller à gauche.
     *  - Le personnage est toit et il veut monter.
     *  - Le personnage est à l'intérieur du wagon et il désire descendre.
     *  --> Si c'est le cas afficher uniquement un message d'erreur
     *
     */
    @Override
    public void executer() {
        int numWAvant = personnage.getPosition().getNumWagon();
        boolean roof = personnage.getPosition().getRoof();
        String nomPerso = personnage.getNom();

        //System.out.println("Execution du deplacement pour le personnage: "
        //+ nomPerso +" currently au wagon n: "+ numWAvant
        //+ " Roof? "+ roof + " - Direction: " + d);

        if (numWAvant == 3 && d == Direction.ARRIERE) {
            System.out.println(nomPerso + " est deja sur le dernier wagon.");
        } else if (numWAvant == 0 && d == Direction.AVANT) {
            System.out.println(nomPerso + " est deja sur le premier wagon.");
        } else if (roof && d == Direction.HAUT) {
            System.out.println(nomPerso + " est deja sur le toit du wagon.");
        } else if (!roof && d == Direction.BAS) {
            System.out.println(nomPerso + " est deja a l'interieur du wagon.");
        }else if (numWAvant != 3 && d == Direction.ARRIERE) {
            (personnage).setPosition(new Case(roof, numWAvant + 1));
            messageArriere();
        } else if (numWAvant != 0 && d == Direction.AVANT) {
            (personnage).setPosition(new Case(roof, numWAvant - 1));
            messageAvant();
        } else if (roof && d == Direction.BAS) {
            (personnage).setPosition(new Case(false, numWAvant));
            messageBas();
        } else if (!roof && d == Direction.HAUT) {
            (personnage).setPosition(new Case(true, numWAvant));
            messageHaut();
        }
        System.out.println(" ");
    }

    public Direction getD(){
        return d;
    }
}
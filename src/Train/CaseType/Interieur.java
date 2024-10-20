package Train.CaseType;

/**
 * La classe Interieur représente une case à l'intérieur d'un wagon, sans toit.
 * Cette classe hérite de la classe Case.
 */
public class Interieur extends Case {
    //CONSTRUCTEUR
    /**
     * Constructeur de la classe Interieur.
     *
     * @param numWagon Le numéro du wagon.
     */
    public Interieur(int numWagon) {
        super(false, numWagon);
    }
}

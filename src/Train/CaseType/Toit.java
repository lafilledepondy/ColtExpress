package Train.CaseType;

/**
 * La classe Toit représente une case sur le toit d'un wagon du train.
 * Cette classe hérité de la classe Case.
 */
public class Toit extends Case {
    //CONSTRUCTEUR
    /**
     * Constructeur de la classe Toit.
     *
     * @param numWagon Le numéro du wagon.
     */
    public Toit(int numWagon) {
        super(true, numWagon);
    }
}

package Train.CaseType;

/**
 * La classe LocomotiveInt représente une case à l'intérieur de la locomotive du train, sans toit.
 * Cette classe hérite de la classe Case.
 */
public class LocomotiveInt extends Case {
    //CONSTRUCTEUR
    /**
     * Constructeur de la classe LocomotiveInt.
     * Crée une case à l'intérieur de la locomotive sans toit.
     */
    public LocomotiveInt() {
        super(false,0);
    }
}
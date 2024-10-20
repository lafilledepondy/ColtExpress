package Train.ButinType;

/**
 * La classe Magot représente un type spécifique de Butin qui contient une valeur monétaire fixe de 1 000.
 * Cette classe étend la classe abstraite Butin et fait partie du package Train.ButinType.
 */
public class Magot extends Butin {
    //CONSTRUCTEUR
    /**
     * Constructeur de la classe Magot.
     * Crée une instance de Magot avec un nom donné et une valeur monétaire fixe de 1 000.
     */
    public Magot() {
        super(1000);
    }
}
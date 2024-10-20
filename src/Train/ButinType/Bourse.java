package Train.ButinType;

import java.util.Random;

/**
 * La classe Bourse représente un type spécifique de Butin qui contient une valeur monétaire aléatoire entre 0 et 500.
 * Cette classe étend la classe abstraite Butin et fait partie du package Train.ButinType.
 */
public class Bourse extends Butin {
    //CONSTRUCTEUR
    /**
     * Constructeur de la classe Bourse.
     * Crée une instance de Bourse avec une valeur monétaire aléatoire entre 0 et 500.
     */
    public Bourse() {
        super(new Random().nextInt(501));
    }
}
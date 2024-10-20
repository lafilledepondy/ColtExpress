package Train.PersonneType;

import Train.CaseType.Case;

/**
 * La classe Passager représente un type de personne qui existe à bord du train à l'intérieur des wagons.
 * Le passager n'a pas de fonctionalité spéciale que contenir des butins.
 * Cette classe étend la classe Personnage et fait partie du package Train.PersonneType.
 */
public class Passager extends Personne{
    //CONSTRUCTEUR
    /**
     * Constructeur de la classe Bandit.
     *
     * @param pos la case sur laquelle se trouve le passager.
     * Crée une instance de Passager avec une position et une poche vide qui va contenir par la suite des butins.
     */
    public Passager(Case pos){
        super(pos);
    }
}
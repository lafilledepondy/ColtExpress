package Train.PersonneType;

import Train.CaseType.Case;

/**
 * La classe abstraite Personnage représente un type de personne spéciale.
 * Il peut realiser des actions.
 * Cette classe fait partie du package Train.PersonneType.
 */
public abstract class Personnage extends Personne{
    //ATTRIBUT
    String nom;

    //CONSTRUCTEUR
    /**
     * Constructeur de la classe Personnage.
     *
     * @param pos la case sur laquelle se trouve le personnage.
     * @param nom une chaine de caractère représentant le nom du personnage.
     * Crée une instance de Personnage avec une position et une poche vide qui pourra contenir par la suite des butins.
     */
    public Personnage(Case pos,String nom) {
        super(pos);
        this.nom = nom;
    }
    /**
     * Récupère le nom du personnage
     *
     * @return une chaine de caractère
     **/
    public String getNom(){
        return this.nom;
    }
}

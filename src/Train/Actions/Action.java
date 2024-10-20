package Train.Actions;

import Train.PersonneType.Personnage;

/**
 * La classe abstraite Action représente une action générique effectuée par une personne dans une direction donnée.
 * Cette classe fait partie du package Train.Actions.
 */
public abstract class Action {
    //ATTRIBUT
    Personnage personnage;

    //CONSTRUCTEUR
    /**
     * Constructeur de la classe Action.
     *
     * @param p La personne effectuant l'action.
     */
    public Action(Personnage p){
        this.personnage = p;
    }

    //METHODE
    /**
     * Méthode abstraite à implémenter par les classes filles pour exécuter l'action spécifique.
     */
    public abstract void executer();
}
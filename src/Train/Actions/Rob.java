package Train.Actions;

import Train.ButinType.Butin;
import Train.CaseType.Case;
import Train.PersonneType.Bandit;
import Train.PersonneType.Passager;

/**
 * La classe Recuperer représente une action où un bandit récupère un butin d'une case.
 * Cette classe fait partie du package Train.Actions.
 */
public class Rob extends Action {
    //ATTRIBUT
    Case Case;

    //CONSTRUCTEUR
    /**
     * Constructeur de la classe Recuperer.
     *
     * @param Case Le bandit effectuant l'action de récupération.
     * @param bandit Le bandit effectuant l'action de récupération.
     */
    public Rob(Bandit bandit, Case Case) {
        super(bandit);
        this.Case = Case;
    }

    //METHODE
    /**
     * Méthode pour exécuter l'action de braquage.
     * Verifier qu'il existe un passager sur la case du bandit pour le braquer et qu'il contient des butins
     * Si oui :
     *  - Enlever un butin aléatoire du passager
     *  - Ajouter le butin au bandit qui a braqué
     *  Sinon :
     *  - Afficher un message d'erreur
     */
    @Override
    public void executer() {
        if (Case.contientButinPassager()){
            Butin b = Case.getButinPassager();
            personnage.ajouterButin(b);
            System.out.println(this.personnage.getNom() + " a braqué un passagé!");
            ((Bandit)personnage).ajouterSommeArgent(b);
        }
        System.out.println(" ");
    }
}
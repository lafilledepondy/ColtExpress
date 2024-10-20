package Train.Actions;

import Train.ButinType.Butin;
import Train.CaseType.Case;
import Train.PersonneType.Bandit;
import Train.PersonneType.Personnage;
import Train.PersonneType.Passager;
/**
 * La classe Braquer représente une action où un personnage braque un passager ou récupère un butin sur le sol.
 * Cette classe fait partie du package Train.Actions.
 */
public class Braquer extends Action {
    //ATTRIBUT
    Case c;

    //CONSTRUCTEUR
    /**
     * Constructeur de la classe Braquer.
     *
     * @param bandit le bandit effectuant l'action de braquage.
     * @param c La case sur laquelle se déroule l'action.
     */
    public Braquer(Bandit bandit, Case c) {
        super(bandit);
        this.c = c;
    }

    //METHODE
    /**
     * Méthode pour exécuter l'action de récupération.
     * S'il existe des butins par terre dans la case du bandit :
     *  - Enlever un butin aléatoire de ceux par terre.
     *  - Ajouter ce butin au bandit.
     * Sinon :
     * - Afficher un message d'erreur.
     */
    @Override
    public void executer() {
        if(this.c.contientButinParTerre()) {
            Butin bRand = this.c.enleverButinAleat();
            personnage.ajouterButin(bRand);
            System.out.println(personnage.getNbButins() + " $ a été ajouté à " + personnage.getNom());
            ((Bandit)personnage).ajouterSommeArgent(bRand);
        }else{
            System.out.println("Pas de butin pas terre dans la case du bandit!");
        }
        System.out.println(" ");
    }
}
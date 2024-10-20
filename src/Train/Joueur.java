
package Train;

import Train.Actions.Action;
import Train.CaseType.Case;
import Train.PersonneType.Bandit;

import java.util.ArrayList;

/**
 * La classe Joueur représente un type de personne spéciale.
 * Il controle un bandit mis à bord et peut modifier l'écran d'affiche en appuyant sur les boutons affichés.
 * Cette classe fait partie du package Train.
 */
public class Joueur {
    private Bandit bandit;
    private boolean canPlay;
    private ArrayList<Action> actionList;

    // CONSTRUCTEUR
    /**
     * Constructeur de la classe Joueur.
     *
     * Crée une instance de Joueur avec un bandit, son nom et une liste d'action vide de base.
     */
    public Joueur(){
        this.actionList = new ArrayList<>();
        this.canPlay = false;
    }

    /**
     * Récupère le bandit du joueur.
     *
     * @return un bandit a bord.
     **/
    public Bandit getBandit(){
        return bandit;
    }

    /**
     * Récupère la liste d'actions que va faire le bandit du joueur.
     *
     * @return une liste d'actions.
     **/
    public ArrayList<Action> getActionList() {
        return actionList;
    }

    public boolean getCanPLay(){
        return canPlay;
    }
    public void changeCanPlay(){
        this.canPlay = !canPlay;
    }
    /**
     * Indique si la liste d'actions est vide ou non.
     *
     * @return un boolean true si elle est vide,
     *                    false sinon.
     **/
    public boolean hasMoves(){
        return !this.actionList.isEmpty();
    }

    /**
     * Inialise le bandit avec un nom mis en paramètre
     * et un indice representant l'indice du wagon sur lequel il serait dessus.
     *
     * @param s une chaine de caractere representant le nom du bandit
     * @param i un entier representant l'indice du wagon.
     **/
    public void initialiseBandit(String s,int i){
        this.bandit = new Bandit(s,new Case(true,i));
    }

    /**
     * Ajoute une action dans la liste d'actions que va effectuer le bandit du joueur.
     *
     * @param a une action à ajouter.
     **/
    public void ajouterAction(Action a){
        if(actionList.size() < 4) {
            this.actionList.add(a);
            System.out.println(a.getClass().getSimpleName() + " a été bien ajouté!");
        }else{
            System.out.println("Liste d'action rempli au max!");
        }
    }

    /**
     * Enlever une action de la liste d'actions du joueur pour indiquer qu'elle sait achever.
     *
     * @param a une action a enlever.
     **/
    public void enleverAction(Action a){
        if(!actionList.isEmpty()) {
            actionList.remove(a);
            System.out.println("Action Enlevé!");
        }else{
            System.out.print("Pas d'action déja cliqué!");
        }
    }

    /**
     * Exécuter la premiere action ajoutée dans la liste d'actions
     **/
    public void executerAction(){
        if(!hasMoves()){
            System.out.println("No actions clicked left!");
        }else{
            Action a = actionList.get(0);
            a.executer();
            enleverAction(a);
        }
    }

    /**
     * Enlève la dernière action ajoutée si elle existe.
     **/
    public void retour(){
        if(hasMoves()) {
            actionList.removeLast();
            System.out.println("La dernière action ajoutée a été bien enlevée.");
        } else {
            System.out.println("Pas d'action à enlever!");
        }
    }

/*
    public void initialiseBandit(){
        for(Bandit b: bandits){}}

    public Bandit getBandit(int i, int j){
        for(Bandit b: bandits){
            int l = 1;
            if(b.getPosition().getRoof()) i=0;
            int col = b.getPosition().getNumWagon();
            if(i == l && j==col){
                return b;
            }
        }
        return null;
    }
    */
}
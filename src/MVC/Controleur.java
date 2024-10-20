package MVC;

import Train.Actions.*;
import Train.Actions.Action;
import Train.CaseType.Case;
import Train.PersonneType.Marshall;
import Train.Train;
import Train.Joueur;
import Train.PersonneType.Bandit;
import static Train.Direction.*;
import Train.Direction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * La classe Controleur gere les effets des cliques sur les boutons.
 * Elle manipule le modele fin d'effectuer les changements nécessaire,
 * puis met à jour l'ecran pour afficher les changements effectuer.
 * Cette classe implémente la classe ActionListener et KeyListener de la bibliothèque de Java
 * et fait partie du package MVC.
 */
public class Controleur implements ActionListener, KeyListener {
    private Modele modele;
    private int nbPartie = 0;
    private boolean gameEnded = false;

    // CONSTRUCTEUR
    /**
     * Constructeur de la classe Controleur.
     * @param modele un modèle qui comporte les composants à afficher.
     *
     */
    public Controleur(Modele modele){
        this.modele = modele;
    }

    /**
     * Recupère le modèle du controleur
     *
     * @return un modèle
     */
    public Modele getModele(){
        return this.modele;
    }
    /**
     * Méthodes non utilisées donc non implementées
     */
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * Méthode qui gère les changements nécessaires aux composantes du train quand des boutons sont cliqués
     *
     * @param e un ActionEvent qui correspond au bouton cliqué
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Get the action command associated with the event

        Train t = this.modele.getTrain();
        Joueur joueur = this.modele.getCurrentJoueur();
        Bandit b = joueur.getBandit();
        Action a = null;

        int bNumW = b.getPosition().getNumWagon();
        int bRoofW = b.getPosition().roofToNum();

        if (isMoveCommand(command)){              //Deplacer
            a = moveAction(b,command);
        } else if(isShootCommand(command)){       //Tirer
            a = handleTirerAction(command);
        } else if ("BRAQUER".equals(command)) {   //Braquer
            a = new Braquer(b, t.getEmplacement(bRoofW, bNumW));
        } else if ("ROB".equals(command)) { //Recuperer
            a = new Rob(b,this.modele.getTrain().getEmplacement(bRoofW,bNumW));
        } else if ("RETOUR".equals(command)) {    //Retour
            joueur.retour();
        } else if("READY".equals(command)){      //Ready
            this.modele.getCurrentJoueur().changeCanPlay(); //le joueur peut maintenant cliquer sur action
            System.out.println("Le joueur "+ joueur.getBandit().getNom() + " est dans la phase d'execution!");
            this.modele.NextIndPlayer();
        } else if ("ACTION".equals(command)) {    //Action
            incrementerNbPartie();
            if (gameEnded) {
                return;
            }
            if(!joueur.getCanPLay()){
                System.out.println("Le joueur " + b.getNom() + " est dans la phase de planification!");
            }else {
                handleAction(this.modele.getTrain().getEmplacement(bRoofW, bNumW));
            }
            /*
            if (getNbPartie() == 2) {
                closeGame();
            }
             */
        }
        if(a!=null){
            joueur.ajouterAction(a);
        }
        modele.performUpdate(); // Mettre a jour le modele
    }

    /**
     * Verifie si la commande entrée fait partie de celle des deplacements
     *
     * @param command une chaine de caractères
     * @return un boolean true si la commande fait bien partie,
     *                    false sinon.
     */
    public boolean isMoveCommand(String command) {
        return Arrays.asList("UP", "DOWN", "DROITE", "GAUCHE").contains(command);
    }

    /**
     * Verifie si la commande entrée fait partie de celle des tires
     *
     * @param command une chaine de caractères
     * @return un boolean true si la commande fait bien partie,
     *                    false sinon.
     */
    public boolean isShootCommand(String command) {
        return Arrays.asList("TIRER_DROITE", "TIRER_GAUCHE", "TIRER_DOWN", "TIRER_UP").contains(command);
    }

    /**
     * Donne l'action de déplacement correspondant selon le bouton cliqué.
     *
     * @param b une chaine de caractères
     * @param c une chaine de caractère qui sera converti en une direction
     * @return l'action correspondante.
     */
    private Action moveAction(Bandit b, String c) {
        Direction d = getDirectionFromCommand(c);
        return new Deplacer(b, d);
    }

    /**
     * Donne l'action de Tire correspondant selon le bouton cliqué.
     *
     * @param command une chaine de caractère qui sera converti en une direction
     * @return l'action correspondante.
     */
    private Action handleTirerAction(String command){
        Bandit b = this.modele.getCurrentJoueur().getBandit();
        Tirer a = new Tirer(b, null, null);;
        Direction dirBullet = getDirectionFromCommand(command);
        a.setDirBullet(dirBullet);

        if(dirBullet!=null) {
            int i = b.getPosition().roofToNum();
            int j = b.getPosition().getNumWagon();

            Case caseBandit = this.modele.getTrain().getEmplacement(i, j).getCaseBanditVoisin(b, dirBullet);
            if(caseBandit != null){
               Bandit bCible = this.modele.getBanditSelonCaseRand(caseBandit);
               if(bCible!= null) {
                   a.setbCible(bCible);
                   a.setCible(bCible.getPosition());
               }else{
                   System.out.println("Pas de bandit dans la case cible");
               }
            }else{
                System.out.println("La case est inexistante!");
            }
            if (isShootCommand(command)) {
                System.out.println(b.getNom() + " tire " + AfficheDirection(dirBullet));
            }
        }
        return a;
    }

    /**
     * Fait les modifications nécessaires quand le bouton action est cliqué.
     */
    public void handleAction(Case cible){
        Joueur joueur = this.modele.getCurrentJoueur();
        Marshall marshall = this.modele.getTrain().getMarshall();
        if (joueur.hasMoves()) {
            //Déplacer le marshall avant aucune action
            this.modele.getTrain().getMarshall().deplacerMarshall();

            this.modele.getCurrentJoueur().executerAction();
            System.out.println("Action executé.");

            // Trigger screen update
            SwingUtilities.invokeLater(() -> {
                this.modele.notifyObservers(); // Update the GUI components
            });
            if(joueur.getBandit().estSurMemeCase(marshall)){
                this.modele.getTrain().TirerSurBandit(this.modele.getCurrentJoueur().getBandit(),cible);
            }
            if(this.modele.getCurrentJoueur().getActionList().isEmpty()){
                this.modele.getCurrentJoueur().changeCanPlay();
                System.out.println("Le joueur est maintenant dans la phase de planification!");
            }
            this.modele.NextIndPlayer();
        } else {
            System.out.println("Pas d'action a executé!");
        }
    }

    public int getNbPartie() {
        return nbPartie;
    }

    public void incrementerNbPartie(){
        nbPartie++;
    }

    public void closeGame(){
        gameEnded = true;
        // Disable buttons and input events
        SwingUtilities.invokeLater(() -> {
            modele.notifyObservers(); // Update the GUI components
        });

        for (int i = 0; i < 100; i++) {
            System.out.println("");
        }
        System.out.println("_____________________________");

        List<String> playerInfo = new ArrayList<>();

        for (Joueur j : modele.getJoueurs()) {
            String playerName = j.getBandit().getNom();
            int playerMoney = j.getBandit().getSommeArgent();

            String info = playerName + " : " + playerMoney;
            playerInfo.add(info);
        }

        playerInfo.sort((s1, s2) -> {
            int money1 = Integer.parseInt(s1.split(" : ")[1]);
            int money2 = Integer.parseInt(s2.split(" : ")[1]);
            return Integer.compare(money2, money1);
        });

        System.out.println("Classement des joueurs :");
        for (String info : playerInfo) {
            System.out.println(info);
        }

        //System.exit(0);
    }
}
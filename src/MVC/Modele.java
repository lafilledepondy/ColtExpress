package MVC;

import Train.CaseType.Case;
import Train.Joueur;
import Train.PersonneType.Bandit;
import Train.Train;

import java.util.*;

/**
 * La classe Modele représente les différents objets qui se trouvent dans le train :
 *  - Des wagons composés de cases
 *  - Des personnes spéciales : bandits et Marshall
 *  - Des personnes simples : les passagers et des butins
 * Cette classe étend la classe Observable de la bibliothèque standard de java,
 * qui correspond à un objet observable et fait partie du package MVC.
 */
public class Modele extends Observable {
    private Train train;
    private ArrayList <Joueur> joueurs;
    private int currentIndJoueur;
    private int nbJoueur;

    // CONSTRUCTEUR
    /**
     * Constructeur de la classe Modèle.
     * Initialise un train, une liste de joueurs vide;
     * et un indexe représentant l'index du joueur correspondant.
     */
    public Modele(){
        this.train = new Train();
        this.joueurs = new ArrayList<>();
        this.currentIndJoueur = 0;
        ajouterJoueurs();
    }

    /**
     * Initialise les composants du train.
     * Affiche le train
     * Definis la taille de l'ecran pour la partie du train selon la largeur et la hauteur prédéfini.
     */
    public void performUpdate() {
        // Perform state update in Modele
        setChanged(); // Mark the model as changed
        notifyObservers(); // Notify observers (Vue)
    }

    /**
     * Cherche le trqin du modèle
     *
     * @return un train
     */
    public Train getTrain(){
        return this.train;
    }


    /**
     * Cherche les joueurs de la partie
     *
     * @return une liste de Joueurs
     */
    public ArrayList<Joueur> getJoueurs(){
        return this.joueurs;
    }

    /**
     * Cherche le joueur qui joue pour l'instant donné
     *
     * @return un joueur
     */
    public Joueur getCurrentJoueur(){
        return this.joueurs.get(currentIndJoueur);
    }

    /**
     * Change l'indice du joueur qui joue
     * Si on est au dernier joueur, on rend l'indice 0
     * sinon on incrémente l'indice d'1
     */
    public void NextIndPlayer(){
        if(currentIndJoueur == this.joueurs.size()-1){
            currentIndJoueur = 0;
        } else{
            currentIndJoueur++;
        }
        System.out.println("\n" + "Changement du joueur!");
        System.out.println("C'est le tour de " + joueurs.get(currentIndJoueur).getBandit().getNom() + " : ");
    }

    /**
     * Ajoute des joueurs, leur demande leur nom
     * et leur fait choisir la couleur qu'ils veulent disposer leur bandit avec.
     */
    public void ajouterJoueurs(){
        Scanner scan = new Scanner(System.in);
        //Choisir le nombre de joueurs au clavier
        int nbJoueurs = prendreNbJoueurs(scan);
        this.nbJoueur = nbJoueurs;
        //Définir une couleur différente pour chaque bandit
        setCouleurBandit(scan,nbJoueurs);
    }

    /**
     * Permet de placer la couleur des bandits des joueurs qui jouent la partie
     * Demande un nom Valide que va entrer le joueur au clavier,
     * Ensuite ellen initialise le bandit correspondant au joueur avec la couleur qu'il a choisit
     */
    public void setCouleurBandit(Scanner scan, int nbJoueursEntrer) {
        ArrayList<String> nomsDejaChoisis = new ArrayList<>();
        ArrayList<String> couleursDejaChoisies = new ArrayList<>();

        for (int i = 0; i < nbJoueursEntrer; i++) {
            boolean nomValide = false;
            String nomJoueur = "";
            while (!nomValide) {
                System.out.println("Entrez le nom du joueur " + (i + 1) + " (maximum 10 caractères) : ");
                nomJoueur = scan.next();
                if (nomJoueur.length() <= 10) {
                    if (!nomsDejaChoisis.contains(nomJoueur)) {
                        nomValide = true;
                        nomsDejaChoisis.add(nomJoueur);
                    } else {
                        System.out.println("Ce nom a déjà été choisi. Veuillez en choisir un autre.");
                    }
                } else {
                    System.out.println("Le nom ne peut pas dépasser 10 caractères.");
                }
            }
            Joueur j = new Joueur();
            j.initialiseBandit(nomJoueur, new Random().nextInt(getTrain().getNB_WAGONS()));
            this.joueurs.add(j);

            String couleur = choisirCouleur(couleursDejaChoisies);
            System.out.println(nomJoueur + " a choisi la couleur : " + couleur + "\n");
            j.getBandit().setCouleur(couleur);
        }
    }

    /**
     * Cherche le nombre de joueurs tapé au clavier qui vont jouer la partie.
     *
     * @return un entier indiquant le nombre de joueurs
     */
    public int prendreNbJoueurs(Scanner scan){
        System.out.println("Nombre de joueurs à bord? (Entre 2 et 4 joueurs) ");
        int nbJoueurs = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                //Entrer un chiffre au clavier
                nbJoueurs = scan.nextInt();
                // Vérifier si le nombre de joueurs est entre 2 et 4 inclus
                if (nbJoueurs >= 2 && nbJoueurs <= 4) {
                    isValidInput = true; // Nombre de joueurs valide, sortir de la boucle
                } else {
                    System.out.println("Veuillez entrer un NOMBRE entre 2 et 4 joueurs.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un NOMBRE entier entre 2 et 4 : ");
                // Nettoyer le scanner pour éviter une boucle infinie
                scan.next(); // Consomme l'entrée incorrecte pour éviter la répétition de la boucle
            }
        }
        return nbJoueurs;
    }

    /**
     * Affiche les Butins et passager à bord du train de la partie
     */
   public void AffichageButinsEtPassager() {
       for (int i = 0; i < 2; i++) {
           for (int j = 0; j < getTrain().getNB_WAGONS(); j++) {
               Case pos = this.getTrain().getEmplacement(i,j);
               System.out.println("\n" + "Wagon " + (pos.getNumWagon() + 1)
                       + " - Toit? "+ pos.getRoof());
               System.out.println(" - Nb Passagers: " + pos.getPassagers().size());
               System.out.println(" - Nb Butin: " + pos.getButinsParTerre().size());
           }
       }
       System.out.println("");
   }

    /**
     * Cherche le bandit selon la case entrée
     *
     * @param c une case qui sera évalueé
     * @return un bandit
     */
    public ArrayList<Bandit> getBanditSelonCase(Case c){
        ArrayList<Bandit> banditsSurCase= new ArrayList<>();
        for (Joueur j:joueurs){
            Case bCase = j.getBandit().getPosition();
            if(bCase.getRoof() == c.getRoof() && bCase.getNumWagon() == c.getNumWagon()){
                banditsSurCase.add(j.getBandit());
            }
        }
        return banditsSurCase;
    }
    public Bandit getBanditSelonCaseRand(Case c){
        ArrayList<Bandit> banditSelonCase = getBanditSelonCase(c);
        if(banditSelonCase.size() > 0) {
            Random rnd = new Random();
            int r = rnd.nextInt(banditSelonCase.size());
            return banditSelonCase.get(r);
        }else{
            System.out.println("Pas de bandit voisin!");
            return null;
        }
    }


    /**
     * Fonction qui dit aux objets evalué qu'ils ont été changé
     */
    public void notifyObserversOfChange() {
        setChanged();  // Indicate that the model has changed
        notifyObservers();  // Notify all registered observers
    }

    /**
     * Cherche la couleur tapée au clavier qui est associé à un numéro
     *
     * Si la couleur a été choisi, on demande de tapé un nouveau numéro
     * @return le nom de la couleur
     */
    private String choisirCouleur(ArrayList<String> couleursDejaChoisies) {
        Scanner scan = new Scanner(System.in);
        String couleurChoisie = "";

        System.out.println("Choisissez une couleur parmi les options suivantes : (tapez le numéro correspondant)");
        System.out.println("1. Red");
        System.out.println("2. Black");
        System.out.println("3. Blue");
        System.out.println("4. Brown");

        boolean isValidInput = false;
        while (!isValidInput) {
            if (scan.hasNextInt()) {
                int choix = scan.nextInt();
                switch (choix) {
                    case 1:
                        couleurChoisie = "Red";
                        break;
                    case 2:
                        couleurChoisie = "Black";
                        break;
                    case 3:
                        couleurChoisie = "Blue";
                        break;
                    case 4:
                        couleurChoisie = "Brown";
                        break;
                    default:
                        System.out.println("Veuillez choisir un nombre entre 1 et 4.");
                        continue;
                }

                // Vérifier si la couleur choisie est déjà prise
                if (couleursDejaChoisies.contains(couleurChoisie)) {
                    System.out.println("Cette couleur a déjà été choisie par un autre utilisateur. Veuillez en choisir une autre.");
                } else {
                    isValidInput = true;
                    couleursDejaChoisies.add(couleurChoisie);
                }
            } else {
                System.out.println("Veuillez entrer un nombre valide entre 1 et 4.");
                scan.next();
            }
        }
        return couleurChoisie;
    }

    public Bandit getBanditIndPlayer() {
        return this.getCurrentJoueur().getBandit();
    }

    public int getNbJoueur(){
        return this.nbJoueur;
    }
/*
    public static void main(String[] args) {
        //Suite d'action predeterminée
        Train train = new Train();
        Joueur j = new Joueur();
        Bandit b = j.getBandit();


        System.out.println(b.getPosition());
        Deplacer deplacer0 = new Deplacer(b,BAS); //Descend
        j.ajouterAction(deplacer0);

        Deplacer deplacer1 = new Deplacer(b,AVANT); //NE FAIT RIEN
        j.ajouterAction(deplacer1); //Ne fait rien

        Deplacer deplacer2 = new Deplacer(b,BAS); //NE FAIT RIEN
        j.ajouterAction(deplacer2); //Ne fait rien

        Deplacer deplacer3 = new Deplacer(b,ARRIERE); //recule
        j.ajouterAction(deplacer3); //Recule

        Deplacer deplacer4 = new Deplacer(b,HAUT); //MONTE
        j.ajouterAction(deplacer4); //monte

        // Affichage mis a jour a chaque deplacement du bandit
        //List<Action> actionsCopy = new ArrayList<>(j.getActionList());
        //for (Action action : actionsCopy) {
        //    j.executerAction(action);
        //}
    }
*/
}
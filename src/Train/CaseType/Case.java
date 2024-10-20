package Train.CaseType;

import Train.ButinType.*;
import Train.PersonneType.*;

import java.util.ArrayList;
import java.util.Random;

import Train.Direction;
import static Train.Direction.*;

/**
 * La classe Case représente une case dans un wagon du train, pouvant contenir des passagers et des butins.
 * Cette classe est située dans le package Train.CaseType.
 */
public class Case {
    private boolean roof;
    private int numWagon;
    private ArrayList<Butin> butinsParTerre;
    private ArrayList <Passager> passagers;

    // CONSTRUCTEUR
    /**
     * Constructeur de la classe Case.
     *
     * @param roof     Indique si la case possède un toit.
     * @param numWagon Le numéro du wagon où se trouve la case.
     *
     * Le constructeur verifie que le numero du wagon entré ne dépasse pas le nombre de wagons qui existe dans le train
     */
    public Case(boolean roof, int numWagon) {
        this.butinsParTerre = new ArrayList<>();
        this.passagers = new ArrayList<>();
        if(numWagon < 4 || numWagon > 0){
            this.roof = roof;
            this.numWagon = numWagon;
            this.butinsParTerre = new ArrayList<>();
        }else{
            throw new ArrayIndexOutOfBoundsException("Depacement du train!");
        }
    }

    // METHODES
    /**
     * Getter pour récupérer le numéro du wagon où se trouve la case.
     *
     * @return Le numéro du wagon.
     */
    public int getNumWagon() {
        return numWagon;
    }

    /**
     * Getter pour récupérer la liste des passagers dans le wagon
     *
     * @return une ArrayList de passagers
     */
    public ArrayList<Passager> getPassagers(){
        return passagers;
    }

    /**
     * Getter pour vérifier si la case possède un toit.
     *
     * @return true si la case possède un toit,
     *          sinon false.
     */
    public boolean getRoof() {
        return roof;
    }

    /**
     * Méthode pour récupérer le numéro selon si la case est au toit ou a l'interieur
     *
     * @return 0 si la case est au toit ou
     *         1 si elle est à l'intérieur
     */
    public int roofToNum(){
        if(roof){
            return 0;
        }else{
            return 1;
        }
    }

    /**
     * Méthode pour vérifier si la case contient des passagers.
     *
     * @return true si la case contient des passagers,
     *          sinon false.
     */
    public boolean contientPassager(){
        return !this.passagers.isEmpty();
    }

    /**
     * Méthode pour récupérer un passager aléatoire présent dans la case.
     *
     * @return Un passager aléatoire.
     */
    public Passager getPassagerRandom(){
        if(contientPassager()) {
            Random rnd = new Random();
            int r = rnd.nextInt(this.passagers.size());
            System.out.println("La case a un passager.");
            return this.passagers.get(r);
        }else{
            System.out.println("Pas de passager dans la case!");
            return null;
        }
    }

    public boolean contientButinPassager(){
        if (passagers == null) {
            System.out.println("Pas de passager. :(");
            return false;
        } else {
            for (Passager p : passagers) {
                if (!p.getButins().isEmpty()) {
                    System.out.println("Il y a butin chez au moins un passager. :)");
                    return true;
                }
            }
        }
        System.out.println("Pas de butin chez le passager. :(");
        return false;
    }

    public Butin getButinPassager(){
        if (contientButinPassager()){
            for (Passager p : passagers) {
                if (p.getButins().size() > 0)
                    return p.getButins().get(0);
            }
        }
        System.out.println("Les passagers n'ont pas de butins.");
        return null;
    }

    /**
     * Enlève aléatoirement un butin de la possession d'un passager dans la case.
     *
     * @return Le butin retiré aléatoirement du passager,
     *          ou null s'il n'y a pas de passager
     *          ou si tous ne possèdent pas de butins.
     */
    public Butin getButinFromPassagerRandom(){
        if(!this.contientPassager()) {
            ArrayList<Passager> copyPassagers = this.passagers;

            //Parcourir tout les passager pour verifier qu'il ont des butins
            while (!copyPassagers.isEmpty()) {
                Passager pRand = getPassagerRandom();

                //Verifier que passager contient des butins
                if (pRand.ContientButin()) {
                    Random rnd = new Random();
                    int r = rnd.nextInt(pRand.getNbButins());
                    Butin butin = pRand.getButins().get(r);
                    passagers.get(r).enleverButin(butin);
                    return butin;
                } else {
                    //Enlever de la liste de verification
                    copyPassagers.remove(pRand);
                }
            }
        }else{
            System.out.println("Aucun passager contient des bandits!");
        }
        return null;
    }

    /**
     * Getter pour récupérer la liste des butins présents sur le sol de la case.
     *
     * @return La liste des butins.
     */
    public ArrayList<Butin> getButinsParTerre(){
        return this.butinsParTerre;
    }

    /**
     * Getter pour récupérer un butin de ceux par terre dans la case.
     *
     * @param i L'indice du butin à récupérer.
     * @return un butin
     */
    public Butin getButinParTerre(int i){return this.butinsParTerre.get(i);}

    /**
     * Méthode pour obtenir le nombre de butins présents sur le sol de la case.
     *
     * @return Le nombre de butins.
     */
    public int nbButinsParTerre(){
        return this.butinsParTerre.size();
    }

    /**
     * Méthode pour vérifier si la case contient au moins un butin sur le sol.
     *
     * @return true si la case contient des butins,
     *          sinon false.
     */
    public boolean contientButinParTerre(){
        return !this.butinsParTerre.isEmpty();
    }

    /** Methode qui permet de placer un butin par terre dans une case du train
     * @param b de type Train.Train.Butin qui represente une certaine somme
     */
    /*

    public boolean contientBandit(Bandit b){
        return this.roof == b.getPosition().getRoof() && this.getNumWagon() == b.getPosition().getNumWagon();
    }

    */

    /**
     * Méthode pour placer un butin sur le sol de la case.
     *
     * Si le butin est null :
     *  - afficher une erreur
     * Sinon :
     *  - Ajouter le butin dans la case.
     *
     * @param b Le butin à placer.
     */
    public void placerButin(Butin b){
        if(b != null) {
            this.butinsParTerre.add(b);
            //System.out.println("Butin placer dans la case du wagon n: " + this.getNumWagon());
        }else{
            System.out.println("Pas de butin a placer!");
        }
    }

    /** Methode qui permet d'enlever un butin dans une case du train
     * Iteration sur les butins de la case :
     * - s'il existe un butin de type indiqué par le nom, on le supprime.
     * - Sinon on ne fait
     *
     * @return Le butin enlevé aléatoirement ou null s'il n'existe pas.
     */
    public Butin enleverButinAleat(){
        if(contientButinParTerre()) {
            Random random = new Random();
            int numRand = random.nextInt(nbButinsParTerre());
            Butin butin = this.butinsParTerre.get(numRand);
            this.butinsParTerre.remove(numRand);
            return butin;
        }
        return null;
    }

    /**
     * Méthode pour ajouter un passager à la case.
     */
    public void placerPassager(){
        Passager traveller = new Passager(this);
        this.passagers.add(traveller);
        initialiserButinsPourPassager(traveller);
    }

    /**
     * Méthode pour initialiser la liste des passagers.
     */
    public void initialiserPassagers(){
        Random rand = new Random();
        int nbPassager = rand.nextInt(3); // au moin 0 donc [0-3]
        for (int k = 0; k < nbPassager; k++) {
            placerPassager();

        }

    }

    /**
     * Méthode pour initialiser la liste des butins.
     */
    public void initialiserButins(){
        Random rand = new Random();
        int nbButins = rand.nextInt(4) + 1; // au moin 1 donc [0-3]+1
        for (int k = 0; k < nbButins; k++) {
            if (rand.nextBoolean())
                placerButin(new Bourse());
            else
                placerButin(new Bijoux());
        }
        System.out.println("");
    }

    /**
     * Méthodes pour initialiser le Magot.
     **/
    public void initialiserMagot(){
        if(this.butinsParTerre.size() < 4) {
            this.enleverButinAleat();
        }
        this.butinsParTerre.add(new Magot());
    }

    private void initialiserButinsPourPassager(Passager passager){
        Random rand = new Random();
        int zeroOuUn = rand.nextInt(2) ;
        if (zeroOuUn == 0)
            passager.ajouterButin(new Bourse());
        else
            passager.ajouterButin(new Bijoux());
        }

    /**
     * Vérifie si la case contient un personnage.
     *
     * @param persoPos la case du personnage à vérifier.
     * @return true si un bandit se trouve à la même position,
     *          sinon false.
     */
    public boolean contientPersonnage(Case persoPos){
        return (this.getRoof()== persoPos.getRoof()
                && this.getNumWagon()== persoPos.getNumWagon());
    }

    /**
     * Obtient la case voisine d'un bandit dans une direction spécifiée.
     *
     * @param b Le bandit dont on cherche la case voisine.
     * @param d La direction dans laquelle chercher la case voisine.
     * @return La case voisine dans la direction spécifiée,
     *          ou null si aucune case voisine n'existe.
     */
    public Case getCaseBanditVoisin(Bandit b, Direction d){
        Case bCible = null;
        if(contientPersonnage(b.getPosition())){
            if (d == AVANT && this.getNumWagon() != 0){        bCible = new Case(this.roof,this.getNumWagon()-1);
            }else if(d == ARRIERE && this.getNumWagon()!= 3){  bCible = new Case(this.roof,this.getNumWagon()+1);
            }else if(d == HAUT && !this.roof){                 bCible = new Case(this.roof,this.getNumWagon());
            }else if(d == BAS && this.roof){                   bCible = new Case(!this.roof,this.getNumWagon());
            }
        }
        return bCible;
    }

    /**
     * Méthode pour afficher les coordonnées de la case.
     */
    public String toString(){
        return this.roof + " " + this.numWagon+ " ";
    }
}
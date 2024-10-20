package Train;

import Train.Actions.Tirer;
import Train.CaseType.*;
import Train.PersonneType.*;

/**
 * La classe Train représente le train contenant tous ces composants comme les wagons, les personnes et différents types de butins.
 * Cette classe fait partie du package Train.
 */
public class Train {
    //ATTRIBUTS
    final static int NB_WAGONS = 4;
    private Case[][] emplacements;
    private Marshall marshall;

    //CONSTRUCTEUR
    /**
     * Constructeur de la classe Train qui définit u certain nombre de wagons et initialise les cases du train et les butins à bord.
     */
    public Train() {
        this.emplacements = new Case[2][NB_WAGONS];
        this.marshall = new Marshall(new Case(false,0));
        //System.out.println("Pos du marshall: " + marshall.getPosition().roofToNum() + " " + marshall.getPosition().getNumWagon());

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < NB_WAGONS; j++) {
                if (i == 0) {
                    this.emplacements[i][j] = new Toit(j);
                } else {
                    this.emplacements[i][j] = new Interieur(j);
                    this.emplacements[i][j].initialiserButins();
                    this.emplacements[i][j].initialiserPassagers();
                }
            }
        }
        this.emplacements[1][0].initialiserMagot();
    }

    //METHODES
    /**
     * Getter pour récupérer le nombre de wagons dans le train.
     *
     * @return Le nombre de wagons dans le train.
     */
    public int getNB_WAGONS(){
        return this.NB_WAGONS;
    }

    /**
     * Getter pour récupérer l'emplacement de la case située aux coordonnées spécifiées dans le train.
     *
     * @param i L'indice de la rangée.
     * @param j L'indice de la colonne.
     * @return La case située aux coordonnées (i, j) dans le train.
     */
    public Case getEmplacement(int i, int j){
        return this.emplacements[i][j];
    }

    /**
     * Getter pour récupérer le marshall présent dans le train.
     *
     * @return Le marshall présent dans le train.
     */
    public Marshall getMarshall(){ return this.marshall;}

    /**
     * Méthode pour tirer Sur un bandit se trouvant dans la meme case du marshall le poussant à fuire au toit du wagon.
     * Avec une probabilité de NERVOSITEMARSHALL prédéfinie.
     */
    public void TirerSurBandit(Bandit b,Case cCible){
        int i = marshall.getPosition().roofToNum();
        int j = marshall.getPosition().getNumWagon();
        //System.out.println("Pos du marshal: " + i + " " + j);
        if(b.getPosition().contientPersonnage(this.marshall.getPosition())){
            Tirer t = new Tirer(this.marshall, b, cCible);
            marshall.executerAction(t);
            b.fuireVersLeToit();
        }else{
            System.out.println("Le bandit "+ this.marshall.getNom() + " ne fuit pas ver le haut");
            System.out.println("Pos du bandit: " + b.getPosition().roofToNum() + " " + b.getPosition().getNumWagon());
            System.out.println("Pos du marshall: " + marshall.getPosition().roofToNum() + " " + marshall.getPosition().getNumWagon());

        }
    }

    /**
     * Affiche les emplacements des cases dans le train.
     */
    public void afficherEmplacements() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < NB_WAGONS; j++) {
                Case currentCase = emplacements[i][j];
                System.out.println("Emplacement [" + i + "][" + j + "]: " + currentCase.toString());
            }
        }
    }
}


package Train.PersonneType;

import Train.Actions.Deplacer;
import Train.ButinType.Butin;
import Train.CaseType.Case;
import Train.Direction;

/**
 * La classe Bandit représente un type de personnage que nous pouvons manipulons avec les boutons.
 * Il est capable de se déplacer sur le toit et à l'intérieur des différents wagons,
 *  tirer sur d'autres bandits se trouvant à proximité,
 *  braquer des passagers se trouvant sur la meme case que lui,
 *  ou simplement récuperer des butins se trouvant par terre sur sa case.
 * Cette classe étend la classe Personnage et fait partie du package Train.PersonneType.
 */
public class Bandit extends Personnage {
    //ATRIBUTS
    private int sommeArgent;
    private int bullet;
    private static int nbBullet = 6;
    private String couleur;

    //CONSTRUCTEUR
    /**
     * Constructeur de la classe Bandit.
     *
     * @param nom une chaine de caractere representant le nom.
     * @param position la case sur laquelle se trouve le bandit.
     * Crée une instance de Bandit avec une position et un nom.
     */
    public Bandit(String nom,Case position) {
        super(position, nom);
        this.sommeArgent = 0;
        this.bullet = nbBullet;
    }

    //METHODES
    /**
     * Méthode pour recuperer le nombre de balles que contient le bandit
     *
     * @return un entier superieur a 0.
     */
    public int getBullet(){ return bullet;}

    /**
     * Recupere la somme d'argent que contient le bandit qui represente la somme monetaire totale de ces butins.
     *
     * @return un entier superieur a 0.
     */
    public int getSommeArgent(){ return sommeArgent;}

    /**
     * Méthode qui verifie si le bandit se trouve sur la meme case qu;un autre personnage, soit un bandit ou un marshall.
     *
     * @return un boolean representant la valeur true s'il en a un
     *                                        ou false s'il en a pas
     */
    public boolean estSurMemeCase(Personne p) {
        return this.getPosition().getRoof() == p.getPosition().getRoof() && this.getPosition().getNumWagon() == p.getPosition().getNumWagon();
    }

    /**
     * Méthode qui enleve une balle du bandit s'il en a
     */
    public void enleverBullet(){
        if(this.bullet > 0) {
            this.bullet--;
            System.out.println("Un bullet a été enlever du bandit: " + this.getNom());
            System.out.println("Bullet restant: " + getBullet());
        }else{
            System.out.println("Le bandit n'a plus de bullet");
        }
    }

    /**
     * Méthode qui ajoute une somme d'argent selon la somme du butin en paramètre si le butin n'est pas vide.
     *
     * @param b un butin représentant une somme monétaire à ajouter
     */
    public void ajouterSommeArgent(Butin b) {
        this.sommeArgent += b.getButin();
        System.out.println("Somme d'argent ajouter: "+ b.getButin());
        System.out.println("Somme d'argent totale chez bandit "+ this.getNom() + " : " + getSommeArgent() );
    }

    /**
     * Méthode qui ajoute une somme d'argent selon la somme du butin en paramètre si le butin n'est pas vide.
     *
     * @param b un butin représentant une somme monétaire à ajouter
     */
    public void enleverSommeArgent(Butin b) {
        if(b != null) {
            this.sommeArgent -= b.getButin();
            System.out.println("Somme d'argent perdu: " + b.getButin());
        }else{
            System.out.println("Pas de butin pour enlever la somme!!");
        }
    }

    /**
     * Méthode qui fait fuire le bandit vers le toit du meme wagon.
     *
     */
    public void fuireVersLeToit(){
            Deplacer deplacer = new Deplacer(this, Direction.HAUT);
            deplacer.executer();
            System.out.println("Le bandit "+ this.getNom() + " a fuit vers le toit");
    }

    /**
     * Setter pour définir la couleur du bandit.
     *
     *  @param couleur La couleur à l'attribuer au bandit.
     *  */
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    /**
     * Getter pour récupérer la couleur actuelle du bandit.
     *
     * @return La couleur du bandit.
     */
    public String getCouleur(){
        return couleur;
    }
}

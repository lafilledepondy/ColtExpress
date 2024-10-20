package Train.ButinType;

/**
 * La classe abstraite Butin représente un objet de butin avec un nom et une valeur monétaire.
 * Cette classe est située dans le package Train.ButinType.
 */
public abstract class Butin {
    //ATTRIBUT
    protected int butin;

    //CONSTRUCTEUR
    /**
     * Constructeur de la classe Butin.
     *
     * @param butin La valeur monétaire de l'objet Butin.
     */
    public Butin(int butin) {
        this.butin = butin;
    }

    //METHODES
    /**
     * Getter pour récupérer la valeur monétaire de l'objet Butin.
     *
     * @return La valeur monétaire de l'objet Butin.
     */
    public int getButin() {
        return butin;
    }

    /**
     * Setter pour définir la valeur monétaire de l'objet Butin.
     *
     * @param butin La nouvelle valeur monétaire de l'objet Butin.
     */
    public void setButtin(int butin){
        this.butin = butin;
    }
}
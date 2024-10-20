package Train;

import Train.CaseType.Case;

import java.util.Random;

/** L'énumération Direction représente les différentes directions.
 *  Cette classe fait partie du package Train.
 *  */
public enum Direction {
    AVANT,
    ARRIERE,
    HAUT,
    BAS;

    /**
     * Génère aléatoirement une direction.
     *
     * @return Une direction choisie aléatoirement.
     */
    public static Direction random() {
        Random rnd = new Random();
        int r = rnd.nextInt(4);
        switch (r) {
            case 0:  return Direction.AVANT;
            case 1:  return Direction.ARRIERE;
            case 2:  return Direction.HAUT;
            default: return Direction.BAS;
        }
    }
    /**
     * Obtient la direction selon la commande correspondante
     *
     * @param command un string qui represente une commande de tire ou de deplacement
     * @return la direction correspondante
     */
    public static Direction getDirectionFromCommand(String command) {
        switch (command) {
            case "UP": case "TIRER_UP":
                return HAUT;
            case "DOWN": case"TIRER_DOWN":
                return BAS;
            case "DROITE": case "TIRER_DROITE":
                return ARRIERE;
            case "GAUCHE": case "TIRER_GAUCHE":
                return AVANT;
            default:
                return null;
        }
    }

    public static String getCommandTireFromDirection(Direction d) {
        switch (d) {
            case HAUT:
                return "TIRER_UP";
            case BAS:
                return "TIRER_DOWN";
            case ARRIERE:
                return "TIRER_DROITE" ;
            case AVANT:
                return "TIRER_GAUCHE";
            default:
                return null;
        }
    }
    /**
     * Un texte correspondant a la direction
     *
     * @param d la direction entrée qu'on veut afficher
     * @return la direction correspondante en texte
     */
    public static String AfficheDirection(Direction d) {
        switch (d) {
            case AVANT:
                return "gauche";
            case ARRIERE:
                return "droite";
            case HAUT:
                return "Haut";
            case BAS:
                return "Bas";
            default:
                return "Direction invalide!";
        }
    }

    public static Direction dirSelonCase(Case init, Case cible){
        int jInit = init.getNumWagon();
        int jCible = cible.getNumWagon();

        Direction d = null;
        if(jInit < jCible && (init.getRoof() == cible.getRoof())){
            d = ARRIERE;
        }else if (jInit > jCible && (init.getRoof() == cible.getRoof())){
            d = AVANT;
        }else if(jInit == jCible && init.getRoof() && !cible.getRoof()){
            d = BAS;
        }else if(jInit == jCible && !init.getRoof() && cible.getRoof()){
            d = HAUT;
        }
        return d;
    }
}

package TestTrain;

import Train.CaseType.Case;
import Train.Direction;

public class TestDirection {

    public static void main(String[] args) {
        testRandom();
        testGetDirectionFromCommand();
        testGetCommandTireFromDirection();
        testAfficheDirection();
    }

    public static void testRandom() {
        Direction randomDirection = Direction.random();

        // Vérifie si la direction aléatoire n'est pas null
        assert randomDirection != null;
        // Ajoutez d'autres vérifications si nécessaire
    }

    public static void testGetDirectionFromCommand() {
        Direction direction = Direction.getDirectionFromCommand("UP");

        // Vérifie si la direction correspondante à la commande "UP" est HAUT
        assert direction == Direction.HAUT;
        // Ajoutez d'autres vérifications si nécessaire
    }

    public static void testGetCommandTireFromDirection() {
        String command = Direction.getCommandTireFromDirection(Direction.BAS);

        // Vérifie si la commande correspondant à la direction BAS est "TIRER_DOWN"
        assert command.equals("TIRER_DOWN");
        // Ajoutez d'autres vérifications si nécessaire
    }

    public static void testAfficheDirection() {
        String directionText = Direction.AfficheDirection(Direction.ARRIERE);

        // Vérifie si le texte correspondant à la direction ARRIERE est "droite"
        assert directionText.equals("droite");
        // Ajoutez d'autres vérifications si nécessaire
    }
}

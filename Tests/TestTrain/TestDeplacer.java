package TestTrain;

import Train.Train;
import Train.Joueur;
import Train.PersonneType.*;
import Train.Actions.*;
import Train.Direction;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestDeplacer {
    Train train = new Train();
    Joueur joueur = new Joueur();
    Bandit bandit = joueur.getBandit();

    @Test
    public void testDeplacer() {
        Joueur joueur = new Joueur();
        joueur.initialiseBandit("Bandit", 3);
        Bandit bandit = joueur.getBandit();

        //Vérifier que le bandit est correctement initialisé
        assertNotNull(bandit);

        //Vérifier la position initiale du bandit
        assertEquals(3, bandit.getPosition().getNumWagon());
        assertTrue(bandit.getPosition().getRoof());

        //Vérifier que le bandit est initialement sur le toit du dernier wagon
        assertEquals(3, bandit.getPosition().getNumWagon());
        assertTrue(bandit.getPosition().getRoof());

        //Vérifier que le joueur n'a aucune action initialement
        assertTrue(joueur.getActionList().isEmpty());

        Action deplacement = new Deplacer(bandit, Direction.BAS);
        joueur.ajouterAction(deplacement);
        deplacement.executer();

        //Vérifier que le bandit a changé d'altitude
        assertNotEquals(true, bandit.getPosition().getRoof());
        assertEquals(3, bandit.getPosition().getNumWagon());

        //Vérifier que le bandit est à sa nouvelle position --> à l'intérieur du wagon
        assertEquals(false, bandit.getPosition().getRoof());

        //Ne peux pas dépasser le max_wagon
        Action deplacementArriere = new Deplacer(bandit, Direction.ARRIERE);
        joueur.ajouterAction(deplacementArriere);
        deplacementArriere.executer();
        assertEquals(3, bandit.getPosition().getNumWagon());

        //Ne peux pas dépaser le min_wagon
        Action deplacementAvant = new Deplacer(bandit, Direction.AVANT);
        joueur.ajouterAction(deplacementAvant);
        deplacementAvant.executer();
        assertEquals(2, bandit.getPosition().getNumWagon());

        //Bandit ne peut pas aller plus haut ! s'il est déjà à l'intérieur du wagon
        Action deplacementHaut = new Deplacer(bandit, Direction.HAUT);
        joueur.ajouterAction(deplacementHaut);
        deplacementHaut.executer();
        assertTrue(bandit.getPosition().getRoof());

        Action deplacementBas = new Deplacer(bandit, Direction.BAS);
        joueur.ajouterAction(deplacementBas);
        deplacementBas.executer();
        assertEquals(false, bandit.getPosition().getRoof());
    }
}

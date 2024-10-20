package TestTrain;

import Train.ButinType.Bijoux;
import Train.ButinType.Butin;
import Train.CaseType.Case;
import Train.PersonneType.Passager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestPassager {
    @Test
    public void testCreationPassager() {
        // Création d'un passager
        Passager passager = new Passager(new Case(false, 1));
        // Vérification de l'initialisation de la position du passager
        assertFalse(passager.getPosition().getRoof());
        assertEquals(1, passager.getPosition().getNumWagon());
    }

    @Test
    public void testAjouterButin() {
        // Création d'un passager
        Passager passager = new Passager(new Case(false, 1));
        // Création d'un butin
        Butin butin = new Bijoux();
        // Ajout du butin au passager
        passager.ajouterButin(butin);
        // Vérification que le passager contient le butin
        assertTrue(passager.ContientButin());
    }

    @Test
    public void testEnleverButin() {
        // Création d'un passager
        Passager passager = new Passager(new Case(false, 1));
        // Création d'un butin
        Butin butin = new Bijoux();
        // Ajout du butin au passager
        passager.ajouterButin(butin);
        // Vérification que le passager contient le butin
        assertTrue(passager.ContientButin());
        // Enlèvement du butin
        passager.enleverButin(butin);
        // Vérification que le passager ne contient plus le butin
        assertFalse(passager.ContientButin());
    }
}

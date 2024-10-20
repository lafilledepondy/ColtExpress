package TestTrain;

import Train.ButinType.Bijoux;
import Train.ButinType.Butin;
import Train.CaseType.Case;
import Train.PersonneType.Personne;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestPersonne {
    @Test
    public void testCreationPersonne() {
        Case position = new Case(false, 1);
        Personne personne = new PersonneTest(position);
        assertFalse(personne.getPosition().getRoof());
        assertEquals(1, personne.getPosition().getNumWagon());
        assertNotNull(personne.getButins());
        assertEquals(0, personne.getButins().size());
    }

    @Test
    public void testAjouterButin() {
        Personne personne = new PersonneTest(new Case(false, 1));
        Butin butin = new Bijoux();
        personne.ajouterButin(butin);
        assertTrue(personne.ContientButin());
        assertTrue(personne.getButins().contains(butin));
    }

    @Test
    public void testEnleverButin() {
        Personne personne = new PersonneTest(new Case(false, 1));
        Butin butin = new Bijoux();
        personne.ajouterButin(butin);
        assertTrue(personne.ContientButin());
        personne.enleverButin(butin);
        assertFalse(personne.ContientButin());
        assertFalse(personne.getButins().contains(butin));
    }

    @Test
    public void testEnleverButinAleat() {
        Personne personne = new PersonneTest(new Case(false, 1));
        Butin butin1 = new Bijoux();
        Butin butin2 = new Bijoux();
        Butin butin3 = new Bijoux();
        personne.ajouterButin(butin1);
        personne.ajouterButin(butin2);
        personne.ajouterButin(butin3);
        assertTrue(personne.ContientButin());
    }

    private class PersonneTest extends Personne {
        public PersonneTest(Case pos) {
            super(pos);
        }
    }
}

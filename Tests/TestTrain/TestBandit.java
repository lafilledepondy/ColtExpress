package TestTrain;

import Train.ButinType.Bijoux;
import Train.ButinType.Butin;
import Train.CaseType.Case;
import Train.PersonneType.Bandit;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestBandit {
    @Test
    public void testEnleverBullet() {
        Bandit bandit = new Bandit("Test", new Case(false, 0));
        assertEquals(6, bandit.getBullet());
        bandit.enleverBullet();
        assertEquals(5, bandit.getBullet());
        for (int i = 0; i < 5; i++) {
            bandit.enleverBullet();
        }
        assertEquals(0, bandit.getBullet());
        bandit.enleverBullet();
    }

    @Test
    public void testAjouterSommeArgent() {
        Bandit bandit = new Bandit("Test", new Case(false, 0));
        Butin butin = new Bijoux();
        bandit.ajouterSommeArgent(butin);
        assertEquals(butin.getButin(), bandit.getSommeArgent());
    }

    @Test
    public void testEnleverSommeArgent() {
        Bandit bandit = new Bandit("Test", new Case(false, 0));
        bandit.ajouterSommeArgent(new Bijoux());
        assertTrue(bandit.getSommeArgent() >= 0);
        bandit.enleverSommeArgent(new Bijoux());
        assertEquals(0, bandit.getSommeArgent());
        bandit.enleverSommeArgent(null);
    }

    @Test
    public void testFuirVersLeToit() {
        Bandit bandit = new Bandit("Test", new Case(false, 0));
        assertFalse(bandit.getPosition().getRoof());
        bandit.fuireVersLeToit();
        assertTrue(bandit.getPosition().getRoof());
    }

    @Test
    public void testSetCouleur() {
        Bandit bandit = new Bandit("Test", new Case(false, 0));
        bandit.setCouleur("Rouge");
        assertEquals("Rouge", bandit.getCouleur());
    }

    @Test
    public void testGetCouleur() {
        Bandit bandit = new Bandit("Test", new Case(false, 0));
        bandit.setCouleur("Bleu");
        assertEquals("Bleu", bandit.getCouleur());
    }
}

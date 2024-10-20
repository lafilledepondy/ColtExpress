package TestTrain;

import Train.Actions.Braquer;
import Train.Actions.Tirer;
import Train.ButinType.Bijoux;
import Train.CaseType.Case;
import Train.PersonneType.Bandit;
import Train.Train;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTirer {
    @Test
    public void testExecuter() {
        Train train = new Train();

        Case caseTireur = train.getEmplacement(0, 1);
        Case caseCible = train.getEmplacement(0, 2);
        caseCible.placerButin(new Bijoux()); //Placer un butin dans la case cible

        Bandit tireur  = new Bandit("Tireur", caseTireur);
        Bandit toucher = new Bandit("Toucher", caseTireur);

        Braquer braquer = new Braquer(toucher,caseCible); //Ajout la classe Braquer
        braquer.executer();
        assertTrue(toucher.ContientButin());

        Tirer tirerAction = new Tirer(tireur, toucher, caseCible);
        tirerAction.getDirBullet(); // Direction du bullet est droite

        assertFalse(caseCible.contientButinParTerre());
        assertEquals(6 , tireur.getBullet());

        //Le bandit tireur tire sur le bandit toucher qui fait tomber un butin par terre
        tirerAction.executer();

        assertTrue(caseCible.contientButinParTerre());
        assertEquals(5 , tireur.getBullet());
        assertFalse(toucher.ContientButin());

        assertFalse(tireur.ContientButin());
    }
}

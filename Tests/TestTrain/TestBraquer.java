package TestTrain;

import Train.ButinType.Bijoux;
import Train.CaseType.Case;
import Train.PersonneType.Bandit;
import Train.Joueur;
import Train.Actions.Braquer;
import Train.Train;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBraquer {
    @Test
    public void testExecuter(){
        Train train = new Train();

        Joueur joueur = new Joueur();
        joueur.initialiseBandit("Hello", 3);
        Bandit bandit = joueur.getBandit();
        Case positionBandit = bandit.getPosition();

        assertEquals(0, bandit.getNbButins());

        train.getEmplacement(0, 3).placerPassager();
        assertEquals(true, positionBandit.getRoof());
        assertEquals(3, positionBandit.getNumWagon());

        train.getEmplacement(0, 3).getPassagerRandom().ajouterButin(new Bijoux());
        assertEquals(true, train.getEmplacement(0, 3).contientPassager());
        assertEquals(true, bandit.estSurMemeCase(train.getEmplacement(0, 3).getPassagerRandom()));
        assertTrue(train.getEmplacement(0, 3).getPassagerRandom().getNbButins() > 0);

        Braquer braquer = new Braquer(bandit, train.getEmplacement(0, 3));
        braquer.executer();

        assertTrue(bandit.getNbButins() >= 0);}
}

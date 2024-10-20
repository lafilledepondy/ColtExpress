package TestTrain;

import Train.Actions.*;
import Train.CaseType.Case;
import Train.Direction;
import Train.PersonneType.Marshall;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestMarshall {
    @Test
    public void testDeplacerMarshall() {
        Marshall marshall = new Marshall(new Case(false, 1));
        marshall.deplacerMarshall();
        assertTrue(marshall.getPosition().getNumWagon() == 0 || marshall.getPosition().getNumWagon() == 2 || marshall.getPosition().getNumWagon() == 1);
    }

    @Test
    public void testExecuterAction() {
        Marshall marshall = new Marshall(new Case(false, 1));
        Deplacer deplacer = new Deplacer(marshall, Direction.ARRIERE);
        marshall.executerAction(deplacer);
        assertTrue(marshall.getPosition().getNumWagon() >= 0);
    }
}

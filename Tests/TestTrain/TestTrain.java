package TestTrain;


import Train.Train;
import org.junit.Test;
import Train.PersonneType.*;
import Train.CaseType.*;

import static org.junit.Assert.*;

public class TestTrain {

    @Test
    public void testCreationTrain() {
        Train train = new Train();
        assertNotNull(train);
        assertEquals(4, train.getNB_WAGONS());
        assertNotNull(train.getMarshall());
        assertNotNull(train.getEmplacement(0, 0));
        assertNotNull(train.getEmplacement(1, 0));
    }

    @Test
    public void testAfficherEmplacements() {
        Train train = new Train();
        // Test l'affichage sans erreur
        train.afficherEmplacements();
    }
}

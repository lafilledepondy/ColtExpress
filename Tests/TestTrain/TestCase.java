package TestTrain;

import Train.ButinType.Bijoux;
import Train.ButinType.Bourse;
import Train.ButinType.Magot;
import Train.CaseType.*;
import Train.Train;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCase {
    Train train = new Train();

    @Test
    public void testNbButinsParTerre() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < train.getNB_WAGONS(); j++) {
                int nbButinsParTerre = train.getEmplacement(i, j).nbButinsParTerre();
                assertTrue(nbButinsParTerre >= 0);
            }
        }
    }

    @Test
    public void testPlacerButin(){
        Case caseTest = new Case(true, 1);
        caseTest.placerButin(new Bijoux());
        assertEquals(1, caseTest.nbButinsParTerre());
    }

    @Test
    public void testEnleverButinAleat(){
        Case caseTest = new Case(true, 1);
        Bijoux bijoux = new Bijoux();
        caseTest.placerButin(bijoux);
        assertEquals(bijoux, caseTest.enleverButinAleat());
        assertEquals(0, caseTest.nbButinsParTerre());
    }

    @Test
    public void testContientButinParTerre(){
        Case caseTest1 = new Case(true, 1);
        assertFalse(caseTest1.contientButinParTerre());

        Case caseTest2 = new Case(true, 1);
        caseTest2.placerButin(new Bijoux());
        assertTrue(caseTest2.contientButinParTerre());
    }

    @Test
    public void testInterieur(){
        Interieur interieur = new Interieur(1);
        assertFalse(interieur.getRoof());
        assertEquals(1, interieur.getNumWagon());
    }

    @Test
    public void testLocomotiveInt(){
        LocomotiveInt locomotiveInt = new LocomotiveInt();
        assertFalse(locomotiveInt.getRoof());
        assertEquals(0, locomotiveInt.getNumWagon());
    }

    @Test
    public void testLocomotiveRf(){
        LocomotiveRf locomotiveRf = new LocomotiveRf();
        assertTrue(locomotiveRf.getRoof());
        assertEquals(0, locomotiveRf.getNumWagon());
    }

    @Test
    public void testToit(){
        Toit toit = new Toit(2);
        assertTrue(toit.getRoof());
        assertEquals(2, toit.getNumWagon());
    }

    @Test
    public void testInitialiserPassagers() {
        Case testCase = new Case(false, 1);
        testCase.initialiserPassagers();
        assertFalse(testCase.getPassagers().isEmpty());
    }

    @Test
    public void testInitialiserButins() {
        Case testCase = new Case(false, 1);
        testCase.initialiserButins();
        assertTrue(testCase.contientButinParTerre());
        assertTrue(testCase.nbButinsParTerre() < 5 && testCase.nbButinsParTerre() > 0);
    }

    @Test
    public void testGetButinParTerreWhenMagotIsFirst() {
        Case testCase = new Case(true, 2);
        testCase.placerButin(new Magot());
        testCase.placerButin(new Bourse());
        assertEquals(Magot.class, testCase.getButinParTerre(0).getClass());
    }

    @Test
    public void testGetNumWagon() {
        Case testCase = new Case(true, 2);
        assertEquals(2, testCase.getNumWagon());
    }

    @Test
    public void testGetPassagers() {
        Case testCase = new Case(true, 2);
        assertNotNull(testCase.getPassagers());
    }
}

package TestTrain;

import Train.ButinType.Butin;
import Train.ButinType.Bijoux;
import Train.ButinType.Bourse;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestButin {
    @Test
    public void testGetButin_Bijoux() {
        Butin bijoux = new Bijoux();
        assertEquals(500, bijoux.getButin());
    }

    @Test
    public void testSetButin_Bijoux() {
        Butin bijoux = new Bijoux();
        bijoux.setButtin(700);
        assertEquals(700, bijoux.getButin());
    }

    @Test
    public void testGetButin_Bourse() {
        Bourse bourse = new Bourse();
        assertTrue(bourse.getButin() >= 0 && bourse.getButin() <= 500);
    }

    @Test
    public void testSetButin_Bourse() {
        Bourse bourse = new Bourse();
        bourse.setButtin(300);
        assertEquals(300, bourse.getButin());
    }
}

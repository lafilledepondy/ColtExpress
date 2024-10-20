package TestTrain;

import Train.Direction;
import Train.Actions.Action;
import Train.Actions.Deplacer;
import Train.PersonneType.Bandit;
import Train.Joueur;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestJoueur {
    Joueur j = new Joueur();

    //Test la methode getBandit()
    Bandit b = j.getBandit();
    @Test
    public void TestAjouterAction(){
        Action d= new Deplacer(b, Direction.BAS);
        j.ajouterAction(d);
    }
    @Test
    public void TestGetActionListEtHasMovesEtAjouterAction(){
        //Verifier que le joueur n'a pas mis aucune action
        assertEquals(false,j.hasMoves());

        //Le joueur souhaite realiser une action pour le bandit
        Action d= new Deplacer(b, Direction.BAS);
        j.ajouterAction(d);

        //On verifie que l'action a été ajouter a la liste d'action
        assertEquals(true,j.hasMoves());
    }
}

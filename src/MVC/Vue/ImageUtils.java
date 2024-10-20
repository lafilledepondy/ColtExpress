package MVC.Vue;

import Train.Actions.*;
import Train.Actions.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class ImageUtils {
    public static Image resizeImage(Image originalImage, int newWidth, int newHeight) {
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = resizedImage.createGraphics();

        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);

        g2d.dispose();
        return resizedImage;
    }
    public static ImageIcon actionIconCorr(Action a){
        if(a==null) System.out.println("Action vide!");

        ImageIcon imgIcon = null;
        if(a instanceof Deplacer) {
            imgIcon = deplacerIconCorr(((Deplacer)a));
        }else if (a instanceof Tirer) {
            imgIcon = tirerIconCorr((Tirer)a);
        }else if (a instanceof Braquer){
            imgIcon = new ImageIcon("src/images/braquer.png");
        }else if (a instanceof Rob) {
            imgIcon = new ImageIcon("src/images/rob.png");
        }
        return imgIcon;
    }


    public static ImageIcon deplacerIconCorr(Deplacer d){
        return switch (d.getD()) {
            case HAUT -> new ImageIcon("src/images/deplacerHaut.png");
            case BAS -> new ImageIcon("src/images/deplacerBas.png");
            case ARRIERE -> new ImageIcon("src/images/deplacerDroite.png");
            case AVANT -> new ImageIcon("src/images/deplacerGauche.png");
            default -> null;
        };
    }

    public static ImageIcon tirerIconCorr(Tirer t){
        switch(t.getDirBullet()){
            case HAUT :
                return  new ImageIcon("src/images/bullet_up.png");
            case BAS :
                return  new ImageIcon("src/images/bullet_down.png");
            case ARRIERE:
                return  new ImageIcon("src/images/bullet_droite.png");
            case AVANT:
                return new ImageIcon("src/images/bullet_gauche.png");
            default:
                return null;
        }
    }
}

package MVC.Vue;

import MVC.Modele;
import Train.Actions.Action;
import Train.ButinType.*;
import Train.Joueur;
import Train.PersonneType.Bandit;
import Train.PersonneType.Passager;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static MVC.Vue.ImageUtils.actionIconCorr;
import static MVC.Vue.ImageUtils.resizeImage;

/**
 * La classe AffichageTrain va s'occuper à afficher les composantes du train aux emplacements correct dans l'écran.
 * Cette classe étend la classe JPanel de la bibliothèque standard de java qui s'occupe des sous écrans.
 * et fait partie du package MVC.
 */
public class AffichageTrain extends JPanel {
    //ATTRIBUTS
    private Modele modele;
    private Label labelJoueur;
    private Label labelBullet;

    //CONSTRUCTEUR
    /**
     * Constructeur de la classe Affichage.
     * @param m un modèle qui comporte les composants à afficher.
     * Affiche les composants du train.
     */
    public AffichageTrain(Modele m){
        this.modele = m;
    }

    /**
     *  Dessine-les differents objets du train sur le sous écran dediée au train.
     *
     * @param g est une classe abstraite de Java qui permet de dessiner des objets
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelHeight = getHeight();
        //System.out.println("Panel height: " + panelHeight);

        int tailleBetPIcon = 25;
        int taillePersonne = 35;
        int tailleWIconX = 220;
        int tailleWIconY = 130;
        int gapBetP = 40;
        int gapWX = 10; int gapWY = 15;
        int wagonY = 0;

        for (int i = 0; i < 2; i++) { // Toit & Intérieure
            int butinY = panelHeight - tailleWIconY - gapWY;
            //int butinY = (panelHeight / 2) + (tailleWIconY * i) - taillePersonne - (taillePersonne * i) - 7;

            for (int j = 0; j < this.modele.getTrain().getNB_WAGONS(); j++) { // Wagon
                //Tableau des butins de la case
                ArrayList<Butin> butinsParCase = this.modele.getTrain().getEmplacement(i, j).getButinsParTerre();
                ArrayList<Passager> passagerParCase = this.modele.getTrain().getEmplacement(i, j).getPassagers();
                int wagonX = gapWX + j * tailleWIconX;

                if (i==0){
                    wagonY = panelHeight / 2 - tailleWIconY;
                    drawWagonToit(g, wagonX, wagonY, tailleWIconX, tailleWIconY);
                }else {
                    wagonY = panelHeight / 2;
                    drawWagonInt(g, wagonX, wagonY, tailleWIconX, tailleWIconY+50);

                    // Dessiner les points entre les wagons
                    if (i == 1 && j < this.modele.getTrain().getNB_WAGONS() - 1) {
                        int pointX = wagonX + tailleWIconX - 8;
                        g.fillOval(pointX, butinY, 5, 5);
                        g.fillOval(pointX + 5, butinY, 5, 5);
                        g.fillOval(pointX + 10, butinY, 5, 5);
                    }
                }
                //System.out.println("\n");
                //System.out.println("ICONS--Wagon " + (j + 1) + ":");

                for (int k = 0; k < butinsParCase.size(); k++) { // Nb de Butins
                    // Le butin correspondant selon l'indice
                    Butin butin = butinsParCase.get(k);
                    int butinX = gapBetP + (k * (tailleBetPIcon + 3) + j * tailleWIconX);

                    // Dessin des butins dans les cases
                    drawButinIcon(g,butin,butinX,butinY,tailleBetPIcon);
                }
                for(int p=0;p< passagerParCase.size();p++){
                    // Le passager correspondant selon l'indice
                    //int passagerX = gapBetP + (p * (tailleBetPIcon + 3) + j * tailleWIconX);
                    int passagerX = ((j+1) * tailleWIconX) - p * (tailleBetPIcon + 3) - (gapWX * 4);
                    drawPassagerIcon(g,passagerX, wagonY+5,tailleBetPIcon);
                }
            }
        }
        //Dessiner les bandits à la fin, car pas besoin de parcourir la boucle
        drawMarshall(g,taillePersonne,tailleWIconX,tailleWIconY);
        drawBandit(g,taillePersonne,tailleWIconX);

        drawCurrListeActionJoueur(g,taillePersonne);
        drawStatusBandit(g,taillePersonne);

        addStatusJoueur();
        //addStatusBullet();
    }

    public void addStatusJoueur(){
        String stringBullet = String.valueOf(this.modele.getCurrentJoueur().getBandit().getBullet());
        String stringSomme = String.valueOf(this.modele.getCurrentJoueur().getBandit().getSommeArgent());
        String nameJ = this.modele.getCurrentJoueur().getBandit().getNom();
        String label = nameJ + " ⁂ Bullet = " + stringBullet + " ⁂ Somme = " + stringSomme;

        if (labelJoueur == null) {
            labelJoueur = new Label(label);
            labelJoueur.setBounds(50-nameJ.length(), 45, 400, 50);
            this.add(labelJoueur);
        } else {
            labelJoueur.setText(label);
        }
    }

    /**
     * Dessine les Icons des different butins selon leur type
     */
    private void drawButinIcon(Graphics g,Butin butin, int x, int y, int size){
        // Dessin des butins dans les cases
        if (butin instanceof Bourse) {
            //Dessin de bourse
            drawBourseIcon(g, x, y, size);
        } else if (butin instanceof Bijoux) {
            //Dessin de Bijoux
            drawBijouxIcon(g, x, y, size);
        }else if (butin instanceof Magot){
            //Dessin de Magot
            drawMagotIcon(g, x, y,size);
        }
    }
    public void drawStatusBandit(Graphics g, int tailleIcon){
        ImageIcon banditIcon= new ImageIcon("src/images/bandit" + this.modele.getBanditIndPlayer().getCouleur() + ".png");
        Image banditImage = banditIcon.getImage();

        int statusX = tailleIcon*4;
        int statusY = 7;

        g.drawImage(banditImage,statusX,statusY,tailleIcon+7,tailleIcon+7,null);
    }

    public void drawCurrListeActionJoueur(Graphics g, int tailleAction){
        int ind = 1;
        int panelWidth = getWidth();
        int rectX = (panelWidth/2)+ tailleAction*2 - 3;
        int rectY = tailleAction - 3;

        g.drawRect(rectX, rectY, (tailleAction*4) + 3 * 4 + 9,tailleAction + 6);
        for(Action a : this.modele.getCurrentJoueur().getActionList()){
            ImageIcon actionIcon = actionIconCorr(a);
            Image actionIconImg= actionIcon.getImage();
            if(actionIconImg == null) System.out.print("Pas d'image!");
            int ActionX = (panelWidth/2) + tailleAction + ((3 + tailleAction) * ind);

            g.drawImage(actionIconImg, ActionX, tailleAction, tailleAction, tailleAction, null);
            ind++;
        }
    }
    /**
     * Dessine l'Icon d'une bourse en chargeant une image de bourse
     * prise par le site https://game-icons.net/
     */
    private void drawBourseIcon(Graphics g, int x, int y, int size) {
        ImageIcon bourseIcon = new ImageIcon("src/images/bourse.png");
        Image bourseImage = bourseIcon.getImage();

        g.drawImage(bourseImage, x, y, size, size, null);
    }

    /**
     * Dessine l'Icon d'un bijoux en chargeant une image de bijoux
     * prise par le site https://game-icons.net/
     */
    private void drawBijouxIcon(Graphics g, int x, int y, int size) {
        ImageIcon bijouxIcon = new ImageIcon("src/images/bijoux.png");
        Image bijouxImage = bijouxIcon.getImage();

        g.drawImage(bijouxImage, x, y, size, size, null);
    }

    /**
     * Dessine l'Icon d'un Magot en chargeant une image de magot
     * prise par le site https://game-icons.net/
     */
    private void drawMagotIcon(Graphics g, int x, int y, int size){
        ImageIcon magotIcon = new ImageIcon("src/images/magot.png");
        Image magotImage = magotIcon.getImage();

        g.drawImage(magotImage, x, y, size, size, null);
    }

    /**
     * Dessine l'Icon de tout les bandits en chargeant une image de bandit
     * prise par le site https://game-icons.net/
     */
    private void drawBandit(Graphics g, int size, int wagonX){
        for (Joueur joueur : this.modele.getJoueurs()){
            int j = joueur.getBandit().getPosition().getNumWagon();
            int banditX = 100;
            if( j!=0 ){banditX = banditX + j  * wagonX;}
            int i = joueur.getBandit().getPosition().roofToNum();
            int banditY = 180 + 125 * (i);

            ArrayList<Bandit> bandits = this.modele.getBanditSelonCase(joueur.getBandit().getPosition());
            if(bandits.size() > 1){
                for (int k = 0; k < bandits.size(); k++){
                    //Chargement de l'image
                    ImageIcon banditIcon= new ImageIcon("src/images/bandit" + bandits.get(k).getCouleur() + ".png");
                    Image banditImage = banditIcon.getImage();

                    banditX+= k * 40;
                    g.drawImage(banditImage, banditX, banditY, size, size, null);
                }
            }else {
                //Chargement de l'image
                ImageIcon banditIcon= new ImageIcon("src/images/bandit" + joueur.getBandit().getCouleur() + ".png");
                Image banditImage = banditIcon.getImage();

                //Dessiner l'image
                g.drawImage(banditImage, banditX, banditY, size, size, null);
            }
        }
    }

    /**
     * Dessine l'Icon d'un Marshall en chargeant l'image d'un marshall
     * prise par le site https://game-icons.net/
     */
    private void drawMarshall(Graphics g, int size,int wagonX,int wagonY){
        ImageIcon marshallIcon = new ImageIcon("src/images/marshall.png");
        Image marshallImage = marshallIcon.getImage();

        int j = this.modele.getTrain().getMarshall().getPosition().getNumWagon();
        int marshallX = 40;
        if( j!=0 ){marshallX = marshallX + j  * wagonX;}

        g.drawImage(marshallImage,marshallX,170 + wagonY,size,size,null);

    }

    /**
     * Dessine l'Icon de tout les passagers en chargeant une image de passager
     * prise par le site https://game-icons.net/
     */
    private void drawPassagerIcon(Graphics g,int x,int y,int size){
        ImageIcon passagerIcon = new ImageIcon("src/images/passager.png");
        Image passagerImage = passagerIcon.getImage();

        g.drawImage(passagerImage, x, y, size, size, null);

    }

    /**
     * Dessine tous les toits des wagons du train
     * prise par le site https://game-icons.net/
     */
    private void drawWagonToit(Graphics g,int x, int y,int width,int height){
        //Chargement de l'image
        ImageIcon wagonToitIcon = new ImageIcon("src/images/Rectangle.png");
        Image wagonToitImage = wagonToitIcon.getImage();

        //Redimensionement des images
        Image resizedImgToit = resizeImage(wagonToitImage,width,height);

        //Dessiner l'image
        g.drawImage(resizedImgToit,x,y,width,height,null);
    }

    /**
     * Dessine toutes les cases intérieures des wagons du train
     * prise par le site https://game-icons.net/
     */
    private void drawWagonInt(Graphics g,int x, int y,int width,int height){
        //Chargement de l'image
        ImageIcon wagonIntIcon = new ImageIcon("src/images/RectangleAvecRoux.png");
        Image wagonIntImage = wagonIntIcon.getImage();

        //Redimensionement des images
        Image resizedImgInt = resizeImage(wagonIntImage,width,height);

        //Dessiner l'image
        g.drawImage(resizedImgInt,x,y,width,height,null);
    }
}
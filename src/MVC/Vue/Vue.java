package MVC.Vue;

import MVC.Controleur;
import MVC.Modele;
import Train.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * La classe Vue représente l'écran affiché contenant tous les elements du train et les boutons que le joueur peut cliquer dessus
 * Cette classe étend la classe JFrame de la bibliothèque standard de java qui s'occupe des different types d'affichage
 * et fait partie du package MVC.
 */
public class Vue extends JFrame implements Observer {
    public static int width = 900;
    public static int heigth = 500;
    private Controleur controleur;
    private VueCommandes buttons;

    // CONSTRUCTEUR
    /**
     * Constructeur de la classe Vue.
     * @param m un modèle qui comporte les composants à afficher.
     * Crée un écran interactif contenant d'une part les composants du train et d'autre part des boutons intéractifs,
     * donnant une visualisation du jeu Colt-Express.
     */
    public Vue(Modele m) {
        setTitle("Colt-Express :0");
        this.setLayout(new BorderLayout());
        this.controleur = new Controleur(m);
        this.controleur.getModele().addObserver(this); // Register this view as an observer of the model

        //Afficher les composants du train
        initComponents();

        buttons = new VueCommandes(this.controleur, width, 150);
        add(buttons, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);

        // Centrer la fenêtre principale
        SwingUtilities.invokeLater(() -> {
            setLocationRelativeTo(null);
        });
    }

    public static void main(String[] args) {
        Vue vue = new Vue(new Modele());
    }

    /**
     * Initialise les composants du train.
     * Affiche le train
     * Definis la taille de l'ecran pour la partie du train selon la largeur et la hauteur prédéfini.
     */
    public void initComponents(){
        //Affichage des Butins
        this.controleur.getModele().AffichageButinsEtPassager();
        //Affichage de Train
        AffichageTrain trainAff = new AffichageTrain(this.controleur.getModele());
        //Limiter la taille de l'affichage
        trainAff.setPreferredSize(new Dimension(width, heigth));
        //Placer cet affichage au milieu de ma frame
        add(trainAff, BorderLayout.CENTER);
    }

    /**
     *  Modifie l'écran du jeu après l'interaction avec les boutons
     *
     * @param o un objet observable par le joueur, o représente notre modèle qui
     * est les differentes composantes du train.
     * @param arg un object pas utilisé
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Modele) {
            repaint();
        }
    }

    /**
     * La classe VueCommandes représente la partie de l'écran qui affiche les boutons cliquables.
     * Cette classe étend la classe JPanel de la bibliothèque standard de java qui s'occupe des sous partie de JFrame
     * et fait partie de la classe Vue.
     */
    class VueCommandes extends JPanel {
        private Controleur c;
        public static int buttonSize = 75;
        public static int spacing = 5;
        private JButton up, down, droite, gauche,
                tirerUp, tirerDown, tirerDroite, tirerGauche,
                rob, braquer, action, ready, retour;

        //CONSTRUCTEUR
        /**
         * Constructeur de la classe VueCommandes.
         * @param c le controleur qui s'occupe de modifier les caracteristiques des composantes du train.
         * @param width un entier representant la largeurdu petit écran correspondant a celui des boutons en bas.
         * @param height un entier representant la longeur du petit écran.
         * Crée un ecran interactif avec des boutons qui affiche le jeu Colt-Express.
         */
        public VueCommandes(Controleur c, int width, int height) {
            //Set an absolute Layout to manually place all buttons
            this.setLayout(null);
            //Initializes the size of the container
            this.setPreferredSize(new Dimension(width, height));
            this.c = c;
            //movementGroup = new ButtonGroup(); //actionGroup = new ButtonGroup();            //addAndGroupButtons(movementGroup, up, down, droite, gauche); //addAndGroupButtons(actionGroup, tirerUp, tirerDown, tirerDroite, tirerGauche, braquer, action, retour);
            creerBoutons();
            ajouterBoutons();
            ajouterActionListener();
            definirTailleBoutons();
            placerCommandPourBoutons();
            ajouterDeco();
        }

        /**
         *  Ajoute une deco d'image permettant une meilleure comprehension des fonctionnalités des boutons.
         */
        public void ajouterDeco(){
            Image tirerImage = new ImageIcon("src/images/tirer.png").getImage();
            Image resizedImage = ImageUtils.resizeImage(tirerImage, 47, 47);
            JLabel tirerLabel = new JLabel(new ImageIcon(resizedImage));
            tirerLabel.setBounds(205, 40, 47, 47);
            this.add(tirerLabel);
        }

        /**
         * Definit la taille des differents boutons et leur position dans le petit ecran.
         */
        public void definirTailleBoutons() {
            // Set preferred size for buttons
            int DirSize = buttonSize - 20;
            this.up.setBounds(buttonSize * 7 - spacing * 20, 0, DirSize, buttonSize / 2);
            this.down.setBounds(buttonSize * 7 - spacing * 20, buttonSize + 2 * spacing, DirSize, buttonSize / 2);
            this.droite.setBounds(buttonSize * 8 - spacing * 27, buttonSize / 2 + spacing, DirSize, buttonSize / 2);
            this.gauche.setBounds(buttonSize * 6 - spacing * 13, buttonSize / 2 + spacing, DirSize, buttonSize / 2);

            this.tirerUp.setBounds(buttonSize * 3 - spacing * 5, 0, buttonSize - 20, buttonSize / 2);
            this.tirerDown.setBounds(buttonSize * 3 - spacing * 5, buttonSize + 3 * spacing, buttonSize - 20, buttonSize / 2);
            this.tirerDroite.setBounds(buttonSize * 4 - spacing * 8, buttonSize / 2 + spacing, buttonSize - 20, buttonSize / 2);
            this.tirerGauche.setBounds(buttonSize * 2 - 10, buttonSize / 2 + spacing, buttonSize - 20, buttonSize / 2);

            this.rob.setBounds(buttonSize * 9 - spacing * 6, 0, buttonSize-15, buttonSize - 25);
            this.braquer.setBounds(buttonSize * 9 - spacing * 20, 0, buttonSize - 15, buttonSize - 25);            this.action.setBounds(buttonSize * 10 + spacing + 5 / 2, buttonSize / 2, buttonSize + 3 * spacing, buttonSize / 2);
            this.retour.setBounds(buttonSize / 2, 0, DirSize, DirSize / 2);
            this.ready.setBounds(buttonSize * 9 - spacing * 18, buttonSize + 2 * spacing, buttonSize + 4 * spacing, buttonSize / 2);
        }

        /**
         * Recupere les images mis sur les boutons et initialise les boutons.
         */
        public void creerBoutons(){
            // Charger les images
            ImageIcon tirerUpIcon = new ImageIcon("src/images/bullet_up.png");
            ImageIcon tirerDownIcon = new ImageIcon("src/images/bullet_down.png");
            ImageIcon tirerDroiteIcon = new ImageIcon("src/images/bullet_droite.png");
            ImageIcon tirerGaucheIcon = new ImageIcon("src/images/bullet_gauche.png");
            ImageIcon braquerIcon = new ImageIcon("src/images/braquer.png");
            ImageIcon robIcon = new ImageIcon("src/images/rob.png");
            Image resizedImage = ImageUtils.resizeImage(robIcon.getImage(), 25, 25);

            // Create buttons
            this.up = new TriangleButton(Direction.HAUT); //JButton("↑")
            this.down = new TriangleButton(Direction.BAS); //JButton("↓")
            this.droite = new TriangleButton(Direction.AVANT); //JButton("→")
            this.gauche = new TriangleButton(Direction.ARRIERE); //JButton("←")

            this.tirerUp = new JButton(tirerUpIcon); //⍏, ⇞
            this.tirerDown = new JButton(tirerDownIcon); //⍖, ⇟
            this.tirerDroite = new JButton(tirerDroiteIcon); //⍆, ⇻
            this.tirerGauche = new JButton(tirerGaucheIcon); //⍅, ⇺

            this.rob = new JButton(robIcon);
            this.braquer = new JButton(braquerIcon);
            this.action = new JButton("ACTION!");
            this.action.setForeground(new Color(0, 128, 0));
            Font boldFont = new Font(this.action.getFont().getName(), Font.BOLD, this.action.getFont().getSize());
            this.action.setFont(boldFont);

            this.retour = new JButton("⟲");
            this.ready = new JButton("READY!");
        }

        /**
         * Attribuer des commandes donnant une signification aux boutons
         */
        public void placerCommandPourBoutons(){
            // Set action commands for buttons
            this.up.setActionCommand("UP");
            this.down.setActionCommand("DOWN");
            this.droite.setActionCommand("DROITE");
            this.gauche.setActionCommand("GAUCHE");

            this.tirerUp.setActionCommand("TIRER_UP");
            this.tirerDown.setActionCommand("TIRER_DOWN");
            this.tirerDroite.setActionCommand("TIRER_DROITE");
            this.tirerGauche.setActionCommand("TIRER_GAUCHE");

            this.rob.setActionCommand("ROB");
            this.braquer.setActionCommand("BRAQUER");
            this.action.setActionCommand("ACTION");
            this.retour.setActionCommand("RETOUR");
            this.ready.setActionCommand("READY");
        }
        /**
         * Ajouter les boutons au petit écran des boutons.
         */
        public void ajouterBoutons(){
            // Add buttons to the panel
            this.add(this.up);
            this.add(this.down);
            this.add(this.droite);
            this.add(this.gauche);
            this.add(this.tirerUp);
            this.add(this.tirerDown);
            this.add(this.tirerDroite);
            this.add(this.tirerGauche);
            this.add(this.braquer);
            this.add(this.action);
            this.add(this.retour);
            this.add(this.ready);
            this.add(this.rob);
        }

        /**
         * Mettre à l'écout ces boutons pour qu'il déclenche des actions dans le jeu.
         */
        public void ajouterActionListener(){
            // add to ActionListener
            this.up.addActionListener(this.c);
            this.down.addActionListener(this.c);
            this.droite.addActionListener(this.c);
            this.gauche.addActionListener(this.c);
            this.tirerUp.addActionListener(this.c);
            this.tirerDown.addActionListener(this.c);
            this.tirerDroite.addActionListener(this.c);
            this.tirerGauche.addActionListener(this.c);
            this.braquer.addActionListener(this.c);
            this.ready.addActionListener(this.c);
            this.action.addActionListener(this.c);
            this.retour.addActionListener(this.c);
            this.rob.addActionListener(this.c);
        }
    }

    /**
     * La classe TriangleButton représente les boutons qui sont de forme triangulaire ensemble.
     * Ces boutons sont ceux du déplacement et du tirage.
     * Cette classe étend la classe JButton de la bibliothèque standard de java qui s'occupe des different types de boutons et leur fonctionnement
     * et fait partie de la classe Vue.
     */
    class TriangleButton extends JButton {
        private Direction direction;

        // CONSTRUCTEUR
        /**
         * Constructeur de la classe Vue.
         *
         * @param direction la direction correspondante a ces boutons.
         */
        public TriangleButton(Direction direction) {
            this.direction = direction;
            setBorderPainted(true);
            setFocusPainted(false);
            setContentAreaFilled(true);
        }

        /**
         *  Dessine les boutons triangulaires.
         *
         * @param g est une classe abstraite de Java qui permet de dessiner des objets
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int[] xPoints = new int[3];
            int[] yPoints = new int[3];

            int coef = 3;

            switch (direction) {
                case HAUT:
                    xPoints[0] = getWidth() / 2;
                    yPoints[0] = getHeight() / coef;
                    xPoints[1] = getWidth() - (getWidth() / coef);
                    yPoints[1] = getHeight() - (getHeight() / coef);
                    xPoints[2] = (getWidth() / coef);
                    yPoints[2] = getHeight() - (getHeight() / coef);
                    break;
                case BAS:
                    xPoints[0] = (getWidth() / coef);
                    yPoints[0] = (getHeight() / coef);
                    xPoints[1] = getWidth() - (getWidth() / coef);
                    yPoints[1] = (getHeight() / coef);
                    xPoints[2] = getWidth() / 2;
                    yPoints[2] = getHeight() - (getHeight() / coef);
                    break;
                case ARRIERE:
                    xPoints[0] = getWidth() - (getWidth() / coef);
                    yPoints[0] = getHeight() / coef;
                    xPoints[1] = getWidth() - (getWidth() / coef);
                    yPoints[1] = getHeight() - (getHeight() / coef);
                    xPoints[2] = (getWidth() / coef);
                    yPoints[2] = getHeight() / 2;
                    break;
                case AVANT:
                    xPoints[0] = (getWidth() / coef);
                    yPoints[0] = (getHeight() / coef);
                    xPoints[1] = getWidth() - (getWidth() / coef);
                    yPoints[1] = getHeight() / 2;
                    xPoints[2] = (getWidth() / coef);
                    yPoints[2] = getHeight() - (getHeight() / coef);
                    break;
            }

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.BLACK);
            g2d.fillPolygon(xPoints, yPoints, 3);
            g2d.dispose();
        }
    }
}

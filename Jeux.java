import java.util.* ;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Jeux {
    // création et initialisation du tableau 2048 et des touches (tapés au clavier)
    private static int[][] tableau = {{ 0, 2, 0, 0, 16 }, { 0, 0, 0, 0, 4 }, { 0, 2, 0, 0, 0 }, { 0, 2, 8, 0, 0 }, { 0, 2, 0, 0, 0 }};
    private static String key = "";

    /**
    * Affiche les cases du 2048
    */
    public static void draw() {
        // creation des polices d'écritures
        Font font = new Font("SANS", Font.BOLD, 50);
        Font font1 = new Font("SANS", Font.BOLD, 30);
        // mise en place de la police font
        StdDraw.setFont(font);
        for (int i=0; i< tableau.length; i++) {
            for (int j=0; j< tableau[0].length; j++) {
                // met une unique couleur par nombre
                setColor(tableau[j][i]);
                // regle l'épaisseur des traits
                StdDraw.setPenRadius(0.03);
                // affichage de chaques cases
                StdDraw.point(0.025+i*0.2, 0.975-j*0.2);
                StdDraw.point(0.175+i*0.2, 0.975-j*0.2);
                StdDraw.point(0.025+i*0.2, 0.825-j*0.2);
                StdDraw.point(0.175+i*0.2, 0.825-j*0.2);
                StdDraw.filledRectangle(0.1+i*0.2, 0.9-j*0.2, 0.09, 0.075);
                StdDraw.filledRectangle(0.1+i*0.2, 0.9-j*0.2, 0.075, 0.09);
                // ecriture des nombres dans les cases
                StdDraw.setPenColor(StdDraw.BLACK);
                if (tableau[j][i] > 0) {
                    if (tableau[j][i] < 99)
                        StdDraw.setFont(font);
                    else
                        StdDraw.setFont(font1);
                    StdDraw.text(0.1+i*0.2, 0.89-j*0.2, Integer.toString(tableau[j][i]));
                }
            }
        }
        // affiche les modifications sur l'écran
        StdDraw.show();
    }

    /**
    * Change le tableau 2048 en fonction du mouvement fait par le joueur
    * et rajoute une case aléatoirement avec comme valeur 2
    * 
    * @param key touche préssé par l'utilisateur
    */
    public static void changeTable (String key) {
        if (key == "left") {
            for (int k=0; k<5; k++){
                for (int i=0; i<tableau.length; i++) {
                    for (int j=0; j<tableau.length-1; j++) {
                        if (tableau[i][j] == 0) {
                            tableau[i][j] = tableau[i][j+1];
                            tableau[i][j+1] = 0;
                        } else if (tableau[i][j] == tableau[i][j+1]){
                            tableau[i][j] = 2* tableau[i][j];
                            tableau[i][j+1] = 0;
                        }
                    }
                }
            }
        }else if (key == "right") {
            for (int k=0; k<5; k++){
                for (int i=0; i<tableau.length; i++) {
                    for (int j=0; j<tableau.length-1; j++) {
                        if (tableau[i][4-j] == 0) {
                         tableau[i][4-j] = tableau[(i)][3-j];
                         tableau[i][3-j] = 0;
                        } else if (j<4 && tableau[i][4-j] == tableau[(i)][3-j]) {
                        tableau[i][4-j] = 2* tableau[i][4-j];
                        tableau[i][3-j] = 0;
                        }
                    }
                }
            }
        } else if (key == "up") {
            for (int k=0; k<5; k++){
                for (int j=0; j<tableau.length; j++) {
                    for (int i=0; i<tableau.length-1; i++) {
                        if (tableau[i][j] == 0) {
                            tableau[i][j] = tableau[i+1][j];
                            tableau[i+1][j] = 0;
                        } else if (tableau[i][j] == tableau[i+1][j]) {
                            tableau[i][j] = tableau[i][j] * 2;
                            tableau[i+1][j] = 0;
                        }
                    }
                }
            }
        } else if (key == "down") {
                     for (int k=0; k<5; k++){
                         for (int j=0; j<tableau.length; j++) {
                             for (int i=0; i<tableau.length-1; i++) {
                                 if (tableau[4-i][j] == 0) {
                                     tableau[4-i][j] = tableau[3-i][j];
                                     tableau[3-i][j] = 0;
                                 } else if (tableau[4-i][j] == tableau[3-i][j]) {
                                     tableau[4-i][j] = tableau[4-i][j] * 2;
                                     tableau[3-i][j] = 0;
                                 }
                             }
                         }
                     }
                 }
        // ajoute une case de valeur 2 à un endroit aléatoire
        addRandomCase();
    }

    /**
    * Ajoute une case de valeur 2 à un endroit aléatoire
    */
    public static void addRandomCase () {
        int placeNbRandom = (int)(Math.random()*((double)nbCaseDispo()));
        outerloop:
        for (int i=0; i<tableau.length; i++) {
             for (int j=0; j<tableau.length; j++) {
                if (tableau[i][j] == 0){
                    if (placeNbRandom == 0){
                        tableau[i][j] = 2;
                        break outerloop;
                    } else {
                        placeNbRandom --;
                    }
                }
             }
        }
    }

    /**
    * Renvoi le nombre de case disponible pour savoir si l'on peut en rajouter une
    *
    * @return nbCase :retourne le nombre de case disponible
    */
    public static int nbCaseDispo () {
        int nbCase = 0;
        for (int i=0; i<tableau.length; i++) {
            for (int j=0; j<tableau.length; j++) {
                if (tableau[i][j] == 0)
                    nbCase ++;
            }
        }
        return nbCase;
   }

    /**
    * 
    */
   public static boolean inGame () {
   boolean inGame = true;
        for (int i=0; i< tableau.length; i++) {
            for (int j=0; j< tableau.length; j++) {
                if (tableau[i][j] == 0)
                    return true;
            }
        }
        return true;
   }

    /**
    * Renvoi la direction choisi par l'utilisateur
    *
    * @param key :touche tapé par l'utilisateur
    * @return "": pour réinitialiser la direction
    */
    public static String setDirection (final String key) {
        if (key.equals ("up")) {
            changeTable(key);
        } else if (key.equals ("down")) {
            changeTable(key);
        } else if (key.equals ("left")) {
            changeTable(key);
        } else if (key.equals ("right")) {
            changeTable(key);
        }
        StdDraw.pause(100);
        return "";
    }

    /**
    * Met la couleur de la case en fontion de sa valeur
    */
    public static void setColor(int nombre) {
        switch (nombre) {
            case 0:
                StdDraw.setPenColor(StdDraw.GRAY);
                break;
            case 2:
                StdDraw.setPenColor(new Color(255, 240, 210));
                break;
            case 4:
                StdDraw.setPenColor(new Color(250, 170, 60));
                break;
            case 8:
                StdDraw.setPenColor(new Color(250, 150, 90));
                break;
            case 16:
                StdDraw.setPenColor(new Color(250, 110, 70));
                break;
            case 32:
                StdDraw.setPenColor(new Color(250, 90, 50));
                break;
            case 64:
                StdDraw.setPenColor(new Color(255, 10, 10));
                break;
            case 128:
                StdDraw.setPenColor(new Color(255, 240, 80));
                 break;
            case 256:
                StdDraw.setPenColor(new Color(255, 230, 20));
                 break;
            case 512:
                StdDraw.setPenColor(StdDraw.ORANGE);
             break;
            case 1024:
                StdDraw.setPenColor(StdDraw.ORANGE);
                break;
            case 2048:
                StdDraw.setPenColor(StdDraw.ORANGE);
                break;
            default:
                StdDraw.setPenColor(StdDraw.ORANGE);
        }
        return;
    }

    /**
    * Retourne true si l'utilisateur a perdu (donc il ne peut plus jouer)
    *
    * @return boolean
    */
    public static boolean perdu() {
        for (int i=0; i<tableau.length; i++){
            for (int j=0; j<tableau.length; j++){
                if (tableau[i][j] == 0)
                    return false;
                if (j<4) {
                    if (tableau[i][j] == tableau[i][j+1])  
                        return false;
                }
                if (i<4) {
                    if (tableau[i][j] == tableau[i+1][j])  
                        return false;
                }
            }
        }
        return true;
    }

    /**
    * Retourne true si l'utilisateur a gagner (donc il ne peut plus jouer)
    *
    * @return boolean
    */
    public static boolean gagner() {
        for (int i=0; i<tableau.length; i++){
            for (int j=0; j<tableau.length; j++){
                if (tableau[i][j] == 2048)
                    return true;
            }
        }
        return false;
    }

    public static void debutPartie() {
        // création de 2 polices d'écritures
        Font font1 = new Font("SANS", Font.BOLD, 50);
        Font font2 = new Font("SANS", Font.BOLD, 80);
        // créer un écran orange (255, 210, 70)
        StdDraw.clear(new Color(255, 210, 70));
        // création d'un bouton JOUER 
        StdDraw.setPenColor(new Color(255, 170, 50));
        StdDraw.filledRectangle(0.5, 0.4, 0.2, 0.1);
        StdDraw.setFont(font1);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(0.5, 0.39, "JOUER");
        // écriture du titre
        StdDraw.setFont(font2);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5, 0.8, "2048");
        // affiche les modifications sur l'écran
        StdDraw.show();
        // tant qu'on a pas clique sur le bouton, la partie ne commence pas encore
        while (!StdDraw.isKeyPressed (KeyEvent.VK_ENTER) && !(StdDraw.mouseX() < 0.7 && StdDraw.mouseX() >0.3 && StdDraw.mouseY() < 0.5 && StdDraw.mouseY() > 0.3 && StdDraw.isMousePressed()) ) {
            if (Calendar.getInstance().get(Calendar.SECOND)%2 ==0) {
                StdDraw.setPenColor(new Color(255, 170, 50));
                StdDraw.filledRectangle(0.5, 0.4, 0.2, 0.1);
                StdDraw.show();
            } else {
                StdDraw.setFont(font1);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.text(0.5, 0.39, "JOUER");
                StdDraw.show();
            }

        }
        StdDraw.clear(StdDraw.LIGHT_GRAY);
        StdDraw.show();
    }

	public static void main(String[] args) {
        boolean inGame = true;
        boolean gagner = false;
        StdDraw.enableDoubleBuffering();
        debutPartie();
        draw();
        outerloop:
        while (inGame){
            if (StdDraw.isKeyPressed (KeyEvent.VK_DOWN)){
            key = "down";
            key = setDirection(key);
            } else if (StdDraw.isKeyPressed (KeyEvent.VK_UP)){
                key = "up";
                key = setDirection(key);
            } else if (StdDraw.isKeyPressed (KeyEvent.VK_LEFT)){
                key = "left";
                key = setDirection(key);
            } else if (StdDraw.isKeyPressed (KeyEvent.VK_RIGHT)){
                key = "right";
                key = setDirection(key);
            } else if (StdDraw.isKeyPressed (KeyEvent.VK_BACK_SPACE))
                break outerloop;
            if (perdu())
                inGame = false;
            if (gagner()){
                inGame = false;
                gagner = true;
            }
            draw();
        }
        StdDraw.clear(new Color(255, 210, 70));
        Font font2 = new Font("SANS", Font.BOLD, 50);
        StdDraw.setFont(font2);
        if (gagner)
            StdDraw.text(0.5, 0.5, "Vous avez gagné");
        else
            StdDraw.text(0.5, 0.5, "Vous avez perdu");
        
        StdDraw.show();
	}
}
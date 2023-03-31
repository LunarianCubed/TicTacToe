package tictactoe.local;

import java.awt.*;

public class GameUtil {


    static char board[][] = {{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};
    static int MOUSE_X = 0;
    static int MOUSE_Y = 0;

    // 0: menu, 1: game, 2: end
    static int gameStatus = 0;

    static boolean isX = true;
    static char player;
    static char computer;
    static final int OFFSET = 200;
    static Image Circle = Toolkit.getDefaultToolkit().getImage("img/O.png");
    static Image Cross = Toolkit.getDefaultToolkit().getImage("img/X.png");



    public static void drawWord(Graphics g, String word, int x, int y, int size) {
        g.setFont(new Font("Arial", Font.PLAIN, size));
        g.drawString(word, x, y);
    }


}

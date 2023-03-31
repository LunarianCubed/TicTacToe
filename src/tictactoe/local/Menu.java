package tictactoe.local;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    public void paintSelf(Graphics g){
        g.setColor(Color.darkGray);

        GameUtil.drawWord(g, "Click to Choose", 150, 80, 30);
        GameUtil.drawWord(g, "If you want to move first or second", 150, 110, 30);

        g.drawRect(150, 150, 400, 200);
        GameUtil.drawWord(g, "Move First", 250, 240, 50);
        GameUtil.drawWord(g, "Play as O", 250, 290, 30);
        g.drawRect(150, 400, 400, 200);
        GameUtil.drawWord(g, "Move Second", 230, 490, 50);
        GameUtil.drawWord(g, "Play as X", 250, 540, 30);
    }


    void gameSelect(){
        if(GameUtil.MOUSE_X > 150 && GameUtil.MOUSE_X < 550) {
            if (GameUtil.MOUSE_Y > 150 && GameUtil.MOUSE_Y < 350) {
                GameUtil.isX = false;
                GameUtil.gameStatus = 1;
            }else if (GameUtil.MOUSE_Y > 400 && GameUtil.MOUSE_Y < 550) {
                GameUtil.isX = true;
                GameUtil.gameStatus = 1;
            }
        }
    }

}

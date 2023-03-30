package tictactoe.local;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JFrame {

    public void launch(){
        this.setVisible(true);
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);
        this.setTitle("Tic Tac Toe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton()==1){
                    GameUtil.MOUSE_X = e.getX();
                    GameUtil.MOUSE_Y = e.getY();
                    gameSelect();
                }
            }
        });

    }

    public static boolean isX = true;




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

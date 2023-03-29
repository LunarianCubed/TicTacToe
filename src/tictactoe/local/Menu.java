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
                    TicTacToe.MOUSE_X = e.getX();
                    TicTacToe.MOUSE_Y = e.getY();
                    gameSelect();
                }
            }
        });

    }

    public static boolean isX = true;




    @Override
    public void paint(Graphics g){
        g.setColor(Color.darkGray);

        TicTacToe.drawWord(g, "Click to Choose", 150, 80, 30);
        TicTacToe.drawWord(g, "If you want to move first or second", 150, 110, 30);

        g.drawRect(150, 150, 400, 200);
        TicTacToe.drawWord(g, "Move First", 250, 240, 50);
        TicTacToe.drawWord(g, "Play as O", 250, 290, 30);
        g.drawRect(150, 400, 400, 200);
        TicTacToe.drawWord(g, "Move Second", 230, 490, 50);
        TicTacToe.drawWord(g, "Play as X", 250, 540, 30);
    }


    void gameSelect(){
        if(TicTacToe.MOUSE_X > 150 && TicTacToe.MOUSE_X < 550) {
            if (TicTacToe.MOUSE_Y > 150 && TicTacToe.MOUSE_Y < 350) {
                TicTacToe.isX = false;TicTacToe ttt = new TicTacToe();
                ttt.launch();
                this.dispose();
            }else if (TicTacToe.MOUSE_Y > 400 && TicTacToe.MOUSE_Y < 550) {
                TicTacToe.isX = true;
                TicTacToe ttt = new TicTacToe();
                ttt.launch();
                this.dispose();
            }
        }
    }

}

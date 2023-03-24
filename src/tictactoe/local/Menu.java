package tictactoe.local;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    static char board[][] = {{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};
    MapBottom mapBottom = new MapBottom();


    public void launch(){
        this.setVisible(true);
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);
        this.setTitle("Tic Tac Toe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.BLACK);

        g.setColor(Color.darkGray);
        g.drawLine(250, 50, 250, 650);
        g.drawLine(450, 50, 450, 650);
        g.drawLine(50, 250, 650, 250);
        g.drawLine(50, 450, 650, 450);
    }

//    Image Circle = Toolkit.getDefaultToolkit().getImage("imgs/O.png");
//    Image Cross = Toolkit.getDefaultToolkit().getImage("imgs/X.png");

}

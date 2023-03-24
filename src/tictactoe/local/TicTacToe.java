package tictactoe.local;


import java.awt.*;

public class TicTacToe extends javax.swing.JFrame{
    static char board[][] = {{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};
    MapBottom mapBottom = new MapBottom();


    public void launch(){
        this.setVisible(true);
        this.setSize(900, 900);
        this.setLocationRelativeTo(null);
        this.setTitle("Tic Tac Toe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.darkGray);
        g.drawLine(300, 0, 300, 900);
        g.drawLine(600, 0, 600, 900);
        g.drawLine(0, 300, 900, 300);
        g.drawLine(0, 600, 900, 600);
    }


}

package tictactoe.local;


import java.awt.*;

public class TicTacToe extends javax.swing.JFrame{
    MapBottom mapBottom = new MapBottom();


    public void launch(){
        this.setVisible(true);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setTitle("Tic Tac Toe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.darkGray);
        g.drawLine(200, 0, 200, 600);
        g.drawLine(400, 0, 400, 600);
        g.drawLine(0, 200, 600, 200);
        g.drawLine(0, 400, 600, 400);
    }


}

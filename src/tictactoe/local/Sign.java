package tictactoe.local;

import java.awt.*;

import static tictactoe.local.TicTacToe.board;

public class Sign {

    static final int OFFSET = 225;


    static Image Circle = Toolkit.getDefaultToolkit().getImage("img/O.png");
    static Image Cross = Toolkit.getDefaultToolkit().getImage("img/X.png");
    void paintSelf(Graphics g){

        for(int i = 0; i < 3; i++){
            for(int j = 0; i < 3; j++){
                if(board[i][j]=='X'){
                    g.drawImage(Circle, 75+OFFSET * i, 75+OFFSET*j, 150, 150, null);
                }
                if(board[i][j]=='O'){
                    g.drawImage(Circle, 75+OFFSET * i, 75+OFFSET*j, 150, 150, null);
                }
            }
        }
        g.drawImage(Circle, 300, 300, 100, 100, null);


    }
}

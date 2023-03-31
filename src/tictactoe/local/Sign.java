package tictactoe.local;

import java.awt.*;


public class Sign {

    void paintSelf(Graphics g){

        g.setColor(Color.BLACK);
        g.drawLine(250, 50, 250, 650);
        g.drawLine(450, 50, 450, 650);
        g.drawLine(50, 250, 650, 250);
        g.drawLine(50, 450, 650, 450);


        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(GameUtil.board[i][j]=='X'){
                    g.drawImage(GameUtil.Cross, 75 + j * GameUtil.OFFSET, 75 + i * GameUtil.OFFSET, 150, 150, null);
                }
                if(GameUtil.board[i][j]=='O'){
                    g.drawImage(GameUtil.Circle, 75 + j * GameUtil.OFFSET, 75 + i * GameUtil.OFFSET, 150, 150, null);
                }
            }
        }
    }
}

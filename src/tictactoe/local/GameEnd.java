package tictactoe.local;

import java.awt.*;

public class GameEnd {

    public void paintSelf(Graphics g){
        g.setColor(Color.darkGray);
        switch (TicTacToe.evaluate()) {
            case -1 -> GameUtil.drawWord(g, "You Win!", 250, 300, 50);
            case 1 -> GameUtil.drawWord(g, "You Lose!", 250, 300, 50);
            case 0 -> GameUtil.drawWord(g, "Draw!", 250, 300, 50);
        }
    }
}

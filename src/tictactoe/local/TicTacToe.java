package tictactoe.local;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class TicTacToe extends javax.swing.JFrame{

    Sign sign = new Sign();
    Menu menu = new Menu();





    public void launch(){
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);
        this.setTitle("Tic Tac Toe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);
        setIsX();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton()==1){
                    GameUtil.MOUSE_X = e.getX();
                    GameUtil.MOUSE_Y = e.getY();
                }
                switch (GameUtil.gameStatus){
                    case 0:
                        menu.gameSelect();
                        repaint();
                        break;
                    case 1:
                        playerMove();
                        repaint();
                        break;
                    case 2:
                        break;
                }

            }
        });


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.darkGray);

        switch (GameUtil.gameStatus){
            case 0:
                menu.paintSelf(g);
                break;
            case 1:
//                bufferImage = this.createImage(700, 700);
//                Graphics buffer = bufferImage.getGraphics();
//                buffer.setColor(Color.gray);
//                sign.paintSelf(buffer);
//                buffer.drawImage(bufferImage, 0, 0, null);
                sign.paintSelf(g);
                break;
            case 2:

                break;
        }

    }


    private static int[] findBestMove(){
        int bestVal = -1000;
        int[] bestMove = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (GameUtil.board[i][j] == ' ') {
                    GameUtil.board[i][j] = GameUtil.computer;
                    int moveVal = minimax(0, false);
                    GameUtil.board[i][j] = ' ';
                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;
    }

    private static int minimax(int depth, boolean isMax){
        int score = evaluate();
        if (score == 10)
            return score;
        if (score == -10)
            return score;
        if (!isMoveLeft())
            return 0;
        if (isMax) {
            int best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (GameUtil.board[i][j] == ' ') {
                        GameUtil.board[i][j] = GameUtil.computer;
                        best = Math.max(best, minimax(depth + 1, !isMax));
                        GameUtil.board[i][j] = ' ';
                    }
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (GameUtil.board[i][j] == ' ') {
                        GameUtil.board[i][j] = GameUtil.player;
                        best = Math.min(best, minimax(depth + 1, !isMax));
                        GameUtil.board[i][j] = ' ';
                    }
                }
            }
            return best;
        }
    }

    private static boolean isMoveLeft(){
        return GameUtil.moveLeft>0;
    }
    private static int evaluate(){
        if(GameUtil.board[1][1]==GameUtil.player) {
            if ((GameUtil.board[0][0] == GameUtil.player && GameUtil.board[2][2] == GameUtil.player) ||
                    (GameUtil.board[0][1] == GameUtil.player && GameUtil.board[2][1] == GameUtil.player) ||
                    (GameUtil.board[0][2] == GameUtil.player && GameUtil.board[2][0] == GameUtil.player) ||
                    (GameUtil.board[1][0] == GameUtil.player && GameUtil.board[1][2] == GameUtil.player))
                return 10;
        }
        if(GameUtil.board[0][0] == GameUtil.player) {
            if ((GameUtil.board[0][1] == GameUtil.player && GameUtil.board[0][2] == GameUtil.player) ||
                    (GameUtil.board[1][0] == GameUtil.player && GameUtil.board[2][0] == GameUtil.player))
                return 10;
        }
        if(GameUtil.board[2][2] == GameUtil.player) {
            if ((GameUtil.board[0][2] == GameUtil.player && GameUtil.board[1][2] == GameUtil.player) ||
                    (GameUtil.board[2][0] == GameUtil.player && GameUtil.board[2][1] == GameUtil.player))
                return 10;
        }
        if(GameUtil.board[1][1]==GameUtil.computer) {
            if ((GameUtil.board[0][0] == GameUtil.computer && GameUtil.board[2][2] == 'O') ||
                    (GameUtil.board[0][1] == GameUtil.computer && GameUtil.board[2][1] == 'O') ||
                    (GameUtil.board[0][2] == GameUtil.computer && GameUtil.board[2][0] == 'O') ||
                    (GameUtil.board[1][0] == GameUtil.computer && GameUtil.board[1][2] == 'O'))
                return -10;
        }
        if(GameUtil.board[0][0] == GameUtil.computer) {
            if ((GameUtil.board[0][1] == GameUtil.computer && GameUtil.board[0][2] == 'O') ||
                    (GameUtil.board[1][0] == GameUtil.computer && GameUtil.board[2][0] == 'O'))
                return -10;
        }
        if(GameUtil.board[2][2] == GameUtil.computer) {
            if ((GameUtil.board[0][2] == GameUtil.computer && GameUtil.board[1][2] == 'O') ||
                    (GameUtil.board[2][0] == GameUtil.computer && GameUtil.board[2][1] == 'O'))
                return -10;
        }
        return 0;

    }



    private static void computerMove() {

        if(isMoveLeft());
        put(GameUtil.computer, findBestMove()[0], findBestMove()[1]);
    }




    private static void playerMove(){

        int col, row;
        if(GameUtil.MOUSE_X < 250)
            col = 0;
        else if(GameUtil.MOUSE_X < 450)
            col = 1;
        else
            col = 2;

        if(GameUtil.MOUSE_Y < 250) row = 0;
        else if(GameUtil.MOUSE_Y < 450)
            row = 1;
        else
            row = 2;

        put(GameUtil.player, row, col);

        for(char[] a: GameUtil.board)
            System.out.println(Arrays.toString(a));

        computerMove();
    }

    private static void put(char c, int x, int y){
        if(GameUtil.board[x][y] == ' '){
            GameUtil.board[x][y] = c;
            GameUtil.moveLeft--;
        }
    }






    public static void setIsX() {
        GameUtil.player= GameUtil.isX ? 'O' : 'X';
        GameUtil.computer = GameUtil.isX ? 'X' : 'O';
    }




    private static void gameStart(){
        if(!GameUtil.isX){
           playerMove();
        }
        while(GameUtil.gameStatus==1){
            computerMove();
            if(evaluate()!=0&&isMoveLeft())
                playerMove();
        }
    }




}

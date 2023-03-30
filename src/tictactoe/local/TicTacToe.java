package tictactoe.local;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class TicTacToe extends javax.swing.JFrame{

    Sign sign = new Sign();
    Menu menu = new Menu();

    Image bufferImage = null;




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


    private int[] findBestMove(){
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

    private int minimax(int depth, boolean isMax){
        int score = evaluate();
        if (score == 10)
            return score;
        if (score == -10)
            return score;
        if (isMovesLeft() == false)
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

    private int evaluate(){
        for (int row = 0; row < 3; row++) {
            if (GameUtil.board[row][0] == GameUtil.board[row][1] &&
                    GameUtil.board[row][1] == GameUtil.board[row][2]) {
                if (GameUtil.board[row][0] == GameUtil.computer)
                    return +10;
                else if (GameUtil.board[row][0] == GameUtil.player)
                    return -10;
            }
        }
        for (int col = 0; col < 3; col++) {
            if (GameUtil.board[0][col] == GameUtil.board[1][col] &&
                    GameUtil.board[1][col] == GameUtil.board[2][col]) {
                if (GameUtil.board[0][col] == GameUtil.computer)
                    return +10;
                else if (GameUtil.board[0][col] == GameUtil.player)
                    return -10;
            }
        }
        if (GameUtil.board[0][0] == GameUtil.board[1][1] && GameUtil.board[1][1] == GameUtil.board[2][2]) {
            if (GameUtil.board[0][0] == GameUtil.computer)
                return +10;
            else if (GameUtil.board[0][0] == GameUtil.player)
                return -10;
        }
        if (GameUtil.board[0][2] == GameUtil.board[1][1] && GameUtil.board[1][1] == GameUtil.board[2][0]) {
            if (GameUtil.board[0][2] == GameUtil.computer)
                return +10;
            else if (GameUtil.board[0][2] == GameUtil.player)
                return -10;
        }
        return 0;
    }



    private static void computerMove() {

        put(GameUtil.computer, col, row);
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
    }

    private static void put(char c, int x, int y){
        if(GameUtil.board[x][y] == ' '){
            GameUtil.board[x][y] = c;
        }
    }


    private void winCheck(){
        int State = 2;

        for(char[] a: GameUtil.board)
            for(char b: a)
                if(b == ' '){
                    State = 0;
                    break;
                }

        if(GameUtil.board[1][1]=='X') {
            if ((GameUtil.board[0][0] == 'X' && GameUtil.board[2][2] == 'X') ||
                    (GameUtil.board[0][1] == 'X' && GameUtil.board[2][1] == 'X') ||
                    (GameUtil.board[0][2] == 'X' && GameUtil.board[2][0] == 'X') ||
                    (GameUtil.board[1][0] == 'X' && GameUtil.board[1][2] == 'X'))
                State = 1;
        }
        if(GameUtil.board[0][0] == 'X') {
            if ((GameUtil.board[0][1] == 'X' && GameUtil.board[0][2] == 'X') ||
                        (GameUtil.board[1][0] == 'X' && GameUtil.board[2][0] == 'X'))
                State = 1;
        }
        if(GameUtil.board[2][2] == 'X') {
            if ((GameUtil.board[0][2] == 'X' && GameUtil.board[1][2] == 'X') ||
                    (GameUtil.board[2][0] == 'X' && GameUtil.board[2][1] == 'X'))
                State = 1;
        }
        if(GameUtil.board[1][1]=='O') {
            if ((GameUtil.board[0][0] == 'O' && GameUtil.board[2][2] == 'O') ||
                    (GameUtil.board[0][1] == 'O' && GameUtil.board[2][1] == 'O') ||
                    (GameUtil.board[0][2] == 'O' && GameUtil.board[2][0] == 'O') ||
                    (GameUtil.board[1][0] == 'O' && GameUtil.board[1][2] == 'O'))
                State = -1;
        }
        if(GameUtil.board[0][0] == 'O') {
            if ((GameUtil.board[0][1] == 'O' && GameUtil.board[0][2] == 'O') ||
                    (GameUtil.board[1][0] == 'O' && GameUtil.board[2][0] == 'O'))
                State = -1;
        }
        if(GameUtil.board[2][2] == 'O') {
            if ((GameUtil.board[0][2] == 'O' && GameUtil.board[1][2] == 'O') ||
                    (GameUtil.board[2][0] == 'O' && GameUtil.board[2][1] == 'O'))
                State = -1;
        }


        switch (State) {
            case 0 -> {
                System.out.println("Draw");

            }
            case 1 -> System.out.println("X Wins");
            case -1 -> System.out.println("O Wins");
        }
    }




    public static void setIsX() {
        GameUtil.player= GameUtil.isX ? 'O' : 'X';
        GameUtil.computer = GameUtil.isX ? 'X' : 'O';
    }








}

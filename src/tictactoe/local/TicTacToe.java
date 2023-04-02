package tictactoe.local;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class TicTacToe extends javax.swing.JFrame{

    Sign sign = new Sign();
    Menu menu = new Menu();
    GameEnd gameEnd = new GameEnd();

    static Image bufferImage = null;




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
                switch (GameUtil.gameStatus) {
                    case 0 -> menu.gameSelect();
                    case 1 -> gameStart();
                }

            }
        });

        while (true){
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            repaint();
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.darkGray);
        bufferImage = this.createImage(700, 700);
        Graphics buffer = bufferImage.getGraphics();


        switch (GameUtil.gameStatus) {
            case 0 -> menu.paintSelf(buffer);
            case 1 -> sign.paintSelf(buffer);
//                sign.paintSelf(g);
            case 2 -> gameEnd.paintSelf(buffer);
        }


        g.drawImage(bufferImage, 0, 0,null);

    }


    private static int[] findBestMove(){
        int[] bestMove = new int[2];
        int bestVal = -1000;
        for(int i = 0; i < 9; i++){
            if(GameUtil.board[i/3][i%3]==' '){
                GameUtil.board[i/3][i%3] = GameUtil.computer;
                int moveVal = minimax(0, false);
                GameUtil.board[i/3][i%3] = ' ';
                if(moveVal > bestVal){
                    bestMove[0] = i/3;
                    bestMove[1] = i%3;
                    bestVal = moveVal;
                }
            }
        }
        return bestMove;
    }


    private static boolean isMoveLeft(){
        for(int i = 0; i < 9; i++){
            if(GameUtil.board[i/3][i%3]==' ')
                return true;
        }
        return false;
    }
    public static int evaluate(){
        if(GameUtil.board[1][1]==GameUtil.player) {
            if ((GameUtil.board[0][0] == GameUtil.player && GameUtil.board[2][2] == GameUtil.player) ||
                    (GameUtil.board[0][1] == GameUtil.player && GameUtil.board[2][1] == GameUtil.player) ||
                    (GameUtil.board[0][2] == GameUtil.player && GameUtil.board[2][0] == GameUtil.player) ||
                    (GameUtil.board[1][0] == GameUtil.player && GameUtil.board[1][2] == GameUtil.player))
                return -1;
        }
        if(GameUtil.board[0][0] == GameUtil.player) {
            if ((GameUtil.board[0][1] == GameUtil.player && GameUtil.board[0][2] == GameUtil.player) ||
                    (GameUtil.board[1][0] == GameUtil.player && GameUtil.board[2][0] == GameUtil.player))
                return -1;
        }
        if(GameUtil.board[2][2] == GameUtil.player) {
            if ((GameUtil.board[0][2] == GameUtil.player && GameUtil.board[1][2] == GameUtil.player) ||
                    (GameUtil.board[2][0] == GameUtil.player && GameUtil.board[2][1] == GameUtil.player))
                return -1;
        }
        if(GameUtil.board[1][1]==GameUtil.computer) {
            if ((GameUtil.board[0][0] == GameUtil.computer && GameUtil.board[2][2] == GameUtil.computer) ||
                    (GameUtil.board[0][1] == GameUtil.computer && GameUtil.board[2][1] == GameUtil.computer) ||
                    (GameUtil.board[0][2] == GameUtil.computer && GameUtil.board[2][0] == GameUtil.computer) ||
                    (GameUtil.board[1][0] == GameUtil.computer && GameUtil.board[1][2] == GameUtil.computer))
                return 1;
        }
        if(GameUtil.board[0][0] == GameUtil.computer) {
            if ((GameUtil.board[0][1] == GameUtil.computer && GameUtil.board[0][2] == GameUtil.computer) ||
                    (GameUtil.board[1][0] == GameUtil.computer && GameUtil.board[2][0] == GameUtil.computer))
                return 1;
        }
        if(GameUtil.board[2][2] == GameUtil.computer) {
            if ((GameUtil.board[0][2] == GameUtil.computer && GameUtil.board[1][2] == GameUtil.computer) ||
                    (GameUtil.board[2][0] == GameUtil.computer && GameUtil.board[2][1] == GameUtil.computer))
                return 1;
        }
        return 0;

    }


    private static int minimax(int depth, boolean isMaximizing) {
        int score = evaluate();
        if(score == 1)
            return score;
        if(score == -1)
            return score;
        if(!isMoveLeft())
            return 0;

        if(isMaximizing){
            int best = -1000;
            for(int i = 0; i < 9; i++){
                if(GameUtil.board[i/3][i%3]==' '){
                    GameUtil.board[i/3][i%3] = GameUtil.computer;
                    best = Math.max(best, minimax(depth+1, false));
                    GameUtil.board[i/3][i%3] = ' ';
                }
            }
            return best;
        }else{
            int best = 1000;
            for(int i = 0; i < 9; i++){
                if(GameUtil.board[i/3][i%3]==' '){
                    GameUtil.board[i/3][i%3] = GameUtil.player;
                    best = Math.min(best, minimax(depth+1, true));
                    GameUtil.board[i/3][i%3] = ' ';
                }
            }
            return best;
        }
    }


    private static void computerMove() {
        if(evaluate()!=0||!isMoveLeft()) {
            GameUtil.gameStatus = 2;
            return;
        }
        int[] bestMove = findBestMove();
        if(isMoveLeft())
            put(GameUtil.computer, bestMove[0],bestMove[1]);
        for(char[] a: GameUtil.board)
            System.out.println(Arrays.toString(a));
        if(evaluate()!=0||!isMoveLeft()) {
            GameUtil.gameStatus = 2;
        }
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

        if(put(GameUtil.player, row, col))
            computerMove();

        for(char[] a: GameUtil.board)
            System.out.println(Arrays.toString(a));
    }

    private static boolean put(char c, int x, int y){
        if(GameUtil.board[x][y] == ' '){
            GameUtil.board[x][y] = c;
            return true;
        }
        return false;
    }






    public static void setIsX() {
        GameUtil.player= GameUtil.isX ? 'O' : 'X';
        GameUtil.computer = GameUtil.isX ? 'X' : 'O';
    }




    private static void gameStart(){
        if(GameUtil.isX){
            put(GameUtil.computer, 1,1);
        }
        playerMove();
    }
}

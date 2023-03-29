package tictactoe.local;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class TicTacToe extends javax.swing.JFrame{

    public static int MOUSE_X = 0;
    public static int MOUSE_Y = 0;

    public static boolean isX = true;
    static char player;
    static char computer;
    boolean begin = false;

    Sign sign = new Sign();


    public static char board[][] = {{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};


    public void launch(){
        this.setVisible(true);
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);
        this.setTitle("Tic Tac Toe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setIsX(isX);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton()==1){
                    detectMouse(e.getX(), e.getY());


                }
            }
        });


//        while(true){
//            repaint();
//            try{
//                Thread.sleep(40);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//
//        }

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.darkGray);
        g.drawLine(250, 50, 250, 650);
        g.drawLine(450, 50, 450, 650);
        g.drawLine(50, 250, 650, 250);
        g.drawLine(50, 450, 650, 450);

       sign.paintSelf(g);


    }








    private static void detectMouse(int x, int y){

        int row, col;
        if(x < 250)
            row = 0;
        else if(x < 450)
            row = 1;
        else
            row = 2;

        if(y < 250) col = 0;
        else if(y < 450)
            col = 1;
        else
            col = 2;

        put(player, row, col);

        for(char[] a: board)
            System.out.println(Arrays.toString(a));
    }

    private static void put(char c, int x, int y){
        if(board[x][y] == ' '){
            board[x][y] = c;


        }
    }


    private void winCheck(){
        int State = 2;

        for(char[] a: board)
            for(char b: a)
                if(b == ' '){
                    State = 0;
                    break;
                }

        if(board[1][1]=='X') {
            if ((board[0][0] == 'X' && board[2][2] == 'X') ||
                    (board[0][1] == 'X' && board[2][1] == 'X') ||
                    (board[0][2] == 'X' && board[2][0] == 'X') ||
                    (board[1][0] == 'X' && board[1][2] == 'X'))
                State = 1;
        }
        else if(board[0][0] == 'X') {
            if ((board[0][1] == 'X' && board[0][2] == 'X') ||
                        (board[1][0] == 'X' && board[2][0] == 'X'))
                State = 1;
        }
        else if(board[2][2] == 'X') {
            if ((board[0][2] == 'X' && board[1][2] == 'X') || (board[2][0] == 'X' && board[2][1] == 'X'))
                State = 1;
        }
        else if(board[1][1]=='O') {
            if ((board[0][0] == 'O' && board[2][2] == 'O') ||
                    (board[0][1] == 'O' && board[2][1] == 'O') ||
                    (board[0][2] == 'O' && board[2][0] == 'O') ||
                    (board[1][0] == 'O' && board[1][2] == 'O'))
                State = -1;
        }
        else if(board[0][0] == 'O') {
            if ((board[0][1] == 'O' && board[0][2] == 'O') ||
                    (board[1][0] == 'O' && board[2][0] == 'O'))
                State = -1;
        }
        else if(board[2][2] == 'O') {
            if ((board[0][2] == 'O' && board[1][2] == 'O') || (board[2][0] == 'O' && board[2][1] == 'O'))
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




    public static void setIsX(boolean isX) {
        if(isX){
            player = 'X';
            computer = 'O';
        }
        else{
            player = 'O';
            computer = 'X';
        }
    }




    public static void drawWord(Graphics g, String word, int x, int y, int size) {
        g.setFont(new Font("Arial", Font.PLAIN, size));
        g.drawString(word, x, y);
    }

    private static void drawPic(Graphics g, Image pic, int x, int y){
        g.drawImage(pic, 75 + x * 150, 75 + y * 150, 150, 150, null);
    }




}

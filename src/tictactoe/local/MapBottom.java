package tictactoe.local;

public class MapBottom {
    private static char board[][] = {{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};


    public MapBottom(){

    }

    public static void put(int x, int y, char c){
        if(board[x][y] == ' ')
            board[x][y] = c;
        else System.out.println("This place is already taken");
    }
}

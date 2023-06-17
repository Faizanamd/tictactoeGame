
import java.util.Scanner;

// Player class
class Player {
    private String name;
    private char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (!name.isEmpty())
            this.name = name;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public void setSymbol(char symbol) {
        if (symbol != '\0')
            this.symbol = symbol;
    }

}

// Board implementation
class Board {

    private char[][] board;
    private int boardSize = 3;
    private char p1Symbol, p2Symbol;
    private int count;
    private static final char EMPTY = ' ';
    public static final int PLAYER1WIN = '1';
    public static final int PLAYER2WIN = '2';
    public static final int DRAW = '3';
    public static final int INCOOMPLETE = '4';
    public static final int INVALIDMOVE = '5';

    public Board(char p1Symbol, char p2Symbol) {
        board = new char[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = EMPTY;
            }
        }

        this.p1Symbol = p1Symbol;
        this.p2Symbol = p2Symbol;
    }

    public int move(char symbol, int x, int y) {
        if (x < 0 || x >= boardSize || y < 0 || y >= boardSize || board[x][y] != EMPTY)
            return INVALIDMOVE;

            board[x][y] = symbol;
            count++;
        // Row
        if ( board[x][0] == board[x][1] && board[x][0] == board[x][2])
            return symbol == p1Symbol ? PLAYER1WIN : PLAYER2WIN;

        // column
        if ( board[0][y] == board[1][y] && board[0][y] == board[2][y])
            return symbol == p1Symbol ? PLAYER1WIN : PLAYER2WIN;

        // Diagonal
        if(board[0][0] != EMPTY && board[0][0] == board[1][1]  && board[0][0] == board[2][2])
            return symbol == p1Symbol ? PLAYER1WIN : PLAYER2WIN;

        if(board[0][0] != EMPTY && board[0][2] == board[1][1] && board[0][2] == board[2][0])
            return symbol == p1Symbol ? PLAYER1WIN : PLAYER2WIN;
        if(count == boardSize * boardSize)
            return DRAW;
        return INCOOMPLETE;

    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++)
                System.out.print("| " + board[i][j] + " |");
            System.out.println();
        }
        System.out.println();
    }
    // private boolean checkWin(){

    // }

}

public class tictactoeGame {
    private Player player1, player2;
    private Board board;

    public static void main(String[] args) {
        tictactoeGame t = new tictactoeGame();
        t.startGame();
    }

    private void startGame() {

        // Taking input
        Scanner sc = new Scanner(System.in);
        player1 = takeInput(1);
        player2 = takeInput(2);
        while (player1.getSymbol() == player2.getSymbol()) {
            System.out.println("Symbol already taken!!! Please another symbol ");
            player2.setSymbol(sc.next().charAt(0));
        }

        // creating board

        board = new Board(player1.getSymbol(), player2.getSymbol());

        // Play Game

        boolean playerOneTurn = true;
        int status = board.INCOOMPLETE;
        while (status == board.INCOOMPLETE || status == board.INVALIDMOVE) {
            if (playerOneTurn) {
                System.out.println("player - 1 " + player1.getName() + "'s turn: ");
                System.out.println("Enter X: ");
                int x = sc.nextInt();
                System.out.println("Enter y: ");
                int y = sc.nextInt();
                /*
                 * 1 -> player -1 win
                 * 2 -> player -2 win
                 * 3 -> draw
                 * 4 -> incomplete
                 * 5 -> invalid
                 */
                status = board.move(player1.getSymbol(), x, y);
                if (status == Board.INVALIDMOVE) {
                    System.out.println("Invalid move It's already marked !! Re-Enter : ");
                    continue;
                }
            } else {
                System.out.println("player - 2 " + player2.getName() + "'s turn: ");
                System.out.println("Enter X: ");
                int x = sc.nextInt();
                System.out.println("Enter y: ");
                int y = sc.nextInt();
                status = board.move(player2.getSymbol(), x, y);
                if (status == Board.INVALIDMOVE) {
                    System.out.println("Invalid move !! Re-Enter : ");
                    continue;
                }
            }
            playerOneTurn = !playerOneTurn;
            board.printBoard();
        }

        if (status == board.PLAYER1WIN) {
            System.out.println("Player -1 " + player1.getName() + " win!!");
        } else if (status == board.PLAYER2WIN) {
            System.out.println("Player - 2 " + player2.getName() + " win!!");
        } else
            System.out.println("Draw!!");

    }

    private Player takeInput(int num) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter player " + num + " name: ");
        String name = sc.nextLine();
        System.out.println("Enter player " + num + " symbol: ");
        char c = sc.next().charAt(0);
        Player player = new Player(name, c);
        return player;
    }

}

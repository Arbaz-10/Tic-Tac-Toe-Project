import java.util.Random;
import java.util.Scanner;

/*** TicTacToe_Game ***/

class TicTacToe {
    static char[][] board;

    public TicTacToe() {
        board = new char[3][3];
        initializeBoard();
    }

    void initializeBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                board[row][col] = ' ';
            }
        }
    }

    static void displayBoard() {
        System.out.println("-------------");
        for (int row = 0; row < board.length; row++) {
            System.out.print("| ");
            for (int col = 0; col < board.length; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    static void placeMark(int row, int col, char mark) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            board[row][col] = mark;
        } else {
            System.out.println("Invalid Position!");
        }
    }

    // check for Columns
    static boolean checkColWin() {
        for (int col = 0; col <= 2; col++) {
            if (board[0][col] != ' ' && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                return true;
            }
        }
        return false;
    }

    // check for Rows
    static boolean checkRowWin() {
        for (int row = 0; row <= 2; row++) {
            if (board[row][0] != ' ' && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return true;
            }
        }
        return false;
    }

    // check for Diagonal
    static boolean checkDiagonalWin() {
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]
                || board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        } else {
            return false;
        }
    }

    static boolean checkDraw() {
        for (int row = 0; row <= 2; row++) {
            for (int col = 0; col <= 2; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

}

abstract class Player {
    String name;
    char mark;

    abstract void makeMove();

    boolean isValidMove(int row, int col) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            if (TicTacToe.board[row][col] == ' ') {
                return true;
            }
        }
        return false;
    }
}

class HumanPlayer extends Player {
    // String name;
    // char mark;

    HumanPlayer(String name, char mark) { // () -> parameterize constructor
        this.name = name;
        this.mark = mark;
    }

    void makeMove() {
        Scanner scan = new Scanner(System.in);
        int row;
        int col;
        do {
            System.out.println("Enter the row and col ");
            row = scan.nextInt();
            col = scan.nextInt();
        } while (!isValidMove(row, col));

        TicTacToe.placeMark(row, col, mark);
    }
}

class AIPlayer extends Player {

    AIPlayer(String name, char mark) { // () -> parameterize constructor
        this.name = name;
        this.mark = mark;
    }

    void makeMove() {
        Scanner scan = new Scanner(System.in);
        int row;
        int col;
        do {
            Random r = new Random();
            row = r.nextInt(3);
            col = r.nextInt(3);
        } while (!isValidMove(row, col));

        TicTacToe.placeMark(row, col, mark);
    }
}

public class TicTacToe_Game {
    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();

        /*
         * // check for [columns] win
         * // t.placeMark(0, 0, 'X');
         * // t.placeMark(1, 1, 'O');
         * // t.placeMark(2, 0, 'X');
         * // t.placeMark(0, 2, 'O');
         * // t.placeMark(1, 0, 'X');
         * // t.displayBoard();
         * // System.out.println(t.checkColWin());
         * 
         * // check for [row] win
         * // t.placeMark(0, 0, 'X');
         * // t.placeMark(1, 0, 'O');
         * // t.placeMark(0, 1, 'X');
         * // t.placeMark(1, 1, 'O');
         * // t.placeMark(0, 2, 'X');
         * // t.displayBoard();
         * // System.out.println(t.checkRowWin());
         * 
         * // check for [Diagonal] win
         * // t.placeMark(0, 0, 'X');
         * // t.placeMark(1, 1, 'X');
         * // t.placeMark(2, 2, 'X');
         * // t.displayBoard();
         * // System.out.println(t.checkDiagonalWin());
         */
        /*
         * // t.displayBoard();
         * // System.out.println(t.checkColWin());
         * // System.out.println(t.checkRowWin());
         * // System.out.println(t.checkDiagonalWin());
         */

        HumanPlayer p1 = new HumanPlayer("Arbaz", 'X');
        // HumanPlayer p2 = new HumanPlayer("Aritra", 'O');
        AIPlayer p2 = new AIPlayer("AIBot", 'O');

        Player cp;
        cp = p1;

        while (true) {
            System.out.println(cp.name + " turn! ");
            cp.makeMove(); // row, col & check isValidMove
            TicTacToe.displayBoard();

            if (TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagonalWin()) {
                System.out.println(cp.name + " has won! ");
                break;
            } else if (TicTacToe.checkDraw()) {
                System.out.println("Game is Draw! ");
            } else {
                if (cp == p1) {
                    cp = p2;
                } else {
                    cp = p1;
                }
            }
        }

    }
}

/*
 * class is Imaginary <--> Object is Real
 */
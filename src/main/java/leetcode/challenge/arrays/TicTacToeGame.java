package leetcode.challenge.arrays;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {

    private String[][] board;
    public TicTacToeGame(int size) {
        this.board = new String[size][size];
    }

    public void createBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = "-";
            }
        }
    }

    public int getRandomFirstPlayer() {
        return new Random().nextInt(2);
    }

    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame(3);
        System.out.println(game.getRandomFirstPlayer());
        game.play();
    }

    public boolean isBoardFilled() {
        for (String[] items: board) {
            for (String item: items) {
                if (item == "-")
                    return false;
            }
        }
        return true;
    }

    public String swapTurn(String player) {
        if (player == "X")
            return "0";
        else
            return "X";
    }

    public void showBoard() {
        for (String[] items: board) {
            for (String item: items) {
                System.out.printf("%s ", item);
            }
            System.out.println();
        }
    }

    public boolean isPlayerWinner(String player) {
        boolean win = false;
        int n = this.board.length;

        // Check rows
        for (int i = 0; i < n; i++) {
            win = true;
            for (int j = 0; j < n; j++) {
                if (this.board[i][j] != player) {
                    win = false;
                    break;
                }
            }
            if (win)
                return win;
        }

        // Check columns
        for (int i = 0; i < n; i++) {
            win = true;
            for (int j = 0; j < n; j++) {
                if (this.board[j][i] != player) {
                    win = false;
                    break;
                }
            }
            if (win)
                return win;
        }

        // Check diagonals
        win = true;
        for (int i = 0; i < n; i++) {
            if (this.board[i][i] != player) {
                win = false;
                break;
            }
        }
        if (win)
            return win;

        win = true;
        for (int i = 0; i < n; i++) {
            if (this.board[i][n - 1 - i] != player) {
                win = false;
                break;
            }
        }
        if (win)
            return win;
//        return false;

        for (String[] items: this.board) {
            for (String item: items) {
                if (item == "-")
                    return false;
            }
        }
        return true;
    }

    public void markSpot(int row, int col, String player) {
        this.board[row][col] = player;
    }

    public void play() {
        this.createBoard();
        String player = this.getRandomFirstPlayer() == 0 ? "X" : "0";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.printf("Player %s turn\n", player);
            this.showBoard();

            System.out.println("Enter row and column numbers to mark? ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            this.markSpot(row - 1, col - 1, player);

            if (this.isPlayerWinner(player)) {
                System.out.printf("Player %s has won the game\n", player);
                break;
            }

            if (this.isBoardFilled()) {
                System.out.println("It's a Draw");
                break;
            }

            player = this.swapTurn(player);
        }
        System.out.println();
        this.showBoard();

    }
}

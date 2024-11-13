package DSA.DAA;
import java.util.*;

public class J_10_n_Queen {
    static void printBoard(int board[][],int N) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean isSafe(int board[][], int row, int col, int N) {
        for (int r = row - 1; r >= 0; r--) { // vertically up
            if (board[r][col] == 1) {
                return false;
            }
        }

        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) { // left upper diagonal
            if (board[r][c] == 1) {
                return false;
            }
        }

        for (int r = row - 1, c = col + 1; r >= 0 && c < N; r--, c++) { // right upper diagonal
            if (board[r][c] == 1) {
                return false;
            }
        }
        return true;
    }

    static boolean nQueenUtil(int board[][], int row,int N) {
        if (row == N) {
            printBoard(board,N);
            return true;
        }
        boolean result = false;
        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col,N)) {
                board[row][col] = 1;
                result = nQueenUtil(board, row + 1, N) || result;
                board[row][col] = 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Number of Queens: ");
        int N = sc.nextInt();
        if(N<4 && N!=1){
            System.out.println("Not Possible");
            return;
        }
        System.out.println("For " + N + " * " + N + " matrix : ");
        int board[][] = new int[N][N];
        nQueenUtil(board, 0, N);
    }
}

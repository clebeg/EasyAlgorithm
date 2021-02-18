package io.github.clebeg.algo.leetcode;

public class IsValidSudoku {
    public static void main(String[] args) {
        IsValidSudoku cur = new IsValidSudoku();
        char[][] board = {
                        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                };
        cur.solveSudoku(board);
    }

    public void solveSudoku(char[][] board) {
        if (board.length == 0) return;
        process(board);
        System.out.println(board);
    }

    public boolean process(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        board[i][j] = c;
                        if (isValidSudoku(board)) {
                            if (process(board)) return true;
                            board[i][j] = '.';
                        }
                        board[i][j] = '.';
                    }
                    return false;
                }
            }
        }
        return true;
    }


    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (!isValid(board, i, 0, 1)) return false;
            if (!isValid(board, 0, i, 2)) return false;
            if (!isValid(board, (i/3)*3, (i*3)%9, 3)) return false;
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, int type) {
        int[] showIt = new int[board.length];
        for (int i = 0; i < board.length; i++) {
            char tmp = '.';
            if (type == 1) tmp = board[row][i];
            else if (type == 2) tmp = board[i][col];
            else tmp = board[row + i/3][col + i%3];
            if (tmp != '.') {
                int tmpNum = tmp - '0' - 1;
                if (showIt[tmpNum] == 0) showIt[tmpNum] = 1;
                else return false;
            }
        }
        return true;
    }
}

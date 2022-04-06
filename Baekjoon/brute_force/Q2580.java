package 백트래킹;

import java.io.*;
import java.util.StringTokenizer;

public class Q2580 {
    /*스도쿠*/
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        board = new int[9][9];

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve(0, 0);
    }

    public static void solve(int row, int col) throws IOException {
        if (col == 9) {
            //그 행의 마지막 열까지 다 돌았음
            //다음 행 첫번째 열로 간다.
            solve(row + 1, 0);
            return;
        }
        if (row == 9) {
            //행이 9까지 내려왔으니 9*9순회 끝난 것!(모두 채워짐)
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    bw.write(board[i][j] + " ");
                }
                bw.write('\n');
            }
            bw.flush();
            bw.close();
            System.exit(0); //재귀가 깊을 때 메소드 한번에 종료하는 법
        }

        if (board[row][col] == 0) {
            //1~9까지 하나씩 넣어보면서 가능한지 체크한 후, 가능하다면 재귀 돌림
            for(int i = 1; i < 10; i++) {
                if (isValid(row, col, i)) {
                    board[row][col] = i;
                    solve(row, col + 1);
                    board[row][col] = 0;
                }
            }
            return;
        }
        solve(row, col + 1);
    }

    public static boolean isValid(int row, int col, int n) {
        for (int i = 0; i < 9; i++) {
            //행체크
            if (board[row][i] == n) return false;
            //열체크
            if (board[i][col] == n) return false;
        }
        //사각형체크
        //0 1 2 | 3 4 5 | 6 7 8에서
        //각 자리를 3으로 나눈 나머지가 모두 0,1,2임
        //그래서 그 나머지를 row, col 본인에게서 빼주면 3*3 시작점임
        int startRow = row - row%3;
        int startCol = col - col%3;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[startRow+i][startCol+j]== n) return false;
            }
        }
        return true;
    }
}

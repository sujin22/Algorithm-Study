package boj.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9663 {
    static int[] board;
    static int n;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n];

        search(0);

        System.out.println(cnt);
    }
    public static void search(int depth){
        if(depth == n){
            cnt++;
            return;
        }
        //열을 0~n까지 증가시키며 검사하기
        for(int i = 0; i<n; i++){
            if(isValid(depth, i)){
                board[depth]=i;
                search(depth+1);
            }
        }
    }
    //depth: 현재 행, col: 저장하고 싶은 열
    public static boolean isValid(int depth, int col){
        //행을 0부터 depth 직전 행까지 증가시키며 안되는 경우 있는지 검사하기
        //아직 저장 안된 부분 빼고 검사하기위해 n까지 아니고 depth까지 검사함
        for(int j = 0 ; j<depth; j++){
            //상하체크
            //(저장하고 싶은 열이 이미 저장되어있으면 불가능)
            if(board[j] == col){
                return false;
            }
            //대각선 체크
            //(행,열 차이가 같으면 대각선에 위치함)
            if(Math.abs(j-depth)==Math.abs(board[j]-col)){
                return false;
            }
        }
        return true;
    }
    //좌우체크는 이미 행 기준으로 증가시키며 넣고 있으므로 체크해줄 필요 없음(같은 행에 저장된 값 x)
}

package boj.graph_traversal.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q12887_경로게임 {
    static int N;
    static int[] di = {0, 1, -1};
    static int[] dj = {1, 0, 0};
    static boolean[][] visited;
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new char[2][N];
        for(int i = 0; i<2; i++){
            String str = br.readLine();
            for(int j = 0; j<N; j++){
                board[i][j] = str.charAt(j);
            }
        }

        if(N==1){
            if(board[0][0] == '.' && board[1][0] == '.'){
                System.out.println(1);
            }else{
                System.out.println(0);
            }
        }
        else{
            int result = Integer.MIN_VALUE;
            for(int r = 0;r<2;r++){
                visited = new boolean[2][N];
                if(board[r][0]=='.'){
                    dfs(r, 0);
                    int cnt = 0;
                    for(int i = 0; i<2;i++){
                        for(int j = 0; j<N; j++){
                            if(board[i][j]=='.' && !visited[i][j]){
                                cnt++;
                            }
                        }
                    }
                    result = Integer.max(cnt, result);
                }
            }
            System.out.println(result);
        }

    }

    static void dfs(int i , int j){
        visited[i][j] = true;
        if(j==N-1){
            return;
        }
        for(int ind = 0; ind<3; ind++){
            int nextI = i+di[ind];
            int nextJ = j+dj[ind];
            if(nextI>=0 && nextI<2 && nextJ>=0 && nextJ<N
                    && !visited[nextI][nextJ] && board[nextI][nextJ]=='.'){
                dfs(nextI, nextJ);
                break;
            }
        }
    }
}

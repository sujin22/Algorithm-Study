package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14430_자원캐기 {
    /*[실버1]Q14430 - 자원 캐기*/
    //bfs로 풀면 메모리 초과 나므로, dp로 풀어야 한다.
    static int N, M;
    static int [][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];

        for(int i =1; i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<M+1; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i =1 ;i<N+1;i++){
            for(int j = 1; j<M+1; j++){
                map[i][j] += Integer.max(map[i-1][j], map[i][j-1]);
            }
        }
        System.out.println(map[N][M]);
    }
}

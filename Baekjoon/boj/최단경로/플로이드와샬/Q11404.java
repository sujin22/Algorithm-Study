package boj.최단경로.플로이드와샬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q11404 {
    /*[골드4]Q11404 - 플로이드*/
    static int n, m;
    static final long INF = 1000000000;
    static long [][] dist;  //인접행렬
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());    //도시개수
        m = Integer.parseInt(br.readLine());    //버스개수(간선)

        dist = new long[n+1][n+1];

        for(int i = 1; i<n+1;i++){
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for(int i = 0; i<m;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            dist[start][end] = Long.min(dist[start][end], cost);
        }

        floydWarshall();

        for(int i = 1; i<n+1;i++){
            for(int j = 1; j<n+1;j++){
                if(dist[i][j] == INF)   System.out.print("0 ");
                else System.out.print(dist[i][j]+" ");
            }
            System.out.println();
        }


    }
    public static void floydWarshall(){
        for(int k = 1;k<n+1; k++){
            for(int i = 1; i<n+1;i++){
                for(int j = 1; j<n+1;j++){
                    dist[i][j] = Long.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}

package dp;

import java.io.*;
import java.util.StringTokenizer;

public class Q11660_구간합구하기5 {
    /*[실버1]Q11660 - 구간 합 구하기5*/
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int [][] graph = new int[N+1][N+1];
        for(int i =1 ; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<N+1; j++){
                int now = Integer.parseInt(st.nextToken());
                graph[i][j] = graph[i-1][j] + graph[i][j-1] - graph[i-1][j-1] + now;
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            bw.write(graph[x2][y2] - graph[x2][y1-1] - graph[x1-1][y2] + graph[x1-1][y1-1] + "\n");
        }
        bw.flush();
        bw.close();
    }
}

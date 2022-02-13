package 최단경로.플로이드와샬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q11780_플로이드2 {
    /*[골드2]Q11780 - 플로이드2*/
    static int n, m;
    static final long INF = 1000000000;
    static long [][] dist;  //인접행렬
    static int [][] bridge;  //거쳐가는 노드를 저장할 행렬
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());    //도시개수
        m = Integer.parseInt(br.readLine());    //버스개수(간선)

        dist = new long[n+1][n+1];
        bridge = new int[n+1][n+1];

        for(int i = 1; i<n+1;i++){
            Arrays.fill(dist[i], INF);
            Arrays.fill(bridge[i], 0);
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

        //최소비용 출력
        for(int i = 1; i<n+1;i++){
            for(int j = 1; j<n+1;j++){
                if(dist[i][j] == INF)   System.out.print("0 ");
                else System.out.print(dist[i][j]+" ");
            }
            System.out.println();
        }

        //최소비용에 포함된 도시 개수, 경로 출력
        for(int i = 1; i<n+1;i++){
            for(int j = 1; j<n+1; j++){
                if(dist[i][j] == INF || dist[i][j] == 0){
                    System.out.println("0");
                }
                else{
                    //경로 검사하고 개수와 함께 출력하기
                    ArrayList<Integer> path = new ArrayList<>();
                    find(path, i,j);
                    System.out.print(path.size()+" ");
                    while(!path.isEmpty()){
                        System.out.print(path.remove(0)+" ");
                    }
                    System.out.println();
                }
            }
        }


    }
    public static void floydWarshall(){
        for(int k = 1;k<n+1; k++){
            for(int i = 1; i<n+1;i++){
                for(int j = 1; j<n+1;j++){
                    if(dist[i][j] > dist[i][k]+dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                        bridge[i][j] = k;
                    }
                }
            }
        }
    }
    public static void find(ArrayList<Integer> path, int i, int j){
        if(bridge[i][j] == 0){
            path.add(i);
            path.add(j);
            return;
        }
        find(path, i, bridge[i][j]);
        path.remove(path.size()-1);
        find(path, bridge[i][j], j);
    }
}

package graph_traversal.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1976_여행가자 {
    /*[골드4]Q1976 - 여행가자*/
    //도시의 수가 최대 200개고, 경로의 길이가 최대 2000이라서
    //도시 수가 더 적으므로, bfs로 서로 연결된 애들을 미리 파악함
    //이후에 경로를 M-1번의 수행으로 행렬 값 검사하는 게 효율적이라고 생각되어 그렇게 풀었음

    static int N, M;
    static int[][] arr;
    static int[] path;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        arr = new int[N+1][N+1];
        StringTokenizer st;
        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=N; j++){
                int state = Integer.parseInt(st.nextToken());
                arr[i][j] = state;
            }
        }

        //bfs를 돌며 연결된 애들 모두 연결되었다고 인접행렬 갱신
        for(int i= 1; i<N+1; i++){
            bfs(i);
        }

        path = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<M; i++){
            path[i] = Integer.parseInt(st.nextToken());
        }

        //연결되어있는지 행렬 검사해서 YES or NO 출력
        for(int i = 0; i<M-1;i++){
            if(arr[path[i]][path[i+1]]==0){
                System.out.println("NO");
                System.exit(0);
            }
        }
        System.out.println("YES");
    }


    private static void bfs(int c){
        Queue<Integer> q = new ArrayDeque();
        boolean[] visited = new boolean[N+1];
        q.add(c);
        visited[c] = true;

        while(!q.isEmpty()){
            int next = q.poll();
            arr[c][next] = 1; //인접행렬 갱신
            for(int i = 1; i<N+1; i++){
                if(!visited[i] && arr[next][i] == 1){
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}

package graph_traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1260 {
    static int n;
    static int m;
    static int[][] graph;
    static boolean []check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        graph = new int[n+1][n+1];  //그래프
        check = new boolean[n+1];   //방문여부 저장하는 배열

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph[v1][v2] = 1;
            graph[v2][v1] = 1;
        }

        dfs(v);
        System.out.println();
        Arrays.fill(check, false);
        bfs(v);
    }

    public static void dfs(int v) {
        //현재 정점 방문하면서 check, print
        check[v] = true;
        System.out.print(v);

        //현재 정점과 연결된 정점들을 순회하면서 정점마다 재귀로 또 호출
        for(int i = 1;i<n+1;i++){
            if(graph[v][i] == 1 && !check[i]){
                //간선이 있고, 그 정점에 방문한 적이 없으면
                System.out.print(" ");
                dfs(i);
            }
        }
    }

    public static void bfs(int v) {
        //앞으로 방문할 정점들을 저장할 큐 선언
        Queue<Integer> q = new LinkedList<Integer>();

        //현재 정점 추가
        q.add(v);
        check[v] = true;

        //현재 정점의 자식들을 다 방문한다. 방문하면 poll하고 그 자식들 큐에 저장함(이유:현재 정점 자식들 순회 끝나면 걔네 자식들 순회해야하니까)
        while(!q.isEmpty()){
            //현재 정점 poll해서 저장, 출력
            int tmp = q.poll();
            System.out.print(tmp+" ");

            //자식들 순회하며 큐에 add
            //순회한 자식은 큐에 중복으로 들어가지 않게 하기 위해 check처리
            for(int i = 1; i<n+1; i++){
                if(graph[tmp][i] == 1 && !check[i]){
                    check[i] = true;
                    q.add(i);
                }
            }
        }

    }
}

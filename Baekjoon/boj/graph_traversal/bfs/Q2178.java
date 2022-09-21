package boj.graph_traversal.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2178 {
    static int n;
    static int m;
    static int[][] graph;
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];  //그래프
        check = new boolean[n][m];   //방문여부 저장하는 배열

        for (int i = 0; i < n; i++) {
            String rowStr = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = rowStr.charAt(j) - '0';
            }
        }
//        for(int i = 0; i<n;i++){
//            System.out.println(Arrays.toString(graph[i]));
//        }

        Queue<Vertex> q = new LinkedList<Vertex>();
        //Queue 선언할때는 ArrayDequeue
        q.add(new Vertex(0, 0));
        check[0][0] = true;
        while (!q.isEmpty()) {
            Vertex tmp = q.poll();
            if (tmp.i - 1 < 0 || tmp.i + 1 >= n || tmp.j - 1 < 0 || tmp.j + 1 >= n) {
                continue;
            }

            if (graph[tmp.i - 1][tmp.j] == 1 && !check[tmp.i - 1][tmp.j]) {
                graph[tmp.i - 1][tmp.j] = graph[tmp.i][tmp.j] + 1;
                check[tmp.i - 1][tmp.j] = true;
                q.add(new Vertex(tmp.i - 1, tmp.j));
            }

            if (graph[tmp.i + 1][tmp.j] == 1 && !check[tmp.i + 1][tmp.j]) {
                graph[tmp.i + 1][tmp.j] = graph[tmp.i][tmp.j] + 1;
                check[tmp.i + 1][tmp.j] = true;
                q.add(new Vertex(tmp.i + 1, tmp.j));
            }

            if (graph[tmp.i][tmp.j - 1] == 1 && !check[tmp.i][tmp.j - 1]) {
                graph[tmp.i][tmp.j - 1] = graph[tmp.i][tmp.j] + 1;
                check[tmp.i][tmp.j - 1] = true;
                q.add(new Vertex(tmp.i, tmp.j - 1));
            }

            if (graph[tmp.i][tmp.j + 1] == 1 && !check[tmp.i][tmp.j + 1]) {
                graph[tmp.i][tmp.j + 1] = graph[tmp.i][tmp.j] + 1;
                check[tmp.i][tmp.j + 1] = true;
                q.add(new Vertex(tmp.i, tmp.j + 1));
            }

        }

//        System.out.println();
//
//        for(int i = 0; i<n;i++){
//            System.out.println(Arrays.toString(graph[i]));
//        }
        System.out.println(graph[n - 1][m - 1]);

    }

    public static class Vertex {
        public int i;
        public int j;

        public Vertex(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}

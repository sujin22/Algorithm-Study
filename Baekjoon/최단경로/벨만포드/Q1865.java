package 최단경로.벨만포드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1865 {
    /*[골드3]Q1865 - 웜홀*/
    //자기 자신으로 돌아왔을 때 값이 작아진다는 것 == negative cycle이 존재한다는 것
    final static long INF = 10001;
    static Edge[] edges;
    static long[] dist;
    static int N, M, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine()); //테스트케이스 수
        tcLoop:
        for (int t = 0; t < TC; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());    //지점 수
            M = Integer.parseInt(st.nextToken());    //도로 수
            W = Integer.parseInt(st.nextToken());    //웜홀 수

            edges = new Edge[2*M + W];
            //도로 입력
            for (int i = 0; i < 2*M; i+=2) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(start, end, cost);
                edges[i+1] = new Edge(end, start, cost);

            }
            //웜홀 입력
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges[2*M + i] = new Edge(start, end, cost * -1);
            }

            dist = new long[N + 1];
            //한 점을 시작점으로 벨만포드 알고리즘을 통해 negative cycle이 있는지 찾는다.(어차피 음의 사이클 있으면 한번만 검사해도 알 수 있으니까)
            Arrays.fill(dist, INF);
            dist[1] = 0;
            if (bellman_ford()) {
                System.out.println("YES");
                continue tcLoop;
            }
            System.out.println("NO");
        }

    }

    static boolean bellman_ford() {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < 2*M + W; j++) {
                Edge edge = edges[j];
                /*여기서 INF일 경우 continue해주지 않는 이유: 한번의 검사로 전체에 음의 사이클이 있는지 검사할 것이므로 제외해야 함.
                 * INF인 곳(출발점에서 접근하지 않는 곳)에 음의 사이클이 있을 수도 있음
                 * 최단거리를 구하는 것이 아니고 음의 사이클을 구하는 것이기 때문*/
                if (dist[edge.end] > dist[edge.start] + edge.cost) {
                    if (i == N) return true;    //음의 사이클 존재
                    dist[edge.end] = dist[edge.start] + edge.cost;
                }
            }
        }
        return false;
    }

    static class Edge {
        int start;
        int end;
        long cost;

        public Edge(int start, int end, long cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}

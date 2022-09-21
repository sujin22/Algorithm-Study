package boj.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q22865 {
    /*[골드4] Q22865 - 가장 먼 곳*/

    static int N, M, A, B, C;
    static ArrayList<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());   //정점 개수
        graph = new ArrayList[N+1];
        for(int i = 0; i<N+1; i++){
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());    //A위치
        B = Integer.parseInt(st.nextToken());    //B위치
        C = Integer.parseInt(st.nextToken());    //C위치

        M = Integer.parseInt(br.readLine());
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[start].add(new Edge(end, weight));
            graph[end].add(new Edge(start, weight));
        }
        int [] distA = new int[N+1];
        int [] distB = new int[N+1];
        int [] distC = new int[N+1];
        Arrays.fill(distA, Integer.MAX_VALUE);
        Arrays.fill(distB, Integer.MAX_VALUE);
        Arrays.fill(distC, Integer.MAX_VALUE);

        dijkstra(A, distA);
        dijkstra(B, distB);
        dijkstra(C, distC);

        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for(int i=1;i<N+1;i++){
            if(i==A || i==B || i==C)    continue;
            //distA,distB,distC 중 최솟값 저장(제일 가까운 도시)
            int tmp = Integer.min(distA[i], distB[i]);
            tmp= Integer.min(tmp, distC[i]);

            if(tmp>max){
                max = tmp;
                maxIndex = i;
            }
        }
        System.out.println(maxIndex);
    }
    public static void dijkstra(int start, int[] dist){
        PriorityQueue<Edge> q = new PriorityQueue<>();
        q.add(new Edge(start, 0));
        dist[start] = 0;

        while(!q.isEmpty()){
            Edge e = q.poll();
            int v = e.vertex;
            int dst = e.dist;

            if(dst > dist[v])   continue;

            for(int i = 0 ;i<graph[v].size();i++){
                int nextV = graph[v].get(i).vertex;
                int nextDst = graph[v].get(i).dist + dst;

                if(nextDst < dist[nextV]){
                    dist[nextV] = nextDst;
                    q.add(new Edge(nextV, nextDst));
                }
            }
        }
    }
    public static class Edge implements Comparable<Edge>{
        int vertex;
        int dist;

        public Edge(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return dist - o.dist;
        }
    }

}

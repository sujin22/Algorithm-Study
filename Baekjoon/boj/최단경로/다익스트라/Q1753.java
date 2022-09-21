package boj.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1753 {
    static int V, E, K;
    static ArrayList<Edge>[] graph;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());   //정점 개수
        E = Integer.parseInt(st.nextToken());   //간선 개수
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());   //시작점

        //인접리스트 선언
        graph = new ArrayList[V+1];
        for(int i = 0; i<V+1; i++){
            graph[i] = new ArrayList<>();
        }
        //최단거리 dp테이블 선언
        dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;

        for(int i = 0; i<E;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());   //시작점
            int end = Integer.parseInt(st.nextToken());   //끝점
            int w = Integer.parseInt(st.nextToken());   //가중치

            graph[start].add(new Edge(end, w));
        }

        dijkstra();

        int cnt = 0;
        for(int i = 1; i<V+1; i++){
            if(dist[i]==Integer.MAX_VALUE){
                System.out.println("INF");
            }else{
                System.out.println(dist[i]);
            }
        }

    }
    public static void dijkstra(){
        PriorityQueue<Edge> q = new PriorityQueue<>();
        q.add(new Edge(K, 0));
        while(!q.isEmpty()){
            Edge e = q.poll();
            int vertex = e.vertex;
            int cost = e.cost;

            if(dist[vertex] < cost) continue;
            for(int i = 0; i<graph[vertex].size();i++){
                int nextVertex = graph[vertex].get(i).vertex;
                int nextCost = graph[vertex].get(i).cost + cost;
                if(dist[nextVertex]> nextCost){
                    q.add(new Edge(nextVertex, nextCost));
                    dist[nextVertex] = nextCost;
                }
            }

        }
    }
    static class Edge implements Comparable<Edge>{
        int vertex;
        int cost;

        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }
}

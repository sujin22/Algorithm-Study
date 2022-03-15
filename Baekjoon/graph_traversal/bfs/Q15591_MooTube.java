package graph_traversal.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q15591_MooTube {
    /*[골드5]Q15591 - MooTube*/
    static int n, q;
    static boolean[] visited;
    static ArrayList<Edge>[] network;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        network = new ArrayList[n+1];
        for(int i =0; i<n+1;i++){
            network[i] = new ArrayList<>();
        }
        visited = new boolean[n+1];

        for(int i = 0; i<n-1;i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            network[v1].add(new Edge(v2,w));
            network[v2].add(new Edge(v1,w));
        }


        for(int i = 0; i<q; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int video = Integer.parseInt(st.nextToken());

            Arrays.fill(visited,false);
            bfs(k, video);

        }

    }

    static void bfs(int k, int v){
        int cnt = 0;    //k 이상인 비디오 개수
        Queue<Edge> q = new ArrayDeque<>();
        q.offer(new Edge(v, Long.MAX_VALUE));
        while(!q.isEmpty()){
            Edge now = q.poll();
            visited[now.v] = true;
            if(now.usado >= k && now.usado!=Long.MAX_VALUE) cnt++;
            for(Edge e: network[now.v]){
                if(!visited[e.v]){
                    //부모와 자식 중 더 작은 유사도로 큐에 넣기
                    q.add(new Edge(e.v, Long.min(e.usado, now.usado)));
                }
            }
        }
        System.out.println(cnt);
    }
    static class Edge{
        int v;
        long usado;

        public Edge(int v, long usado) {
            this.v = v;
            this.usado = usado;
        }
    }

}

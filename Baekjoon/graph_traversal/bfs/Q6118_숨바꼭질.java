package graph_traversal.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q6118_숨바꼭질 {
    static int [] dist;
    static boolean [] visited;
    static int n,m;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        visited = new boolean[n+1];
        graph = new ArrayList[n+1];
        for(int i = 1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i<m;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }

        bfs();

        int maxNum = 0;
        int maxCnt = 0;
        int maxDist = Integer.MIN_VALUE;

        for(int i = 2; i<n+1;i++){
            if(maxDist<dist[i] && dist[i]!=Integer.MAX_VALUE){
                maxDist = dist[i];
                maxNum = i;
                maxCnt = 0;
            }
            if(maxDist == dist[i]){
                maxCnt++;
            }
        }

        System.out.println(maxNum+" "+maxDist+" "+maxCnt);
    }
    public static void bfs(){
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(1,0));
        visited[1] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i:graph[now.end]){
                if(!visited[i] && dist[i] > now.depth+1){
                    dist[i] = now.depth+1;
                    q.add(new Node(i, dist[i]));
                    visited[now.end] = true;
                }
            }
        }
    }
    static class Node{
        int end, depth;

        public Node(int end, int depth) {
            this.end = end;
            this.depth = depth;
        }
    }

}

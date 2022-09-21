package boj.최소스패닝트리_MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1922_네트워크연결 {
    /*[골드4]Q1922 - 네트워크 연결*/
    static int N, M, result, cnt;
    static PriorityQueue<Edge> pq;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());   //컴퓨터 개수
        M = Integer.parseInt(br.readLine());   //연결할 수 있는 선의 수

        pq = new PriorityQueue<>();
        for(int i = 0; i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Edge(start, end, cost));
        }

        parent = new int[N+1];

        result = 0; //최소 스패닝 트리의 가중치 합
        cnt = 0;    //연결된 정점의 수 카운트하는 변수
        kruskal();
        System.out.println(result);

    }
    public static void kruskal(){
        //각 정점 본인을 부모로 트리 초기화
        for(int i = 0; i<N+1;i++){
            parent[i] = i;
        }
        while(!pq.isEmpty()){
            Edge e = pq.poll();
            if(find(parent, e.start) != find(parent, e.end)){
                result += e.cost;
                cnt++;
                if(cnt == N-1)  return;
                union(parent, e.start, e.end);
            }
        }

    }
    public static int find(int[] arr, int a){
        if(arr[a] == a) return a;
        else    return find(arr, arr[a]);
    }
    public static void union(int[] arr, int a, int b){
        a = find(arr, a);
        b = find(arr, b);

        if(a>b) arr[a] = b;
        else arr[b] = a;
    }
    public static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}

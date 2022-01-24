package 최소스패닝트리_MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q14621_나만안되는연애 {
    /*[골드3]Q14621 - 나만 안되는 연애*/
    static int N, M, cnt, result;
    static PriorityQueue<Edge> pq;
    static int [] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //성별 입력받기
        char[] gender = new char[N+1];         //남초, 여초에 따라 각각 M, W 저장함
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<N+1;i++){
            gender[i] = st.nextToken().charAt(0);
        }

        //간선 입력받기
        pq = new PriorityQueue<>();
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if(gender[start] == gender[end]) continue; //같은 성별의 학교를 연결하는 간선이면 큐에 추가하지 않는다.

            pq.add(new Edge(start, end, cost));
        }

        parent = new int[N+1];
        result = 0;
        cnt = 0;
        kruskal();

        if(cnt != N-1) System.out.println(-1);
        else System.out.println(result);

    }
    static void kruskal(){
        //트리 부모 노드 자기 자신으로 초기화
        for(int i = 1; i<N+1;i++){
            parent[i] = i;
        }
        while(!pq.isEmpty()){
            Edge e = pq.poll();
            if(find(parent, e.start) != find(parent, e.end)){
                result += e.cost;
                cnt++;
                if(cnt == N-1) return;
                union(parent, e.start, e.end);
            }
        }
    }

    static int find(int[] arr, int a){
        if(arr[a] == a) return a;
        else return find(arr, arr[a]);
    }
    static void union(int[] arr, int a, int b){
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

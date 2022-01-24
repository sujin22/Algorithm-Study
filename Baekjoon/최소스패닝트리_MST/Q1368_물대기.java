package 최소스패닝트리_MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1368_물대기 {
    /*[골드2]Q1368 - 물대기*/
    //가상의 노드를 만들어서 그 노드와 연결되면 우물을 건설한다.

    static int N;
    static PriorityQueue<Edge> pq;
    static int[] wells; //우물 비용
    static int[] parent;
    static int result, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        wells = new int[N+1];
        for(int i = 1; i<N+1; i++){
            wells[i] = Integer.parseInt(br.readLine()); //i번째 논에 우물 건설하는 비용
        }

        parent = new int[N+1];
        pq = new PriorityQueue<>();

        //인접행렬 저장(간선 비용 저장
        for(int i = 1; i<N+1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new Edge(0,i,wells[i])); //가상의 0번노드와 연결되는 간선 생성한다. cost는 우물을 건설하는 비용
            for(int j = 1; j<N+1; j++){
                int value = Integer.parseInt(st.nextToken());
                pq.add(new Edge(i,j,value));
            }
        }

        result = 0;   //최소비용
        cnt = 0;    //간선개수
        kruskal();

        System.out.println(result);

    }
    static void kruskal(){
        //초기화
        for(int i = 0; i<N+1;i++){
            parent[i] = i;
        }

        while(!pq.isEmpty()){
            Edge e = pq.poll();
            if(find(e.start) != find(e.end)){
//                System.out.println("선택된 간선: "+ e.toString());
                result+=e.cost;
                cnt++;

                union(e.start, e.end);
            }
        }

    }
    static int find(int a){
        if(parent[a] == a)  return a;
        else    return find(parent[a]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a>b) parent[a] = b;
        else parent[b] = a;
    }

    static class Edge implements Comparable<Edge>{
        int start, end, cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }

        public String toString(){
            return start + ","+ end+ ","+cost;
        }
    }
}

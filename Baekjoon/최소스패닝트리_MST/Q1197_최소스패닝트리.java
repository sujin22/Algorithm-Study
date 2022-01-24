package 최소스패닝트리_MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1197_최소스패닝트리 {
    /*[골드4]Q1197 - 최소 스패닝 트리*/
    static int V, E;
    static PriorityQueue<Edge> pq;
    static int[] parent;
    static int result, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());   //정점의 개수
        E = Integer.parseInt(st.nextToken());   //간선의 개수

        pq = new PriorityQueue<>();  //Edge를 저장하는 우선순위 큐
        for(int i = 0; i<E;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            pq.add(new Edge(start, end, cost));
        }
        parent = new int[V+1];


        result = 0; //최소스패닝트리의 가중치 합
        cnt = 0; //몇개의 정점을 지나왔는지 세는 변수

        kruskal();

        System.out.println(result);
    }

    static void kruskal(){
        //sets 배열 초기화
        for(int i = 1; i<V+1;i++){
            parent[i] = i;
        }
        while(!pq.isEmpty()){
            Edge e = pq.poll();
            if(find(parent, e.start) != find(parent, e.end)){
                //edge의 시작점과 끝점이 같은 집합이 아니라면(연결되어있지 않다면) union한다(연결한다).
                result += e.cost; //결과값에 가중치 더하기
                cnt++;  //체크한 정점의 개수

                if(cnt == V-1)    return;   //정점을 다 연결했으면 끝낸다.(MST 간선개수 == 정점개수 -1)

                union(parent, e.start, e.end);
            }
        }

    }
    //Union-find 연산
    public static int find(int[] arr, int a){
        //배열 arr에서 원소 a의 부모를 찾아 반환하는 함수.
        if(arr[a]==a)   return a;
        else return find(arr, arr[a]);
    }
    public static void union(int[]arr, int a, int b){
        //원소 a가 속한 집합과, 원소 b가 속한 집합을 union하는 함수
        int aParent = find(arr, a); //a의 루트 노드 찾기
        int bParent = find(arr,b);  //b의 루트 노드 찾기

        //더 작은 정점으로 루트를 바꿔줌
        if(aParent > bParent){
            arr[aParent] = bParent;
        }
        else{
            arr[bParent] = aParent;
        }

    }
    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        long cost;

        public Edge(int start, int end, long cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        //cost 기준 오름차순 정렬
        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}

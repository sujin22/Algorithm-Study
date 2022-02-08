package 최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q16118_달빛여우 {
    /*[골드1]Q16118 - 달빛 여우*/

    //홀수일 경우 소수점계산을 피하기 위해, 여우를 2배
    //늑대를 각 1배, 4배로 계산한다

    static int n, m;
    static long[] dp_fox;
    static long[][] dp_wolf;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 0; i<n+1;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i<m;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, cost));
            graph[end].add(new Node(start, cost));
        }

        dp_fox = new long[n+1];
        dp_wolf = new long[n+1][2]; //fast속도로 도달한 cost, slow속도로 도달한 cost 따로 관리
        Arrays.fill(dp_fox, Long.MAX_VALUE);
        for(int i = 0; i<n+1;i++){
            Arrays.fill(dp_wolf[i], Long.MAX_VALUE);
        }
        dp_fox[1] = 0;
        dp_wolf[1][0] = 0;

        dijkstra_fox();
        dijkstra_wolf();

        //여우가 더 작은 경우를 세어 출력함
        int cnt = 0;
        for(int i = 2; i<n+1;i++){
            if(dp_fox[i]<Long.min(dp_wolf[i][0], dp_wolf[i][1])){
                cnt++;
//                System.out.println(i+", fox: "+dp_fox[i]+", wolf: "+dp_wolf[i][0]+ " "+dp_wolf[i][1]);
            }
        }
        System.out.println(cnt);

    }

    static void dijkstra_fox(){
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(1,0));

        while(!q.isEmpty()){
            Node now = q.poll();

            if(dp_fox[now.index]< now.cost) continue;

            for(int i = 0; i<graph[now.index].size();i++){
                Node next = graph[now.index].get(i);
                int nextIndex = next.index;
                long nextCost = now.cost + next.cost*2;//여우속도

                if(nextCost<dp_fox[nextIndex]){
                    dp_fox[nextIndex] = nextCost;
                    q.offer(new Node(nextIndex, nextCost));
                }
            }
        }
    }

    static void dijkstra_wolf(){
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(1,0, 0));

        while(!q.isEmpty()){
            Node now = q.poll();

            //빠른 속도로 접근한 cost인지, 느린 속도로 접근한 cost인지 구별해 검사
            if(dp_wolf[now.index][now.wasFast]< now.cost)
                continue;

            for(Node next: graph[now.index]){
                long nextCost = dp_wolf[now.index][now.wasFast]+
                        ((now.wasFast==0)? next.cost:next.cost*4);
                //이전에 느리게 접근했을 경우 이번에는 빠르게 갈테니 1배,
                // 빠르게 접근했을 경우 4배한 가중치 더하기

                int nextState = 1- now.wasFast;// 1<->0 전환
                if(nextCost < dp_wolf[next.index][nextState]){
                    dp_wolf[next.index][nextState] = nextCost;
                    q.offer(new Node(next.index, nextCost, nextState));
                }
            }
        }
    }
    static class Node implements Comparable<Node>{
        int index;
        long cost;
        int wasFast;//달린 속도가 slow면 0, fast면 1

        public Node(int index, long cost) {
            this.index = index;
            this.cost = cost;
        }

        public Node(int index, long cost, int wasFast) {
            this.index = index;
            this.cost = cost;
            this.wasFast = wasFast;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}

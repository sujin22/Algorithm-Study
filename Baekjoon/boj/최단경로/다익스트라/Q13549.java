package boj.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*숨바꼭질3*/
public class Q13549 {
    static int N, K;
    final static int MAX_POS = 100001;  //위치 범위가 0~ 100000이라서
    static int[] spendTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        spendTime = new int[MAX_POS];   //최단소요시간을 저장할 배열
        Arrays.fill(spendTime, Integer.MAX_VALUE);
        spendTime[N] = 0;   //시작점을 0으로

        dijkstra();

        System.out.println(spendTime[K]);

    }
    public static void dijkstra(){
        PriorityQueue<Edge> q = new PriorityQueue<>();
        q.add(new Edge(N, 0));
        while(!q.isEmpty()){
            Edge e = q.poll();
            int pos = e.pos;
            int cost = e.cost;
            if(spendTime[pos] < cost)   continue;

            //x+1로 이동하는 경우
            int nextPos = pos + 1;
            int nextCost = cost + 1;
            if(nextPos < MAX_POS && spendTime[nextPos] > nextCost){
                spendTime[nextPos] = nextCost;
                q.add(new Edge(nextPos, nextCost));
            }

            //x-1로 이동하는 경우
            nextPos = pos-1;
            nextCost = cost +1;
            if(nextPos>=0 && nextPos < MAX_POS && spendTime[nextPos] > nextCost){
                spendTime[nextPos] = nextCost;
                q.add(new Edge(nextPos, nextCost));
            }

            //2x로 이동하는 경우
            nextPos = pos*2;
            nextCost = cost;
            if(nextPos < MAX_POS && spendTime[nextPos] > nextCost){
                spendTime[nextPos] = nextCost;
                q.add(new Edge(nextPos, nextCost));
            }
        }
    }

    public static class Edge implements Comparable<Edge>{
        int pos;
        int cost;

        public Edge(int pos, int cost) {
            this.pos = pos;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }
}

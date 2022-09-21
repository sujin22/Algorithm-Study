package boj.최단경로.벨만포드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q11657 {
    /*[골드4]Q11657 타임머신*/
    static final long INF = Long.MAX_VALUE;
    static int N, M;
    static Edge[] edges;
    static long [] dist;
    //예를 들어 음의간선 -10000이 엄청 많을 경우, underflow가 발생하므로 출력초과 발생한다.
    // 따라서 거리를 long형으로 선언하여야 한다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   //정점 개수
        M = Integer.parseInt(st.nextToken());   //간선 개수

        edges = new Edge[M];
        dist = new long[N+1];
        Arrays.fill(dist, INF);

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(start, end, weight);
        }


        if(!bellman_ford()){
            System.out.println(-1); //음의 사이클 존재
        }else{
            for(int i = 2; i<N+1;i++){
                if(dist[i]==INF) System.out.println(-1);    //최단거리 존재하지 않음
                else System.out.println(dist[i]);   //최단거리 출력
            }
        }
    }

    static boolean bellman_ford(){
        dist[1] = 0;

        for(int i = 1; i<N+1;i++){
            for(int j = 0; j<M; j++){
                int start = edges[j].start;
                int end = edges[j].end;
                long d = edges[j].dist;

                long nextDist = dist[start] + d;

                if(dist[start]==INF) continue; //무한대면 갱신하지 않음
                if(dist[end] > nextDist){    //현재 저장된 값보다 새로운 값이 더 작으면 갱신
                    if(i==N){   //다 돌고 난 마지막에 한번 더 돌았는데 값이 바뀌면 음의 사이클 존재하는 것
                        return false;
                    }
                    dist[end] = nextDist;
                }
            }
        }
        return true;
    }
    static class Edge{
        int start, end;
        long dist;

        public Edge(int start, int end, long dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }
    }
}

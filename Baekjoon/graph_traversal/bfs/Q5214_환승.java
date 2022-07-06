package graph_traversal.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q5214_환승 {
    /*[골드2]Q5214 - 환승*/
    //지하철 노선도에서 착안함
    //튜브에 무슨 역이 있는지, 역에 무슨 튜브가 연결되어있는지 따로 관리
    //visited를 역에만 해줬었는데 시간 초과가 나서, 튜브에도 방문처리 해주니까 해결됨
    static int N, K, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] stations = new ArrayList[N+1]; //역에 연결된 튜브 저장
        ArrayList<Integer>[] tubes = new ArrayList[M];  //튜브에 담긴 역 저장

        for(int i = 0; i<N+1; i++){
            stations[i] = new ArrayList<>();
        }

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            tubes[i] = new ArrayList<>();
            for(int j = 0; j<K ; j++){
                int s = Integer.parseInt(st.nextToken());
                stations[s].add(i);
                tubes[i].add(s);
            }
        }

        boolean [] visited = new boolean[N+1];
        boolean[] tubeVisited = new boolean[M];
        Queue<Station> q = new ArrayDeque<>();
        q.offer(new Station(1, 1));
        visited[1] = true;
        while(!q.isEmpty()){
            Station now = q.poll();
            if(now.n == N){
                System.out.println(now.depth);
                System.exit(0);
            }

            for(int tube: stations[now.n]){
                if(!tubeVisited[tube]){
                    tubeVisited[tube] = true;
                    for(int next : tubes[tube]){
                        if(!visited[next]){
                            visited[next] = true;
                            q.offer(new Station(next, now.depth+1));
                        }
                    }
                }
            }
        }
        System.out.println(-1);

    }
    public static class Station{
        int n;
        int depth;

        public Station(int n, int depth) {
            this.n = n;
            this.depth = depth;
        }
    }
}

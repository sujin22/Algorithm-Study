package graph_traversal.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1726_로봇 {
    /*[골드3] Q1726_로봇*/
    /* [요약]
    bfs로 시뮬레이션 했다.
    현재 방향으로 1,2,3만큼 이동하는 경우 / left 또는 right으로 이동하는 경우
    이렇게 5가지 경우를 검사하여 큐에 집어넣고, target에 도달할때까지 반복했다.
    주의할 점은 1,2,3만큼 이동할 때, 중간이 막혀있으면 그 위치로 이동할 수 없다는 점이다.
     */

    //동, 서, 남, 북
    static int[] di = {0, 0, 1, -1};
    static int[] dj = {1, -1, 0, 0};
    static final int DIR_E = 0;
    static final int DIR_W = 1;
    static final int DIR_S = 2;
    static final int DIR_N = 3;

    static int N, M;
    static boolean [][] map;
    static boolean [][][]visited;
    static Unit start, target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new boolean[M+1][N+1];
        visited = new boolean[M+1][N+1][4];

        for(int i = 1; i<M+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<N+1; j++){
                //True이면 갈 수 없는 곳
                map[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }
        st = new StringTokenizer(br.readLine());
        start = new Unit(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())-1);

        st = new StringTokenizer(br.readLine());
        target = new Unit(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())-1);

        //bfs
        Queue<Unit> q = new ArrayDeque<>();
        q.offer(start);
        visited[start.i][start.j][start.dir] = true;
        int result = Integer.MAX_VALUE;
        while(!q.isEmpty()){
            Unit now = q.poll();
            if(now.i== target.i && now.j == target.j && now.dir==target.dir){
                result = Integer.min(result, now.depth);
            }
            //Go 1~3
            for(int i = 1; i<4;i++){
                int nextI = now.i + di[now.dir]*i;
                int nextJ = now.j + dj[now.dir]*i;
                if(nextI>0 && nextI<=M && nextJ>0 && nextJ<=N){
                    if(map[nextI][nextJ])   break;  //가는길이 막혀있으면 못감
                    if(!visited[nextI][nextJ][now.dir]){
                        q.add(new Unit(nextI, nextJ, now.dir, now.depth+1));
                        visited[nextI][nextJ][now.dir] = true;
                    }
                }
            }

            //Turn Left, Right
            switch(now.dir){
                case DIR_E:
                case DIR_W:
                    if(!visited[now.i][now.j][DIR_S]){
                        q.add(new Unit(now.i, now.j, DIR_S, now.depth+1));
                        visited[now.i][now.j][DIR_S] = true;
                    }
                    if(!visited[now.i][now.j][DIR_N]){
                        q.add(new Unit(now.i, now.j, DIR_N, now.depth+1));
                        visited[now.i][now.j][DIR_N] = true;
                    }
                    break;
                case DIR_S:
                case DIR_N:
                    if(!visited[now.i][now.j][DIR_W]){
                        q.add(new Unit(now.i, now.j, DIR_W, now.depth+1));
                        visited[now.i][now.j][DIR_W] = true;
                    }
                    if(!visited[now.i][now.j][DIR_E]){
                        q.add(new Unit(now.i, now.j, DIR_E, now.depth+1));
                        visited[now.i][now.j][DIR_E] = true;
                    }
                    break;
            }
        }
        System.out.println(result);
    }
    static class Unit{
        int i,j,dir,depth;

        public Unit(int i, int j, int dir) {
            this.i = i;
            this.j = j;
            this.dir = dir;
        }

        public Unit(int i, int j, int dir, int depth) {
            this.i = i;
            this.j = j;
            this.dir = dir;
            this.depth = depth;
        }
    }
}

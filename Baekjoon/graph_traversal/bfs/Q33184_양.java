package graph_traversal.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q33184_양 {
    /*[실버1]Q33184 - 양*/
    //bfs로 풀이할거고, 상하좌우 탐색해 양 개수, 늑대 개수를 센다.
    // 각 bfs가 끝나면 그 영역의 양,늑대 크기를 비교한 뒤 적절한 양, 늑대 수를 결과값에 더해준다.
    static int dx[] = {-1,0,0,1};
    static int dy[] = {0,1,-1,0};

    static boolean[][] visited;
    static char[][] graph;
    static int R,C;
    static int sheep, wolf;
    static int sheep_area, wolf_area;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        visited = new boolean[R][C];
        graph = new char[R][C];

        for(int i = 0; i<R;i++){
            String str = br.readLine();
            for(int j = 0; j<C; j++){
                graph[i][j] = str.charAt(j);
                //울타리 위치일 경우 방문처리한다.
                if(graph[i][j] == '#'){
                    visited[i][j] = true;
                }
            }
        }
        sheep = wolf = 0;
        for(int i = 0; i<R;i++){
            for(int j = 0; j<C;j++){
                if(visited[i][j]) continue;
                sheep_area = wolf_area = 0;
                bfs(i,j);
                if(sheep_area>wolf_area){
                    sheep+=sheep_area;
                }else{
                    wolf+=wolf_area;
                }
            }
        }
        System.out.println(sheep+" "+wolf);
    }
    static void bfs(int sx, int sy){

        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(sx,sy));
        visited[sx][sy] = true;

        while(!q.isEmpty()){
            Pos now = q.poll();
            if(graph[now.i][now.j] == 'o')  sheep_area++;
            if(graph[now.i][now.j] == 'v')  wolf_area++;

            for(int i = 0; i<4;i++){
                int nextI = now.i+dx[i];
                int nextJ = now.j+dy[i];

                if(nextI>=0 && nextI<R &&nextJ>=0 && nextJ<C
                        && !visited[nextI][nextJ]){

                    q.offer(new Pos(nextI, nextJ));
                    visited[nextI][nextJ]= true;
                }
            }
        }

    }
    static class Pos{
        int i,j;

        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}

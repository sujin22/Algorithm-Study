package graph_traversal.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2206_벽부수고이동하기 {
    /*[골드4]Q2206 - 벽 부수고 이동하기*/
    static int[] di = {0, 0, -1, 1};
    static int[] dj = {-1, 1, 0, 0};

    static int n,m;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        System.out.println(bfs());
    }

    static int bfs(){
        Queue<Pos> q = new ArrayDeque<>();
        q.add(new Pos(0, 0, 1, false));

        boolean[][][] visited = new boolean[n][m][2];   //벽 부수지 않고 도달한 경우, 부수고 도달한 경우

        while (!q.isEmpty()) {
            Pos now = q.poll();

            //목적지 도착하면 출력
            if (now.i == n - 1 && now.j == m - 1) {
                return now.cnt;
            }

            //상하좌우 체크
            for (int d = 0; d < 4; d++) {
                int nextI = now.i + di[d];
                int nextJ = now.j + dj[d];

                if(nextI<0 || nextI>=n || nextJ<0 || nextJ>=m) continue;

                int nextCnt = now.cnt+1;

                if(map[nextI][nextJ]=='0'){ // 벽 아니면
                    if(!now.destroyed && !visited[nextI][nextJ][0]) {
                        //벽 부순적 없을 경우
                        q.add(new Pos(nextI, nextJ, nextCnt, false));
                        visited[nextI][nextJ][0] = true;
                    } else if(now.destroyed && !visited[nextI][nextJ][1]){
                        //벽 부순 적 있을 경우
                        q.add(new Pos(nextI, nextJ, nextCnt, true));
                        visited[nextI][nextJ][1] = true;
                    }
                }else if(map[nextI][nextJ]=='1'){ //벽이면
                    //벽 부순 적 없으면 부수고 이동
                    if(!now.destroyed){
                        q.add(new Pos(nextI, nextJ, nextCnt, true));
                        visited[nextI][nextJ][1] = true;
                    }
                }
            }
        }
        return -1;
    }

    static class Pos {
        int i;
        int j;
        int cnt;
        boolean destroyed;

        public Pos(int i, int j, int cnt, boolean d) {
            this.i = i;
            this.j = j;
            this.cnt = cnt;
            this.destroyed = d;
        }
    }
}

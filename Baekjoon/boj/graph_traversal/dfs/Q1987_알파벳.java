package boj.graph_traversal.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1987_알파벳 {
    /*[골드4]Q1987 - 알파벳*/
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static int R, C;
    static char[][] arr;
    static boolean[][] visited;
    static boolean[] checked = new boolean[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        visited = new boolean[R][C];

        for(int i = 0 ;i<R;i++){
            String str = br.readLine();
            for(int j = 0; j<C; j++){
                arr[i][j] = str.charAt(j);
            }
        }
//        System.out.println(bfs());
        visited[0][0] = true;
        checked[arr[0][0]-'A'] = true;
        System.out.println(dfs(0,0,1));
    }

    static int dfs(int nowX, int nowY, int depth){
        int max = depth;
        for(int i = 0; i<4; i++) {
            int nextX = nowX + dx[i];
            int nextY = nowY + dy[i];


            if (nextX < 0 || nextX >= R || nextY < 0 || nextY >= C) {
                continue;
            }
            if (!visited[nextX][nextY] && !checked[arr[nextX][nextY] - 'A']) {
                visited[nextX][nextY] = true;
                checked[arr[nextX][nextY] - 'A'] = true;
                max = Integer.max(max, dfs(nextX, nextY, depth + 1));
                visited[nextX][nextY] = false;
                checked[arr[nextX][nextY] - 'A'] = false;
            }
        }
        return max;
    }
}

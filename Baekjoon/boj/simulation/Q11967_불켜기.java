package boj.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q11967_불켜기 {
    /*[골드3] Q11967 - 불켜기*/
    //Pos리스트 가지는 배열 만들고, dfs로 켜기
    //불 켜진 여부 검사하는 light boolean 배열
    //불 켜질때마다 total 증가
    static int[] dx = new int[]{0,0,1,-1};
    static int[] dy = new int[]{1,-1,0,0};
    static int N, M;
    static ArrayList<Pos>[][] list;
    static boolean[][] light, visited;
    static int totalCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        light = new boolean[N+1][N+1];
        visited = new boolean[N+1][N+1];

        list = new ArrayList[N+1][N+1];
        for(int i =0; i<N+1;i++){
            for(int j = 0; j<N+1;j++){
                list[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());

            list[x][y].add(new Pos(sx,sy));
        }

        visited[1][1] = true;
        light[1][1] = true;
        totalCnt++;
        dfs(new Pos(1,1));

        System.out.println(totalCnt);
    }

    private static void dfs(Pos now){
        ArrayList<Pos> switches = list[now.x][now.y];
        for(Pos p: switches){
            //불이 꺼져있는 방이면 스위치를 켠다.
            if(!light[p.x][p.y]){
                totalCnt++;
                light[p.x][p.y] = true;
                //불 켠 곳 방문할 수 있는지 검사(인접한 곳에 visited가 true인 곳이 있는지)
                for(int i = 0; i<4; i++){
                    Pos next = new Pos(p.x+dx[i], p.y+dy[i]);
                    if(next.x<=0 || next.x >=N+1 || next.y<=0 || next.y>=N+1)   continue;
                    if(visited[next.x][next.y]){
                        visited[p.x][p.y] = true;
                        dfs(p);
                    }
                }
            }
        }
        //인접한 방으로 이동한다
        for(int i = 0; i<4; i++){
            Pos next = new Pos(now.x+dx[i], now.y+dy[i]);
            //범위를 넘어가거나, 방문했던 적이 있거나, 불이 꺼져있으면 continue
            //(방문하지 않은, 불 켜진, 인접한 방)
            if(next.x<=0 || next.x >=N+1 || next.y<=0 || next.y>=N+1 || visited[next.x][next.y]|| !light[next.x][next.y]){
                continue;
            }
            visited[next.x][next.y] = true;
            dfs(next);
        }
    }

    private static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

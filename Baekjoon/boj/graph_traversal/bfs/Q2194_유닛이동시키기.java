package boj.graph_traversal.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2194_유닛이동시키기 {
    /*[골드5]Q2194 - 유닛 이동시키기*/
    static int N,M,A,B,K;
    static Pos start, end;
    static int[][] visited;
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,1,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   //행 개수
        M = Integer.parseInt(st.nextToken());   //열 개수
        A = Integer.parseInt(st.nextToken());   //유닛 행 개수
        B = Integer.parseInt(st.nextToken());   //유닛 열 개수
        K = Integer.parseInt(st.nextToken());   //장애물 개수

        visited = new int[N+1][M+1];

        //장애물 입력받기
        for(int i = 0; i<K;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            visited[x][y] = -1;
        }

        start = new Pos();
        end = new Pos();
        //시작점 입력
        st = new StringTokenizer(br.readLine());
        start.x = Integer.parseInt(st.nextToken());
        start.y = Integer.parseInt(st.nextToken());

        //끝점 입력
        st = new StringTokenizer(br.readLine());
        end.x = Integer.parseInt(st.nextToken());
        end.y = Integer.parseInt(st.nextToken());

        bfs();
    }
    static void bfs(){
        Queue<Pos> q = new ArrayDeque<>();
        q.add(start);
        visited[start.x][start.y] = 1;
        int depth = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int s = 0; s<size; s++){
                Pos now = q.poll();
                //끝점을 만나면 depth 출력
                if(now.equals(end)){
                    System.out.println(depth);
                    return;
                }
                for(int i = 0; i<4;i++){
                    int nextX = now.x + dx[i];
                    int nextY = now.y + dy[i];
                    if(isPossible(nextX, nextY)){
                        q.add(new Pos(nextX, nextY));
                        visited[nextX][nextY] = 1;
                    }
                }
            }
            //한번 이동할떄마다 depth 증가
            depth++;
        }
        //끝점을 만나지 못했으면 이동이 불가능한 것
        System.out.println(-1);
    }
    static boolean isPossible(int x, int y){
        //범위를 벗어나거나, 방문한 적 있는 곳이면 false
        if(!inRange(x,y) || visited[x][y] == 1)    return false;

        for(int i = 0; i<A; i++){
            for(int j = 0; j<B;j++){
                int checkX = x + i;
                int checkY = y + j;
                //하나라도 범위를 벗어나거나 장애물이면 false 리턴
                if(!inRange(checkX, checkY)){
                    return false;
                }
                if(visited[checkX][checkY] == -1){
                    return false;
                }
            }
        }
        return true;
    }
    static boolean inRange(int x, int y) {
        if(x <=0 || x>N || y<=0 || y>M) return false;
        else return true;
    }

    static class Pos{
        int x,y;

        public Pos() {
        }

        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
        public boolean equals(Pos p){
            if(p.x == this.x && p.y == this.y) return true;
            else return false;
        }
    }
}

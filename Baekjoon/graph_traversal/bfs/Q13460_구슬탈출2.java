package graph_traversal.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q13460_구슬탈출2 {
    /*[골드1]Q13460 - 구슬탈출2*/
    static int dx[] = {0,0, -1,1};
    static int dy[] = {-1,1, 0,0};

    static final int LEFT = 0;
    static final int RIGHT = 1;
    static final int UP = 2;
    static final int DOWN = 3;

    static int n, m;
    static char[][] board;
    static Marble marble = new Marble();    //초기 구슬 위치 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];

        for(int i =0; i<n;i++){
            String str = br.readLine();
            for(int j = 0; j<m;j++){
                board[i][j] = str.charAt(j);
                if(board[i][j] == 'R'){
                    marble.red = new Pos(i, j);
                    board[i][j] ='.';
                }else if(board[i][j] == 'B'){
                    marble.blue = new Pos(i, j);
                    board[i][j] ='.';
                }
            }
        }
        marble.depth = 0;
        System.out.println(bfs());

    }

    static int bfs(){
        Queue<Marble> q = new ArrayDeque<>();
        q.offer(marble);

        while(!q.isEmpty()){
            Marble now = q.poll();
            //10번만에 탈출못하면 -1 리턴
            if(now.depth >10)   return -1;
            //상하좌우 검사
            for(int i = 0; i<4; i++){
                Pos red = new Pos(now.red.i, now.red.j);
                Pos blue = new Pos(now.blue.i, now.blue.j);
                //빨간색 이동
                int redCnt = rollOver(i, red.i, red.j);
                //파란색 이동
                int blueCnt = rollOver(i, blue.i, blue.j);

                //좌우이동일경우
                if(i == LEFT || i== RIGHT){
                    //도착지점 계산
                    red.j += dy[i]*redCnt;
                    blue.j += dy[i]*blueCnt;
                    //끝까지 굴러가는 거니까, red와 같은 위치더라도 blue가 구멍에 빠지면 컨티뉴
                    if(board[blue.i][blue.j] == 'O'){
                        continue;
                    }
                    //도착지점 같은지 검사해서, 같다면 위치 조정
                    if(red.i == blue.i && red.j== blue.j) {
                        //더 많이 이동한 구슬 j좌표 1감소
                        if (redCnt > blueCnt) red.j -= dy[i];
                        else blue.j -= dy[i];
                    }
                }
                //상하이동일경우
                else{
                    //도착지점 계산
                    red.i += dx[i]*redCnt;
                    blue.i += dx[i]*blueCnt;
                    //도착지점 같은지 검사해서, 같다면 위치 조정
                    ////끝까지 굴러가는 거니까, red와 같은 위치더라도 blue가 구멍에 빠지면 컨티뉴
                    if(board[blue.i][blue.j] == 'O'){
                        continue;
                    }
                    if(red.i == blue.i && red.j== blue.j) {
                        //더 많이 이동한 구슬 i좌표 1감소
                        if (redCnt > blueCnt) red.i -= dx[i];
                        else blue.i -= dx[i];
                    }

                }

                //빨간 구슬이 구멍에 빠지면 성공, depth 출력
                if(board[red.i][red.j] == 'O' && now.depth+1 <=10) return now.depth+1;

                //방문하지 않은 위치라면 q에 넣기
                q.add(new Marble(red, blue, now.depth+1));
            }
        }
        return -1;
    }
    static int rollOver(int ind, int i, int j){
        //이동
        int cnt = 0;
        while(true){
            i += dx[ind];
            j += dy[ind];
            cnt++;

            if(board[i][j] == '#'){
                return cnt-1;
            }
            if(board[i][j] =='O'){
                break;
            }
        }
        return cnt;
    }

    static class Marble{
        Pos red, blue;
        int depth;

        public Marble() {
        }

        public Marble(Pos red, Pos blue, int depth) {
            this.red = red;
            this.blue = blue;
            this.depth = depth;
        }
    }
    static class Pos{
        int i,j;

        public Pos() {
        }

        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}

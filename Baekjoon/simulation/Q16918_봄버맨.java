package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q16918_봄버맨 {
    /*[실버1]Q16918 - 봄버맨*/
    static int dx[] = new int[]{0,0,1,-1};
    static int dy[] = new int[]{1,-1,0,0};
    static int R,C,N;
    static Unit[][] map;
    static int time = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new Unit[R][C];

        for(int i = 0; i < R; i++){
            String str = br.readLine();
            for(int j = 0; j<C; j++){
                map[i][j] = new Unit(str.charAt(j), time);
            }
        }
        time = 1;
        while(time!=N+1){
            //홀수일 경우: 터질 애들 터짐
            if(time%2 != 0){
                Queue<Bomb> explode = new ArrayDeque<>();
                //전체를 돌며 이번에 터질 폭탄의 위치 큐에 저장
                // Reason: 한번에 터트려야 하므로(이번에 터질 폭탄이 옆 폭탄때문에 사라지면 안됨) 큐에 저장했다가 터트림
                for(int i = 0; i<R; i++){
                    for(int j = 0; j<C; j++){
                        //그 위치가 폭탄이 O일 때만 큐에 추가함 (O가 아니면 근처 폭탄이 터질 때 없어진 것임)
                        if(map[i][j].state == 'O' && map[i][j].time+3 == time)    explode.offer(new Bomb(i,j));
                    }
                }
                while(!explode.isEmpty()){
                    Bomb now = explode.poll();
                    //상하좌우와 본인을 .으로 바꿈
                    map[now.i][now.j].state = '.';
                    for(int i = 0; i<4; i++){
                        if(now.i + dx[i]>=0 && now.i + dx[i]<R && now.j+dy[i]>=0 && now.j+dy[i]<C){
                            map[now.i+dx[i]][now.j+dy[i]].state = '.';
                        }
                    }
                }
            }
            //짝수일 경우: 전체를 돌며 .인 부분에 폭탄 추가
            else{
                for(int i = 0; i<R; i++){
                    for(int j=0;j<C;j++){
                        if(map[i][j].state == '.'){
                            map[i][j].state = 'O';
                            map[i][j].time = time;
                        }
                    }
                }
            }
            time++;
        }
        for(int i = 0; i<R;i++){
            for(int j = 0; j<C; j++){
                System.out.print(map[i][j].state);
            }
            System.out.println();
        }


    }
    static class Unit{
        char state;
        int time;

        public Unit(char state, int time) {
            this.state = state;
            this.time = time;
        }
    }
    static class Bomb{
        int i, j;

        public Bomb(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}

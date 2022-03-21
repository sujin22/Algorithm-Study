package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q17790_새로운게임 {
    /*[골드2] Q17780 - 새로운 게임*/
    static final int dx[] = {0, 0, 0, -1, 1};
    static final int dy[] = {0, 1, -1, 0, 0};
    static final int RIGHT = 1;
    static final int LEFT = 2;
    static final int UP = 3;
    static final int DOWN = 4;

    static final int WHITE = 0;
    static final int RED = 1;
    static final int BLUE = 2;

    static Unit[][] board;
    static Horse[] horses;
    static int n, k;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new Unit[n+1][n+1]; //체스판
        for(int i = 1; i< n+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j< n+1; j++){
                //각 칸에 색상 저장
                board[i][j] = new Unit(Integer.parseInt(st.nextToken()));
            }
        }
        horses = new Horse[k+1]; //말 방향 저장 배열
        for(int i = 1; i<k+1;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y].horses.add(i);    //체스판에 말 놓기

            int direction = Integer.parseInt(st.nextToken());
            horses[i] = new Horse(x,y,direction);//방향 저장
        }
        System.out.println(game());
    }

    static int game(){
        int cnt = 0;
        while(true){
            //1000이상이면 -1 리턴
            if(cnt>1000) return -1;
            //말들을 돌며 체크
            for(int i = 1; i<k+1;i++){
                int posX = horses[i].i;
                int posY = horses[i].j;
                int dir = horses[i].dir;

                //제일 아래 말이 아니면 이동안함
                if(board[posX][posY].horses.get(0)!= i) continue;

                int nextX = posX+dx[dir];
                int nextY = posY+dy[dir];
                //다음 칸이 체스판 범위 넘어갈 경우
                if(!isInArea(nextX,nextY)){
                    switch(horses[i].dir){
                        case LEFT: horses[i].dir = RIGHT; break;
                        case RIGHT: horses[i].dir = LEFT; break;
                        case UP: horses[i].dir = DOWN; break;
                        case DOWN: horses[i].dir = UP; break;
                    }
                    int tmpX = posX + dx[horses[i].dir];
                    int tmpY = posY + dy[horses[i].dir];
                    if(isInArea(tmpX, tmpY) && board[tmpX][tmpY].color != BLUE){
                        i--;
                    }
                    continue;
                }
                //다음 칸이 체스판 범위 넘어가지 않는경우, 이동할 칸의 색에 따라 다르게 연산해줌
                switch(board[nextX][nextY].color){
                    //하얀색일 경우
                    case WHITE:
                        //그냥 이동한다.
                        for(int h:board[posX][posY].horses){
                            board[nextX][nextY].horses.add(h);
                            horses[h].i = nextX;
                            horses[h].j = nextY;
                        }
                        board[posX][posY].horses.clear();
                        break;
                    //빨간색일 경우
                    case RED:
                        Collections.reverse(board[posX][posY].horses);  //순서를 바꿔준다.
                        //다음 칸으로 이동한다.
                        for(int h:board[posX][posY].horses){
                            board[nextX][nextY].horses.add(h);
                            horses[h].i = nextX;
                            horses[h].j = nextY;
                        }
                        board[posX][posY].horses.clear();
                        break;
                    //파란 색일 경우
                    case BLUE:
                        //이동은 하지 않고 방향 바꾸기
                        switch(horses[i].dir){
                            case LEFT: horses[i].dir = RIGHT; break;
                            case RIGHT: horses[i].dir = LEFT; break;
                            case UP: horses[i].dir = DOWN; break;
                            case DOWN: horses[i].dir = UP; break;
                        }
                        //바꾸고 나서 갈 방향이 범위 밖이거나, 파란색이면 이동하지 않고 멈춤
                        //바꾸고 나서 갈 방향이 그 외 다른 색들이면 이동해야함. 따라서 반복자인 i를 1 감소시켜서, 현재 말에 대해 한번 더 연산해줌
                        int tmpX = posX + dx[horses[i].dir];
                        int tmpY = posY + dy[horses[i].dir];
                        if(isInArea(tmpX, tmpY) && board[tmpX][tmpY].color != BLUE){
                            i--;
                        }
                        break;
                }
                //쌓인 말의 개수가 4개 이상일 경우 cnt+1(아직 지금 연산한 회차를 안더해줬으니 1 더함) 리턴
                if(board[nextX][nextY].horses.size()>=4)
                    return cnt+1;
            }
            cnt++;
        }

    }
    static boolean isInArea(int x, int y){
        if(x<1 || x>n || y<1 || y>n)  return false;
        else   return true;
    }
    static class Horse{
        int i,j,dir;

        public Horse(int i, int j, int dir) {
            this.i = i;
            this.j = j;
            this.dir = dir;
        }
    }
    //각 칸의 정보 저장
    static class Unit{
        int color;
        ArrayList<Integer> horses;

        public Unit(int color) {
            this.color = color;
            horses = new ArrayList<>();
        }
    }
}

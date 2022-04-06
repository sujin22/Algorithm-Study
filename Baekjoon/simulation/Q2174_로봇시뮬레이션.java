package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2174_로봇시뮬레이션 {
    /*[골드5] Q2174 - 로봇 시뮬레이션*/
    static int A, B, N, M;
    static char[] left_rotate = new char[]{'N','W','S','E'};
    static char[] right_rotate = new char[]{'N','E','S','W'};

    static int [] dx = new int[]{0,0,-1,1}; //N,S,W,E 순서
    static int [] dy = new int[]{-1,1,0,0};


    static int[][] board;   //로봇이 위치하면 로봇 번호 저장, 아니면 0저장
    static Robot[] robots;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        board = new int[B+1][A+1]; //1부터 사용

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        robots = new Robot[N+1];    //1부터 사용
        M = Integer.parseInt(st.nextToken());

        //로봇의 초기 위치
        for(int i = 1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = (B+1)-Integer.parseInt(st.nextToken()); //뒤집어서 저장
            char dir = st.nextToken().charAt(0);
            robots[i] = new Robot(x,y,dir);
            board[y][x] = i;
        }

        for(int i = 0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int robotNum = Integer.parseInt(st.nextToken());
            char order = st.nextToken().charAt(0);
            int times = Integer.parseInt(st.nextToken());

            //times만큼 반복
            for(int t = 0; t<times; t++){
                char nowDir = robots[robotNum].dir;
                switch(order){
                    case 'L':
                        //다음 방향을 찾는다.
                        for(int dirIndex = 0; dirIndex<4; dirIndex++){
                            //현재 방향 찾아서 다음 인덱스로 바꿈
                            if(left_rotate[dirIndex] == nowDir){
                                dirIndex = (dirIndex+1)%4;
                                robots[robotNum].dir = left_rotate[dirIndex];
                                break;
                            }
                        }
                        break;

                    case 'R':
                        //다음 방향을 찾는다.
                        for(int dirIndex = 0; dirIndex<4; dirIndex++){
                            if(right_rotate[dirIndex] == nowDir){
                                dirIndex = (dirIndex+1)%4;
                                robots[robotNum].dir = right_rotate[dirIndex];
                                break;
                            }
                        }
                        break;

                    case 'F':
                        //한칸 앞으로 이동했을 때 좌표 구하기
                        int fowardIndex = -1;
                        switch(nowDir){
                            case 'N':
                                fowardIndex = 0;
                                break;
                            case 'S':
                                fowardIndex = 1;
                                break;
                            case 'W':
                                fowardIndex = 2;
                                break;
                            case 'E':
                                fowardIndex = 3;
                                break;
                        }
                        int nextX = robots[robotNum].x + dx[fowardIndex];
                        int nextY = robots[robotNum].y + dy[fowardIndex];

                        //벽과 충돌하는지(0 또는 A+1/B+1)
                        if(nextX==0 || nextX==A+1 || nextY ==0 || nextY == B+1){
                            System.out.println("Robot "+robotNum+" crashes into the wall");
                            System.exit(0);
                        }
                        //로봇과 충돌하는지(board가 0이 아님)
                        if(board[nextY][nextX] != 0){
                            System.out.println("Robot "+robotNum+" crashes into robot "+board[nextY][nextX]);
                            System.exit(0);
                        }

                        board[robots[robotNum].y][robots[robotNum].x] = 0;
                        board[nextY][nextX] = robotNum;
                        robots[robotNum].x = nextX;
                        robots[robotNum].y = nextY;
//                        System.out.println("("+robotNum+") "+nextX+" "+nextY+" "+nowDir);
                        break;
                }
            }
        }
        System.out.println("OK");
    }
    static class Robot{
        int x, y;
        char dir;

        public Robot() {
        }

        public Robot(int x, int y, char dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}

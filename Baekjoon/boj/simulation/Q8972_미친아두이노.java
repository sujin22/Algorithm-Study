package boj.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q8972_미친아두이노 {
    static Pos player = new Pos(0,0);
    static Queue<Pos> crazyQueue = new ArrayDeque<>();

    static char[][] board;
    static int R, C;

    static int di[] = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int dj[] = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];

        for(int r = 0; r<R; r++){
            String str = br.readLine();
            board[r] = str.toCharArray();
            for(int c= 0; c<C; c++){
                if(board[r][c] == 'I'){
                    player = new Pos(r,c);
                }else if(board[r][c] == 'R'){
                    crazyQueue.add(new Pos(r,c));
                }
            }
        }
        String route = br.readLine();
        boolean aliveFlag = true;
        int cnt = 0;
        for(int ind = 0; ind<route.length(); ind++){
            cnt++;
            if(!movePlayer(route.charAt(ind) - '0')){
                aliveFlag = false;
                break;
            }
            if(!moveCrazy()) {
                aliveFlag = false;
                break;
            }
        }
        if(aliveFlag)   printBoard();
        else System.out.println("kraj "+ cnt);
    }
    public static boolean movePlayer(int dir){
        int nextI = player.i + di[dir];
        int nextJ = player.j + dj[dir];
        //미친 아두이노 만나면 false
        if(board[nextI][nextJ] == 'R') return false;

        if(dir != 5){
            board[nextI][nextJ] = 'I';
            board[player.i][player.j] = '.';
            player.i = nextI;
            player.j = nextJ;
        }
        return true;
    }
    public static boolean moveCrazy(){
        int[][] crazyNum = new int[R][C];

        while(!crazyQueue.isEmpty()){
            Pos crazy = crazyQueue.poll();

            int minDistance = Integer.MAX_VALUE;
            int dir = 0;
            for(int i = 0; i<10; i++){
                if(i == 5){
                    continue;
                }
                int nextI = crazy.i + di[i];
                int nextJ = crazy.j + dj[i];

                if(nextI < 0 || nextI>=R || nextJ < 0 || nextJ >= C){
                    continue;
                }

                int distance = Math.abs(player.i - nextI) + Math.abs(player.j - nextJ);

                if(distance < minDistance){
                    minDistance = distance;
                    dir = i;
                }
            }
            int nextI = crazy.i + di[dir];
            int nextJ = crazy.j + dj[dir];

            if(board[nextI][nextJ] == 'I') {
                return false;
            }
            board[crazy.i][crazy.j] = '.';

            crazyNum[nextI][nextJ]++;
        }

        //1인 것만 큐에 추가, 2면 충돌이어서 없어짐
        for(int i = 0; i<R; i++){
            for(int j = 0; j<C; j++){
                if(crazyNum[i][j] == 1){
                    crazyQueue.add(new Pos(i, j));
                    board[i][j] = 'R';
                }
            }
        }

        return true;
    }

    public static void printBoard(){
        for(int i = 0; i<R; i++){
            for(int j = 0; j<C; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    static class Pos{
        int i, j;

        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}

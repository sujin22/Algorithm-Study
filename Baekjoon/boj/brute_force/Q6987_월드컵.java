package boj.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q6987_월드컵 {
    static final int TC = 4;

    static final int WIN = 0;
    static final int DRAW = 1;
    static final int LOSE = 2;

    static int[][] game;
    static int[][] match = new int[15][2];  //모든 경기에 대한 경우의 수 저장
    static boolean isPossible;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int idx = 0;
        for(int i = 0; i<6;i++){
            for(int j = i+1;j<6;j++){
                match[idx][0] = i;
                match[idx][1] = j;
                idx++;
            }
        }

        loop:
        for(int i = 0; i<TC;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            game = new int[6][3];
            for(int j = 0; j<6;j++){
                int win = Integer.parseInt(st.nextToken());
                int draw = Integer.parseInt(st.nextToken());
                int lose = Integer.parseInt(st.nextToken());

                //"승+무+패"가 5가 아닌 경우 불가능
                if(win+draw+lose!=5) {
                    System.out.print("0 ");
                    continue loop;
                }

                game[j][WIN] = win;
                game[j][DRAW] = draw;
                game[j][LOSE] = lose;
            }
            isPossible = false;
            playGame(0);
            System.out.print(isPossible?"1 ":"0 ");
        }
    }
    public static void playGame(int round){
        if(round == 15){
            isPossible = true;
            return;
        }
        //이번 라운드의 경기 국가
        int c1 = match[round][0];
        int c2 = match[round][1];

        //c1 승리
        if(game[c1][WIN] >0 && game[c2][LOSE] > 0){
            //승리국 승리횟수, 패배국 패배횟수 0 이상일 경우만 시행
            game[c1][WIN]--;
            game[c2][LOSE]--;
            playGame(round+1);
            game[c1][WIN]++;
            game[c2][LOSE]++;
        }

        //무승부
        if(game[c1][DRAW] > 0 && game[c2][DRAW] > 0){
            game[c1][DRAW]--;
            game[c2][DRAW]--;
            playGame(round+1);
            game[c1][DRAW]++;
            game[c2][DRAW]++;
        }

        //c2승리
        if(game[c1][LOSE] > 0 && game[c2][WIN] > 0){
            game[c1][LOSE]--;
            game[c2][WIN]--;
            playGame(round+1);
            game[c1][LOSE]++;
            game[c2][WIN]++;
        }
    }
}

package boj.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14658_하늘에서별똥별이빗발친다 {
    /*[골드4]Q14658 - 하늘에서 별똥별이 빗발친다*/
    static int N, M, L, K;
    static Pos[] stars;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stars = new Pos[K];

        for(int k =0; k<K;k++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            stars[k] = new Pos(i,j);
        }

        int maxDefense=0;
        for(int p=0;p<K;p++){
            Pos now = stars[p]; //트램펄린 시작위치의 기준점이 되는 별
            //i: 트램펄린 시작위치 x좌표, j: 트램펄린 시작위치 y좌표
            for(int i=now.x-L;i<=now.x;i++){
                int j = now.y;
                if(i<0) continue;

                //전체 별을 돌며, 트램펄린에 포함되어있는지 확인
                int cnt=0;
                for(int q=0;q<K;q++){
                    Pos check = stars[q];
                    if(i<=check.x && check.x<=i+L && j<=check.y && check.y<=j+L)
                        cnt++;
                }
                maxDefense = Math.max(maxDefense,cnt);
            }
        }
        System.out.println(K-maxDefense);
    }

    static class Pos{
        int x,y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

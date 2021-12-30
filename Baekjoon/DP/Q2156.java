package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] wine = new int[N+1];
        int[] dpArr = new int[N+1];

        wine[0] = 0; //시작점
        for(int i = 1 ;i<N+1;i++){
            wine[i] = Integer.parseInt(br.readLine());
        }


        dpArr[1] = wine[1];
        if(N>1) dpArr[2] = wine[1] + wine[2];   //N이 1인 경우 runtime error 방지

        for(int i = 3;i<N+1;i++){
            //drinkI: i를 마시는 경우의 최대합
            int drinkI = Integer.max(dpArr[i-3]+wine[i-1], dpArr[i-2]) + wine[i];
            //i-3, i-1 포도주를 마신 후 i 마신 경우 or i-2 마시고 i 마신 경우

            //최대합이려면 어차피 i혹은 i-1에서 끝나므로 둘 중 큰 값 저장
            dpArr[i] = Integer.max(drinkI, dpArr[i-1]);

        }
        System.out.println(dpArr[N]);
    }
}

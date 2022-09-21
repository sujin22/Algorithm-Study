package boj.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] stairs = new int[N+1];
        int[] dpArr = new int[N+1];

        stairs[0] = 0; //시작점
        for(int i = 1 ;i<N+1;i++){
            stairs[i] = Integer.parseInt(br.readLine());
        }


        dpArr[1] = stairs[1];
        if(N>1) dpArr[2] = stairs[1] + stairs[2];   //N이 1인 경우 runtime error 방지
        if(N>2) dpArr[3] = Integer.max(stairs[1], stairs[2])+stairs[3]; //N이 2인 경우 runtime error 방지

        for(int i = 4;i<N+1;i++){
            dpArr[i] = Integer.max(dpArr[i-3]+stairs[i-1], dpArr[i-2]) + stairs[i];
            //i-3, i-1계단을 밟은 후 i 밟은 경우 or i-2계단 밟고 i 밟은 경우
        }
        System.out.println(dpArr[N]);
    }
}

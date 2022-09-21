package boj.brute_force.permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q18429_근손실 {
    /*[실버3]Q18429 - 근손실*/
    static int N, K;
    static int [] kits;
    static int weight = 500;
    static int totalCnt = 0;    //경우의수
    static boolean[] isSelected;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        kits = new int[N];
        isSelected = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            kits[i] = Integer.parseInt(st.nextToken()) - K;
        }

        permutation(0);

        System.out.println(totalCnt);
    }

    private static void permutation(int index){
        if(index == N){
            totalCnt++; //경우의 수 1 증가
            return;
        }
        for(int i = 0; i<N; i++){
            //중복 체크
            if(isSelected[i])   continue;

            if((weight += kits[i]) <500){
                weight -= kits[i];
                continue;
            }
            isSelected[i] = true;
            permutation(index+1);
            weight -= kits[i];
            isSelected[i] = false;
        }
    }
}

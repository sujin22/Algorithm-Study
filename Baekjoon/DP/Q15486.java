package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] term = new int[N];
        int [] payment = new int[N];

        for(int i = 0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            term[i] = Integer.parseInt(st.nextToken());
            payment[i] = Integer.parseInt(st.nextToken());
        }

        long[] dpTable = new long[N+1]; //N번째를 0으로 비워두기 위해서 N+1개 선언
        // 이유 1: i, i+1을 비교하기 때문
        // 이유 2: 제일 마지막 원소 term이 1일일 경우 dpTable[N-1]=payment[N-1] + dpTable[N]이기 때문

        //뒤에서부터 dpTable을 채워준다
        //예를 들어 i=6이면 6일 이후에 발생할 수 있는 최대합을 저장함
        for(int i = N-1;i>=0;i--){
            if(i+term[i]<=N){   //남은 기간동안 term을 수행할 수 있으면
                dpTable[i] = dpTable[i+term[i]] + payment[i];   //이 일이 끝나고 나서부터 수행할 수 있는 최대합을 합쳐서 저장
            }
            dpTable[i] = Math.max(dpTable[i], dpTable[i+1]);//직전 값과 비교해서 계산한 값이 최대가 아니면 최대로 저장(이미 직전값은 최대임)
        }
        System.out.println(dpTable[0]);//제일 앞 원소를 출력하면 최대값임
//        System.out.println(Arrays.toString(dpTable));
    }
}

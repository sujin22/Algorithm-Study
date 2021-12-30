package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9095_2 {
    public static Integer[] dpArr; //int의 경우 초기값이 0이므로, 초기값이 null이어야해서 Integer 사용
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());    //테스트케이스 개수 T

        dpArr = new Integer[12]; //n 최대크기 11
        dpArr[0] = 1;   //실제로는 0이지만 n+0의 경우를 생각해주기 위해 1으로 초기화(어차피 문제 조건에서 n은 항상 양수)
        dpArr[1] = 1;
        dpArr[2] = 2;

        for(int i = 3 ;i<12;i++){
            dpArr[i] = dpArr[i-1]+dpArr[i-2]+dpArr[i-3];
        }

        for(int i = 0; i<T;i++){
            int n = Integer.parseInt(br.readLine());

            System.out.println(dpArr[n]);
        }
    }
}

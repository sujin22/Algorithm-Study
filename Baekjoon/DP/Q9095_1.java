package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9095_1 {
    public static Integer[] dpArr; //int의 경우 초기값이 0이므로, 초기값이 null이어야해서 Integer 사용
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());    //테스트케이스 개수 T

        dpArr = new Integer[12]; //n 최대크기 11
        dpArr[0] = 1;   //실제로는 0이지만 n+0의 경우를 생각해주기 위해 1으로 초기화
        dpArr[1] = 1;

        for(int i = 0; i<T;i++){
            int n = Integer.parseInt(br.readLine());
            System.out.println(numberOfCases(n));
        }
    }

    public static int numberOfCases(int n){
        int count = 0;
        if(dpArr[n] == null){
            //1
            if(n-1>=0){
                count+=numberOfCases(n-1);
            }
            //2
            if(n-2>=0){
                count+=numberOfCases(n-2);
            }
            //3
            if(n-3>=0){
                count+=numberOfCases(n-3);
            }
            dpArr[n] = count;
        }
        return dpArr[n];
    }
}

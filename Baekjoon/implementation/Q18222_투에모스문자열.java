package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q18222_투에모스문자열 {
    /*[실버2]Q18222-투에모스문자열*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long k = Long.parseLong(br.readLine());
        long cnt = solve(k);
        if(cnt%2 == 0) System.out.println(0);
        else System.out.println(1);
    }

    //n에서 2분할을 몇번 해야 1이 되는지 센다.
    //횟수가 홀수면 1, 짝수면 0
    public static long solve(long n){
        long cnt = 0;
        while(n!=1){
            //나보다 작은 2제곱수 구하기
            long num = n;
            if(num%2==0)  num--;   //2의 배수일 경우 1 빼고 계산해준다(본인이 2제곱수일 경우 본인보다 작은 값을 구해야하므로)
            if(num==1)    return cnt+1;
            long power = (long)(Math.log(num)/Math.log(2));
            long result = (long)Math.pow(2,power);  //나보다 작은 2제곱수

            cnt++;
            n-=result;  //n에서 빼주면 이전 위치임
        }
        return cnt;
    }

    /*cnt 세는 방식 말고, 1-x으로 0,1값 바꿔가면서 재귀로 풀어도 될 것 같음(그래서 카테고리에 재귀 있는 듯)*/
}

package boj.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
무조건 더 작은 애를 반으로 나눠야 양이 많이 남는다.
모든 드링크를 한번씩 연산해야한다면,
가장 큰 애(max)를 가만히 두고, 나머지 애들을 반으로 나누어 더했을 경우 가장 양이 많아질 것이다.

따라서, 어차피 max를 제외한 나머지는 반으로 나누어 더해질 것이므로
아래와 같이 연산한다.
 */
public class Q20115 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        double max = 0;    //최대 양
        double sum = 0;    //드링크의 양
        for(int i = 0; i<n;i++){
            int x = Integer.parseInt(st.nextToken());    //Buffer로 받아온 String에서 정수 하나씩 꺼내서 비교

            //지금까지 중 최대값이면 max에 저장
            if(max<x){
                max = x;
            }
            //x는 sum에 더해준다.
            sum+=x;
        }

        //찾은 최대값을 sum(모든 값 더한 것)에서 빼준다
        sum-=max;

        //한번에 연산해준다. (max에 반을 나눈 나머지들 다 더하기)
        double result = max+ sum/2;

        System.out.println(result);
    }
}

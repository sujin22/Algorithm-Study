package boj.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1806_부분합 {
    /*[골드4]Q1806 - 부분합*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int [] arr = new int[n];
        for(int i = 0; i<n;i++){
            int now = Integer.parseInt(st.nextToken());
            //원소 하나가 s 이상이면 길이는 1
            if(now>=s){
                System.out.println(1);
                System.exit(0); //프로그램 끝내기
            }
            arr[i] = now;
        }

        int min = n+1;
        int left = 0;
        int right = 0;
        long sum = arr[right];
        while(true){
            if(sum>=s){
                //S보다 크거나 같으면(만족하면)
                min = Integer.min(min, right-left+1);   //최소길이 저장

                //left 증가시킴
                sum-=arr[left];
                left++;
            } else{
                //만족하지 않으면 right을 증가시킴
                right++;
                if(right==n)    break;  //right가 끝을 만났는데 s보다 작은 상태라면 끝낸다(left 줄여보지 않아도 만족안함)
                sum+=arr[right];
            }
        }
        //min값이 초기값에서 바뀌지 않았을 경우, 불가능한 것이므로 0 저장
        if(min == n+1)   min = 0;
        System.out.println(min);
    }
}

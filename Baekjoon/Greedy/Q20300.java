package greedy;

import java.util.Arrays;
import java.util.Scanner;

/*
근손실이 항상 m을 넘지 않을 때, m의 최솟값?
=> m은 근손실의 최대값인데 그걸 가장 작게 만들어라

정렬해서 "제일 작은 애 + 제일 큰 애", "그 다음 작은 애 + 그 다음 큰 애" 순으로 더했을 때 가장 작아짐
단, n이 홀수일 경우는 가장 큰 애 하나를 빼놓고 나머지 짝수개끼리 짝지어서 수행한다.
이유: 하나를 빼야 한다면, 가장 큰 애에 아무것도 더하지 않는 것이 제일 작아지는 경우이기 때문
 */

public class Q20300 {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long arr[] = new long[n];

        for(int i = 0; i<n ;i++){
            arr[i] = sc.nextLong();
        }

        Arrays.sort(arr);

        long max = 0;

        if(n%2!=0){
            max = arr[n-1];

            for(int i = 0; i<n/2 ;i++){
                long loss = arr[i] + arr[n-2-i];
                if(max< loss){
                    max = loss;
                }
            }
        }else{
            for(int i = 0; i<n/2 ;i++){
                long loss = arr[i] + arr[n-1-i];
                if(max< loss){
                    max = loss;
                }
            }
        }

        System.out.println(max);
    }
}

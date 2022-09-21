package boj.Greedy;

import java.util.Arrays;
import java.util.Scanner;

/*
소요시간의 최소합을 구해야 한다.
앞사람이 시간을 많이 소요하면, 뒷사람의 시간이 그만큼 늘어나기 때문에
오름차순으로 정렬하면 최소합이 될 것이다.
 */

public class Q11399 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //n: 사람의 수
        int n = sc.nextInt();

        //array: 소요 시간 배열
        int array[] = new int[n];
        for(int i = 0; i<n;i++){
            array[i] = sc.nextInt();
        }

        //오름차순 정렬
        Arrays.sort(array);


        int tmp = 0;    //지금까지 소요된 시간을 저장할 변수
        int result = 0; //소요된 시간의 총합을 저장할 변수

        for(int i = 0;i<n;i++){
            tmp += array[i];
            result +=tmp;
        }

        System.out.println(result); //소요된 시간의 총합 출력
    }
}

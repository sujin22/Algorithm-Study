package boj.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1253_좋다 {
    /*[골드4]Q1253_좋다*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        for(int i = 0; i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int cnt = 0;

        for(int i = 0; i<N;i++){
            int left = 0;
            int right = N-1;
            //left는 오른쪽으로만, right은 왼쪽으로만 이동
            while(left<right){
                //타겟인 애는 제외하고 탐색해야 함
                if(left == i || right == i){
                    if(i==left)     left++;
                    if(i==right)    right--;
                    continue;
                }
                int sum = arr[left]+arr[right];
                if(sum>arr[i]){
                    //더 작아져야함, 따라서 right을 감소
                    right--;
                }else if(sum<arr[i]){
                    //더 커져야 함, 따라서 left를 증가
                    left++;
                }else{
                    //똑같음
                    cnt++;
                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}

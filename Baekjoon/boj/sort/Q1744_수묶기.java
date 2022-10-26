package boj.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1744_수묶기 {
    //int형으로도 충분하다
    //오름차순 정렬
    //음수 - 제일 작은 애부터 2개씩 묶는다.
    //양수 - 제일 큰 애부터 2개씩 묶는다.
    //1 - 양수 묶기에서 제외해줘야 한다. 곱하면 손해
    //0 - 음수가 홀수 개일 경우, 절댓값 가장 작은 음수랑 곱해서 없애준다.(0은 하나만 이용하고 나머지는 내버려둔다.)
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int N = reader.nextInt();
        int[] arr = new int[N];
        int negative = 0;
        int positive = 0;
        int zero = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = reader.nextInt();
            if (arr[i] > 0) positive++;
            else if (arr[i] < 0) negative++;
            else zero++;
        }
        Arrays.sort(arr);

        //0을 사용해준다.
        if(negative%2!=0){
            if(zero>0){
                arr[negative-1] = 0;
            }
            negative--;
        }
        Arrays.sort(arr);


        //음수부분 처리
        for(int i =0; i<negative; i+=2){
            if(i+1>=negative) break;
            arr[i] *=arr[i+1];
            arr[i+1] = 0;
        }

        //양수부분 처리
        for(int i = arr.length -1 ; i >= arr.length - positive; i--){
            if(i-1 < arr.length - positive) break;
            if(arr[i] == 1 || arr[i-1] == 1)    continue;
            arr[i] *= arr[i-1];
            arr[i-1] = 0;
            i--;
        }

        int result = 0;
        for(int i =0; i<N; i++){
            result+=arr[i];
        }
        System.out.println(result);
    }
    private static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        private String next() {
            if (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        private int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

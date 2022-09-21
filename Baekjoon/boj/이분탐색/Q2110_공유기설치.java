package boj.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2110_공유기설치 {
    /*[골드5]Q2110-공유기 설치*/
    static int N,C;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   //집 개수
        C = Integer.parseInt(st.nextToken());   //공유기 개수

        arr = new int[N];
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int left = 1;
        int right = arr[N-1] - arr[0]+1;   //마지막 집 위치와 처음 집 위치 사이 거리 +1

        //구하고자하는 최대 거리는 1부터 '첫 집 ~ 마지막 집 사이 거리' 사이에 있다
        while(left < right){
            int mid = (left+right)/2;
            if(routerNum(mid) >= C){
                //필요 개수보다 많거나 같으면 이게 마지막인지 알아봐야 함
                //left를 mid로 이동
                left=mid+1;
            }else{
                //필요 개수보다 적으면 거리를 줄여야함
                //right 범위를 mid까지로 줄이기
                right = mid;
            }
        }
        System.out.println(left-1);
    }
    private static int routerNum(int dist){
        int cnt = 1;
        int lastPos = 0;
        for(int i = 1; i<N; i++){
            if(arr[i] - arr[lastPos] <dist)   continue;
            lastPos = i;
            cnt++;
        }
        return cnt;
    }
}

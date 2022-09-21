package boj.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17951_흩날리는시험지속에서내평점이느껴진거야 {
    /*[골드4]Q17951 - 흩날리는 시험지 속에서 내 평점이 느껴진거야*/
    /* 요약
    그룹 중 최소값이 최대가 되는 최적해를 이분탐색으로 찾는다.
    왼쪽부터 최대한 적은 원소를 채우며, 그룹 개수 k가 가능한지 찾는다.
     */
    static int n, k;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n];

        int min = Integer.MAX_VALUE;
        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            min = Integer.min(min, arr[i]);
            sum+=arr[i];
        }

        int left = min;
        int right = sum;

        while(left<=right){
            int mid = (left+right)/2;
            //그룹이 k개 만들어지는 것이 가능하면
            //더 큰 최적해가 있는지 찾아보아야 하므로, mid보다 큰 범위를 탐색한다.
            if(isPossible(mid)){
                left = mid+1;
            }else{
                //불가능하면 더 작은 최적해 범위를 탐색한다.
                right = mid-1;
            }
        }
        System.out.println(right);
    }
    static boolean isPossible(int mid){
        //합이 가장 작은 그룹의 합이 mid여야 함 -> 모든 그룹의 합이 mid보다 같거나 커야 함
        int sum = 0;
        int groupCnt = 0;
        //왼쪽부터 최대한 적은 개수의 원소를 채우며 그룹 개수를 센다.
        //어차피 더 큰 최적해가 있다면 다음 검사에서 나올 테니까.
        for(int i = 0; i<n; i++){
            sum+=arr[i];
            if(sum>=mid){   //최대한 적은 개수의 원소를 채우므로, mid보다 같거나 커지면 그룹을 자른다.
                sum = 0;
                groupCnt++;
            }
        }
        return groupCnt >= k;
    }
}

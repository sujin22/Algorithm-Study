package boj.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14719_빗물 {
    /*[골드5]Q14719_빗물*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[w];
        for(int i = 0; i<w;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //현위치보다 왼쪽 중 가장 큰 애(left) 찾고, 오른쪽 중 가장 큰 애(right) 찾는다.
        //left, right 중 더 작은 것과 본인의 차이만큼 더한다.
        int result = 0;
        for(int i = 1; i<w-1;i++){
            int now = arr[i];
            int left = now;
            int right = now;
            for(int j = i; j>=0; j--){
                left = Integer.max(left, arr[j]);
            }
            for(int j = i; j<w; j++){
                right = Integer.max(right, arr[j]);
            }

            result += Integer.min(left, right) - now;
        }
        System.out.println(result);
    }
}

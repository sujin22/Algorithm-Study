package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q17829_222풀링 {
    /*[실버2]Q17829 - 222-풀링*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [][] arr = new int[n][n];

        for(int i = 0 ;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ;j<n;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[]tmpArr = new int[4];
        Queue<Integer> nextList = new ArrayDeque<>();
        while(n!=1){
            for(int i =0; i<n;i+=2){
                for(int j = 0; j<n;j+=2){
                    tmpArr[0] = arr[i][j];
                    tmpArr[1] = arr[i][j+1];
                    tmpArr[2] = arr[i+1][j];
                    tmpArr[3] = arr[i+1][j+1];
                    Arrays.sort(tmpArr);
                    nextList.add(tmpArr[2]);
                }
            }
            n /= 2;
            for(int i = 0; i<n;i++){
                for(int j = 0; j<n;j++){
                    arr[j][i] = nextList.poll();
                }
            }
        }
        System.out.println(arr[0][0]);
    }

}

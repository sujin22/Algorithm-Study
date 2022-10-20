package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Q2641_다각형그리기 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());
        int[] sample = new int[length];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0; i< length ;i++){
            sample[i] = Integer.parseInt(st.nextToken());
        }
        int[] reverse_sample= getReverseArr(sample);

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][length];

        for(int i = 0; i< n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<length; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //개수 세기
        int count = 0;
        for(int i = 0; i< n; i++){
            int[] reverseArr = getReverseArr(arr[i]);
            if(checkSame(sample, arr[i]) || checkSame(sample, reverseArr)
            || checkSame(reverse_sample, arr[i]) || checkSame(reverse_sample, reverseArr)){
                count++;
                putArrToSB(arr[i]);
            }
        }
        System.out.println(count + "\n" + sb.toString());
    }
    static void putArrToSB(int[] arr){
        for(int i = 0; i< arr.length;i++){
            sb.append(arr[i]+" ");
        }
        sb.append("\n");
    }
    static boolean checkSame(int[] sample, int[] arr){
        for(int i = 0; i< sample.length;i++){
            boolean isSame = true;
            for(int ind = 0; ind<sample.length; ind++){
                int arrIndex = (i+ind)%arr.length;
                if(sample[ind]!=arr[arrIndex]){
                    isSame = false;
                    break;
                }
            }
            if(isSame)  return true;
        }
        return false;
    }
    static int[] getReverseArr(int[] arr){
        int[] reverseArr = new int[arr.length];
        for(int i = 0; i< arr.length;i++){
            reverseArr[arr.length-i-1] = reverse(arr[i]);
        }
        return reverseArr;
    }

    static int reverse(int dir){
        switch(dir){
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 1;
            default:
                return 2;
        }
    }

}

package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1032_명령프롬프트 {
    /*[브론즈1]Q1032 - 명령프롬프트*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String result = "";
        String [] arr = new String[n];
        for(int i = 0; i<n; i++){
            arr[i] = br.readLine();
        }
        for(int i = 0; i<arr[0].length(); i++){
            boolean isSame = true;
            char c = arr[0].charAt(i);
            for(int j = 1; j<n;j++){
                if(arr[j].charAt(i)!=c){
                    isSame = false;
                    break;
                }
            }
            if(isSame){
                result+=arr[0].charAt(i);
            }else{
                result+='?';
            }
        }
        System.out.println(result);
    }
}

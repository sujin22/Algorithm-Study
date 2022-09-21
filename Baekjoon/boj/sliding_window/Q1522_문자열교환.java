package boj.sliding_window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1522_문자열교환 {
    /*[실버1]Q1522_문자열교환*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        //a개수세기
        int num_a = 0;
        for(int i = 0; i<str.length();i++){
            if(str.charAt(i)=='a'){
                num_a++;
            }
        }

        int min = 1000;
        for(int start = 0; start<str.length(); start++){
            int cnt = 0;    //sliding window 내부 b의 개수
            for(int i = 0; i<num_a; i++){
                if(str.charAt((start+i)%str.length()) == 'b'){
                    cnt++;
                }
            }
            min = Integer.min(min, cnt);
        }

        System.out.println(min);
    }

}

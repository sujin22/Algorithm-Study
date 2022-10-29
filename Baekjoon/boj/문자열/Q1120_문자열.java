package boj.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1120_문자열 {
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        String strA =reader.next();
        String strB =reader.next();

        int min = strA.length();
        for(int i = 0; i<= strB.length() - strA.length(); i++){
            int cnt = 0;
            for(int j = 0; j<strA.length(); j++){
                if(strB.charAt(i+j) != strA.charAt(j)){
                    cnt++;
                }
            }
            min = Integer.min(min, cnt);
        }
        System.out.println(min);
    }
    private static class FastReader{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next(){
            if(st == null|| !st.hasMoreTokens()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
    }
}

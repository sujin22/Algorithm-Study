package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9996_한국이그리울땐서버에접속하지 {
    /*[실버3]Q9996 - 한국이 그리울 땐 서버에 접속하지*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String patternStr = br.readLine();

        String[] pattern = patternStr.split("\\*");
        for(int i = 0; i<N;i++){
            String str = br.readLine();
            if(pattern[0].length()>=str.length() || pattern[1].length() >=str.length()
                    ||patternStr.length()-1 > str.length()){
                System.out.println("NE");
                continue;
            }
            if(pattern[0].equals(str.substring(0,pattern[0].length()))
                    && pattern[1].equals(str.substring(str.length()-pattern[1].length()))){
                System.out.println("DA");
            }else{
                System.out.println("NE");
            }
        }
    }
}

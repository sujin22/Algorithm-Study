package boj.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2745_진법변환 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());

        long result = 0;
        for(int i = 0; i<N.length();i++){
            char now = N.charAt(N.length()-i-1);
            long value = 0;
            if(now>='A'&& now<='Z'){
                //알파벳이면
                int order = now -'A'+1;
                value = (long)Math.pow(B, i)*(9+order);
            }else{

                value = (long)Math.pow(B,i)*(now-'0');
            }

            result+=value;
        }
        System.out.println(result);
    }
}

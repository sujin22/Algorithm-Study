package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q11005_진법변환2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        ArrayList<Character> list = new ArrayList<>();
        while(N>0){
            int remain = N%B;
            N/=B;
            if(remain<10){
                list.add((char)(remain+'0'));
            }else{
                list.add((char)(remain-10+'A'));
            }
        }
        for(int i = list.size()-1;i>=0;i--){
            System.out.print(list.get(i));
        }
    }
}

package boj.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1449_수리공항승 {
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int N = reader.nextInt();
        int L = reader.nextInt();
        int[] leak = new int[N];
        for(int i =0; i<N;i++){
            leak[i] = reader.nextInt();
        }
        Arrays.sort(leak);
        int cnt = 1;
        int tapeEnd = leak[0] + L;
        for(int i = 1; i<N; i++){
            if(leak[i]+1 > tapeEnd){
                cnt++;
                tapeEnd = leak[i]+L;
            }
        }
        System.out.println(cnt);
    }
    private static class FastReader{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String next(){
            if(st==null || !st.hasMoreTokens()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
    }
}

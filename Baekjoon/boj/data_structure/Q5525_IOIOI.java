package boj.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q5525_IOIOI {
    public static void main(String[] args){
        FastReader reader = new FastReader();
        int n = reader.nextInt();
        int m = reader.nextInt();
        String target = reader.next();

        int cnt = 0;
        int result = 0;
        for(int i = 0; i+2 < target.length(); i++){
            if(target.charAt(i)=='I'
                    &&target.charAt(i+1)=='O'
                    &&target.charAt(i+2)=='I'){
                cnt++;
                i++;
            }else{
                cnt = 0;
            }
            if(cnt == n){
                result++;
                cnt--;
            }
        }
        System.out.println(result);
    }
    private static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        private String next(){
            if(st == null || !st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        private int nextInt(){
            return Integer.parseInt(next());
        }
    }
}

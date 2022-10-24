package boj.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Q11652_카드 {
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int n = reader.nextInt();
        HashMap<Long, Integer> cntMap = new HashMap<>();
        int maxCnt = 0;
        long maxCntNum = Long.MAX_VALUE;
        for(int i = 0; i< n; i++){
            long num = reader.nextLong();
            int newCnt = cntMap.getOrDefault(num, 0)+1;
            cntMap.put(num, newCnt);
            if(maxCnt<newCnt){
                maxCnt = newCnt;
                maxCntNum = num;
            }else if(maxCnt == newCnt){
                maxCntNum = Long.min(maxCntNum, num);
            }
        }
        System.out.println(maxCntNum);
    }
    private static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        String next(){
            if(st==null || !st.hasMoreElements()){
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
        long nextLong(){
            return Long.parseLong(next());
        }
    }
}

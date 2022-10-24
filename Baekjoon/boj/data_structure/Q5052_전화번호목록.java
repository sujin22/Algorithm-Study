package boj.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q5052_전화번호목록 {
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int t = reader.nextInt();
        StringBuilder sb = new StringBuilder();
        while(t>0){
            int n = reader.nextInt();
            //String을 오름차순으로 정렬하면(사전순)
            //직전 원소가 현재 원소의 접두어인지 확인하면 된다.
            // -> 접두어가 있는 개수를 세는 게 아니고 하나만 찾으면 NO를 알아낼 수 있기 때문에
            // 911 91144 91155 같은 경우는 생각해주지 않아도 된다.
            PriorityQueue<String> pq = new PriorityQueue<>();
            for(int i =0;i<n; i++){
                pq.add(reader.next());
            }
            boolean isValid = true;
            String prev = pq.poll();
            while(!pq.isEmpty()){
                String now = pq.poll();
                if(now.startsWith(prev)){
                    isValid = false;
                    break;
                }
                prev = now;
            }
            if(isValid) sb.append("YES\n");
            else sb.append("NO\n");
            t--;
        }
        System.out.println(sb);
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
    }
}

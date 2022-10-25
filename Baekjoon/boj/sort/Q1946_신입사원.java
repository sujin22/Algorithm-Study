package boj.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Q1946_신입사원 {
    /*
    * 1 4 o
    * 2 3 o
    * 3 2 o
    * 4 1 o
    * 5 5 x
    * */

    /*
    * 1 4 o
    * 2 5 x
    * 3 6 x
    * 4 2 o
    * 5 7 x
    * 6 1 o
    * 7 3 x
    * */
    //서류순으로 정렬했을 때, 본인보다 앞 순위 중에서 면접 순위가 더 높은 사람이 하나라도 있으면 안됨
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int T = reader.nextInt();
        while(T>0){
            int N = reader.nextInt();
            Grade[] applicant = new Grade[N];
            for(int i = 0; i< N; i++){
                applicant[i] = new Grade(reader.nextInt(), reader.nextInt());
            }
            //서류 순으로 정렬
            Arrays.sort(applicant, Comparator.comparingInt(o -> o.resume));
            int cnt = 1;
            int high = applicant[0].interview; //제일 높은 애 가지고 있기
            for(int i = 1; i< N; i++){
                //비교해서 나보다 높으면 불가능하다
                if(high > applicant[i].interview) {
                    cnt++;
                    high = applicant[i].interview;
                }
            }
            System.out.print(cnt+" ");
            T--;
        }
    }
    private static class Grade{
        int resume;
        int interview;

        public Grade(int resume, int interview) {
            this.resume = resume;
            this.interview = interview;
        }
    }
    private static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
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

package boj.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1025 {
    static int n, m;
    static long max;
    static String[] table;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        table = new String[n];

        for (int i = 0; i < n; i++) {
            table[i] = br.readLine();
        }
        max = -1;

        //(행의 등차,열의 등차) 가능한 경우의수 모두 search함
        for(int i = -n+1;i<n+1;i++){
            for(int j = -m+1;j<m+1;j++){
                search(i,j);
            }
        }
        System.out.println(max);
    }

    public static void search(int dr, int dc){
        //(i,j): 등차수열 시작점
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                String str = "";
                int x = i;
                int y = j;
                while(true){
                    if(x<0 || x>=n|| y<0 || y>=m){
                        break;
                        //테이블 밖으로 나가지 않도록 예외처리
                    }
                    str+=table[x].charAt(y);
                    check(str);
                    //테이블 끝까지 가지 않고 중간에서 끝난 경우도 제곱수일 수 있으므로 매번 체크해줌
                    //ex) 01210일 경우 1210이 아닌 121을 체크해줘야함
                    x += dr;
                    y += dc;

                    if(dr==0 && dc==0){
                        break;
                    }//만약 둘다 0일 경우 무한루프 도므로, 한번만 수행해주고 break해줌
                }

            }
        }
    }
    //제곱수인지 체크하는 함수(제곱수면 max와 비교해서 최대값 저장)
    public static void check(String str){
        if(!str.equals("")){
            long l = Long.parseLong(str);
            long sqrt = (long)Math.sqrt(l);
            if(l == Math.pow(sqrt,2)){
                max = Math.max(max, l);
            }
        }
    }

}

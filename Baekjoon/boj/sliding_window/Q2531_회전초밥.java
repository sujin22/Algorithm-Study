package boj.sliding_window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2531_회전초밥 {
    /*[실버1]Q2531 - 회전초밥*/
    static int N, d, k, c;
    static int[] sushi, checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushi = new int[N];
        checked = new int[d+1];//초밥 가짓수만큼 생성
        for(int i = 0; i<N;i++){
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 1;
        int max = 0;
        checked[c]++;    //쿠폰은 먹고 시작
        //0부터 시작해서 k개 checked, max 초기값 지정
        for(int i = 0; i<k; i++){
            if(checked[sushi[i]] == 0){
                cnt++;
            }
            checked[sushi[i]]++;
        }
        max = cnt;

        //1~N-1까지 시작점 이동
        for(int i = 1; i<N; i++){
            //새로 먹는 초밥이 먹어본 종류가 아니면
            int last = (i+k-1)%N;
            if(checked[sushi[last]] == 0){
                cnt++;
            }
            checked[sushi[last]]++;
            checked[sushi[i-1]]--;

            //빠진 초밥 종류를 더이상 먹지 않는다면
            if(checked[sushi[i-1]] == 0){
                cnt--;
            }
            max = Integer.max(cnt, max);
        }

        System.out.println(max);
    }
}

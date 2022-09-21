package boj.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1474_밑줄 {
    /*[실버1]Q1474 - 밑 줄*/
    static int n, m;
    static String[] words;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        words = new String[n];

        int maxLength = 0;
        for(int i = 0; i<n;i++){
            words[i] = br.readLine();
            m-=words[i].length();
            maxLength = Integer.max(maxLength, words[i].length());
        }

        int _min = m/(n-1);     //언더바 개수 최솟값
        int addCnt = m%(n-1);  //언더바 하나 더 추가되어야 하는 위치 개수(차이가 1을 초과하면 안되므로)

        //언더바 개수가 _min보다 하나 큰 위치가 있는 경우
        String result = words[0];   //첫번째 단어로 시작

        //두번쨰 단어부터 돌린다.
        for(int i = 1; i<n;i++){
            if(addCnt>0 && (words[i].charAt(0)>'_' || n-i==addCnt)){
                //언더바 추가해야 할 위치가 남아있는데,
                //첫글자가 대문자거나, (본인포함)남은 단어 수와 addCnt가 같으면
                //_min보다 언더바 하나 더 넣기
                result+=getUnderBarString(_min+1)+words[i];
                addCnt--;
            } else{
                //그 외의 경우 _min만큼 언더바 붙이기
                result+=getUnderBarString(_min)+words[i];
            }
        }
        System.out.println(result);
    }
    static String getUnderBarString(int n){
        String result = "";
        for(int i = 0; i<n;i++){
            result+="_";
        }
        return result;
    }
}

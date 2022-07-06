package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q16916_부분문자열 {
    /*[골드5]Q16916 - 부분 문자열*/
    /*요약
     * 문자열의 길이가 최대 100만이기 때문에, 일반적인 O(N*M) 탐색 풀이로는 풀 수 없다.
     * 따라서 KMP로 풀이하였다.
     * 접두사, 접미사가 공통된 최대 글자수를 찾아 테이블(table)에 저장해둔 후,
     * 패턴이 일치하지 않았을 때,
     * 그때까지 matched되었던 숫자와 table에서 찾아둔 접미사, 접두사 개수를 이용하여 점프하는 방식으로 효율을 높혔다.
     * KMP 설명은 아래 링크가 이해하기 좋았다.
     * https://m.blog.naver.com/PostView.nhn?blogId=ndb796&logNo=221240660061&proxyReferer=https:%2F%2Fwww.google.com%2F
     * */

    static String S, P;
    static int[] table;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        P = br.readLine();
        table = new int[P.length()];

        findTable();
        System.out.println(KMP(S,P));
    }
    static int KMP(String target, String pattern){
        int tl = target.length(), pl = pattern.length();

        int begin = 0; //시작점
        int matched = 0; //target과 pattern을 비교할 때, 일치한 개수

        while(begin+pl <= tl){
            //일치한 개수가 패턴 길이보다 작고, 다음 글자가 같으면
            if(matched<pl && target.charAt(begin+matched) == pattern.charAt(matched)){
                matched++; //일치한 개수 1 증가

                //증가시킨 후 검사했을 때, 패턴 길이와 같아지면 패턴 발견한 것임
                if(matched == pl){
                    return 1;    //패턴 발견
                }
            }else{
                //불일치하는 순간을 찾으면
                if(matched == 0){
                    //일치하는 것이 하나도 없었을 경우 begin을 1만 증가시킨다.
                    begin++;
                }else{
                    //일치하는 것이 있었을 경우,
                    //그 위치까지의 접두사/접미사 최대 크기를 table에서 가져온다.
                    //일치하는 접두사 크기까지 탐색 시작점을 점프한다.(즉 직전 탐색에서 접미사였던 부분이 접두사가 되는 것)
                    begin = begin+matched - table[matched-1];

                    //일치하는 개수는 그 접미사 크기로 초기화한다.(당연히 동일하므로)
                    matched = table[matched - 1];
                }
            }
        }
        return 0;
    }
    static void findTable(){
        int n = P.length();
        int idx= 0;
        for(int i = 1; i<n;i++){
            //일치하는 문자가 발생했을 때(idx>0),
            // 연속적으로 더 일치하지 않으면 일치했던 부분까지 되돌아가서 다시 검사를 한다.(idx = table[idx-1])
            while(idx>0 && P.charAt(i) != P.charAt(idx)){
                idx = table[idx-1];
            }
            if(P.charAt(i) == P.charAt(idx)){
                idx++;
                table[i] = idx;
            }
        }
    }
}

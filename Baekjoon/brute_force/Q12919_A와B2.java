package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q12919_A와B2 {
    /*[골드5]Q12919 - A와 B 2*/
    // 모든 경우를 다 따져주었을 경우, 2^N이어서 최악의 경우 2^50이 된다.
    // 따라서, S에서 T를 만들지 않고, T에서 S를 만들어야 시간초과가 안 난다(가능한 경우만 재귀하므로).

    static String target;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = br.readLine();
        String s = br.readLine();

        compare(s);
        System.out.println(0);
    }
    public static void compare(String s){
        if(s.equals(target)){
            //같으면 1출력, 프로그램 종료
            System.out.println(1);
            System.exit(0);
        }else if(s.length()==target.length()){
            //같지 않은데 length는 같아졌다면 불가능한 것
            return;
        }
        //끝글자가 A면 제거하고 재귀
        if(s.charAt(s.length()-1) == 'A'){
            compare(s.substring(0, s.length()-1));
        }
        //첫글자가 B면, 제거하고 뒤집어준 후 재귀
        if(s.charAt(0)=='B'){
            StringBuilder sb = new StringBuilder(s.substring(1));
            sb.reverse();
            compare(sb.toString());
        }
    }
}

package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q10808_알파벳개수 {
    /*[브론즈2]Q10808 - 알파벳 개수*/
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int [] cnt = new int['z'-'a'+1];
        for(int i=0; i<str.length(); i++){
            cnt[str.charAt(i)-'a']++;
        }
        for(int i=0; i<cnt.length; i++){
            System.out.print(cnt[i]+" ");
        }

    }
}

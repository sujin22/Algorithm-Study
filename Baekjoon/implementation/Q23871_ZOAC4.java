package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q23871_ZOAC4 {
    /*[브론즈3]Q23871 - ZOAC 4*/
    static int H,W,N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int h = (H-1)/(N+1) + 1;
        int w = (W-1)/(M+1) + 1;

        System.out.println(h*w);

    }
}

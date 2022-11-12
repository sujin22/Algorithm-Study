package boj.DP;

import java.util.*;
import java.io.*;

public class Q1351_무한수열{
    private static HashMap<Long, Long> map = new HashMap<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long P = Long.parseLong(st.nextToken());
        long Q = Long.parseLong(st.nextToken());

        map.put(0L, 1L);

        System.out.println(get(N, P, Q));
    }

    public static long get(long n, long P, long Q){
        if(map.containsKey(n)){
            return map.get(n);
        }

        long elem1 = get(n/P, P, Q);
        long elem2 = get(n/Q, P, Q);

        map.put(n, elem1 + elem2);
        return elem1 + elem2;
    }
}
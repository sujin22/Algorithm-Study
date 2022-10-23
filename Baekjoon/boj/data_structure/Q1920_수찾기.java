package boj.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Q1920_수찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashSet<Integer> numSet = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            int num = Integer.parseInt(st.nextToken());
            numSet.add(num);
        }
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< m;i++){
            int num = Integer.parseInt(st.nextToken());
            if(numSet.contains(num)) sb.append("1\n");
            else sb.append("0\n");
        }
        System.out.println(sb);
    }
}

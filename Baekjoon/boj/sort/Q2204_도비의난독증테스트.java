package boj.sort;

import java.util.*;
import java.io.*;

public class Q2204_도비의난독증테스트 {
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        StringBuilder sb = new StringBuilder();
        while (true) {
            int N = reader.nextInt();
            if (N == 0) break;
            String[] arr = new String[N];
            for (int i = 0; i < N; i++) {
                arr[i] = reader.next();
            }
            Arrays.sort(arr, String.CASE_INSENSITIVE_ORDER);
            sb.append(arr[0]).append("\n");
        }
        System.out.println(sb);
    }

    private static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            if (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
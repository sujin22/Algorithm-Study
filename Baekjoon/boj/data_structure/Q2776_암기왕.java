package boj.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Q2776_암기왕 {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T>0){
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            HashSet<Integer> set = new HashSet<>();
            for(int i = 0; i< N; i++){
                set.add(Integer.parseInt(st.nextToken()));
            }
            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<M; i++){
                int num = Integer.parseInt(st.nextToken());
                if(set.contains(num)){
                    sb.append("1\n");
                }else{
                    sb.append("0\n");
                }
            }
            T--;
        }
        System.out.println(sb);

    }
}

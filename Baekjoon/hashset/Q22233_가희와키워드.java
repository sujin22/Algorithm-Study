package hashset;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Q22233_가희와키워드 {
    /*[실버2]Q22233 - 가희와 키워드*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st= new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashSet<String> memo = new HashSet<>(N);
        for(int i = 0; i<N;i++){
            memo.add(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        int size = N;
        for(int i = 0; i<M;i++){
            String[] written_keywords = br.readLine().split(",");
            for(int j = 0; j<written_keywords.length;j++){
                if(memo.contains(written_keywords[j])){
                    memo.remove(written_keywords[j]);
                    size--;
                }
            }
            sb.append(size+"\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

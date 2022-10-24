package boj.graph_traversal.위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q14567_선수과목 {
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int n = reader.nextInt();
        int m = reader.nextInt();

        ArrayList<Integer>[] nextList = new ArrayList[n+1];
        for(int i = 1; i< n+1; i++){
            nextList[i] = new ArrayList<>();
        }

        int[] indegree = new int[n+1];
        for(int i = 0; i< m ;i++){
            int first = reader.nextInt();
            int next = reader.nextInt();
            indegree[next]++;
            nextList[first].add(next);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        int[] result = new int[n+1];

        for(int i = 1; i< n+1;i++){
            if(indegree[i] == 0){
                queue.offer(i);
                result[i] = 1;
            }
        }

        while(!queue.isEmpty()){
            int now = queue.poll();
            for(int next: nextList[now]){
                indegree[next]--;
                result[next] = Integer.max(result[next], result[now]+1);
                if(indegree[next]==0){
                    queue.offer(next);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i< n+1; i++){
            sb.append(result[i] +" ");
        }
        System.out.println(sb);
    }
    private static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        String next(){
            if(st == null || !st.hasMoreTokens()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
    }
}

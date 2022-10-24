package boj.graph_traversal.위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1516_게임개발 {
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int n = reader.nextInt();
        int[] time = new int[n+1];

        ArrayList<Integer>[] nextBuilds = new ArrayList[n+1];
        for(int i = 1; i< n+1; i++){
            nextBuilds[i] = new ArrayList<>();
        }
        int[] indegree = new int[n+1];

        for(int i = 1; i< n+1; i++){
            time[i] = reader.nextInt();
            while(true){
                int input = reader.nextInt();
                if(input == -1) break;

                nextBuilds[input].add(i);
                indegree[i]++;
            }
        }
        int[] totalTime = new int[n+1];

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i< n+1; i++){
            if(indegree[i] == 0){
                queue.offer(i);
                totalTime[i] = time[i];
            }
        }

        while(!queue.isEmpty()){
            int now = queue.poll();
            for(int i: nextBuilds[now]){
                indegree[i]--;
                totalTime[i] = Integer.max(totalTime[i], totalTime[now]+time[i]);
                if(indegree[i]==0){
                    queue.offer(i);
                }
            }
        }

        for(int i = 1; i< n+1 ;i++){
            System.out.println(totalTime[i]);
        }
    }
    static class FastReader{
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

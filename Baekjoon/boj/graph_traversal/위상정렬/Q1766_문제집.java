package boj.graph_traversal.위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1766_문제집 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = nextInt();
        int m = nextInt();

        //PriorityQueue<Integer>[]를 만들어서 그 인덱스보다 나중에 풀어야 하는 문제들을 저장한다.
        //우선순위 큐로 하는 이유는, 더 작은 수가 먼저 나와야 하기 때문이다.
        Queue<Integer>[] list = new Queue[n+1];
        int[] indegree = new int[n+1];
        for(int i = 1; i< n+1; i++){
            list[i] = new LinkedList<>();
        }
        for(int i = 0; i<m; i++){
            int a = nextInt();
            int b = nextInt();
            list[a].offer(b);
            indegree[b]++;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i =1; i<n+1; i++){
            if(indegree[i]==0){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int now = queue.poll();
            for(int i: list[now]){
                indegree[i]--;
                if(indegree[i] == 0){
                    queue.add(i);
                }
            }
            sb.append(now+" ");
        }
        System.out.println(sb);
    }
    private static String next(){
        if(st== null || !st.hasMoreTokens()){
            try{
                st = new StringTokenizer(br.readLine());
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
    private static int nextInt(){
        return Integer.parseInt(next());
    }
}

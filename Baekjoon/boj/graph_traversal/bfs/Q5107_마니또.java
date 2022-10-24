package boj.graph_traversal.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q5107_마니또 {
    private static BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) {
        int T = 1;
        StringBuilder sb = new StringBuilder();
        while(true){
            int n = nextInt();
            if(n == 0){
                break;
            }
            HashMap<String, Integer> indexMap = new HashMap<>();
            int[] manitto = new int[n];
            int index = 0;
            for(int i =0; i< n;i++){
                String p1 = next();
                String p2 = next();

                if(!indexMap.containsKey(p1)){
                    indexMap.put(p1, index++);
                }
                if(!indexMap.containsKey(p2)){
                    indexMap.put(p2, index++);
                }
                manitto[indexMap.get(p1)] = indexMap.get(p2);
            }

            //bfs: 사람 한명씩 시작점으로 돌면서 연결 고리(loop)의 개수를 구한다.
            int loop = 0;
            boolean[] visited = new boolean[n];
            for(int i = 0; i< n; i++){
                Queue<Integer> queue = new LinkedList<>();
                if(!visited[i]) {
                    queue.add(i);
                    loop++;
                }
                while(!queue.isEmpty()){
                    int now = queue.poll();
                    visited[now] = true;
                    if(!visited[manitto[now]]) {
                        queue.offer(manitto[now]);
                    }
                }
            }
            sb.append(T+" "+loop+"\n");
            T++;
        }
        System.out.println(sb);
    }
    private static String next(){
        if(st == null || !st.hasMoreTokens()){
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

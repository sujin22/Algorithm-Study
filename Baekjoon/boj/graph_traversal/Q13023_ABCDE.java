package boj.graph_traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q13023_ABCDE {
    /*
    * 1. 인접리스트 구현한다.
    * 2. 각 사람마다 dfs를 시작해본다.
    * 3. depth가 4인 경우가 있는지 확인
    * */
    private static int result = 0;

    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int N = reader.nextInt();
        int M = reader.nextInt();
        ArrayList<Integer>[] friends = new ArrayList[N];

        for(int i = 0; i<N; i++){
            friends[i] = new ArrayList<>();
        }

        for(int i =0; i<M; i++){
            int f1 = reader.nextInt();
            int f2 = reader.nextInt();
            friends[f1].add(f2);
            friends[f2].add(f1);
        }

        for(int i =0; i<N; i++){
            boolean[] visited = new boolean[N];
            dfs(i, 1, visited, friends);
            if(result == 0){
                break;
            }
        }
        System.out.println(result);
    }
    private static void dfs(int now, int depth, boolean[] visited, ArrayList<Integer>[] friends){
        if(depth == 4){
            result = 1;
            return;
        }
        visited[now] = true;
        for(int next:friends[now]){
            if(!visited[next]){
                dfs(next, depth+1, visited, friends);
            }
        }
        visited[now] = false;
    }
    private static class FastReader{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
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

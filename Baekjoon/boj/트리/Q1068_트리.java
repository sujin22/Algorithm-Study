package boj.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Q1068_트리 {
    /*
    * 1. int배열 parent 만듬
    * 2. root 노드는 미리 저장해줌
    * 3. 삭제: 1~N parent 돌면서 부모가 삭제할 애인 애 재귀로 싹 없애줌(-2 저장)
    * 4. dfs로 돌고,자식이 없으면 cnt++
    * */
    private final static int ROOT = -1;
    private final static int DELETE = -2;
    private static int cnt = 0;

    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int N = reader.nextInt();
        int[] parent = new int[N];

        int root = 0;
        for(int i = 0; i< N; i++){
            parent[i] = reader.nextInt();
            if(parent[i] == ROOT) root = i;
        }

        int rm = reader.nextInt();
        removeNode(parent, rm);

        boolean[] visited = new boolean[N];

        dfs(parent, visited, root);
        System.out.println(cnt);
    }
    private static void removeNode(int[] parent, int rm){
        for(int i = 0; i< parent.length; i++){
            if(parent[i] == rm){
                removeNode(parent, i);
            }
        }
        parent[rm] = DELETE;
    }
    private static void dfs(int[] parent,boolean[] visited, int now){
        visited[now] = true;


        if(parent[now]!= DELETE){
            boolean isLeaf = true;
            for(int i = 0; i< parent.length; i++){
                if(!visited[i] && parent[i] == now ){
                    dfs(parent, visited, i);
                    isLeaf = false;
                }
            }
            if(isLeaf) {
                cnt++;
//            System.out.println(now);
            }
        }

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

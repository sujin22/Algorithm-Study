package graph_traversal.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2667 {
    static int n;
    static int[][] graph;
    static boolean [][]check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        graph = new int[n][n];  //그래프
        check = new boolean[n][n];   //방문여부 저장하는 배열

        for (int i = 0; i < n; i++) {
            String rowStr = br.readLine();
            for(int j = 0; j<n; j++){
                graph[i][j] = rowStr.charAt(j) - '0';
            }
        }
//        for(int i = 0; i<n;i++){
//            System.out.println(Arrays.toString(graph[i]));
//        }

        int cnt = 0; //단지 수
        ArrayList<Integer> nArr = new ArrayList<Integer>();
        //그래프 전체를 도는데, bfs해서 개수세고
        //bfs하면 주변에 1있는경우만 순회하니까 순회끝나면 cnt 증가시키고 개수도 따로 배열에 저장한다.
        for(int i = 0;i<n;i++){
            for(int j = 0; j<n;j++){
                if(graph[i][j] == 1 && !check[i][j]){
                    int houseCnt = bfs(i,j);
                    if(houseCnt != 0){
                        nArr.add(houseCnt);
                        cnt++;
                    }
                }
            }
        }
        Collections.sort(nArr);

        System.out.println(cnt);
        for(int i = 0;i<nArr.size();i++){
            System.out.println(nArr.get(i));
        }


//        System.out.println();
//
//        for(int i = 0; i<n;i++){
//            System.out.println(Arrays.toString(graph[i]));
//        }

    }
    public static int bfs(int i, int j){
        int cnt = 1;//집 개수
        Queue<Vertex> q = new LinkedList<Vertex>();
        q.add(new Vertex(i,j));
        check[i][j] = true;

        while(!q.isEmpty()){
            Vertex tmp = q.poll();
            if(tmp.i-1 >=0){
                if(graph[tmp.i-1][tmp.j]==1 && !check[tmp.i-1][tmp.j]){
                    graph[tmp.i-1][tmp.j] = graph[tmp.i][tmp.j]+1;
                    check[tmp.i-1][tmp.j] = true;
                    q.add(new Vertex(tmp.i-1, tmp.j));
                    cnt++;
                }
            }
            if(tmp.i+1 < n){
                if(graph[tmp.i+1][tmp.j]==1 && !check[tmp.i+1][tmp.j]){
                    graph[tmp.i+1][tmp.j] = graph[tmp.i][tmp.j]+1;
                    check[tmp.i+1][tmp.j] = true;
                    q.add(new Vertex(tmp.i+1, tmp.j));
                    cnt++;
                }
            }
            if(tmp.j-1 >= 0){
                if(graph[tmp.i][tmp.j-1]==1 && !check[tmp.i][tmp.j-1]){
                    graph[tmp.i][tmp.j-1] = graph[tmp.i][tmp.j]+1;
                    check[tmp.i][tmp.j-1] = true;
                    q.add(new Vertex(tmp.i, tmp.j-1));
                    cnt++;
                }
            }
            if(tmp.j+1 < n){
                if(graph[tmp.i][tmp.j+1]==1 && !check[tmp.i][tmp.j+1]){
                    graph[tmp.i][tmp.j+1] = graph[tmp.i][tmp.j]+1;
                    check[tmp.i][tmp.j+1] = true;
                    q.add(new Vertex(tmp.i, tmp.j+1));
                    cnt++;
                }
            }
        }
        return cnt;
    }
    public static class Vertex{
        public int i;
        public int j;
        public Vertex(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}

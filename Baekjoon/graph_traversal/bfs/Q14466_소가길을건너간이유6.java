package graph_traversal.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q14466_소가길을건너간이유6 {
    /*[골드4] Q14466 - 소가 길을 건너간 이유6*/
    //상하좌우 이동 가능하고, 입력받은 길만 이동할 수 없도록 설정하여 소의 각 위치로부터 bfs 돌린다.
    //bfs돌고나서 visited가 false면 길을 건너야만 이동할 수 있는 경우이므로 세어 준다.

    static int N,K,R;
    static ArrayList<Unit>[][] edges;
    static ArrayList<Unit> cows = new ArrayList<>();
    static int[] dx = new int[]{0,0,-1,1};
    static int[] dy = new int[]{1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   //N*N크기 밭
        K = Integer.parseInt(st.nextToken());   //소 마릿수
        R = Integer.parseInt(st.nextToken());   //길 개수

        //주어지는 길(bfs에서 이동할 수 없는 길)을 인접리스트로 구현
        edges = new ArrayList[N+1][N+1];
        for(int i = 0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            int start_i = Integer.parseInt(st.nextToken());
            int start_j = Integer.parseInt(st.nextToken());
            int end_i = Integer.parseInt(st.nextToken());
            int end_j = Integer.parseInt(st.nextToken());

            if(edges[start_i][start_j] == null)
                edges[start_i][start_j] = new ArrayList<>();
            if(edges[end_i][end_j] == null)
                edges[end_i][end_j] = new ArrayList<>();

            edges[start_i][start_j].add(new Unit(end_i, end_j));
            edges[end_i][end_j].add(new Unit(start_i, start_j));
        }

        //소의 위치 저장
        for(int i = 0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            cows.add(new Unit(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }

        //각각의 소를 시작점으로 bfs 돌려서 visited가 false인 개수를 리턴받는다
        int result = 0;
        for(Unit cow: cows){
            result+= bfs(cow);
        }
        System.out.println(result/2);   //몇 쌍인지 물어봤으므로, 나누기 2
    }
    static int bfs(Unit start){
        boolean[][] visited = new boolean[N+1][N+1];

        Queue<Unit> q = new ArrayDeque<>();
        q.add(start);
        visited[start.i][start.j] = true;
        while(!q.isEmpty()){
            Unit now = q.poll();
            loop:
            for(int i = 0; i<4; i++){
                //인접 정점 방문
                int nextI = now.i+dx[i];
                int nextJ = now.j+dy[i];

                //범위를 벗어나면 continue
                if(nextI<1 || nextI>=N+1 || nextJ<1 || nextJ>=N+1)  continue;
                //방문한 적이 있으면 continue
                if(visited[nextI][nextJ])   continue;

                //현위치의 인접리스트(이동불가능한길)에 존재하는지 체크해서 존재하면 continue
                if(edges[now.i][now.j]!= null){
                    for(Unit edge: edges[now.i][now.j]){
                        if(edge.i==nextI && edge.j == nextJ){
                            continue loop;
                        }
                    }
                }

                //이동 가능할 경우
                visited[nextI][nextJ] = true;
                q.add(new Unit(nextI, nextJ));
            }
        }

        //각 소의 위치 중 방문하지 않은 곳 있으면 개수 세어 준다.
        int cnt = 0;
        for(Unit cow: cows){
            if(!visited[cow.i][cow.j]){
                cnt++;
            }
        }
        return cnt;
    }
    static class Unit {
        int i,j;

        public Unit(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}

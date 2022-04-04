package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q18500_미네랄2 {
    /*[골드2]Q18500 - 미네랄2*/
    static int dx[] = new int[] {0,0,1,-1};
    static int dy[] = new int[] {1,-1,0,0};
    static int R,C;
    static char[][] mineral;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        mineral = new char[R][C];

        for(int i = 0; i<R; i++){
            String str = br.readLine();
            for(int j = 0; j<C; j++){
                mineral[i][j] = str.charAt(j);
            }
        }

        //0행 height: R
        //R-1행 height: 1
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int c = 0; c<N; c++){
            visited = new boolean[R][C];
            int height = R - Integer.parseInt(st.nextToken());
            int crashCol = -1;
            if(c%2==0){
                //왼쪽에서 오른쪽으로 막대기 던짐
                for(int i = 0; i<C;i++){
                    if(mineral[height][i] == 'x'){
                        mineral[height][i] = '.';
                        crashCol = i;
                        break;
                    }
                }
            }else{
                //오른쪽에서 왼쪽으로 막대기 던짐
                for(int i = C-1; i>=0;i--){
                    if(mineral[height][i] == 'x'){
                        mineral[height][i] = '.';
                        crashCol = i;
                        break;
                    }
                }
            }

            if(crashCol == -1) continue; //부서진 칸 없음

            //부서진 칸 주변(상,하, 좌, 우)칸 기준으로 bfs를 통해 클러스터 확인,
            // 분리된 클러스터가 공중에 떠있다면 아래로 내려준다.
            for(int d = 0; d<4; d++){
                int checkI = height+dx[d];
                int checkJ = crashCol+dy[d];
                if(checkI<0 || checkI>=R || checkJ<0 || checkJ>=C||visited[checkI][checkJ]||mineral[checkI][checkJ]=='.'){
                    continue;
                }
                bfs(new Pos(checkI, checkJ));
            }
        }

        for(int i = 0 ;i< R; i++){
            for(int j = 0; j<C; j++){
                System.out.print(mineral[i][j]);
            }
            System.out.println();
        }
    }

    //분리된 클러스터 bfs로 탐색
    static void bfs(Pos start){
        ArrayList<Pos> cluster = new ArrayList<>();   //클러스터 속한 칸 저장
        int[] floor = new int[C];
        Arrays.fill(floor, -1);

        Queue<Pos> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start.i][start.j] = true;
        while(!queue.isEmpty()){
            Pos now = queue.poll();
            cluster.add(now);
            if(floor[now.j] < now.i)    floor[now.j] = now.i;

            for(int i = 0; i<4; i++){
                Pos next = new Pos(now.i+dx[i], now.j + dy[i]);
                if(next.i <0 || next.i>=R || next.j<0 || next.j >=C ||visited[next.i][next.j] || mineral[next.i][next.j] != 'x'){
                    continue;
                }
                queue.add(next);
                visited[next.i][next.j] = true;
            }
        }

        Collections.sort(cluster);

        //클러스터 내릴 수 있는 경우, 아래로 내리기
        //클러스터 전체를 돌며, 하나 아래로 모두가 내려갈 수 있는지 체크, 내려갈 수 있으면 내린다.
        loop:
        while(true){
            for(int i = 0; i<cluster.size();i++){
                Pos now = cluster.get(i);
                if(!cluster.contains(new Pos(now.i+1, now.j)) && (now.i+1==R || mineral[now.i+1][now.j] == 'x')){
                    break loop;
                }
            }
            for(int i = 0; i<cluster.size();i++) {
                Pos now = cluster.get(i);
                mineral[now.i + 1][now.j] = mineral[now.i][now.j];
                mineral[now.i][now.j] = '.';
                cluster.set(i, new Pos(now.i + 1, now.j));
            }
        }
    }
    static class Pos implements Comparable<Pos>{
        int i,j;

        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object obj) {
            return this.i == ((Pos)obj).i && this.j == ((Pos)obj).j;
        }

        @Override
        public int compareTo(Pos o) {
            if(this.i != o.i)   return o.i - this.i;
            else    return this.j - o.j;
        }
    }
}

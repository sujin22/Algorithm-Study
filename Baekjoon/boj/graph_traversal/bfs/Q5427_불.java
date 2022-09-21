package boj.graph_traversal.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q5427_불 {
    /*[골드4]Q5427-불*/

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, 1, -1, 0};

    static int w, h;
    static char[][] map;
    static boolean[][] visited;
    static Queue<Pos> fire;

    static String result;

    public static void main(String[] args) throws IOException {
        result = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());   //TC개수

        //TC만큼 반복한다.
        for(int t = 0; t<tc;t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            int nowX, nowY;
            nowX = nowY = -1;

            map = new char[h][w];
            visited = new boolean[h][w];
            fire = new ArrayDeque<>();  //새로 생긴 불들을 저장할 큐

            for(int i = 0 ;i<h;i++){
                String str = br.readLine();
                for(int j = 0; j<w;j++){
                    map[i][j] = str.charAt(j);
                    //불(*)일 경우 fire큐에 저장
                    if(map[i][j] == '*')
                        fire.offer(new Pos(i,j));

                    //@일 경우 시작위치
                    if(map[i][j] == '@'){
                        nowX = i;
                        nowY = j;
                    }
                }
            }
            bfs(nowX, nowY);
        }
        System.out.println(result);
    }

    static void bfs(int x, int y){
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(x,y));
        visited[x][y] = true;

        int time = 0;

        while(!q.isEmpty()){
            burn(); //불을 한번 번지게 함
            time++;

            //큐에 들어있는 좌표를 전부 검사한다.(현재시간의 불번짐에서 체크할 위치들임)
            //검사하며 동서남북 중 이동할 수 있는 위치를 저장한다.
            int size = q.size();
            for(int i = 0; i<size; i++){
                Pos pos = q.poll();
                //동서남북 체크
                for(int j=0;j<4;j++){
                    int nextX = pos.x + dx[j];
                    int nextY = pos.y + dy[j];

                    //map 밖으로 탈출하는 경우
                    if(nextX<0 || nextX>=h || nextY<0 || nextY>=w){
                        result+=time+"\n";
                        return;
                    }
                    //이동할 수 있는 위치일 경우
                    if(map[nextX][nextY]=='.' && !visited[nextX][nextY]){
                        q.offer(new Pos(nextX, nextY));
                        visited[nextX][nextY] = true;
                    }
                }
            }
        }
        //탈출못했을경우 return하지 못하고 여기까지 온다.
        result+="IMPOSSIBLE\n";

    }

    //동서남북으로 불이 번져나가게 하는 함수
    static void burn(){
        int size = fire.size();
        for(int i = 0; i<size; i++){
            Pos pos = fire.poll();

            //방향벡터로 동서남북 찾아서,
            // 불번짐이 가능할 경우 *로 바꾸고 fire 큐에 추가해준다
            for(int j = 0; j<4; j++) {
                int nowX = pos.x + dx[j];
                int nowY = pos.y + dy[j];
                if(nowX>=0 && nowX<h && nowY>=0 && nowY<w){
                    if(map[nowX][nowY] == '.'
                            || map[nowX][nowY] == '@'){
                        map[nowX][nowY] = '*';
                        fire.offer(new Pos(nowX, nowY));
                    }
                }
            }
        }
    }

    static class Pos{
        int x,y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

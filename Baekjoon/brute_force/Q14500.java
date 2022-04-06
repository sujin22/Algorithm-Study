package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14500 {
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,1,-1,0};
    static int[][] data;
    static boolean[][] visited;
    static int max = 0;

    static int N,M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        data = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M;j++){
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++) {
                dfs(i,j, 0,0);//ㅗ모양 제외 모든 모양 찾기
                searchHatShape(i,j);    //ㅗ모양찾기
            }
        }
        System.out.println(max);

    }

    public static void dfs(int x, int y, int depth, int sum){
        //depth: 현재까지 sum에 더한 개수
        if(depth == 4){
            max  = Math.max(sum, max);
            return;
        }
        for(int i = 0 ;i<4;i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if(nextX<0 || nextX>=N || nextY<0||nextY>=M ){
                continue;
            }
            if(!visited[nextX][nextY]){
                visited[nextX][nextY] = true;
                int result = sum + data[nextX][nextY];
                dfs(nextX, nextY, depth+1, result);
                visited[nextX][nextY] = false;//이 위치를 선택하지 않은 경우에서 visited처리되어있지 않도록 다시 false처리
            }
        }
    }

    public static void searchHatShape(int x, int y){
        int sum = data[x][y];
        int inValid = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i<4;i++){
            if(inValid>=2)  return; //무효한게 2개 이상이면 ㅗ모양 못만듬

            int posX = x+dx[i];
            int posY = y+dy[i];

            if(posX<0 || posX>=N || posY<0||posY>=M){
                inValid++;
                continue;   //무효한 위치이면 넘어감
            }

            sum+=data[posX][posY];  //십자 모양 모두 더하기
            min = Math.min(min, data[posX][posY]);  //상하좌우중 가장 작은 값 찾기
        }
        if(inValid==0){
            sum-= min;  //상하좌우 모두 유효할 경우 가장 작은 값 빼기(어차피 최대값 구하는 것이므로)
        }
        max = Math.max(max, sum);
    }
}

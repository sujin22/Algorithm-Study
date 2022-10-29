package boj.graph_traversal.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q7576_토마토 {
    //bfs
    /*
     * 1. di, dj 배열 만들어서 상하좌우 좌표 이동할 수 있도록 한다.
     * 2. Pos 클래스 만들어서 i, j좌표 저장
     * 3. Queue에 익은 토마토 넣기
     * 4. 익은 토마토 주변 토마토를 익힘 처리 해준다 ->Queue size만큼 poll하면서 동작
     * 5. 새로 익은 토마토만 queue에 넣어 준다.
     */
    private static int[] di = {0, 0, 1, -1};
    private static int[] dj = {1, -1, 0, 0};
    private final static int NONE = -1;
    private final static int RIPE = 1;
    private final static int UNRIPE = 0;


    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int N = reader.nextInt();
        int M = reader.nextInt();
        int[][] arr = new int[M][N];
        int emptyCnt = 0;
        int ripeCnt = 0;
        Queue<Pos> queue = new ArrayDeque<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = reader.nextInt();
                if (arr[i][j] == RIPE) {
                    queue.offer(new Pos(i, j));
                    ripeCnt++;
                } else if(arr[i][j]==NONE){
                    emptyCnt++;
                }
            }
        }
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                Pos now = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nextI = now.i + di[i];
                    int nextJ = now.j + dj[i];
                    if (nextI < 0 || nextI >= M || nextJ < 0 || nextJ >= N) continue;
                    if (arr[nextI][nextJ] == UNRIPE) {
                        queue.offer(new Pos(nextI, nextJ));
                        arr[nextI][nextJ] = RIPE;
                        ripeCnt++;
                    }
                }
                size--;
            }
//            print(arr);
            if(!queue.isEmpty()){
                result++;
            }
        }
        if(ripeCnt == (N*M - emptyCnt)){
            System.out.println(result);
        }else{
            System.out.println(-1);
        }
    }
    private static void print(int[][] arr){
        System.out.println();
        for(int i =0; i<arr.length; i++){
            for(int j = 0; j<arr[i].length;j++){
                System.out.print(String.format("%2d", arr[i][j]));
            }
            System.out.println();
        }
    }

    private static class Pos {
        int i, j;

        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            if (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

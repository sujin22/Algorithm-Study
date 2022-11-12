package boj.LIS;

import java.io.*;
import java.util.*;

public class Q25543_X만들기 {
    private static class Pos{
        int x, y;
        public Pos(int x , int y ){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Pos> points = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points.add(new Pos(x, y));
        }

        Collections.sort(points, (o1, o2)-> {
            return o1.x - o2.x;
        });



        int[][] area = new int[5][N];

        //각 점을 원점으로 했을 때,
        //2,3사분면의 LIS 구하기
        for (int i = 0; i < N; i++) {
            //원점은 포함하지 않는 LIS 구하기 위해 1이 아닌 0으로 저장(그니까 사실상 할 필요 없음)
//            area[2][i] = 0;
//            area[3][i] = 0;
            for (int j = 0; j < i; j++) {
                Pos posI = points.get(i); //원점
                Pos posJ = points.get(j); //비교할 점
                //j점이 원점 기준으로 2사분면에 있을 경우( x가 더 작고, y가 더 큼)
                if (posI.x > posJ.x && posI.y < posJ.y) {
                    area[2][i] = Math.max(area[2][j] + 1, area[2][i]);
                }
                //j점이 원점 기준으로 3사분면에 있을 경우(x가 더 작고, y가 더 작음)
                else if (posI.x > posJ.x && posI.y > posJ.y) {
                    area[3][i] = Math.max(area[3][j] + 1, area[3][i]);
                }
            }
        }

        //x좌표 정렬 시 감소해야하므로, 뒤에서부터 검사해서 증가하는지 확인하기
        for (int i = N-1; i >= 0; i--) {
//            area[1][i] = 0;
//            area[4][i] = 0;
            for (int j = N-1; j > i; j--) {
                Pos posI = points.get(i);
                Pos posJ = points.get(j);
                //원점 기준으로 1사분면에 있을 경우
                if(posI.x < posJ.x && posI.y < posJ.y){
                    area[1][i] = Math.max(area[1][j] + 1, area[1][i]);
                }
                //원점 기준으로 4사분면에 있을 경우
                else if(posI.x < posJ.x && posI.y > posJ.y){
                    area[4][i] = Math.max(area[4][j] + 1, area[4][i]);
                }
            }
        }

        // 모든 점 기준으로 1,2,3,4 사분면의 LIS 길이의 합이 가장 긴 점을 원점으로 한다.
        int max = 0;
        loop:
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for(int j = 1; j< 5; j++){
                if(area[j][i] < 1){
                    continue loop;
                }
                sum += area[j][i];
            }
            max = Math.max(max, sum);
        }

        if(max == 0){
            System.out.println(-1);
        }else{
            System.out.println(N - max - 1); //1~4사분면의 LIS길이와 원점 빼준다.(제거한 압정의 개수 출력)
        }
    }
}
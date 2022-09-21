package boj.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q17779_게리맨더링2 {
    /*[골드4] Q17779 - 게리맨더링2*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] popul = new int[N+1][N+1];
        int total = 0;
        for(int i = 1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                popul[i][j] = Integer.parseInt(st.nextToken());
                total+=popul[i][j];
            }
        }
        int min = Integer.MAX_VALUE;

        for(int x=1; x<=N; x++){
            for(int y = 1; y<=N; y++){
                for(int d1=1; d1<=N; d1++){
                    for(int d2 = 1; d2<=N; d2++){
                        if(d1>=1 && d2>=1 && 1<=x && x<x+d1+d2 && x+d1+d2<=N
                                && 1<=y-d1 && y-d1<y && y<y+d2 && y+d2<=N){
                            //경계선 설정하기
                            boolean border[][] = new boolean[N+1][N+1];
                            for(int i = 0; i<=d1;i++){
                                border[x+i][y-i] = true;
                                border[x+d2 +i][y+d2 -i] = true;
                            }
                            for(int i = 0; i<=d2; i++){
                                border[x +i][y +i] = true;
                                border[x+d1 + i][y-d1 + i] = true;
                            }
                            int[] area = new int[5];

                            //1번 구역
                            for(int r = 1; r<x+d1;r++) {
                                for (int c = 1; c <= y; c++) {
                                    if (border[r][c]) break;
                                    area[0] += popul[r][c];
                                }
                            }
                            //2번 구역
                            for(int r = 1; r<=x+d2;r++) {
                                for (int c = N; c >y; c--) {
                                    if (border[r][c]) break;
                                    area[1] += popul[r][c];
                                }
                            }
                            //3번 구역
                            for(int r = x+d1; r<=N;r++) {
                                for (int c = 1; c <y-d1+d2 ; c++) {
                                    if (border[r][c]) break;
                                    area[2] += popul[r][c];
                                }
                            }
                            //4번 구역
                            for(int r = x+d2+1; r<=N;r++) {
                                for (int c = N; c >= y-d1+d2; c--) {
                                    if (border[r][c]) break;
                                    area[3] += popul[r][c];
                                }
                            }
                            //5번 구역
                            area[4] = total - area[0]-area[1]-area[2]-area[3];

                            Arrays.sort(area);
                            min = Integer.min(min, area[4]-area[0]);
                        }
                    }
                }
            }
        }
        System.out.println(min);
    }
}

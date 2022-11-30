package boj.simulation;

import java.util.Scanner;

public class Q3164_패턴 {
    /*
    * 1. y축 기준으로 짝수번째 행의 칸을 세어 준다.
    * 2. x축 기준으로 짝수번째 행의 칸을 세어 준다.
    * */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pos leftBottom = new Pos(sc.nextInt(), sc.nextInt());
        Pos rightTop = new Pos(sc.nextInt(), sc.nextInt());

        long result = 0;

        //y축 기준 먼저 세어 준다.
        //짝수번째 칸부터 시작
        int startY = leftBottom.y + 1;
        if(startY % 2 != 0){
            startY++;
        }
        for(int y = startY; y<=rightTop.y; y+=2){
            if(y <= leftBottom.x){
                continue;
            }
            result += Math.min(y, rightTop.x) - leftBottom.x;
        }

        //x축 기준으로 세어 준다.
        //짝수번째 칸에서 시작
        int startX = leftBottom.x + 1;
        if(startX % 2 != 0){
            startX++;
        }
        for(int x = startX; x<=rightTop.x; x+=2){
            if(x-1 <= leftBottom.y){
                continue;
            }
            result += Math.min(x-1, rightTop.y) - leftBottom.y;
        }

        System.out.println(result);
    }

    private static class Pos{
        int x,y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

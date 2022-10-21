package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2697_줄자접기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double length = Double.parseDouble(br.readLine());
        double[][] point = new double[3][2]; //빨, 파, 노
        for(int i = 0;i<3; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            point[i][0] = Double.parseDouble(st.nextToken());
            point[i][1] = Double.parseDouble(st.nextToken());
        }
        double start = 0;//줄자의 제일 앞 부분(한번이라도 접힌 이후에는 접힌 부분)
        for(int i =0; i<3; i++){
            if(point[i][0]==point[i][1]){
                //이미 만나있으면
                continue;
            }
            double fold = getFoldPosition(point[i][0],point[i][1]);
            updatePos(point, fold, i);
            length = updateLength(length, fold - start); //줄자의 길이는 접힌 만큼 짧아진다.
            start = fold;
        }
        System.out.println(String.format("%.1f",length));
    }
    //접히는 위치 반환하는 메소드
    private static double getFoldPosition(double p1, double p2){
        double diff = Math.abs(p2 -p1);
        return (p1<p2)? p1+(diff/2):p2+(diff/2);
    }
    private static void updatePos(double[][] point, double fold, int nowColor){
        for(int i = nowColor+1; i<3; i++){
            for(int j = 0; j<2; j++){
                if(point[i][j] < fold){
                    point[i][j] = point[i][j] + (fold-point[i][j])*2;
                }
            }
        }
    }
    private static double updateLength(double length, double diff){
        return Math.max(length - diff, diff);
    }

}

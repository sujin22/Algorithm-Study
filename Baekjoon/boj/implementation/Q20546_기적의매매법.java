package boj.implementation;

import java.io.IOException;
import java.util.Scanner;

public class Q20546_기적의매매법 {
    /*[브론즈1]Q20546 - 기적의 매매법*/
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int Jcash = sc.nextInt(); //준현 현금
        int Scash = Jcash; //성민 현금

        int Jbuy = 0;    //준현 매수 주식 수
        int Sbuy = 0;    //성민 매수 주식 수

        int cnt = 0;    //전일 주가 대비 상승 or 하락 체크
        int yesterday = 0;  //전일 주가
        for(int i = 0; i<14;i++){
            int price = sc.nextInt();
            /*준현 매매법*/
            Jbuy += Jcash/price;
            Jcash -= (Jcash/price) * price;

            /*성민 매매법*/
            if(yesterday-price>0){
                //전일 대비 하락했을 경우
                if(cnt<=0) cnt--;
                else cnt=-1;
            }else if(yesterday - price <0){
                //전일 대비 상승했을 경우
                if(cnt>=0) cnt++;
                else cnt=1;
            }else{
                cnt = 0;
            }

            if(cnt == -3){
                //3일 연속 하락한 주식 - 전량매수
                Sbuy += Scash/price;
                Scash -= (Scash/price) *price;
            }
            if(cnt == 3){
                //3일 연속 상승 주식 - 전량매도
                Scash += Sbuy * price;
                Sbuy = 0;
            }
            yesterday = price;
        }

        //수익 계산
        int Jprofit = Jcash + yesterday*Jbuy;
        int Sprofit = Scash + yesterday*Sbuy;

        if(Jprofit>Sprofit) System.out.println("BNP");
        else if(Jprofit<Sprofit) System.out.println("TIMING");
        else System.out.println("SAMESAME");
    }

}

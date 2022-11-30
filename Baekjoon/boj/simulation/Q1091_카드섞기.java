package boj.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1091_카드섞기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] target = new int[N]; //P
        int[] mix = new int[N]; //S

        int[] origin = new int[N];
        int[] cards = new int[N];
        for(int i = 0; i< N; i++){
            target[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i< N; i++){
            origin[i] = i%3;
            cards[i] = origin[i];
            mix[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;

        while(true){
            //target과 똑같아졌거나
            //한 사이클 다 돌아서 처음 배열과 똑같아졌을 경우(없는 것) 멈춘다.
            if(Arrays.equals(cards, target)){
                break;
            }else if(result!=0 && Arrays.equals(cards, origin)){
                result = -1;
                break;
            }
            int[] next = new int[N];
            for(int i = 0; i<N; i++){
                next[i] = cards[mix[i]];
            }
            result++;
            cards = next;
        }
        System.out.println(result);
    }
}

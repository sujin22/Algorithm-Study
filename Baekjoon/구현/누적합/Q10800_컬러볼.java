package implementation.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Q10800_컬러볼 {
    /*[골드3]Q10800_컬러볼*/
    /*요약
    * size순으로 정렬하고, 본인보다 작은 애들만 검사한다.
    * 누적합을 이용하는데, sum 누적합과 color별 누적합 둘 다 저장한다.
    * (본인까지의 누적합 - 본인과 같은 색의 누적합)으로 결과를 구할 수 있다.*/
    static int n;
    static Ball[] balls;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        balls = new Ball[n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            balls[i] = new Ball(i, color, size);
        }
        Arrays.sort(balls, Comparator.comparingInt(o -> o.size));

        int[] color = new int[n+1]; //1부터 시작함
        int [] result = new int[n];
        int checkIndex = 0;
        int sum = 0;
        for(int i = 0; i<n; i++){
            Ball cur = balls[i];
            while(balls[checkIndex].size<cur.size){
                sum+=balls[checkIndex].size;
                color[balls[checkIndex].color] += balls[checkIndex].size;
                checkIndex++;
            }
            result[cur.index] = sum - color[cur.color];
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<n;i++){
            sb.append(result[i]+"\n");
        }
        System.out.println(sb);
    }
    static class Ball{
        int index, color, size;
        public Ball(int index, int color, int size) {
            this.index = index;
            this.color = color;
            this.size = size;
        }
    }
}

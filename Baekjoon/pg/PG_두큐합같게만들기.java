package pg;

import java.util.ArrayDeque;
import java.util.Queue;

public class PG_두큐합같게만들기 {
    class Solution {
        public int solution(int[] queue1, int[] queue2) {
            Queue<Integer> q1 = new ArrayDeque<>();
            Queue<Integer> q2 = new ArrayDeque<>();

            long sum1=0, sum2 = 0;
            for(int i = 0; i<queue1.length; i++){
                sum1+=queue1[i];
                sum2+=queue2[i];

                q1.add(queue1[i]);
                q2.add(queue2[i]);
            }

            //합이 홀수면 불가능(절대 같아질 수 없음)
            if((sum1+sum2)%2!=0) return -1;

            int limit = queue1.length * 2;

            int answer = 0;
            while(true){
                //전체 경우를 다 탐색할 때까지 안 같아졌을 경우 불가능
                if(answer>limit)    return -1;
                if(sum1 == sum2)    return answer;

                //더 큰쪽에서 더 작은쪽으로 더해줌
                if(sum1>sum2){
                    int now = q1.poll();
                    q2.add(now);
                    sum1-=now;
                    sum2+=now;
                }else{
                    int now = q2.poll();
                    q1.add(now);
                    sum1+=now;
                    sum2-=now;
                }
                answer++;
            }
        }
    }
}

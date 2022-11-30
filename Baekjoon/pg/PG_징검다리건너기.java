package pg;
import java.util.*;
public class PG_징검다리건너기 {
    class Solution {
        public int solution(int[] stones, int k) {
            // return sol1(stones, k); //효율성테스트 전체 시간초과
            //return sol2(stones, k); //sliding window 사용(효율성테스트 일부 시간초과)
            return sol3(stones, k); //이분탐색 사용(성공)
        }
        public int sol3(int[] stones, int k){
            int low = 0;
            int high = 200000000;

            int cross = 0;

            //이분탐색
            while(low <= high){
                int mid = (low + high)/2;

                if(canCross(mid, k, stones)){
                    low = mid+1;
                    cross = Math.max(cross, mid);
                }else{
                    high = mid-1;
                }
            }
            return cross;
        }

        public boolean canCross(int n, int k, int[] stones){
            int zeroCnt = 0;
            //k개 연속으로 못건너는 돌(n명이 건넜을 때 0보다 작아지는 돌)이 있으면
            //못건너는거임

            for(int i = 0; i< stones.length; i++){
                if(stones[i] - n < 0){
                    zeroCnt++;
                }else{
                    zeroCnt= 0;
                }

                if(zeroCnt == k){
                    return false;
                }
            }

            return true;

        }
        public int sol2(int[] stones, int k){
        /*
        [dp + sliding window]

        dp에 그 디딤돌에 도달할 수 있는 횟수를 저장한다.
        이전 k개 dp중 제일 큰 애, 본인 횟수 비교해서 더 작은 애 저장

        k개를 일일히 이중for문으로 검사하면 N^2이어서 시간 초과 남
        어차피 지금 나가는애 빼주고, 새로 들어오는 애만 넣어주면 되니까
        PriorityQueue에 지금 나가는애 빼고, 새로 들어오는 애 넣음
        */
            int[] dp = new int[stones.length+1];
            PriorityQueue<Integer> window = new PriorityQueue<>(Collections.reverseOrder());
            for(int i = 0; i< k; i++){
                dp[i] = stones[i];
                window.add(dp[i]);
                // System.out.print(dp[i]+" ");
            }

            for(int i = k; i <= stones.length; i++){
                if(i == stones.length){
                    dp[i] = window.peek();
                    break;
                }else{
                    dp[i] = Math.min(stones[i], window.peek());
                }
                // System.out.print(dp[i]+" ");

                window.remove(dp[i-k]);
                window.add(dp[i]);
            }

            return dp[stones.length];
        }

//     public int sol1(int[] stones, int k){
//         int cross = Arrays.stream(stones).min().getAsInt();
//         for(int i = 0; i<stones.length; i++){
//             stones[i] -= cross;
//         }

//         /*
//         1. 가장 가까운 디딤돌로만 건너뛸 수 있음
//         2. 디딤돌의 숫자가 0이 되면 더 이상 밟을 수 없음
//         3. 한 번에 최대 k개 뛰어넘을 수 있음
//         -> 1 ~ k 까지 중에 도착지점 또는 디딤돌이 없으면 건너지 못하는 것!
//         */

//         while(true){
//             int pos = -1;
//             while(pos < stones.length){
//                 // System.out.println(pos);
//                 boolean isJumped = false;
//                 for(int i = 1; i<= k; i++){
//                     if(pos+i < stones.length && stones[pos+i] > 0){
//                         //건너갈 디딤돌 있다. 건너감
//                         stones[pos+i]--;
//                         pos += i;
//                         isJumped = true;
//                         break;
//                     }else if(pos+i >= stones.length){
//                         //i만큼 이동했을 때, 도착한다.
//                         pos+=i;
//                         isJumped = true;
//                         break;
//                     }
//                 }
//                 if(!isJumped){
//                     return cross;
//                 }


//             }
//             cross++;
//         }
//     }


    }
}

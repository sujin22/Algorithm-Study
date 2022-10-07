package test.kakao_blind;

import java.util.Arrays;

public class s3 {
    public static void main(String[] args) {
        Solution s= new Solution();
        int[] result = s.solution(new int[][]{{40, 10000}, {25, 10000}},
                new int[]{7000, 9000});
        System.out.println();
        System.out.println(Arrays.toString(result));
    }
    static class Solution {
        public int[] solution(int[][] users, int[] emoticons) {
            int[] answer = new int[2];
            int[][] emoticonResult = new int[emoticons.length][2];  //0: 최대가입자, 1:최대판매액

            long[][][] userCost = new long[users.length][emoticons.length][5];
            long[][] emoticonRateCost = new long[emoticons.length][5];

            //1. emoticon별로 rate 10,20,30,40마다 user의 이모티콘 구매액을 구한다.
            for(int user = 0; user<users.length;user++){
                for(int e = 0; e<emoticons.length;e++){
                    for(double rate = 0.1 ; rate<0.5 ; rate+=0.1){
                        //구매기준 할인율보다 많이 할인하고있으면 구매
                        if(users[user][0]<rate*100){
                            userCost[user][e][(int)rate*10] = (long)(emoticons[e]*(1-rate));
                            emoticonRateCost[e][(int)rate*10] += (long)(emoticons[e]*(1-rate));
                        }
                    }
                }
            }
            int[] emotiRate = new int[emoticons.length];//1234중 할인율 저장
            for(int e = 0; e<emoticons.length;e++){
                int maxRate = 1;
                for(int rate = 1;rate<5;rate++){
                    if(emoticonRateCost[e][maxRate]<emoticonRateCost[e][rate]){
                        maxRate = rate;
                    }
                }
                emotiRate[e] = maxRate;
            }

            long[][] userTotal = new long[users.length][2];
            //유저별로 총 얼마 사용했는지 저장
            for(int user = 0; user<users.length;user++){
                for(int e = 0; e<emoticons.length; e++){
                    userTotal[user][0] += userCost[user][e][emotiRate[e]];
                }
                //기준금액보다 더 많이 썼으면, 플러스를 구매한다.
                if(userTotal[user][0] >= users[user][1]){
                    userTotal[user][1]++;
                    userTotal[user][0]=0;
                }
            }












            for(int i =0; i<emoticons.length;i++){
                System.out.println(Arrays.toString(emoticonResult[i]));

                answer[0] += emoticonResult[i][0];
                answer[1] += emoticonResult[i][1];
            }

            return answer;
        }
    }
}

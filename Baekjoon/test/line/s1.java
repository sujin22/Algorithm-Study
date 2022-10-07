package test.line;

import java.util.HashMap;

public class s1 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int result = s.solution(new int[][]{{2, 10}, {7, 1}, {2, 5}, {2, 9},{7, 32}});
        System.out.println(result);
    }
    static class Solution {
        public int solution(int[][] queries) {
            int answer = 0;
            HashMap<Integer, int[]> map = new HashMap<>();
            //map에 저장
            for(int i = 0; i<queries.length; i++){
                map.put(queries[i][0], new int[]{0,0});
            }
            for(int i = 0; i<queries.length; i++){
                int arrIdx = queries[i][0];
                int addNum = queries[i][1];
                int nowLength = map.get(arrIdx)[0];
                int nowContent = map.get(arrIdx)[1];

                int nextContent = nowContent+addNum;
                if(nowLength < nextContent){
                    if(nowLength!=0) answer+=nowContent;
                    map.put(arrIdx, new int[]{findNextPowerOf2(nextContent), nextContent});
                }else{
                    map.put(arrIdx, new int[]{nowLength, nextContent});
                }
            }

            return answer;
        }
    }
    // 32비트 숫자 `n`의 다음으로 높은 2의 거듭제곱을 계산합니다.
    public static int findNextPowerOf2(int n)
    {
        // `n` 감소(`n` 자체가 2의 거듭제곱인 경우 처리)
        n--;

        // 마지막 설정 비트 이후의 모든 비트 설정
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;

        // `n`을 증가시키고 반환
        return ++n;
    }
}

package pg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PG_Lv2_메뉴리뉴얼 {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] result = s.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"},
                new int[]{2,3,4});

        System.out.println(Arrays.toString(result));
    }
    static class Solution {
        HashMap<String, Integer> candidates;
        public String[] solution(String[] orders, int[] course) {
            String[] answer = {};
            ArrayList<String> result = new ArrayList<>();

            //각 주문에서 course에 포함된 개수를 가지고 있는 부분집합 찾기
            for(int i = 0; i<orders.length; i++) {
                //오름차순 정렬하기
                char[] arr = orders[i].toCharArray();
                Arrays.sort(arr);
                orders[i] = String.valueOf(arr);
            }


            for(int c: course){
                candidates = new HashMap<>();

                for(int i = 0; i<orders.length; i++) {
                    StringBuilder sb = new StringBuilder();
                    combination(orders[i], sb, 0, 0, c);
                }

                //최대값 찾기
                int max = 2;
                for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
                    max = Math.max(max, entry.getValue());
                }
                //가장 많이 호출된 애 result에 넣기
                for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
                    int value = entry.getValue();
                    if (value == max) {
                        if (!result.contains(entry.getKey()))
                            result.add(entry.getKey());
                    }
                }
            }

            answer = result.toArray(new String[0]);
            Arrays.sort(answer);
            return answer;
        }

        private void combination(String str, StringBuilder sb,int sub_idx, int k, int r){
            if(sub_idx == r){
                candidates.put(sb.toString(), candidates.getOrDefault(sb.toString(), 0)+1);
                return;
            }
            for(int i = k; i<str.length(); i++){
                sb.append(str.charAt(i));   //붙인다.
                combination(str, sb,sub_idx+1, i+1, r); //재구ㅣ
                sb.delete(sub_idx, sub_idx+1);  //붙인 거 다시 떼기
            }
        }
    }
}

package pg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PG_Lv2_주차요금계산 {
    class Solution {
        int[] fees;
        public int[] solution(int[] fees, String[] records) {
            HashMap<Integer, Integer> timeMap = new HashMap<>();
            this.fees = fees;

            HashMap<Integer, String> inMap = new HashMap<>();

            for(int i = 0; i< records.length;i++){
                int carNum = Integer.parseInt(records[i].split(" ")[1]);
                String time = records[i].split(" ")[0];
                String type = records[i].split(" ")[2];

                //입차일 경우, 종류별로 시각 HashMap에 추가
                if(type.equals("IN")){
                    inMap.put(carNum, time);
                }else{
                    //출차일 경우, 요금 계산하여 feeMap에 저장
                    //inmap에서 해당 정보 삭제
                    String inTime = inMap.remove(carNum);
                    timeMap.put(carNum,
                            timeMap.getOrDefault(carNum, 0) + calculateTime(inTime, time));
                }
            }
            //out이 찍히지 않은 차량 시간 계산
            for(Map.Entry<Integer, String> e: inMap.entrySet()){
                int carNum = e.getKey();
                String inTime = e.getValue();

                timeMap.put(carNum, timeMap.getOrDefault(carNum,0) + calculateTime(inTime, "23:59"));
//                inMap.remove(carNum);
            }

            ArrayList<Integer> carList = new ArrayList<>(timeMap.keySet());
            Collections.sort(carList);

            int[] answer = new int[carList.size()];
            //요금 계산
            for(int i = 0; i<carList.size(); i++){
                answer[i] = calculateFee(timeMap.get(carList.get(i)));
            }
            return answer;
        }

        public int calculateTime(String in, String out){
            int inHour = Integer.parseInt(in.split(":")[0]);
            int inMin = Integer.parseInt(in.split(":")[1]);
            int outHour = Integer.parseInt(out.split(":")[0]);
            int outMin = Integer.parseInt(out.split(":")[1]);

            int parkingMin = 0;

            inMin += inHour*60;
            outMin += outHour*60;

            parkingMin = outMin - inMin;
            return parkingMin;
        }
        public int calculateFee(int parkingMin){
            int fee = fees[1];
            parkingMin -= fees[0];  //계산된 기본 시간 빼기

            int additionalFee = 0;
            //기본 시간 이상이라면 추가요금 발생
            if(parkingMin>0){
                if(parkingMin%fees[2]!=0){
                    additionalFee = (parkingMin/fees[2] + 1) * fees[3];
                }else{
                    additionalFee = (parkingMin/fees[2]) * fees[3];
                }

            }

            return fee+ additionalFee;
        }
    }
}

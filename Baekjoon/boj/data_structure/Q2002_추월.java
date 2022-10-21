package boj.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Q2002_추월 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        HashMap<String, Integer> cars = new HashMap<>();
        //입구
        for(int i = 0; i< n; i++){
            String carNum = br.readLine();
            cars.put(carNum, i);
        }
        //출구
        int cnt = 0;
        for(int i = 0; i< n; i++){
            String carNum = br.readLine();
            int inOrder = cars.get(carNum);
            //먼저 들어간 차가 아직 안나왔으면, 추월한 것임
            for(int j = 0; j< inOrder; j++){
                if(cars.containsValue(j)){
                    cnt++;
                    break;
                }
            }
            cars.remove(carNum);
        }
        System.out.println(cnt);
    }
}

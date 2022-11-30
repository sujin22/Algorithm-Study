package boj.Greedy;

import java.io.*;
import java.util.*;

public class Q2285_우체국 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        HashMap<Integer, Integer> map = new HashMap<>();

        //마을 번호는 필요 없고, 각 위치와 사람 수만 중요함
        long total = 0;
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());

            map.put(pos, people);
            total += people;
        }
        //pos(key값) 정렬
        List<Integer> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList);

//        int distance = 0; //모든 사람까지의 거리
        long num = 0;
        for (int pos : keyList) {
            num += map.get(pos);
            if(num >= (total+1)/2){
                System.out.println(pos);
                break;
            }
        }

    }
}
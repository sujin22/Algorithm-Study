package boj.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q7662_이중우선순위큐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T > 0){
            int k = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> asc = new PriorityQueue<>();
            PriorityQueue<Integer> desc = new PriorityQueue<>(Collections.reverseOrder());
            HashMap<Integer, Integer> map = new HashMap<>();
            while(k > 0){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                if(cmd.equals("I")){
                    int element = Integer.parseInt(st.nextToken());
                    asc.offer(element);
                    desc.offer(element);
                    map.put(element, map.getOrDefault(element, 0)+1);
                } else{
                    int option = Integer.parseInt(st.nextToken());
                    if(!map.isEmpty()){
                        if(option == 1){
                            delete(desc, map);
                        }else{
                            if(!asc.isEmpty())
                                delete(asc, map);
                        }
                    }
                }
                k--;
            }
            if(map.isEmpty()){
                sb.append("EMPTY\n");
            }else{
                int max = delete(desc, map);
                int min;
                if(map.size()>0){
                    min = delete(asc, map);
                }else{
                    min = max;
                }
                sb.append(max+" "+min+"\n");
            }
            T--;
        }
        System.out.println(sb);
    }
    private static int delete(PriorityQueue<Integer> q, HashMap<Integer, Integer>map){
        int value;
        while(true){
            value = q.poll();
            int cnt = map.getOrDefault(value, 0);
            if(cnt == 0){
                continue;
            } else if(cnt == 1){
                map.remove(value);
            }else{
                map.put(value, cnt-1);
            }
            break;
        }
        return value;
    }
}
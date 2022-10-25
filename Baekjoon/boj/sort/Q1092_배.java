package boj.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1092_배 {
    /*
     * 1. crane 정렬
     *  box List 내림차순 정렬
     * 2. 반복문 1회 반복될 때마다 time을 증가시킨다.
     * 3. 반복문 안에서는,
     * 전체 크레인 돌면서 가능하면 box 삭제, craneIndex 증가
     * 불가능하면 boxIndex 증가
     * */

    /*
2
3 5
5
1 1 5 5 5

     */
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int N = reader.nextInt();
        Integer[] crane = new Integer[N];
        for(int i =0; i<N; i++){
            crane[i] = reader.nextInt();
        }
        int M = reader.nextInt();

        Arrays.sort(crane, Comparator.reverseOrder());

        ArrayList<Integer> box = new ArrayList<>();
        for(int i =0; i<M; i++){
            int weight = reader.nextInt();
            if(weight > crane[0]) {
                System.out.println(-1);
                return;
            }
            box.add(weight);
        }
        Collections.sort(box, Collections.reverseOrder());

        int time = 0;
        while(!box.isEmpty()){
            int boxIndex = 0;
            for(int craneIndex = 0; craneIndex<N;){
                if(boxIndex>=box.size()) break;
                if(crane[craneIndex] >= box.get(boxIndex)){
                    box.remove(boxIndex); //지웠으니까 boxIndex증가시키지않아도됨
                    craneIndex++;
                }else{
                    boxIndex++;
                }
            }
            time++;
        }
        System.out.println(time);
    }
    private static class FastReader{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String next(){
            if(st==null ||!st.hasMoreTokens()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
    }
}

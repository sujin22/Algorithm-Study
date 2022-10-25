package boj.sort;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Q11656_접미사배열 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        PriorityQueue<String> pq = new PriorityQueue<>();
        for(int i =0; i< s.length(); i++){
            pq.add(s.substring(i));
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            sb.append(pq.poll()).append("\n");
        }
        System.out.println(sb);
    }
}

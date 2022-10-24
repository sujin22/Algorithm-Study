package boj.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1966_프린터큐 {
    public static void main(String[] args) {
        FastReader reader = new FastReader();

        int t = reader.nextInt();
        StringBuilder sb = new StringBuilder();
        while(t>0){
            int n = reader.nextInt();
            int target = reader.nextInt();

            Queue<Element> printer = new LinkedList<>();
            for(int i = 0; i< n; i++){
                Element e = new Element(i, reader.nextInt());
                printer.add(e);
            }
            sb.append(getOrder(printer, target)).append("\n");
            t--;
        }
        System.out.println(sb);
    }
    static int getOrder(Queue<Element> printer, int target){
        int order = 1;
        while(!printer.isEmpty()){
            Element nowDoc = printer.poll();
            if(!isFirst(printer, nowDoc)){
                printer.offer(nowDoc);
            }else{
                if(target == nowDoc.index){
                    return order;
                }else{
                    order++;
                }
            }
        }
        return -1;
    }
    static boolean isFirst(Queue<Element> printer, Element now){
        for(Element e: printer){
            if(e.value>now.value) return false;
        }
        return true;
    }
    static class Element{
        int index;
        int value;

        public Element(int index, int value) {
            this.index = index;
            this.value = value;
        }
        public String toString(){
            return index+" "+value;
        }
    }
    static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        private String next(){
            if(st == null || !st.hasMoreTokens()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        private int nextInt(){
            return Integer.parseInt(next());
        }
    }
}

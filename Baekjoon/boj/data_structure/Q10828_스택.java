package boj.data_structure;

import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Q10828_스택 {
    public static void main(String[] args) {
        FastReader reader = new FastReader();

        int n = reader.nextInt();

        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i< n;i++){
            String command = reader.next();
            switch(command){
                case "push":
                    int element = reader.nextInt();
                    stack.add(element);
                    break;
                case "pop":
                    if(stack.empty()){
                        sb.append(-1+"\n");
                    }else {
                        sb.append(stack.pop() + "\n");
                    }
                    break;
                case "size":
                    sb.append(stack.size()+"\n");
                    break;
                case "empty":
                    if(stack.empty()){
                        sb.append(1+"\n");
                    }else{
                        sb.append(0+"\n");
                    }
                    break;
                case "top":
                    if(stack.empty()){
                        sb.append(-1+"\n");
                    }else{
                        sb.append(stack.peek()+"\n");
                    }
                    break;
            }
        }
        System.out.println(sb);
    }
    private static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next(){
            if(st == null || !st.hasMoreElements()){
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
        String nextLine(){
            String str = "";
            try{
                str = br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }
            return str;
        }
    }
}

package boj.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q2493_탑 {
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        long n = reader.nextLong();

        StringBuilder sb = new StringBuilder();
        Stack<Tower> stack = new Stack<>();

        for(int i = 1;i< n+1; i++){
            long now = reader.nextLong();
            if(stack.empty()){
                sb.append("0 ");
            }else{
                while(!stack.empty()){
                    Tower last = stack.peek();
                    if(last.h < now){
                        stack.pop();
                    }else{
                        break;
                    }
                }
                if(stack.empty()){
                    sb.append("0 ");
                }else{
                    sb.append(stack.peek().index+" ");
                }
            }
            stack.push(new Tower(i, now));
        }
        System.out.println(sb);

        reader.close();
    }
    static class Tower{
        int index;
        long h;

        public Tower(int index, long h) {
            this.index = index;
            this.h = h;
        }
    }

    static class FastReader{
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
        Long nextLong(){
            return Long.parseLong(next());
        }
        void close() {
            try{
                br.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}

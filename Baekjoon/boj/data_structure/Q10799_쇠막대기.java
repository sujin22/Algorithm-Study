package boj.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
* piece = 3+3
* stack = (((
* */
public class Q10799_쇠막대기 {
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        char[] arrange = reader.next().toCharArray();
        Stack<Character> stack = new Stack<>();
        int piece = 0;
        for(int i = 0; i< arrange.length; i++){
            //레이저
            if(i+1<arrange.length
                    && arrange[i] =='(' && arrange[i+1]==')'){
                piece += stack.size();
                i++;
            }
            //레이저 아님
            else if(arrange[i] == ')'){
                stack.pop();
                piece++;
            }else if(arrange[i] =='('){
                stack.add('(');
            }
        }
        System.out.println(piece);
    }
    private static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        String next(){
            if(st==null || !st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
    }

}

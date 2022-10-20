package boj.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q1935_후위표기식2 {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = 0;
        String str = "";
        try{
            n = Integer.parseInt(br.readLine()); //N input
            str = br.readLine(); //연산식 input
        }catch(IOException e){
            e.printStackTrace();
        }

        //피연산자 input
        double[] operand = new double[n];
        for(int i = 0; i< n; i++){
            try{
                operand[i] = Integer.parseInt(br.readLine());
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        Stack<Double> stack = new Stack<>();
        for(int i = 0; i<str.length(); i++){
            char now = str.charAt(i);
            if(isAlphabet(now)){
                //피연산자임(스택에 넣는다)
                stack.push(operand[getOperandIndex(now)]);
            }else{
                //연산자임
                double operand1 = stack.pop();
                double operand2 = stack.pop();
                stack.push(solve(now, operand1, operand2));
            }
        }

        System.out.println(String.format("%.2f", stack.pop()));
    }

    static double solve(char operator, double operand1, double operand2){
        switch(operator){
            case '+':
                return operand1 + operand2;
            case '-':
                return operand2 - operand1;
            case '*':
                return operand1 * operand2;
            //case '/':
            default:
                return operand2 / operand1;
        }
    }
    static boolean isAlphabet(char c){
        if(c >='A' && c<='Z'){
            return true;
        }else{
            return false;
        }
    }

    static int getOperandIndex(char c){
        return c - 'A';
    }

    private static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
    }
}

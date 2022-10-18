package boj.data_structure;

import java.util.Scanner;
import java.util.Stack;

public class Q9012_괄호 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < n; i++){
            String str = sc.nextLine();
            System.out.println(solve(str));
        }
    }
    public static String solve(String str){
        Stack<Character> stack = new Stack<>();
        for(int j = 0; j<str.length(); j++){
            char now = str.charAt(j);
            if(now =='('){
                stack.push(now);
            }else{
                if(!stack.empty()){
                    stack.pop();
                }else{
                    return "NO";
                }
            }
        }
        if(stack.empty()){
            return "YES";
        }else{
            return "NO";
        }
    }
}

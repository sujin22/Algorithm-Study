package boj.stack;

import java.util.Scanner;
import java.util.Stack;

public class Q1918_후위표기식 {
    /*[골드3]Q1918 - 후위표기식*/
    final static int OPERAND = 0;
    final static int BRACKET = 1;       //(,)
    final static int OPERATOR_1 = 2;    //+,-
    final static int OPERATOR_2 = 3;    //*,/

    static int increase = 0; //괄호 붙이면서 늘어난 길이

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuilder sb = new StringBuilder();

        Stack<Character> operators = new Stack<>();

        for(int i = 0; i<str.length();i++){
            char now = str.charAt(i);
            int nowType = getType(now);
            switch(nowType){
                case OPERAND:
                    //operand는 그냥 출력함
                    sb.append(now);
                    break;
                case OPERATOR_1:
                case OPERATOR_2:
                    //operator를 만나면 stack에 들어있는 연산자들 차례로 검사해서 now보다 우선순위 높거나 같으면 출력함
                    while(!operators.isEmpty() && getType(operators.peek())>=nowType){
                        sb.append(operators.pop());
                    }
                    //now보다 우선순위 높은 애들 다 나왔으면 now push
                    operators.push(now);
                    break;
                case BRACKET:
                    //'('만나면 push함
                    if(now =='(')   operators.push(now);
                    //')'만나면 '('나올때까지 pop해서 출력함 - 단, 괄호는 출력안함
                    if(now == ')'){
                        while(!operators.isEmpty() && operators.peek() != '('){
                            sb.append(operators.pop());
                        }
                        operators.pop(); //'('도 빼줌
                    }
                    break;
            }
        }
        while(!operators.isEmpty()){
            sb.append(operators.pop());
        }
        System.out.println(sb);
    }
    //연산자인지, 피연산자인지, 괄호인지 구분
    private static int getType(char c){
        if(c=='+'||c=='-')  return OPERATOR_1;
        else if(c=='*'||c=='/') return OPERATOR_2;

        else if(c>='A' && c<='Z') return OPERAND;
        else return BRACKET;
    }

    //c1이 우선이면 true, c2가 우선이면 false
    private static boolean isPrior(char c1, char c2){
        int p1, p2;
        p1 = (c1 == '+' || c1 == '-')? 0:1;
        p2 = (c2 == '+' || c2 == '-')? 0:1;

        if(p1>p2)   return true;
        else    return false;
    }
}

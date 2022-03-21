package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q12100_2048Easy {
    /*[골드2]Q12100 - 2048(Easy)*/
    //<메인 아이디어>
    //arr[i][j]가 0이 아닐 때만 고려하는데,
    //스택에서 peek한 것과 arr[i][j]가 같고, isCombined가 false면 pop하여 더해서 stack에 넣는다.
    //한 줄 다 돌고나면 stack에서 하나씩 pop해 적절한 자리에 차례로 넣어준다.
    static int n;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int[][] board = new int[n][n];
        StringTokenizer st;
        for(int i = 0; i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n ;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                max = Integer.max(max, board[i][j]);
            }
        }
        search(board, 0);

        System.out.println(max);
    }
    //재귀함수로 4번 이동하는 모든 경우의 수 따져줌
    static void search(int[][] arr, int depth){
        if(depth == 5) return;
        search(horizontal(arr, 0, n, 1), depth+1);   //LEFT
        search(horizontal(arr, n-1,-1, -1), depth+1);//RIGHT
        search(vertical(arr, 0,n, 1), depth+1);//UP
        search(vertical(arr, n-1,-1, -1), depth+1);//DOWN
    }

    //세로 방향 움직임(LEFT, RIGHT)
    static int[][] vertical(int[][] arr, int start, int end, int increase){
        int [][] result = new int[n][n];
        Stack<Integer> stack = new Stack<>();
        boolean isCombined = false;

        for(int j = 0; j<n;j++){
            for(int i = start; i!=end; i+=increase){
                if(arr[i][j] ==0){
                    continue;
                }
                //스택이 비어있으면 그냥 push
                if(stack.isEmpty()) {
                    stack.push(arr[i][j]);
                    isCombined = false;
                }
                else{
                    //peek한거랑 똑같으면 합쳐서 push
                    if(!isCombined && stack.peek() == arr[i][j]){
                        stack.push(arr[i][j] + stack.pop());
                        isCombined = true;
                        max = Integer.max(stack.peek(), max);
                    }else{
                        //다르면 그냥 push
                        stack.push(arr[i][j]);
                        isCombined = false;
                    }
                }
            }
            //stack에서 하나씩 꺼내서 끝에 붙여 저장
            while(!stack.isEmpty()){
                int storePos = start+ increase*(stack.size() - 1);
                result[storePos][j] = stack.pop();
            }
            stack.clear();
        }
        return result;
    }

    //가로 방향 움직임(TOP, BOTTOM)
    static int[][] horizontal(int[][] arr, int start, int end, int increase){
        int [][] result = new int[n][n];
        Stack<Integer> stack = new Stack<>();
        boolean isCombined = false;
        for(int i = 0; i<n;i++){
            for(int j = start; j!=end; j+=increase){
                if(arr[i][j] == 0){
                    continue;
                }
                if(stack.isEmpty()) {
                    stack.push(arr[i][j]);
                    isCombined = false;
                }
                else{
                    if(!isCombined && stack.peek() == arr[i][j]){
                        stack.push(arr[i][j] + stack.pop());
                        isCombined = true;
                        max = Integer.max(stack.peek(), max);
                    }else{
                        stack.push(arr[i][j]);
                        isCombined = false;
                    }
                }

            }

            //stack에서 하나씩 꺼내서 끝에 붙여 저장
            while(!stack.isEmpty()){
                int storePos = start+ increase*(stack.size() - 1);
                result[i][storePos] = stack.pop();
            }
            stack.clear();
        }
        return result;
    }
}

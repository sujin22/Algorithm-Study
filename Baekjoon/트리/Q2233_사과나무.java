package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q2233_사과나무 {
    /*[골드1]Q2233 - 사과나무*/
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int parent[] = new int[N+1];
        Pos pos[] = new Pos[N+1];
        for(int i = 0; i<N+1;i++){
            pos[i] = new Pos();
        }
        String str = br.readLine();
        char arr[] = new char[str.length()+1];
        for(int i = 1; i<str.length()+1;i++){
            arr[i] = str.charAt(i-1);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        Stack<Integer> stack = new Stack<>();
        int node = 1;
        for(int index = 1; index<str.length()+1;index++){
            if(arr[index] == '0') {
                //썩은 사과 정점 번호 찾기
                if(x==index)    x = node;
                if(y==index)    y = node;

                pos[node].i = index;
                stack.push(node++);
            }else if(arr[index] == '1'){
                int now = stack.pop();


                //썩은 사과 정점 번호 찾기
                if(x==index)    x = now;
                if(y==index)    y = now;

                pos[now].j = index;
                if(stack.isEmpty()) break;

                //부모찾기
                int nowParent = stack.pop();    //하나 더 pop해서 부모 찾고
                parent[now] = nowParent;
                stack.push(nowParent);  //다시 push해줌
            }
        }

        if(x==y){
            System.out.println(pos[x].i+" "+pos[x].j);
            return;
        }

        ArrayList<Integer> xParents = new ArrayList<>();
        while(x!=0){
            xParents.add(x);
            x = parent[x];
        }
        int result = 0;
        while(y!=0){
            if(xParents.contains(y)){
                result = y;
                break;
            }
            y = parent[y];
        }
        System.out.println(pos[result].i+" "+pos[result].j);
    }
    static class Pos{
        int i,j;

        public Pos() {
        }
    }
}

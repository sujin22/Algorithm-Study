package boj.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q4933_뉴턴의사과 {
    /*[골드3]Q4933_뉴턴의사과*/
    static int[] parent1, parent2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for(int t = 0; t<TC; t++){
            parent1 = new int[27];
            parent2 = new int[27];//알파벳 개수+1 만큼 선언

            StringTokenizer st1 = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            ArrayList<Integer> list1 = strToIntegerArray(st1);
            ArrayList<Integer> list2 = strToIntegerArray(st2);
            int root1 = makeTree(list1, parent1);
            int root2 = makeTree(list2, parent2);

            //두 트리의 루트가 같고, 각 노드의 부모가 모두 같으면 동등함
            boolean result = true;
            if(root1 != root2)  result = false;

            for(int i = 1 ;i<27; i++){
                if(parent1[i] != parent2[i]){
                    result = false;
                    break;
                }
            }
            if(result) System.out.println("true");
            else System.out.println("false");
        }
    }
    static ArrayList<Integer> strToIntegerArray(StringTokenizer str){
        //nil: 0, 알파벳: 1~26으로 변경해 저장
        ArrayList<Integer> list = new ArrayList<>();
        while(str.hasMoreTokens()){
            String s = str.nextToken();
            if(s.equals("end")){
                break;
            }
            if(s.equals("nil")){
                list.add(0);
            }else{
                list.add(s.charAt(0) - 'A' + 1);
            }
        }
        return list;
    }
    static int makeTree(ArrayList<Integer> list, int[] parent){
        Stack<Integer> stack = new Stack<>();
        for(int now: list){
            if(now == 0){
                //nil일 경우 push만 해줌
                stack.push(now);
            }else{
                if(stack.size()>=2){
                    //스택에 2개 이상 들어있으면 부모 찾아줌
                    int right = stack.pop();
                    int left = stack.pop();
                    parent[right] = now;
                    parent[left] = now;
                }
                stack.push(now); //방금 부모였던 애도 부모찾아줘야하니까 push
            }
        }
        //루트노드 반환함
        if(stack.size()>=1){
            return stack.pop();
        }else{
            return 0;   //빈 트리
        }
    }
}

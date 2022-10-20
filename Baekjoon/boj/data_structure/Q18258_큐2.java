package boj.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q18258_큐2 {
    //직접 구현해보기(ArrayDeque나 LinkedList 사용하지 않고)
    static int[] arr;
    static int front, back;
    public static void main(String[] args) {
        FastReader rd = new FastReader();
        int n = rd.nextInt();
        arr = new int[n];
        front = -1;
        back = -1;

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++){
            String cmd = rd.next();
            switch(cmd){
                case "push":
                    int element = rd.nextInt();
                    push(element);
                    break;
                case "pop":
                    sb.append(pop());
                    sb.append("\n");
                    break;
                case "size":
                    sb.append(size());
                    sb.append("\n");
                    break;
                case "empty":
                    if(empty()){
                        sb.append("1\n");
                    }else{
                        sb.append("0\n");
                    }
                    break;
                case "front":
                    sb.append(front());
                    sb.append("\n");
                    break;
                case "back":
                    sb.append(back());
                    sb.append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }
    static void push(int element){
        arr[back+1] = element;
        back++;
        if(front == -1){
            front++;
        }
    }

    static int pop(){
        if(!empty()){
            int element = arr[front];
            front++;
            return element;
        }else{
            return -1;
        }
    }
    static int size(){
        if(front==-1){
            return 0;
        }
        return back - front + 1;
    }
    static boolean empty(){
        return (size()==0);
    }

    static int front(){
        if(!empty()){
            return arr[front];
        }else{
            return -1;
        }
    }
    static int back(){
        if(!empty()){
            return arr[back];
        }else{
            return -1;
        }
    }

    private static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next(){
            if(st== null || !st.hasMoreElements()){
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
    }
}

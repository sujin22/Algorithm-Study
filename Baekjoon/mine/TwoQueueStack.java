package mine;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class TwoQueueStack {
    Queue<Integer> q1, q2;
    public TwoQueueStack(){
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }
    public void push(int element){
        q1.offer(element);
    }
    public int pop(){
        while(q1.size()>1){
            q2.add(q1.poll());
        }
        int popValue = q1.poll();
        Queue<Integer> tmp = q1;
        q1 = q2;
        q2 = tmp;

        return popValue;
    }
}

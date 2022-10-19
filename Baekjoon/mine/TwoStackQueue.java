package mine;

import java.util.Stack;

public class TwoStackQueue {
    Stack<Integer> inStack;
    Stack<Integer> outStack;

    public TwoStackQueue(){
        inStack = new Stack<>();
        outStack = new Stack<>();
    }
    public void enqueue(int element){
        inStack.push(element);
    }

    public int dequeue(){
        if(outStack.empty()){
            while(!inStack.empty()){
                outStack.push(inStack.pop());
            }
            if(outStack.empty()){
                //TODO: inStack, outStack이 둘 다 비어있으면 어떻게 할지
            }
        }
        return outStack.pop();
    }
}

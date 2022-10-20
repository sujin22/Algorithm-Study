package boj.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q11279_최대힙 {
    //이진트리 편의 상 0은 사용하지 않고, 1을 루트 노드로 사용한다.
    static int[] arr = new int[100001];
    static int numElements = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(br.readLine());
            if (input == 0) {
                if (numElements == 0) sb.append("0\n");
                else sb.append(pop() + "\n");
            } else {
                add(input);
            }
        }
        System.out.println(sb);
    }
    static void swapNode(int node1, int node2) {
        int tmp = arr[node1];
        arr[node1] = arr[node2];
        arr[node2] = tmp;
    }

    static void add(int element) {
        //가장 마지막 노드에 추가한다.
        arr[++numElements] = element;

        //위치 찾아 주기
        int now = numElements;

        //루트노드일 경우 멈춘다.
        while (now > 1 && arr[now] > arr[now/2]) {
            swapNode(now, now/2);
            now = now/2;
        }
    }

    static int pop() {
        //루트 노드에 있는 값을 리턴해줄 것이다.
        int value = arr[1];

        //루트 노드 자리에 마지막 노드를 올린다.
        arr[1] = arr[numElements];
        arr[numElements--] = 0;//비워주기

        //루트 노드의 적절한 위치를 찾아 준다.
        //(자식들 중 더 큰 자식과 자리를 바꾸어 준다.)
        int now = 1;

        //두 자식이 모두 루트노드보다 작을 때까지 내려간다.
        //왼쪽 자식이 존재할 때까지만 돈다.
        while (now * 2 <= numElements) {
            int left = now * 2;
            int right = now * 2 + 1;

            //최대힙 만족하면 멈춘다.
            if (arr[left] < arr[now] && arr[right] < arr[now]) {
                break;
            }
            if (arr[left] > arr[right]) {
                //parent <-> left
                swapNode(now, left);
                now = left;
            } else{
                //parent <-> right
                swapNode(now, right);
                now = right;
            }
        }
        return value;
    }
}

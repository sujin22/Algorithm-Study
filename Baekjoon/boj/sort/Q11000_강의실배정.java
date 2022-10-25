package boj.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q11000_강의실배정 {
    /*
     * 1 3
     * 2 4
     * 3 5
     *
     * 1. 수업 클래스 생성
     * 2. 수업 배열 만든다
     * 3. 수업 배열을 시작 시간에 따라 정렬
     * 4. PriorityQueue에 끝나는 시간을 넣는다.
     * 5. peek()했을 때 현재 시작 시간보다 크면 cnt증가시키지 않고, 끝나는 시간 큐에 집어넣음
     * 6. 현재 시작 시간보다 크면 cnt를 증가시키고 끝나는 시간을 큐에 집어넣는다.
     * */
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int N = reader.nextInt();
        Lesson[] lessons = new Lesson[N];
        for (int i = 0; i < N; i++) {
            lessons[i] = new Lesson(reader.nextInt(), reader.nextInt());
        }
        Arrays.sort(lessons, (o1, o2) -> o1.start - o2.start);

        PriorityQueue<Integer> endQueue = new PriorityQueue<>();
        int room = 0;
        for (int i = 0; i < N; i++) {
            while (!endQueue.isEmpty()
                    && endQueue.peek() <= lessons[i].start) {
                endQueue.poll();
            }
            endQueue.offer(lessons[i].end);
            if (room < endQueue.size()) {
                room = endQueue.size();
            }
        }
        System.out.println(room);
    }


    private static class Lesson {
        int start;
        int end;

        public Lesson(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public String toString(){
            return start+" "+end;
        }
    }

    private static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            if (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

    }

}

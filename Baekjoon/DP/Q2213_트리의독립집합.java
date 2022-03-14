package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q2213_트리의독립집합 {
    /*[골드1]Q2213 - 트리의 독립집합*/
    static final int FALSE = 0;
    static final int TRUE = 1;

    static int[] weight;    //정점 별 가중치 저장하는 배열
    static ArrayList<Integer>[] tree;   //인접리스트
    static int[][] dp;
    static int N;

    static PriorityQueue<Integer> path = new PriorityQueue<>();
    static boolean [] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        weight = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<N+1;i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }

        tree = new ArrayList[N+1];
        for(int i =1; i<N+1;i++){
            tree[i] = new ArrayList<>();
        }
        for(int i = 0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            tree[n1].add(n2);
            tree[n2].add(n1);
        }

        dp = new int[2][N+1];
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);

        int[] result = new int[2];
        //1을 선택하지 않는 경우
        result[FALSE] = findMax(FALSE, 0, 1);

        //1을 선택하는 경우
        result[TRUE] = findMax(TRUE, 0, 1);

        System.out.println(Integer.max(result[0], result[1]));

        //경로 찾기
        selected = new boolean[N+1];
        findPath(0, 1);

        while(!path.isEmpty()){
            System.out.print(path.poll()+" ");
        }
    }
    //selected: 현재 노드 선택하는지, prev: 이전 정점, current: 현재 정점
    static int findMax(int selected, int prev, int current) {
        //현재 위치를 검사한 적 있는지 확인(dp에 저장되어 있으면 그거 리턴)
        int sum = dp[selected][current];
        if(sum!=-1) return sum;

        //현재가 선택되었는지 여부에 따라 sum 시작을 다르게 한다.
        sum = (selected==TRUE)? weight[current]:0;

        //인접한 자식을 돌며 재귀한다.
        for(int next: tree[current]){
            //양방향 연결이므로, 부모일 경우 continue해줌
            if(next == prev)    continue;
            if(selected==FALSE){
                //자식은 선택 하 거나, 안하거나 둘 다 가능함
                //따라서 두 경우 다 재귀하여 더 큰 값을 current 가중치에 더해준다.
                int sub1 = findMax(TRUE, current, next);
                int sub2 = findMax(FALSE, current, next);
                sum+=Integer.max(sub1, sub2);
            }else{
                //현재가 TRUE이므로 인접한 자식은 무조건 선택할 수 없음
                sum += findMax(FALSE, current, next);
            }
        }
        dp[selected][current] = Integer.max(dp[selected][current], sum);
        return sum;
    }

    //경로를 찾는 함수
    static void findPath(int prev, int current){
        //이전 정점이 선택되지 않았고
        // 현재 정점 선택하는 값이 미선택하는 값보다 크면
        if(!selected[prev]
                && dp[TRUE][current] > dp[FALSE][current]){
            //path에 현재 정점을 추가하고, 선택되었다고 표시한다.
            path.offer(current);
            selected[current] = true;
        }
        //그리고 현재정점의 자식들을 재귀로 돌림
        for(int next:tree[current]){
            if(next == prev)    continue;
            findPath(current, next);
        }
    }

}

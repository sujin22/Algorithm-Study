package boj.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*특정 거리의 도시 찾기*/
public class Q18352 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   //도시 개수
        int M = Integer.parseInt(st.nextToken());   //도로 개수
        int K = Integer.parseInt(st.nextToken());   //찾아야 하는 최단거리
        int X = Integer.parseInt(st.nextToken());   //출발 도시 번호

        //그래프 저장할 인접리스트 선언
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i<N+1 ;i++){
            //도시 개수만큼 리스트 추가(0은 사용하지 않을 것)
            graph.add(new ArrayList<Integer>());
        }
        //도시 입력받기
        for(int i = 0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
        }

        int[] dist = new int[N+1];    //최단거리 저장할 배열

        PriorityQueue<Node> q = new PriorityQueue<Node>(
                (o1, o2) -> Integer.compare(o1.dist, o2.dist)
        );

        //모든 dist를 최대값으로 설정(최소값을 저장할거니까)
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;    //출발할 도시 최단거리만 0으로 설정

        //우선순위 큐에 시작점 추가
        q.add(new Node(X, 0));

        while(!q.isEmpty()){
            //방문할 노드를 poll해온다.
            Node nowNode = q.poll();

            //만약 그 노드의 dist값이 현재 dist배열값보다 크면
            //즉, 이미 다른 경로에서 최단거리를 가지고 있다면
            if(nowNode.dist > dist[nowNode.idx]){
                continue;
            }

            //현재 도시와 인접한 노드들 dist 갱신
            for(int i = 0; i< graph.get(nowNode.idx).size();i++){
                int nextCity =  graph.get(nowNode.idx).get(i);
                int newDist = nowNode.dist + 1;
                if(dist[nextCity]>newDist){
                    //'현재 가진 dist값(최단거리)'가 '현재노드에서 이동하는 거릿값'보다 크면 갱신
                    //갱신된 노드는 큐에 저장(방문할 것이므로)
                    //즉, 인접한 노드 중 이미 최단거리로 방문하지 않은 노드로만 이동해서 검사한다는 뜻
                    dist[nextCity] = newDist;
                    q.add(new Node(nextCity, newDist));
                }
            }
        }

        int cnt = 0;
        for (int i=1; i<dist.length; i++){
            if (dist[i] == K) {
                System.out.println(i);
                cnt++;
            }
        }
        if (cnt == 0)  System.out.println(-1);

    }
    static class Node{
        int idx;
        int dist;

        public Node(int idx, int dist){
            this.idx = idx;
            this.dist = dist;
        }
    }
}

package boj.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q1765_닭싸움팀정하기 {
    /*[골드1]Q1765 - 닭싸움 팀 정하기*/
    static int n,m;
    static ArrayList<Integer>[] friends, enemies;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        friends = new ArrayList[n+1];
        enemies = new ArrayList[n+1];

        for(int i = 1; i<n+1;i++){
            friends[i] = new ArrayList<>();
            enemies[i] = new ArrayList<>();
        }

        for(int i = 0; i<m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            char option = st.nextToken().charAt(0);
            int stu1 = Integer.parseInt(st.nextToken());
            int stu2 = Integer.parseInt(st.nextToken());

            if(option == 'F'){
                friends[stu1].add(stu2);
                friends[stu2].add(stu1);
            }
            else if(option == 'E'){
                enemies[stu1].add(stu2);
                enemies[stu2].add(stu1);
            }
        }
        for(int i = 1;i<n+1;i++){
            visited = new boolean[n+1];
            findEnemy(i, i,0);
        }

        visited = new boolean[n+1];
        int teamCnt = 0;
        for(int i = 1;i<n+1;i++){
            if(visited[i])  continue;
            findFriend(i);
            teamCnt++;
        }

        System.out.println(teamCnt);

    }
    private static void findEnemy(int start,int now, int depth){
        //친구 발견하면 friends list에 넣어준다.
        if(depth == 2){
            friends[start].add(now);
            return;
        }
        //적을 찾아 재귀함수 실행해준다.(방문하지 않은 적만 방문함)
        for(int enemy: enemies[now]){
            if(visited[enemy]) continue;
            visited[enemy] = true;
            findEnemy(start, enemy, depth+1);
        }
    }

    private static void findFriend(int stu){
        //방문하지 않은 친구를 방문해서 true로 만들어준다.(같은팀은 전부 true됨)
        for(int friend: friends[stu]){
            if(visited[friend]) continue;
            visited[friend] = true;
            findFriend(friend);
        }
    }
}

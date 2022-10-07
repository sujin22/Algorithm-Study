package test.kakao_blind;

import java.util.*;

public class s6 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(3,4,2,3,3,1,5));
    }
    static class Solution {
        int n,m,x,y,r,c,k;
        int[] di = new int[]{1,0,0,-1};
        int[] dj = new int[]{0,-1,1,0};
        String[] directions = new String[]{"d","l","r","u"};
        String answer = "u";

        public String solution(int n, int m, int x, int y, int r, int c, int k) {
            this.n = n;
            this.m = m;
            this.x = x;
            this.y = y;
            this.r = r;
            this.c = c;
            this.k = k;

            Queue<Node> q = new PriorityQueue<>(Node::compareTo);
            q.add(new Node(x,y));

            while(!q.isEmpty()){
                Node now = q.poll();
                if(answer.length()==now.route.length()
                        && answer.compareTo(now.route.toString())>0){
                    answer = now.route.toString();
                    System.out.println("저장완료 "+now.route + " "+answer);
                }

                if(now.route.length()==k && now.i == r && now.j == c){
                    if(answer.length()<k ||
                            answer.length() == k && answer.compareTo(now.route.toString())>0){
                        answer = now.route.toString();
                        System.out.println("저장완료 "+now.route + " "+answer);
                    }else{
                        continue;
                    }
                }else if(now.route.length()>=k){
                    System.out.println("도착못함 "+now.route + " "+answer);
                    continue;
                }else if(answer.length()>=now.route.length()
                        && answer.substring(0, now.route.length()).compareTo(now.route.toString())<0){
                    System.out.println("이미탈락 "+ now.route + " "+answer);
                    continue;
                }

                for(int i =0 ;i<4; i++){
                    int nextI = now.i + di[i];
                    int nextJ = now.j + dj[i];
                    String dir = directions[i];
                    if(nextI<1 || nextI>n || nextJ<1 || nextJ>m)
                        continue;
                    StringBuilder sb = new StringBuilder(now.route);
                    sb.append(dir);

                    if(sb.length()<=k){
                        q.add(new Node(nextI, nextJ, sb));
                    }
                }
            }
//            dfs(x,y,new StringBuilder());

            if(answer.equals("")){
                return "impossible";
            }
            return answer;
        }

        private void dfs(int nowI, int nowJ, StringBuilder route){
            if(route.length()==k && nowI == r && nowJ == c){
                if(!answer.isEmpty() && answer.compareTo(route.toString())<0){
                    System.out.println(route + " "+answer);
                    return;
                }
                answer = route.toString();
            }else if(route.length()>=k){
                System.out.println(route + " "+answer);
                return;
            }else if(answer.length()>=route.length() && answer.substring(0, route.length()).compareTo(route.toString())<0){
                System.out.println();
                System.out.println(route + " "+answer);
                System.out.println("=====");
                System.out.println(answer.substring(0, route.length())+" "+route);
                System.out.println("=====");
                System.out.println();

                return;
            }
            for(int i =0 ;i<4; i++){
                int nextI = nowI + di[i];
                int nextJ = nowJ + dj[i];
                String dir = directions[i];
                if(nextI<1 || nextI>n || nextJ<1 || nextJ>m)
                    continue;
                StringBuilder sb = new StringBuilder(route);
                sb.append(dir);

                if(sb.length()<=k) dfs(nextI, nextJ, sb);
            }
        }
        class Node{
            int i,j;
            StringBuilder route = new StringBuilder();

            public Node(int i, int j) {
                this.i = i;
                this.j = j;
            }

            public Node(int i, int j, StringBuilder route) {
                this.i = i;
                this.j = j;
                this.route = route;
            }

            public int compareTo(Node p){
                return route.compareTo(p.route);
            }
        }
    }
}

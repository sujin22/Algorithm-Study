package boj.data_structure;

import java.io.*;
import java.util.*;

public class Q17255_N으로만들기 {
    static char[] target;
    static HashSet<String> routes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = br.readLine().toCharArray();
        routes = new HashSet<>();

        for(int i = 0; i<target.length;i++){
            findRoute(target[i]+"", target[i]+"", i, i);
        }
        System.out.println(routes.size());
    }
    private static void findRoute(String str, String route, int left, int right){
        if(left==0 && right==target.length-1){
            routes.add(route);
            return;
        }
        if(left>0){
            findRoute(target[left-1]+str, route+" "+target[left-1]+str, left-1, right);
        }
        if(right<target.length-1){
            findRoute(str+target[right+1],route+" "+str+target[right+1], left, right+1);
        }
    }
}

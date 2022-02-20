package graph_traversal.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q3584_가장가까운공통조상 {
    /*[골드4]Q3584_가장가까운공통조상*/
    static int[] parent;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for(int t= 0; t<TC;t++){
            n = Integer.parseInt(br.readLine());
            parent = new int[n+1];
            for(int i = 0; i<n-1;i++){
                StringTokenizer st= new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                parent[c] = p;
            }
            StringTokenizer st= new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            findAncestor(n1, n2);
        }
    }
    static void findAncestor(int n1, int n2){
        //n1의 부모를 리스트에 쭉 저장한다.
        ArrayList<Integer> parentList1 = new ArrayList<>();
        int p = n1;
        while(p != 0){
            parentList1.add(p);
            p = parent[p];
        }

        //n2의 부모를 타고 올라가며, 리스트에 존재하면 출력하고 함수를 끝낸다.
        p = n2;
        while(p != 0){
            if(parentList1.contains(p)){
                System.out.println(p);
                return;
            }
            p = parent[p];
        }
    }
}

package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q4256_트리 {
    /*[골드3]Q4256 - 트리*/
    static int [] preorder, inorder;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for(int t = 0; t<TC; t++){
            int n = Integer.parseInt(br.readLine());
            preorder = new int[n+1];
            inorder = new int[n+1];
            sb = new StringBuilder();

            StringTokenizer st1 = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int i = 0;i<n; i++){
                preorder[i] = Integer.parseInt(st1.nextToken());
                inorder[i] = Integer.parseInt(st2.nextToken());
            }
            postorder(0, 0, n);
            System.out.println(sb);
        }
    }
    //rootIndex: preorder에서의 index(루트노드 인덱스임)
    //start, end: inorder에서의 범위
    public static void postorder(int rootIndex, int start, int end){
        //inorder 순회하면서 root노드 찾을거임
        for(int i = start; i<end; i++){
            if(preorder[rootIndex] == inorder[i]){
                //left subtree
                //직후이므로 root인덱스는 하나 증가시키면 됨
                //범위는 처음부터 루트노드(i)까지
                postorder(rootIndex+1, start, i);

                //right subtree
                //"left에서 돈 횟수+1"만큼 rootIndex를 증가시켜야 함
                //i - start: left에서 돈 횟수(루트 좌측 개수만큼 돔)
                //범위는 루트노드(i) 하나 뒤부터 end까지
                postorder(rootIndex+i-start+1, i+1, end);
                sb.append(preorder[rootIndex]+" ");
            }
        }
    }
}

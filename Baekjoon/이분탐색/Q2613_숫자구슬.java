package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2613_숫자구슬 {
    /*[골드2]Q2613 - 숫자구슬*/
    static int n, m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   //원소개수
        m = Integer.parseInt(st.nextToken());   //그룹개수

        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        int max = 0; //제일 큰 원소 저장
        int sum = 0; //모든 원소의 합 저장
        for(int i = 0; i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
            max = Integer.max(max, arr[i]);
            sum+= arr[i];
        }
        //이분탐색으로 최적해(그룹의 합 중 가장 큰 값이 최소가 되도록)를 찾는다.
        //원소들 중 가장 큰 원소를 left, 모든 원소의 합을 right
        // (어떤 그룹이든 그 원소를 포함해야할테니 그룹의 합 중 최솟값은 가장 큰 원소이다.
        // 그룹의 합 중 최댓값은 모든 원소의 합이다.
        int left = max;
        int right = sum;
        while(left<=right){
            int mid = (left+right)/2; //중간값
            //mid가 최적해가 될 수 있는지 검사한다.
            //(오른쪽 그룹들이 mid를 넘지 않을 때, 전체 그룹개수 m개가 가능한지)
            //왼쪽부터 최대한 많은 원소를 채워가며 검사한다.(어차피 가능할 경우 더 작은 값을 찾아나갈테니까)
            if(isPossible(mid)){
                //가능할 경우
                right = mid-1;
                //(더 작은 최적해가 있는지 찾기 위해 왼쪽 부분 탐색)
            }else{
                //불가능한 경우
                left = mid +1;
                //(가능한 최적해를 찾으려면 mid를 더 크게 해줘야함)
            }
        }

        System.out.println(left);//최적해

        /*그룹 원소 수 출력*/
        int groupCnt = 0;   //그룹의 개수
        int cnt = 0;        //그룹 내 원소의 개수
        sum = 0;
        for(int i = 0; i<n;i++){
            sum+=arr[i];
            if(sum>left){
                groupCnt++;
                sum = arr[i];
                System.out.print(cnt+" ");
                cnt = 0;
            }
            cnt++;
            //남은 원소의 개수가 남은 그룹의 수와 같아지면
            //현재원소까지 cnt 출력, 그 후로는 원소 1개씩 배정
            if(m-groupCnt == n-i){
                for(int j = i; j<n;j++){
                    System.out.print(cnt+" ");
                    cnt = 1;
                }
                break;
            }
        }

    }
    public static boolean isPossible(int x){
        //x가 최적해가 될 수 있는지 검사한다.
        int groupCnt = 1; //그룹의 개수
        int sum = 0;
        for(int i = 0; i<n;i++){
            sum+=arr[i];
            if(sum>x ){
                //합계가 x보다 크거나 같아지면
                groupCnt++;
                sum = arr[i];
            }
        }
        if(groupCnt <= m)   return true;
        else return false;
    }

}

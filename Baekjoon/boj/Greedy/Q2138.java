package boj.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2138 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //전구 개수
        int n = Integer.parseInt(br.readLine());

        int target[] = new int[n];  //만들고자 하는 배열
        int arr0[] = new int[n];    //첫 번째 전구를 누르지 않았을 경우
        int arr1[] = new int[n];    //첫 번째 전구를 눌렀을 경우


        String s = br.readLine();
        String target_s = br.readLine();

        for (int i = 0; i < n; i++) {
            arr0[i] = s.charAt(i) - '0';  //정수로 변형
            arr1[i] = arr0[i];
            target[i] = target_s.charAt(i) - '0'; //정수로 변형
        }

        int cnt0 = 0;
        int cnt1 = 1;
        //arr1의 첫 번째 스위치를 누른다
        arr1[0] = 1 - arr1[0];    //0 -> 1, 1 -> 0 변환
        arr1[1] = 1 - arr1[1];

        //두번째 스위치부터 연산 시작
        for (int i = 1; i < n; i++) {
            //arr0
            if (arr0[i - 1] != target[i - 1]) {
                //하나 앞 원소가 목표와 다르면
                //(못바꾸고 지나간 버튼은 뒤에서 더이상 바뀌지 않기 때문에, 하나 앞 원소를 기준으로 두고 비교한다)
                arr0[i - 1] = 1 - arr0[i - 1];
                arr0[i] = 1 - arr0[i];
                if (i + 1 < n) {  //마지막 원소일 경우 예외처리
                    arr0[i + 1] = 1 - arr0[i + 1];
                }

                cnt0++; //횟수 증가
            }

            //arr1
            if (arr1[i - 1] != target[i - 1]) {
                arr1[i - 1] = 1 - arr1[i - 1];
                arr1[i] = 1 - arr1[i];
                if (i + 1 < n) {
                    arr1[i + 1] = 1 - arr1[i + 1];
                }

                cnt1++; //횟수 증가
            }
        }

        //불가능한 경우 따져주기
        if (arr0[n - 1] != target[n - 1] && arr1[n - 1] != target[n - 1]) { //둘 다 목표에 도달하지 못했을 경우
            System.out.println(-1);
        } else if(arr0[n-1] == target[n-1] && arr1[n-1] == target[n-1]){    //둘 다 목표에 도달했을 경우(더 작은 값 출력)
            System.out.println(Math.min(cnt0, cnt1));
        } else if(arr0[n-1]==target[n-1]){  //둘 중 하나만 도달했을 경우
            System.out.println(cnt0);
        } else if(arr1[n-1]==target[n-1]){
            System.out.println(cnt1);
        }
    }
}

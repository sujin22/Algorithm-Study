package greedy;

import java.util.Scanner;

/*
[실버5]
거스름돈 동전의 최소 개수를 출력해야 하므로,
5원이 최대한 많아야 한다.
따라서,
5로 나눈 나머지가 0이 아닐 경우, 2씩 빼주면서 5로 나누어떨어지는 시점을 찾는다.

n이 1, 3 등일 경우, 절대 2원과 5원으로 거슬러줄 수 없다.
그런 경우 n-2를 해주다보면 n이 음수가 된다.
따라서, n<0일 경우 -1을 출력한다.
 */

public class Q14916 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int coin = 0;

        //n이 음수가 되었다면, 2와 5로 거슬러줄 수 없는 경우이므로 반복문을 종료한다.
        //n이 0이 되었을 경우, 동전 세기가 종료된 것이므로 반복문을 종료한다.
        while (n>0) {
            //n이 5로 나누어떨어지지 않을 경우
            if (n % 5 != 0) {
                n -= 2; //2를 빼고 동전 수를 하나 증가시킨다.
                coin++;
            } else {
                //5로 나누어떨어질 경우, 동전 수를 증가시키고 n을 나누어준다.
                coin += n / 5;
                n %= 5;
            }

        }

        if(n<0){
            System.out.println(-1);
        }else{
            System.out.println(coin);
        }
    }
}

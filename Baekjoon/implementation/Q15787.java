package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q15787 {
    static ArrayList<boolean[]> crossList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean trains[][] = new boolean[N][20];

        crossList = new ArrayList<>();
        for(int i = 0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            switch(order){
                case 1:
                    int index =  Integer.parseInt(st.nextToken())-1;
                    int x =  Integer.parseInt(st.nextToken()) -1;
                    trains[index][x] = true;
                    break;
                case 2:
                    index =  Integer.parseInt(st.nextToken())-1;
                    x =  Integer.parseInt(st.nextToken()) -1;
                    trains[index][x] = false;
                    break;
                case 3:
                    index =  Integer.parseInt(st.nextToken())-1;
                    for(int j=18;j>=0;j--){
                        trains[index][j+1] = trains[index][j];
                    }
                    trains[index][0] = false;
                    break;
                case 4:
                    index =  Integer.parseInt(st.nextToken())-1;
                    for(int j=1;j<=19;j++){
                        trains[index][j-1] = trains[index][j];
                    }
                    trains[index][19] = false;
                    break;
            }
        }

        for(int i = 0;i<N;i++){
            if(isValid(trains[i])){
                crossList.add(trains[i]);
            }
        }
        System.out.println(crossList.size());
    }
    public static boolean isValid(boolean[] train){
        for(int i = 0;i<crossList.size();i++){
            boolean[] arr = crossList.get(i);
            int cnt = 0;
            for(int j = 0;j<20;j++){
                if(train[j]!=arr[j])   cnt++;
            }
            if(cnt==0)  return false;
        }
        return true;
    }
}

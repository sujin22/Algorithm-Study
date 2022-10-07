package test.kakao_blind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class s5 {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] result = s.solution(
                new String[]{
                        "UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"
                }
        );
        System.out.println(Arrays.toString(result));
    }

    static class Solution {
        Node[][] table = new Node[50 + 1][50 + 1];
        HashMap<Integer, ArrayList<int[]>> mergeList;
        int idx = 0;

        public String[] solution(String[] commands) {
            String[] answer = {};
            ArrayList<String> result = new ArrayList<>();
            for (int i = 1; i < 51; i++) {
                for (int j = 1; j < 51; j++) {
                    table[i][j] = new Node();
                }
            }
            mergeList = new HashMap<>();

            for (String command : commands) {
                String[] commandArr = command.split(" ");
                switch (commandArr[0]) {
                    case "UPDATE":
                        if (commandArr.length == 4) {
                            // r, c, value
                            int r = Integer.parseInt(commandArr[1]);
                            int c = Integer.parseInt(commandArr[2]);
                            String value = commandArr[3];

                            table[r][c].value = value;

                            if(table[r][c].isMerged){
                                ArrayList<int[]> list = mergeList.get(table[r][c].mergeId);
                                for(int[] arr: list){
                                    table[arr[0]][arr[1]].value = value;
                                }
                            }

                        } else if (commandArr.length == 3) {
                            //value1, value2
                            allUpdate(commandArr[1], commandArr[2]);
                        }
                        break;
                    case "MERGE":
                        int r1 = Integer.parseInt(commandArr[1]);
                        int c1 = Integer.parseInt(commandArr[2]);
                        int r2 = Integer.parseInt(commandArr[3]);
                        int c2 = Integer.parseInt(commandArr[4]);

                        merge(r1, c1, r2, c2);
                        break;
                    case "UNMERGE":
                        int r = Integer.parseInt(commandArr[1]);
                        int c = Integer.parseInt(commandArr[2]);

                        ArrayList<int[]> list = mergeList.get(table[r][c].mergeId);
                        for(int[] arr: list){
                            if(arr[0]!=r || arr[1]!=c){
                                table[arr[0]][arr[1]].value = "EMPTY";
                            }
                            table[arr[0]][arr[1]].isMerged = false;
                        }
                        break;
                    case "PRINT":
                        int print_r = Integer.parseInt(commandArr[1]);
                        int print_c = Integer.parseInt(commandArr[2]);

                        result.add(table[print_r][print_c].value);
                        break;
                }
                System.out.println(command);
                printTable();
            }
            return result.toArray(new String[0]);
        }

        class Node {
            String value = "EMPTY";
            boolean isMerged = false;
            int mergeId = -1;
        }

        public void allUpdate(String value1, String value2) {
            for (int i = 1; i < 51; i++) {
                for (int j = 1; j < 51; j++) {
                    if (table[i][j].value.equals(value1)) {
                        table[i][j].value = value2;
                    }
                }
            }
        }

        public void merge(int r1, int c1, int r2, int c2) {
            ArrayList<int[]> list = new ArrayList<>();

            String value = table[r1][c1].value;

            int start_r, start_c, end_r, end_c;
            if(r1<r2){
                start_r = r1;
                end_r = r2;
            }else{
                start_r = r2;
                end_r = r1;
            }
            if(c1<c2){
                start_c = c1;
                end_c = c2;
            }else{
                start_c = c2;
                end_c = c1;
            }

            for(int i = start_r; i<=end_r;i++){
                for(int j = start_c; j<=end_c; j++){
                    ArrayList<int[]> tmpList = new ArrayList<>();
                    if (table[i][j].isMerged) {
                        tmpList = mergeList.get(table[i][j].mergeId);
                        mergeList.remove(table[i][j].mergeId);
                    } else {
                        tmpList.add(new int[]{i, j});
                        table[i][j].isMerged = true;
                    }
                    list.addAll(tmpList);
                }
            }

            mergeList.put(idx, list);
            for (int[] arr : list) {
                table[arr[0]][arr[1]].mergeId = idx;
                table[arr[0]][arr[1]].value = value;
            }
            idx++;
        }

        public void printTable(){
            for(int i=1;i<3;i++){
                for(int j = 1; j<3;j++){
                    System.out.print(table[i][j].value+" ");
                }
                System.out.println();
            }
            System.out.println("==============");
        }

    }
}

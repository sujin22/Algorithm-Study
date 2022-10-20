package pg;

import java.util.Arrays;

import static java.lang.Integer.compare;

class PG_Lv2_전화번호목록  {
    public static void main(String[] args) {
        String[] strings = {"12334", "33"};
        Arrays.sort(strings);
        System.out.println(Arrays.toString(strings));
//        solution(new String[]{"123","123"});
    }
    public static boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book, (s1, s2)-> compare(s1.length(), s2.length()));

        return answer;
    }
}
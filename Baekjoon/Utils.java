import java.util.*;

public class Utils {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        powerSet(arr);
    }
    /*배열*/
    private static void array(){
        //선언
        int[] a = new int[8];
        int[] b = {1,2,3,4,5};

        //배열 -> 문자열
        Arrays.toString(a);

        //char배열 -> 문자열
        char[] c = {'a','b','c'};
        String str = new String(c);
        str = String.valueOf(c);

        //문자열 -> char배열
        str = "Hello";
        char[] charArray = str.toCharArray();
        System.out.println(String.valueOf(charArray));

        //배열 정렬
        int[] arr = {3,4,5,2,1};
        String[] stringArray = {"a","e","z","f"};

        //배열 오름차순 정렬
        Arrays.sort(arr);
        System.out.println(Arrays.toString(Arrays.stream(arr).toArray()));
        Arrays.sort(stringArray);
        System.out.println(Arrays.toString(stringArray));

        //배열 내림차순 정렬
        //기본형은 불가능, Wrapper로 감싸줘야 함
        Integer[] tmp = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(tmp, Collections.reverseOrder());
        System.out.println(Arrays.toString(Arrays.stream(tmp).toArray()));

        //N차원 배열 정렬
        //배열의 0번값을 기준으로 정렬
        Comparator<int[]> com1 = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] -o2[0]; // 반대로 바꾸면 큰값부터 내림차순 정렬
            }
        };
        //람다
        Comparator<int[]> com1_2 = (o1, o2) -> o1[0] - o2[0];

        //배열의 1번값을 기준으로 정렬
        Comparator<int[]> com2 = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        };
        //람다
        Comparator<int[]> com2_2 = (o1, o2) -> o1[1] - o2[1];

        //문자열로 이루어졌으면 연산자 -를 사용하지 않고, compareTo 사용한다.
        String[][] exArray = {{"aa","100"}, {"ac","101"}, {"ab","102"},{"ad","103"}};
        Arrays.sort(exArray, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[0].compareTo(o2[0]);
            }
        });

        int[][] arr2 = {{1,5000},{2,3000},{3,7000},{4,1000}};

        //com1, com2 자리에 람다를 넣어서 한번에 해결해도 됨
        //com1으로 정렬
        Arrays.sort(arr2, com1);
        /*
        * 1 5000
        * 2 3000
        * 3 7000
        * 4 1000
        * */

        //com2로 정렬
        Arrays.sort(arr2, com2);
        /*
        * 4 1000
        * 2 3000
        * 1 5000
        * 3 7000
        * */

        //배열 자르기
        int[] arr3 = {0,1,2,3,4,5};
        int[] sliced = Arrays.copyOfRange(arr3, 2,4);   //2번째 인자(포함)부터 4번째 인자(미포함)앞까지 출력

    }

    /*ArrayList*/
    private static void arrayList(){
        ArrayList<String> list = new ArrayList<>();

        list.add("java");
        list.add(0,"java"); //0번째 인덱스에 삽입

        list.set(1, "c++"); //1번째 인덱스 값 변경

        list.remove(1); //1번째 인덱스 값 삭제

        list.contains("java");
        list.indexOf("java");

        //arraylist to array
        ArrayList<String> arrayList = new ArrayList<>();
        String[] array = arrayList.toArray(new String[arrayList.size()]);
    }

    /*문자열*/
    private static void string(){
        String str = "abc";
        //특정 문자로 시작하는지 판단
        boolean v = str.startsWith("a");
        //특정 문자로 끝나는지 판단
        v = str.endsWith("a");

        //"a"가 위치하는 인덱스 찾기
        int idx = str.indexOf("a");

        //잘라서 배열에 넣기 (slice)
        String[] sliced = str.split("");//한개씩 나눠서 배열에 넣음
        sliced = str.split(" ");//저거 기준으로 자름

        //자르기
        str = "01234";
        String subString = str.substring(0,2); //0번째 인자(포함)부터 2번째 인자(미포함)앞까지 문자 추출
        System.out.println(subString);
        //소문자 변환
        String lower = str.toLowerCase();
        String upper = str.toUpperCase();

        //공백 제거
        //문자열 앞, 뒤 공백 제거
        str = " abcdef ";
        String trim = str.trim();
        System.out.println(trim);

        //문자열 가운데 공백도 제거
        str = " abc def ";
        String replace = str.replace(" ", "");
        System.out.println(replace);

        //처음 만난 애만 제거
        str ="ab cd ef";
        String replaceFirst = str.replaceFirst(" ","");
        System.out.println(replaceFirst);

        //뭔가를 문자열로 변환
        int a = 23;
        char b = 'c';
        boolean c = false;
        double d = 32.1;
        str = String.valueOf(a);
        str = String.valueOf(b);
        str = String.valueOf(c);
        str = String.valueOf(d);
    }

    /*StringBuilder*/
    private static void stringBuilder(){
        StringBuilder sb = new StringBuilder();
        sb.append("a");
        String str = sb.toString();
    }

    /*HashMap*/
    private static void hashMap(){
        //선언
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("java", 0);
        hm.get("java");

        //containsKey로 존재유무 확인
        if(!hm.containsKey("java")) hm.put("java", 3);

        //특정 키가 있으면 값 설정, 있으면 기존 값 가져오는 함수
        hm.put("java", hm.getOrDefault("java",3));

        //keySet()함수로 맵 순회
        for(String key: hm.keySet()){
            hm.get(key);
        }
    }

    /*Queue*/
    private static void queue(){
        Queue<Integer> q = new ArrayDeque<>();
        //삽입
        q.add(10);
        q.offer(2);

        //삭제
        q.remove();
        q.poll();

        q.peek();//프론트값 확인

        q.clear();
        q.isEmpty();
    }

    /*PriorityQueue*/
    private static void priorityQueue(){
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //최소힙
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder()); //최대힙

        pq.add(3);
        pq.remove();
        pq.peek();

        //객체 사용 시

        class Node{
            int x;
            int y;

            public Node(int x, int y) {
                this.x = x;
                this.y = y;
            }

            //비교 함수 만들어야함
            public int compareTo(Node p){
                if(this.y<p.y){
                    return -1;
                }else if(this.y == p.y){
                    if(this.x < p.x){
                        return -1;
                    }
                }
                return 1;
            }
        }
        PriorityQueue<Node> pq1 = new PriorityQueue<>(Node::compareTo);
        pq1.add(new Node(1,2));
        pq1.add(new Node(1,2));
        pq1.add(new Node(1,2));

        while(!pq1.isEmpty()){
            Node node = pq1.remove();
            System.out.println(node.y+" "+node.x);
        }

    }

    /*Math 라이브러리*/
    private static void math(){
        Math.max(10,2);
        Math.min(10,2);

        Math.abs(-10);

        Math.ceil(-3.2); //올림 -3
        Math.floor(-3.2); //내림 -4
        Math.round(-3.26); //첫째자리에서 반올림 -3

        //소수 둘째, 셋째자리에서 반올림하고싶으면
        double a = 1.23456;
        String b = String.format("%.1f",a); //.1f는 둘째자리에서 반올림함

        Math.pow(2,2); //제곱 2^2
        Math.sqrt(4); //제곱근 2
    }

    //소수인지 판별하는 함수
    private static boolean isPrime(long n){
        if(n<=1) return false;
        else if(n==2) return true;
        for(int i = 2; i<=Math.sqrt(n) ; i++){
            if(n%i == 0)    return false;
        }
        return true;
    }

    //배열에서 부분집합 모두 구하기(PowerSet)
    private static void powerSet(int[] nums){
        int r; //원소 개수 r개인 부분집합 구하기

        for(int i = 0; i<=nums.length; i++){
            r = i;
            int [] subset = new int[r];
            combination(nums, subset, 0,0, r);
        }
    }
    private static int[] combination(int[] nums, int[] subset, int cnt, int k, int r){
        if(cnt == r){
            System.out.println(Arrays.toString(subset));
            return subset;
        }
        for(int i = k; i<nums.length; i++){
            subset[cnt] = nums[i];
            combination(nums, subset, cnt+1, i+1, r);
        }
        return subset;
    }

    //////

}

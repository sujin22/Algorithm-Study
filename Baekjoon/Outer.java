public class Outer {
    private static final String TEST01 = "I'm TEST01";

    static
    {
        //Outer 클래스 초기화
        System.out.println("1 - Initializing class Outer, where TEST01 = " + TEST01);
    }

    public static void main(String[] args)
    {
        System.out.println("2 - TEST01       --> " + TEST01 );
        System.out.println("3 - Inner.class  --> " + Inner.class);
        System.out.println("5 - Inner.info() --> " + Inner.info() );
    }

    private static class Inner
    {
        static
        {
            System.out.println("4 - Initializing class Inner");
        }

        public static String info()
        {
            return "I'm a method in Inner";
        }
    }
}

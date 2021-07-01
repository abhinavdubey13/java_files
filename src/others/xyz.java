package others;

public class xyz {

    public static void main(String[] args) {
        System.out.println(1 << 0);
        System.out.println(1 << 1);
        System.out.println(1 << 2);
        System.out.println(1 << 3);
        System.out.println(1 << 4);

        /**
         *
         * int n = arr.len;
         * int N = 2^n;
         *
         * for : i-> 0-N
         *   for : j-> 0-n
         *
         *          int x = i && (1<<j);
         *          if( x!=0){
         *              add to current list
         *          }
         *
         */


    }
}

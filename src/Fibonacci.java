public class Fibonacci {

    /*
     *      Killian Callaghan
     *      18332783
     *      Practical 3
     */

    static int fibonacciIterative(int n){

        if (n<=1)
            return 1;

        int fib = 1;
        int prevFib =  1;

        for (int i = 2; i < n; i++) {
            int temp = fib;
            fib = fib + prevFib;
            prevFib = temp;
        }
        return fib;
    }

    public static int fibonacciRecursion(int n)
    {

        if(n <= 1)
        return n;

        return fibonacciRecursion(n-1) + fibonacciRecursion(n-2);
    }

    public static void main (String args[])
    {

        int n = 40;
        System.out.println(fibonacciIterative(n));
        System.out.println(fibonacciRecursion(n));

        final long startTime = System.currentTimeMillis();
        fibonacciIterative(n);
        final long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("the time taken Iterative: " + elapsedTime);

        final long startTimeRE = System.currentTimeMillis();
        fibonacciIterative(n);
        final long elapsedTimeRE = System.currentTimeMillis() - startTime;
        System.out.println("the time taken Recursion: " + elapsedTimeRE);




    }



}

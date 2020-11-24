/**
 * Coursera - Algorithms Part I
 * Week 1 - Interview Questions Part.2
 *
 * Question 3: Egg Drop
 *
 * Suppose that you have an N-story building (with floors 1 through N) and plenty
 * of eggs. An egg breaks if it is dropped from floor T or higher and does not
 * break otherwise. Your goal is to devise a strategy to determine the value of T
 * given the following limitations on the number of eggs and tosses:
 *
 * Version 0: 1 egg, ≤ T tosses.
 * Version 1: ~1lgN eggs and ~1lgN tosses.
 * Version 2: ~lgT eggs and ~2lgT tosses.
 * Version 3: 2 eggs and ~2sqrt(N) tosses.
 * Version 4: 2 eggs and ≤ c*sqrt(T) tosses for some fixed constant c.
 *
 *
 * Solutions:
 *
 * Version 0
 * Try each floor starting from the bottom. The first floor that the egg breaks on
 * is the value of T.
 *
 * Version 1
 * Use a binary search on the floors. First, try floor T/2. If the egg breaks, T
 * must be equal to T/2 or smaller. If the egg does not break, T must be greater
 * than T/2. Continue testing the mid-point of the subset of floors until T is
 * determined.
 *
 * Version 2
 * Start test at floor 0 and exponentially grow (2^t) floor number until first egg
 * breaks. The value of T must be between 2^t and 2^(t-1). This range can then
 * be searched in ~lgT tosses using the technique from version 1.
 *
 * Version 3
 * Test floors in increments of sqrt(N) starting from floor 0. When the egg breaks
 * on floor t, return to the previous test floor t-1 and increment by each floor.
 * The remaining sqrt(N) tests will be enough to check each floor between floor t-1
 * and t. The floor that breaks will be the value of T.
 *
 * Version 4
 * Try to throw eggs from 1, 4, 9, 16, 25,…(k-1)^2, k^2…floor,
 * if the egg is broken on floor k^2, it means (k-1)^2≤T ≤k^2,
 * this step has been tried √T times (k ≈ √T).
 * Then start from floor (k-1)^2+1 and end at floor k^2-1,
 * this step needs to try k^2-1-(k-1)^2-1+1=2√T-2times. Total used 3√T-2 times.
 */

public class W1P2N3EggDrop {
    public static int T = 10;
    static int numsOfTry = 0;
    static int numsOfEggBroken = 0;

    public static boolean drop(int i){
        numsOfTry++;
        if(i < T) {
            System.out.println("from " + i + " no");
            return false;
        }
        else{
            System.out.println("from " + i + " yes");
            numsOfEggBroken++;
            return true;
        }
    }

    //1egg;T
    public static void go0(int n){
        for (int i = 0; i < n; i++) {
            boolean tmp = drop(i);
            if(tmp == true) {
                System.out.println("T is " + i);
                break;
            }
        }
    }

    //lg(n)egg;lg(n)
    public static void go1(int start, int end){
        while(start < end) {
            int mid = (start + end) / 2;
            boolean tmp = drop(mid);
            if (tmp == false) start = mid + 1;
            else end = mid;
        }
        System.out.println("T is " + (start + end) / 2);
    }

    //lg(T)egg;2lg(T)
    public static void go2(int n){
        int i;
        int start = 0;
        int end = n - 1;
        boolean tmp = false;
        for (i = 0; i <= (int)(Math.log(n)  / Math.log(2)); i++) {
            int t = (int)Math.pow(2,(double)i);
            tmp = drop(t);
            if(tmp == true){
                if(i > 1) start = (int)Math.pow(2,(double)(i - 1)) + 1;
                end =(int)Math.pow(2,(double)i) - 1;
                System.out.println(start);
                break;
            }
        }
        if (tmp == false) go1((int)Math.pow(2,(double) (i-1)) + 1, n-1);
        else go1(start, end);
    }

    //2egg;2√n
    public static void go3(int n){
        int l = (int) Math.sqrt(n);
        int k = -1;
        for (int i = 0; i * l < n; i++) {
            boolean tmp = drop(i * l);
            if(tmp == true){
                k = i;
                break;
            }
        }
        for (int i = (k-1) * l + 1; i < k * l; i++) {
            boolean tmp = drop(i);
            if(tmp == true){
                System.out.println("\nT is " + i);
                break;
            }
        }
    }

    //2egg;3√T
    public static void go4(int n){
        int k = -1;
        int i;
        boolean tmp = false;
        for (i = 0; Math.pow(i, 2) < n; i++) {
            tmp = drop((int)Math.pow(i, 2));
            if(tmp == true){
                k = i;
                break;
            }
        }
        if (tmp == false) k = i;

        for (int j = (int)Math.pow(k-1, 2) + 1; j < Math.pow(k, 2); j++) {
            tmp = drop(j);
            if(tmp == true){
                System.out.println("\nT is " + j);
                break;
            }
        }
    }


    public static void main(String[] args) {
        int n = 15;
        //go1(n,0, n-1);
        go4(n);
        System.out.println("Number of the Broken Eggs is " + numsOfEggBroken);
        System.out.println("Number of trying is " + numsOfTry);
    }
}

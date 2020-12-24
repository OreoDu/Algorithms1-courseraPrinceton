/**
 * Coursera - Algorithms Part I
 * Week 2 - Interview Questions Part.2
 *
 * Question 3: Permutation
 *
 * DNF: Given an array of n buckets, each containing a red, white, or blue pebble, sort them by color.
 * The allowed operations are:
 * swap(i,j): swap the pebble in bucket i with the pebble in bucket j
 * color(i)determine the color of the pebble in bucket i
 *
 * The performance requirements are as follows:
 * At most n calls to color()
 * At most n calls to swap()
 * Constant extra space.
 *
 * - Solution:
 *   three-way partition
 */

public class W2P2N3DutchNationalFlag {
    private static void exch(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(char a, char b) {
        return (a == 'R' && (b == 'W'|| b == 'B')) || (a == 'W' && b == 'B');
    }

    public static void sort(char[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(char[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo, gt = hi;
        char sp = a[lo];

        while(i <= gt) {
            if(less(a[i],sp)) exch(a, i++, lt++);
            else if(less(sp, a[i])) exch(a, i, gt--);
            else i++;
        }

        sort(a, lo, lt-1);
        sort(a, gt + 1, hi);
    }

    private static void printArray(char[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.print("]");
    }

    public static void main(String args[]) {
        char[] s = {'B', 'R', 'W', 'R', 'W', 'W', 'B', 'R'};
        sort(s);
        printArray(s);
    }
}

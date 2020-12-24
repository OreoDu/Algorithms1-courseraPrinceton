/**
 * Coursera - Algorithms Part I
 * Week 3 - Interview Questions Part.1
 *
 * Question 1: Merging with smaller auxiliary array
 *
 * Suppose that the subarray a[0] to a[n−1] is sorted and the subarray a[n] to a[2∗n−1] is sorted.
 * How can you merge the two subarrays so that a[0] to a[2∗n−1] is sorted
 * using an auxiliary array of length n (instead of 2n)?
 *
 *
 * - Solution: only store the half result.
 *   step1. merge the smallest n items into auxiliary array.
 *   step.2 copy the remained items into the left half array(a[n] ~ a[2*n -1])
 *   step.3 copy the items in the auxiliary array into the right half array(a[0] ~ a[n - 1])
 *   step.4 merge the right half array using the auxiliary and copy the result into the original array.
 *
 */

public class W3P1N1MergeWithSmallAux {

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        Comparable[] aux = (hi- lo) % 2 == 0 ?  new Comparable[(hi - lo) / 2 + 1] : new Comparable[(hi - lo + 1) / 2];
        int i = lo, j = mid + 1;

        // merge the smallest n items;
        for (int k = 0; k < aux.length; k++) {
            if (j > hi) aux[k] = a[i++];
            else if (less(a[j], a[i])) aux[k] = a[j++];
            else aux[k] = a[i++];
        }

        // copy the remained items in the left half into the right half.
        int index = mid + 1;
        while(i <= mid) a[index++] = a[i++];

        // copy the items in the auxiliary array into the left half
        for (int k = 0; k < aux.length; k++) a[lo + k] = aux[k];

        // merge the right half
        lo = i;
        mid = j - 1;
        for (int k = 0; k <= hi - lo; k++) {
            if (hi <= lo) return;
            if (j > hi) aux[k] = a[i++];
            else if (i > mid) aux[k] = a[j++];
            else if (less(a[j], a[i])) aux[k] = a[j++];
            else aux[k] = a[i++];
        }

        // copy the result into the right half
        for (int k = 0; k <= hi - lo; k++) a[lo + k] = aux[k];
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;

        int mid = (lo + hi) / 2;
        sort(a, lo, mid);
        sort(a,mid + 1, hi);
        if(!less(a[mid + 1],a[mid])) return;
        merge(a, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        sort(a,  0, a.length - 1);
    }

    private static void printArray(Comparable[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.print("]");
    }

    public static void main(String[] args) {
        Comparable[] a = {2,9,2,3,8,7,9,1,6,10,4,5};
        sort(a);
        printArray(a);
    }
}

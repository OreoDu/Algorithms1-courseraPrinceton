import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Coursera - Algorithms Part I
 * Week 3 - Interview Questions Part.1
 *
 * Question 2: Counting inversions
 *
 *  An inversion in an array a[] is a pair of entries a[i] and a[j] such that i<j but a[i]>a[j].
 *  Given an array, design a linearithmic algorithm to count the number of inversions.
 *
 *
 * - Solution: counting while merge
 *
 */

public class W3P1N2CountingInversions {
    static int countInver = 0;
    static List<ArrayList<Comparable>> inverPair = new ArrayList<>();

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if(hi <= lo) return;

        int mid = (lo + hi) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        if(!less(a[mid + 1],a[mid])) return;
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = 0; k <= hi; k++) {
            aux[k] = a[k];
        }
        // merge
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) {
                int index = i;
                while(index <= mid) {
                    countInver++;
                    inverPair.add(new ArrayList<>(Arrays.asList(aux[index++],aux[j])));
                }
                a[k] = aux[j++];
            }
            else a[k] = aux[i++];
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void printArray(Comparable[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("]");
    }

    private static void printArrayList(List a) {
        System.out.print("[");
        for (int i = 0; i < a.size(); i++) {
            System.out.print( a.get(i)+ " ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Comparable[] a = {4,3,8,2,1,7,9,2};
        sort(a);
        printArray(a);
        System.out.println(countInver);
        printArrayList(inverPair);
    }
}

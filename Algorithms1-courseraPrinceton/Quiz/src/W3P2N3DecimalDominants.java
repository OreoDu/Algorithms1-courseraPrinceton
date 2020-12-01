import java.util.*;

/**
 * Coursera - Algorithms Part I
 * Week 3 - Interview Questions Part.2
 *
 * Question 3: Decimal dominants.
 * Given an array with n keys, design an algorithm to find all values that occur more than n/10 times.
 * The expected running time of your algorithm should be linear.
 *
 * Same question:
 * Given an array of size n and a number k, find all elements that appear more than n/k times
 * https://www.geeksforgeeks.org/given-an-array-of-of-size-n-finds-all-the-elements-that-appear-more-than-nk-times/
 *
 * - Solution 1: count and record the the occurrences of every element and then find the elements of which occurrences are more than n/k.
 *               we can use hash map to store the data.
 *               Time Complexity O(n) + O(k) Auxiliary Space: more or less than O(k)
 * - Solution 2: Sort the array (O(nlogn)) and then traverse the array to find the elements of which occurrences are more than n/k.
 *               Time Complexity O(nlogn) + O(n).
 * - Solution 3: use k-1 counters.  (like Tetris game)
 *　　           Time Complexity: O(nk)　Auxiliary Space: O(k)
 * - Solution 4: determine the (n/10)th largest key using quick select and check if it occurs more than n/k times.
 *
 */

public class W3P2N3DecimalDominants {
/* Solution 1:
    public static void moreThanNdK(int[] a, int n, int k) {
        HashMap<Integer, Integer> m = new HashMap<>();
        int x = n / k;

        for (int i = 0; i < a.length; i++) {
            if (!m.containsKey(a[i])) {
                m.put(a[i],1);
            }else {
                int count = m.get(a[i]);
                m.put(a[i], count + 1);
            }
        }

        for (Map.Entry i : m.entrySet()) {
            Integer re = (Integer) i.getValue();
            if (re > x)
                System.out.println(i.getKey());
        }
    }
 */
    public static void moreThanNdK(int[] a, int n, int k) {

    }

    public static void main(String[] args) {
        int[] array = {1,1,1,2,2,2,3,3,3,4};
        int k = 4;
        moreThanNdK(array, array.length, k);
    }

}

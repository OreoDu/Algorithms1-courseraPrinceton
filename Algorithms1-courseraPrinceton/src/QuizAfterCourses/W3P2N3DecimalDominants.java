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
 *            0    1    2   3    4    5   6   7   8   9
 *  n         1    3    1   2    5    3   4   5   2   1
 *  sorted    1    1    1   2    2    3   3   4   5   5     n = 10 , k = 5   result = 1
 *
 * - Solution 1: count and record the the occurrences of every element and then find the elements of which occurrences are more than n/k.
 *               we can use hash map to store the data.
 *               Time Complexity O(n) + O(k) Auxiliary Space: more or less than O(k)
 * - Solution 2: Sort the array (O(nlogn)) and then traverse the array to find the elements of which occurrences are more than n/k.
 *               Time Complexity O(nlogn) + O(n).
 *
 *               Boyer–Moore majority vote algorithm: https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
 *               https://cs.stackexchange.com/questions/91803/explaination-for-variation-of-boyer-moore-majority-voting-algorithm
 *               https://algorithms.tutorialhorizon.com/find-all-elements-in-an-array-which-appears-more-than-nk-times-n-is-array-size-and-k-is-a-number/
 * - Solution 3: use k-1 counters.  (like Tetris game)
 *　　           Time Complexity: O(nk)　Auxiliary Space: O(k)
 *
 *               https://cs.stackexchange.com/questions/100876/algorithm-to-find-all-values-that-occur-more-than-n-10-times
 * - Solution 4: determine the (n/k)th largest key using quick select and check if it occurs more than n/k times.
 *
 */

public class W3P2N3DecimalDominants {
/* Solution 1:
    public static void moreThanNdK(int[] a, int k) {
        HashMap<Integer, Integer> m = new HashMap<>();
        int x = a.length / k;

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

/* Solution 3:
    private static class element {
        int element;
        int counter;

        public element(int element, int counter) {
            this.element = element;
            this.counter = counter;
        }
    }

    public static int findEle(element[] elements, int ele) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].element == ele) return i;
        }
        return -1;
    }

    public static void add(element[] elements, int ele) {
        // cheak if the array is full
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].counter == 0) {
                elements[i].element = ele;
                elements[i].counter++;
                return;
            }
        }

        for (int i = 0; i < elements.length; i++) {
            elements[i].counter--;
        }
    }

    public static void moreThanNdK(int[] a, int k) {
        element[] elements = new element[k-1];

        for (int i = 0; i < k - 1; i++) {
            elements[i] = new element(0,0);
        }

        for (int i = 0; i < a.length; i++) {
            int index = findEle(elements,a[i]);
            if (index != -1) elements[index].counter++;
            else add(elements, a[i]);
        }

        for (int i = 0; i < elements.length; i++) {
            int count = 0;
            for (int j = 0; j < a.length; j++) {
                if(a[j] == elements[i].element && elements[i].counter != 0) count++;
            }
            if (count > a.length/k)
                System.out.println(elements[i].element + " appears more than n/"
                    + k + " times, Actual count is " + count);
        }
    }
 */

// Solution 4:
    public static void exch(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static int partition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while(true) {
            while (a[++i] < a[lo]) if (i == hi) break;
            while (a[--j] > a[lo]) if (j == lo) break;
            if (j <= i) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static int iNdKth(int k, int[] a) {
        k = k - 1;
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            int j = partition(a, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else return a[k];
        }
        return a[k];
    }

    public static void moreThanNdK(int[] a, int k) {
        Set<Integer> result = new HashSet<>();

        // O(k*2n)
        for (int i = 1; i <= k; i++) {
            result.add(iNdKth(a.length / k * i, a));
        }

        // O(kn)
        for (int num : result) {
            int count = 0;
            for (int j = 0; j < a.length; j++) {
                if(a[j] == num) count++;
            }
            if (count > a.length/k)
                System.out.println(num + " appears more than n/"
                        + k + " times, Actual count is " + count);
        }
    }

    public static void main(String[] args) {
        int[] array = {1,4,5,2,1,5,2,4,5,1,2,5};
        int k = 4;
        moreThanNdK(array, k);
    }

}

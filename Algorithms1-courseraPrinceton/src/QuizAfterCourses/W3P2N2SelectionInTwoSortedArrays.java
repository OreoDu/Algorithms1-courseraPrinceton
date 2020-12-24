/**
 * Coursera - Algorithms Part I
 * Week 3 - Interview Questions Part.2
 *
 * Question 2: Selection in two sorted arrays.
 *  Given two sorted arrays a[] and b[], of lengths n1, and n2 and an integer 0 < k < n1 + n2,
 *  design an algorithm to find a key of rank k.
 *  The order of growth of the worst case running time of your algorithm should be logn, where n = n1 + n2.
 *
 *  Version 1: n1=n2 (equal length arrays) and k=n/2 (median).
 *  Version 2: k=n/2 (median).
 *  Version 3: no restrictions.
 *
 *       0    1    2   3    4    5
 *  n1   1    3    5   8    12   15
 *  n2   4    7    9   10   11
 *
 *   https://www.geeksforgeeks.org/k-th-element-two-sorted-arrays/
 * - Solution 1: merge the two sorted arrays.
 *               Time Complexity: O(n). Auxiliary Space : O(m + n)
 * - Solution 2: we dont have to store the merged array, we just need to find the kth element
 *               Time Complexity : O(k). Auxiliary Space : O(1)
 * - Solution 3: if we can be sure that some elements are smaller or bigger than
 *               the subpart of the two arrays and the kth element is in this subpart.
 *               then we can get rid of those elements during the searching.
 *               - version1: we can compare the n1[mid1] and n2[mid2], then get rid of the unuseful elements.
 *                 Time Complexity: O(log n + log m)
 *               - version2: because we only care about the element of which index+1 is smaller than k in the two arrays,
 *                 so just need to compare n1[min(n1.length, k/2)] and n2[min(n2.length, k/2)]
 *                 Time Complexity: O(2 log k)
 *
 *   https://stackoverflow.com/questions/26436151/selection-in-two-sorted-arrays
 * - Solution 5: Design a constant-time algorithm to determine whether a[i] is a key of rank k.
 *               Use this subroutine and binary search.
 *               Suppose that n1[i] is the k-th largest element,
 *               then we can say that there are (k - i - 1) elements before the k-th element which are belong to n2.
 *               So n2[k - i - 1 - 1] <= n1[i] <= n2[k - i - 1] and we can use this to search the k-th element.
 *
 */

public class W3P2N2SelectionInTwoSortedArrays {
/* Solution 3 version 1:
    public static int findK(int[] n1, int[] n2, int k){
        return findK(n1, n2, k, 0, n1.length - 1, 0, n2.length - 1);
    }

    public static int findK(int[] n1, int[] n2, int k, int l1, int h1, int l2, int h2) {
        int len1 = h1 - l1 + 1, mid1 = (l1 + h1) / 2;
        int len2 = h2 - l2 + 1, mid2 = (l2 + h2) / 2;
        int mid = (len1 + len2) / 2;

        if (h1 < l1) return n2[l2 + k - 1];
        if (h2 < l2) return n1[l1 + k - 1];
        if ( len1 == 1 && n1[mid1] > n2[mid2] && k <= mid) return n2[l2 + k -1];
        if ( len2 == 1  && n1[mid1] < n2[mid2] && k <= mid) return n1[l1 + k -1];

        if (k > mid) {
            // the k-th element is in the right part of the two arrays
            // and we can get rid of some small elements that can not be the k-th largest element.
            if (n1[mid1] < n2[mid2]) return findK(n1, n2, k - mid1 - 1 + l1,mid1 + 1, h1, l2, h2);
            else return findK(n1, n2, k - mid2 - 1 + l2, l1, h1, mid2 + 1, h2);
        } else {
            // the k-th element is in the left part of the two arrays.
            // and we can get rid of some large elements that can not be the k-th largest element.
            if (n1[mid1] < n2[mid2]) return findK(n1, n2, k, l1, h1, l2, mid2);
            else return findK(n1, n2, k, l1, mid1, l2, h2);
        }
    }
*/

/* Solution 3 version 2:
    public static int  findK(int[] n1, int[] n2, int k) {
        int h1 = n1.length > k ? k - 1 : n1.length - 1;
        int h2 = n2.length > k ? k - 1 : n2.length - 1;
        return findK(n1, n2, k,  0, h1, 0, h2);
    }

    public static int findK(int[] n1, int[] n2, int k, int l1, int h1, int l2, int h2) {
        int len1 = h1 - l1 + 1, len2 = h2 - l2 + 1;

        // make sure that the length of n1 is smaller than n2
        if (len1 > len2) return findK(n2, n1, k, l2, h2, l1, h1);

        if (l1 > h1) return n2[l2 + k - 1];
        if (k == 1) return Math.min(n1[l1], n2[l2]);

        int i = Math.min(k/2, len1);
        int j = Math.min(k/2, len2);

        if (n1[i -1 + l1] > n2[j - 1 + l2]) {
            // n2[l2] ~ n2[j - 1 + l2] (the number of them is no more than k/2)can only at most
            // find (i - 1)(which is smaller than k/2) elements which are larger than them.
            // so we can get rid of those element.
            return findK(n1, n2, k - j, l1, h1, l2 + j, h2);
        }else {
            // n1[l1] ~ n1[i - 1 + l1] (the number of them is no more than k/2) can only at most
            // find (j - 1)(which is smaller than k/2) elements which are larger than them.
            // so we can get rid of those element.
            return findK(n1, n2, k - i, l1 + i, h1, l2, h2);
        }
    }
 */
    public static int isK(int[] n1, int[] n2, int i, int k) {
        if (k - i - 1 < n2.length) {
            if (k - i - 2 >= 0 && n1[i] < n2[k - i - 2]) return -1;
            else if (n1[i] > n2[k - i - 1]) return 1;
            else return 0;
        }else if (k - i - 2 < n2.length && n1[i] >= n2[k - i - 2]) return 0;
        else return -1;
    }

    public static int biSearch(int[] n1, int[] n2, int k) {
        int lo = 0, hi = n1.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (mid >= k || isK(n1, n2, mid, k) == 1) hi = mid - 1;
            else if (isK(n1, n2, mid, k) == -1) lo = mid + 1;
            else if (isK(n1, n2, mid, k) == 0) return n1[mid];
        }
        return -1;
    }

    public static int findK(int[] n1, int[] n2, int k) {
        int re1 = biSearch(n1, n2, k);
        int re2 = biSearch(n2, n1, k);
        return re1 != -1 ? re1 : re2;
    }

    public static void main(String[] args) {
        int[] n1 = {1, 5, 7, 7, 9, 10};
        int[] n2 = {2, 3, 4, 6, 8};
        int k = 11;

        int result = findK(n1, n2, k);
        System.out.println(result);
    }
}

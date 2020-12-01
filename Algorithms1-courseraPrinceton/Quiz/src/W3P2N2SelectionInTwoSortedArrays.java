/**
 * Coursera - Algorithms Part I
 * Week 3 - Interview Questions Part.2
 *
 * Question 2: Selection in two sorted arrays.
 *  Given two sorted arrays a[] and b[], of lengths n1, and n2 and an integer 0 < k < n1 + n2,
 *  design an algorithm to find a key of rank k.
 *  The order of growth of the worst case running time of your algorithm should be logn, where n = n1 + n2n.
 *
 *  Version 1: n1=n2 (equal length arrays) and k=n/2 (median).
 *  Version 2: k=n/2 (median).
 *  Version 3: no restrictions.
 *
 * https://www.geeksforgeeks.org/k-th-element-two-sorted-arrays/
 *       0    1    2   3    4    5
 *  n1   1    3    5   8    12   15
 *  n2   4    7    9   10   11
 *
 * - Solution 1: merge the two sorted arrays.
 *               Time Complexity: O(n). Auxiliary Space : O(m + n)
 * - Solution 2: we dont have to store the merged array, we just need to find the kth element
 *               Time Complexity : O(k). Auxiliary Space : O(1)
 * - Solution 3: if we can be sure that some elements are smaller or bigger than
 *               the subpart of the two arrays and the kth element is in this subpart.
 *               then we can get rid of those elements during the searching.
 *               Time Complexity: O(log n + log m)
 * - Solution 4:
 *
 * - Solution 5: Design a constant-time algorithm to determine whether a[i] is a key of rank k.
 *               Use this subroutine and binary search.
 *
 */

public class W3P2N2SelectionInTwoSortedArrays {
//Solution 3:
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
            if (n1[mid1] < n2[mid2]) return findK(n1, n2, k - mid1 - 1 + l1,mid1 + 1, h1, l2, h2);
            else return findK(n1, n2, k - mid2 - 1 + l2, l1, h1, mid2 + 1, h2);
        } else {
            if (n1[mid1] < n2[mid2]) return findK(n1, n2, k, l1, h1, l2, mid2);
            else return findK(n1, n2, k, l1, mid1, l2, h2);
        }
    }


    public static void main(String[] args) {
        int[] n1 = {1, 5, 7, 7, 9, 10};
        int[] n2 = {2, 3, 4, 6, 8};
        int k = 2;

        int result = findK(n1, n2, k);
        System.out.println(result);
    }
}

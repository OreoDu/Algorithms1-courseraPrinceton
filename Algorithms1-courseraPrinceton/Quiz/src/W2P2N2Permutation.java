/**
 * Coursera - Algorithms Part I
 * Week 2 - Interview Questions Part.2
 *
 * Question 2: Permutation
 *
 * Given two integer arrays of size n, design a subquadratic algorithm to determine whether one is a permutation of the other.
 * That is, do they contain exactly the same entries but, possibly, in a different order.
 */
public class W2P2N2Permutation {

    private static void exch(int[] a,int i,int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void sort(int[] a) {
        for(int h = a.length / 3; h >= 1; h = h/3) {
            for (int i = h; i < a.length; i++) {
                for (int j = i; j >= h && a[j] < a[j-h]; j -= h)
                    exch(a,j,j-h);
            }
        }
    }

    public static boolean permutation(int[] a, int[] b) {
        sort(a);
        sort(b);
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) continue;
            else return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 10;
        int[] a = {1,4,4,5};
        int[] b = {1,4,5,4};
        /*Random rn = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = rn.nextInt(10);
            b[i] = rn.nextInt(10);
        } */
        System.out.println(permutation(a,b));
    }
}

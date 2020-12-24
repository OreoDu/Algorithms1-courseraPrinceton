import java.awt.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Coursera - Algorithms Part I
 * Week 2 - Interview Questions Part.2
 *
 * Question 1: Intersection Of Two Sets
 *
 * Given two arrays a[] and b[], each containing n distinct 2D points in the plane,
 * design a subquadratic algorithm to count the number of points.
 * that are contained both in array a[] and array b[].
 */
public class W2P2N1IntersectionOfTwoSets {
/* Solution 1:
    public class PlanePoints{
        private Set<Point> s = new HashSet<>();
        private int samePointsNum;

        PlanePoints(int n,Point[] inputa, Point[] inputb){
            for(int i=0;i<n;i++) {
                s.add(inputa[i]);
                s.add(inputb[i]);
            }
            samePointsNum = 2*n - s.size();
        }

        public int getSamePointsNum() {
            return samePointsNum;
        }
    }
 */
    public static class PlanePoints{
        Point[] a,b;
        int n;
        private int samePointsNum;

        PlanePoints(int n,Point[] inputa, Point[] inputb){
            a = inputa;
            b = inputb;
            this.n = n;
        }

        private boolean smallThan(Point a, Point b) {
            return a.x < b.x || (a.x == b.x && a.y < b.y);
        }

        private void exch (Point[] a, int i, int j) {
            Point temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        private void sort(Point[] p) {
            for (int h = n/3; h >= 1; h = h/3) {
                for (int i = h; i < n; i++) {
                    for (int j = i; j >= h && smallThan(a[j],a[j-h]); j -= h)
                        exch(p,i,j-h);
                }
            }
        }

        public int getSamePointsNum() {
            sort(a);
            sort(b);

            int i = 0, j = 0;
            int index = 0;
            Point[] intersect = new Point[n];
            while(i < n && j < n) {
                if (a[i].equals(b[j])) {
                    intersect[index++] = a[i++];
                    j++;
                }else if (smallThan(a[i],b[j])) i++;
                else j++;
            }
            Point[] result = Arrays.copyOfRange(intersect,0,index);
            samePointsNum = result.length;

            return samePointsNum;
        }
    }

    public static void main(String[] args) {
        int n = 10;
        Random rn = new Random();
        Point[] a = new Point[n];
        Point[] b = new Point[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Point();
            a[i].setLocation(rn.nextInt(10),rn.nextInt(10));
            b[i] = new Point();
            b[i].setLocation(rn.nextInt(10),rn.nextInt(10));
        }
        b[3] = a[1];
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        PlanePoints p = new PlanePoints(n,a,b);
        System.out.println(p.getSamePointsNum());
    }
}

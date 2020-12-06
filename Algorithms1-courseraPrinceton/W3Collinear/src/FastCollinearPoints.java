import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {
    private LineSegment[] segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("null argument to constructor!");
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException("The points in the array can not be null!");
        }

        List<LineSegment> segmentList = new ArrayList<>();
        Point[] pointsCp = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCp);

        for (int i = 0; i < pointsCp.length - 1; i++) {
            if (pointsCp[i].compareTo(pointsCp[i + 1]) == 0)
                throw new IllegalArgumentException("There are duplicated points!");
        }

        int len = pointsCp.length;
        LinkedList<Point> pointList = new LinkedList<>();

        for (int i = 0; i < len; i++) {
            Arrays.sort(pointsCp);
            // suppose that system sort(java use merge sort for objects which is stable) is stable.
            // so if thr slope of the points are the same, they will order by the points order.
            Arrays.sort(pointsCp, pointsCp[i].slopeOrder());

            for (int j = 0; j < len - 1; j++) {
                pointList.add(pointsCp[j]);
                while (j + 1 < pointsCp.length && pointsCp[0].slopeTo(pointsCp[j]) == pointsCp[0].slopeTo(pointsCp[j+1])) {
                    pointList.add(pointsCp[++j]);
                }

                // we have to make sure that there are more than two points in the line and also the first point is the smallest.
                if (pointList.size() >= 3 && pointsCp[0].compareTo(pointList.get(0)) < 0) {
                    LineSegment lineSegment = new LineSegment(pointsCp[0], pointList.get(pointList.size() - 1));
                    if (!segmentList.contains(lineSegment)) segmentList.add(lineSegment);
                }
                pointList.clear();
            }
        }
        segments = segmentList.toArray(new LineSegment[segmentList.size()]);
    }

    // the number of line segments
    public int numberOfSegments()  {
        if (segments == null) return 0;
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.clone();
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

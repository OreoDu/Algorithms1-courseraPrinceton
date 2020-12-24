package W3Collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new NullPointerException("null argument to constructor!");
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

        for (int i = 0; i < len - 3; i++) {
            for (int j = i + 1; j < len -2; j++) {
                for (int k = j + 1; k < len - 1; k++) {
                    for (int l = k + 1; l < len; l++) {
                        if (pointsCp[i].slopeTo(pointsCp[j]) == pointsCp[i].slopeTo(pointsCp[k]) &&
                            pointsCp[i].slopeTo(pointsCp[j]) == pointsCp[i].slopeTo(pointsCp[l])) {
                            LineSegment tmp = new LineSegment(pointsCp[i], pointsCp[l]);
                            if (!segmentList.contains(tmp)) segmentList.add(tmp);
                        }
                    }
                }
            }
        }
        segments = segmentList.toArray(new LineSegment[segmentList.size()]);
    }

    // the number of line segments
    public int numberOfSegments() {
        if (segments == null) return 0;
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.clone();
    }
}
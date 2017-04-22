import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {

    private ArrayList<Point> mypoints = new ArrayList<>();
    private ArrayList<LineSegment> mylineSegments = new ArrayList<>();
    private ArrayList<Point> mycopy;

    public FastCollinearPoints(Point[] points) {
        if (points == null){
            throw new NullPointerException();
        }
        for (Point p :
                points
                ) {
            if(p==null){
                throw new NullPointerException();
            }
            mypoints.add(p);
        }

        mypoints.sort(Point::compareTo);
        for (int i = 0; i <mypoints.size()-1; i++) {
            if(mypoints.get(i).compareTo(mypoints.get(i+1))==0)
                throw new IllegalArgumentException();
        }

    }// finds all line segments containing 4 or more points
    public int numberOfSegments() // the number of line segments
    {
        return this.mylineSegments.size();
    }
    public LineSegment[] segments() // the line segments
    {
        ArrayList<LineSegment> res_linesegment = new ArrayList<>();

        //按照第i个点的斜率顺序排序
        for (int i = 0; i < this.mypoints.size(); i++) {

            Point incoke_one = this.mypoints.get(i);
            this.mycopy = (ArrayList<Point>) mypoints.clone();
            this.mycopy.sort(incoke_one.slopeOrder());
            int count = 1;

            for (int j = 2; j < this.mypoints.size(); j++) {

                if (
                        mycopy.get(j).slopeTo(incoke_one) ==
                                mycopy.get(j - 1).slopeTo(incoke_one)
                        ) {
                    count++;
                } else {
                    if (count >= 3) {

                        Point[] temp = new Point[count + 1];

                        for (int k = j - count; k < j; k++) {
                            temp[k - j + count] = mycopy.get(k);
                        }
                        temp[count] = incoke_one;

                        Arrays.sort(temp, Point::compareTo);

                        res_linesegment.add(new LineSegment(temp[0], temp[count]));

                    }
                    count = 1;
                }
            }

            if (count >= 3) {

                Point[] temp = new Point[count + 1];

                for (int k = mycopy.size() - count; k < mycopy.size(); k++) {
                    temp[k - mycopy.size() + count] = mycopy.get(k);
                }
                temp[count] = incoke_one;

                Arrays.sort(temp, Point::compareTo);

                res_linesegment.add(new LineSegment(temp[0], temp[count]));

            }
        }

        res_linesegment.sort(new Comparator<LineSegment>() {
            @Override
            public int compare(LineSegment l1, LineSegment l2) {
                return l1.toString().compareTo(l2.toString());
            }
        });

        int len  = res_linesegment.size();
        for (int i = 1; i < len; i++) {
            if(res_linesegment.get(i).toString().equals(res_linesegment.get(i-1).toString()))
            {
                res_linesegment.remove(i);
                --len;
                --i;
            }
        }
        return res_linesegment.toArray(new LineSegment[res_linesegment.size()]);
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
//print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
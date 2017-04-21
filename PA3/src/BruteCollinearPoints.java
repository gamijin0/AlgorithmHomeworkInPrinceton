import java.io.Console;
import java.util.ArrayList;
import edu.princeton.cs.algs4.*;

public class BruteCollinearPoints {

    private Point[] points;
    private ArrayList<LineSegment> mylineSegments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        if (points == null){
            throw new NullPointerException();
        }
        for (Point p :
                points
             ) {
            if(p==null){
                throw new NullPointerException();
            }
        }
        this.points = points;
    }
    public int numberOfSegments(){
        return this.mylineSegments.size();
    }



    public LineSegment[] segments(){
        for (int i= 0;i<points.length-3;i++){
            for (int j= i+1;j<points.length-2;j++){
                for (int k= j+1;k<points.length-1;k++){
                    for (int l= k+1;l<points.length;l++){
                        ArrayList<Point> temp = new ArrayList<>();
                        temp.add(points[i]);
                        temp.add(points[j]);
                        temp.add(points[k]);
                        temp.add(points[l]);
                        temp.sort(Point::compareTo);

                        //check if there is some repeated points
                        for(int t =0;t<3;t++){
                            if(temp.get(t).compareTo(temp.get(t+1))==0){
                                throw  new IllegalArgumentException();
                            }
                        }

                        if(
                            temp.get(0).slopeTo(temp.get(1))==temp.get(0).slopeTo(temp.get(2)) &&
                            temp.get(0).slopeTo(temp.get(2))==temp.get(0).slopeTo(temp.get(3))
                                ){

//                                StdOut.println(temp.get(0).slopeTo(temp.get(1)));
//                                StdOut.println(temp.get(0).slopeTo(temp.get(2)));
//                                StdOut.println(temp.get(0).slopeTo(temp.get(3)));
//                                StdOut.println();
                                LineSegment a =  new LineSegment(temp.get(0),temp.get(3));
                                this.mylineSegments.add(a);
                        }


                    }
                }
            }
        }
        return (LineSegment[]) this.mylineSegments.toArray(new LineSegment[this.numberOfSegments()]);
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
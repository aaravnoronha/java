import java.awt.geom.*;     // for Point2D.Double
import java.util.ArrayList; // for ArrayList
public class IrregularPolygon {

    private ArrayList<Point2D.Double> myPolygon;

    public static void main(String[] args) {
	    // write your code here
        IrregularPolygon poly = new IrregularPolygon();
        poly.add(new Point2D.Double(0.0,0.0));
        poly.add(new Point2D.Double(4.0,0.0));
        poly.add(new Point2D.Double(0.0,5.0));
        poly.add(new Point2D.Double(0.0,1.0));

        double peri = poly.perimeter();
        System.out.println("peri = " + peri);
        double a = poly.area();
        System.out.println("area = " + a);

    }
    // constructors
    public IrregularPolygon() {
        myPolygon = new ArrayList<Point2D.Double>();
    }

    // public methods
    public void add(Point2D.Double aPoint) {
        myPolygon.add(aPoint);
    }

    public void draw() { }

    public double perimeter() {
        double peri = 0.0;
        for(int i = 0; i < myPolygon.size(); i++) {
            Point2D.Double p1 = myPolygon.get(i);
            Point2D.Double p2 = myPolygon.get((i+1) % myPolygon.size());
            peri += Math.sqrt((p2.y - p1.y)*(p2.y - p1.y) + (p2.x - p1.x)*(p2.x - p1.x));
            System.out.println("i = "  + i);
            System.out.println(peri);
        }
        return peri;
    }

    public double area() {
        double a = 0.0;
        int n = myPolygon.size();
        for(int i = 0; i < n - 1; i++){
            Point2D.Double p1 = myPolygon.get(i);
            Point2D.Double p2 = myPolygon.get((i+1) % n);
            a += p1.x*p2.y - p2.x*p1.y;
        }
        Point2D.Double p0   = myPolygon.get(0);
        Point2D.Double pn_1 = myPolygon.get(n-1);
        a += pn_1.x * p0.y - pn_1.y * p0.x;
        a = Math.abs(a);
        a /= 2.0;
        return a;
    }


}

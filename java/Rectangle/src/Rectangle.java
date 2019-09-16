import javax.swing.*;

public class Rectangle  {

    private static JFrame frame;
    private static MyPanel panel;
    public int myX;
    public int myY;
    public int myWidth;
    public int myHeight;

    public static void main(String[] args) {

        Rectangle r = new Rectangle(50,50,50,50);
        r.getArea();
        r.getPerimeter();

	    frame = new JFrame();
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new MyPanel(r);
        frame.add(panel);
        frame.setVisible(true);

        r.draw();
    }

    public Rectangle() {
        myX = 0;
        myY = 0;
        myWidth = 0;
        myHeight = 0;
    }

    public Rectangle(int x, int y, int width, int height) {
        myX = x;
        myY = y;
        myWidth = width;
        myHeight = height;

    }

    public double getPerimeter() {
        double perimeter = 2 * (myWidth + myHeight);
        return perimeter;
    }

    public double getArea() {
        double area = myWidth * myHeight;
        return area;

    }

    public void draw() {
        panel.repaint();
    }

}

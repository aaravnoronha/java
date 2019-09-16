public class ShapeTester {

    public void main(String[] args) {
        double a, b, c;
        int i = 5, j = 10;
        a = doThis(i);
        Square sq1 = new Square();
        sq1.setSide(a);
        sq1.setArea();
        b = sq1.getArea();

        Square sq2 = new Square();
        sq2.setSide((double)j);
        sq2.setArea();
        c = sq2.getArea();

        Rectangle r1 = new Rectangle(a, b);
        r1.setArea();

        System.out.println("Square1 area = " +  b + " Square2 area = " + c + " Rect1 area = " + r1.getArea());

    }
}

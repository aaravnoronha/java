import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    public Rectangle rect;
    public MyPanel(Rectangle r) {
        rect = r;
    }

    public void paintComponent(Graphics g) {
        g.drawRect(rect.myX, rect.myY, rect.myWidth, rect.myHeight);
    }

    public void drawRect(Rectangle r) {

    }

}

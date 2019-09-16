
import javax.swing.*;
import java.awt.*;


public class DrawHouse extends JPanel
{
    private static JFrame frame;
    public static void main(String[] args)
    {
        DrawHouse dh = new DrawHouse();
    }
    public DrawHouse()
    {
        frame = new JFrame();
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);
    }
    public void paintComponent(Graphics g)
    {
        g.drawLine(100,150,100,350);
        g.drawLine(400,150,400,350);
        g.drawLine(100,350,400,350);
        g.drawLine(200,350,200,300);
        g.drawLine(200,300,250,300);
        g.drawLine(250,300,250,350);
        g.drawLine(100,150,400,150);
        g.drawLine(100,150,225,50);
        g.drawLine(225,50,400,150);
        g.drawRect(150,180,35,35);
        g.drawRect(275,180,35,35);

    }
}

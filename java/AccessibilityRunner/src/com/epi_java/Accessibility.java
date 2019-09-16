package com.epi_java;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public class Accessibility extends JFrame implements MouseListener,KeyListener {
    private Color backgroundColor;
    private int fontSize;
    public Accessibility()
    {
        setSize(1000, 800);
        // setContentPane(new DrawPane());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addKeyListener(this);
        addMouseListener(this);
        backgroundColor = new Color(100,100,100);
        fontSize = 12;
    }

        public void drawRectanglesWithLabels(Graphics g) {
            g.setColor(Color.RED);
            g.fillRect(10, 10, 100, 20);
            g.fillRect(115, 10, 100, 20);
            g.setColor(Color.BLACK);
            g.drawString("brighter", 15, 80);
            g.drawString("darker", 115, 80);
        }

        public void medicalPageContent(Graphics g) {
        }

        public void paintComponent(Graphics g) {
            setBackground(backgroundColor);
            drawRectanglesWithLabels(g);
            g.setColor(Color.BLACK);
            Font font = new Font("Serif", Font.PLAIN, fontSize);
            g.setFont(font);
            medicalPageContent(g);
            // super.paintComponent(g);
        }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            if (fontSize <= 75) {
                fontSize += 5;
            }
        } else if (key == KeyEvent.VK_DOWN) {
            if (fontSize >= 13) {
                fontSize -= 5;
            }
        }
        System.out.println("fontSize = " + fontSize);
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        requestFocusInWindow();
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("hello");
        int x = e.getX();
        int y = e.getY();
        int red = backgroundColor.getRed();
        int green = backgroundColor.getGreen();
        int blue = backgroundColor.getBlue();
        if (x >= 10 && x <= 110 && y >= 10 && y <= 30) {
            red += 3;
            green += 3;
            blue += 3;
            if (red > 255)
                red = 255;
            if (green > 255)
                green = 255;
            if (blue > 255)
                blue = 255;
        } else if (x >= 115 && x <= 215 && y >= 10 && y <= 30) {
            red -= 3;
            green -= 3;
            blue -= 3;
            if (red < 34)
                red = 255;
            if (green < 34)
                green = 255;
            if (blue < 34)
                blue = 34;
        }
        backgroundColor = new Color(red, green, blue);
        repaint();

    }
}

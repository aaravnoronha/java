package com.epi_java;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ColorChooseFrame extends JFrame implements KeyListener, FocusListener, MouseListener
{
    SlideBar bar1;
    SlideBar bar2;
    SlideBar bar3;
    int barchoice;
    boolean firsttime;
    DisplayPanel canvas;

    public ColorChooseFrame ( )
    {
        super( "Color Choose" );
        bar1 = new SlideBar ( 40, 20, 255, 125, true );
        bar2 = new SlideBar ( 80, 20, 255, 125, false );
        bar3 = new SlideBar ( 120, 20, 255, 125, false );
        barchoice = 1;
        firsttime = true;
        canvas = new DisplayPanel();
        setContentPane(canvas);
        canvas.setBackground(Color.white);
        canvas.addFocusListener(this);
        canvas.addKeyListener(this);
        canvas.addMouseListener(this);

        setSize ( 410, 360 );
        setResizable(false);
        setDefaultCloseOperation ( DISPOSE_ON_CLOSE );
        setLocation ( 20, 50 );
        show ( );
    }

    class DisplayPanel extends JPanel
    {

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            paintback ( g );
            bar1.draw ( g );
            bar2.draw ( g );
            bar3.draw ( g );
            if ( firsttime )
                instructions ( g );
            else
                drawColoredRect ( g );
        }

        public void paintback ( Graphics g )
        {
            g.setColor ( Color.black );
            g.fillRect ( 0, 0, 10, 320 );
            g.fillRect ( 0, 0, 400, 10 );
            g.fillRect ( 390, 0, 10, 320 );
            g.fillRect ( 0, 310, 400, 10 );
            g.setColor ( Color.white );
            g.fillRect ( 10, 10, 380, 300 );
        }

        public void instructions ( Graphics g )
        {
            g.setColor ( Color.black );
            g.drawString ( "Click on the applet ", 200, 80 );
            g.drawString ( "window to start.", 200, 110 );
            g.drawString ( "Use the up, down, right, ", 200, 140 );
            g.drawString ( "and left arrow keys to ", 200, 170 );
            g.drawString ( "control the bars.", 200, 200 );
            firsttime = false;
        }

        public void drawColoredRect ( Graphics g )
        {
            Color chosen = new Color ( bar1.Value(), bar2.Value(), bar3.Value() );
            g.setColor ( chosen );
            g.fillRect ( 150, 60, 200, 200 );
            g.setColor ( Color.black );
            g.drawRect ( 150, 60, 200, 200 );
            g.drawString ( "Color (" + bar1.Value() + ", " + bar2.Value() + ", " +
                    bar3.Value() + " )", 200, 280 );
        }
    }

    public void focusGained(FocusEvent evt)
    {
        firsttime = false;
        movebar ( 0 );
        canvas.repaint();
    }

    public void focusLost(FocusEvent evt)
    {
        firsttime = true;
        movebar ( 0 );
        canvas.repaint();
    }

    public void mousePressed(MouseEvent evt)
    {
        canvas.requestFocus();
    }

    public void mouseEntered(MouseEvent evt) { }
    public void mouseExited(MouseEvent evt) { }
    public void mouseReleased(MouseEvent evt) { }
    public void mouseClicked(MouseEvent evt) { }

    public void keyTyped ( KeyEvent e )   {}

    public void keyPressed ( KeyEvent e )
    {
        int value = e.getKeyCode();
        if ( value == KeyEvent.VK_DOWN )
            movebar ( -5 );
        else if ( value == KeyEvent.VK_UP )
            movebar ( 5 );
        else if ( value == KeyEvent.VK_RIGHT )
        {
            if ( barchoice == 3 )
                barchoice = 3;
            else
                barchoice++;
            movebar ( 0 );
        }
        else if ( value == KeyEvent.VK_LEFT )
        {
            if ( barchoice == 1 )
                barchoice = 1;
            else
                barchoice--;
            movebar ( 0 );
        }
        canvas.repaint();
    }

    public void keyReleased ( KeyEvent e )   {}

    public void movebar ( int motion )
    {
        if ( barchoice == 1 )
            bar1.slide ( motion );
        else if ( barchoice == 2 )
            bar2.slide ( motion );
        else if ( barchoice == 3 )
            bar3.slide ( motion );
    }
}

class SlideBar
{
    private int xpos, ypos, height, position;
    public boolean on = true;

    public SlideBar  ( int x, int y, int h, int p, boolean o )
    {
        xpos = x;
        ypos = y;
        height = h;
        position = p;
        on = o;
    }

    public void slide ( int m )
    {
        on = true;
        position += m;
        if ( position > 255 )
            position = 255;
        else if ( position < 0 )
            position = 0;
    }

    public void draw ( Graphics g )
    {
        g.setColor ( Color.black );
        g.drawLine ( xpos, ypos, xpos, ypos + height );
        if ( on )
            g.setColor ( Color.red );
        g.fillRect ( xpos - 10, ypos + height - position - 5, 20, 10 );
        g.drawString ( "" + position, xpos - 5, ypos + height + 20 );
        on = false;
    }

    public int Value ()
    {
        return position;
    }
}




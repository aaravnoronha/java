package com.epi_java;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
public class Trig extends JApplet
{
    private int choice;
    private double xval;
    private JFrame frame;
    private DisplayPanel canvas;
    private ButtonPanel buttonPanel;
    public Trig ( )
    {
        choice = 0;
        xval = 0.0;
    }
    public void run()
    {
        frame = new JFrame ("Trig");
        frame.setSize(780, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas = new DisplayPanel();
        canvas.setBackground(Color.black);
        buttonPanel = new ButtonPanel ( );
        frame.getContentPane().add(canvas, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.NORTH );
        frame.setVisible(true);
    }
    public Insets getInsets()
    {
        return new Insets ( 10, 10, 10, 10 );
    }
    class DisplayPanel extends JPanel
    {
        private Image numbers;
        private int xcorner, ycorner;
        private int width, height;
        private double xleft, xright, xincrement;
        private int ybottom, ytop;
        private String name[] = { "sin", "cos", "tan", "csc", "sec", "cot" };
        private String numerator[] =   { "opposite","adjacent","opposite","hypotenuse","hypotenuse","adjacent" };
        private String denominator[] = { "hypotenuse","hypotenuse","adjacent","opposite","adjacent","opposite" };
        public DisplayPanel ( )
        {
            try
            {
                numbers = ImageIO.read(new File("Numbers.jpg"));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            waitForImage ( this, numbers );
            setBackground ( Color.white );
            width = 760;
            height = 300;
            xleft = ( -Math.PI / 6.0 );
            xright = ( 19.0 * Math.PI / 6.0 );
            xincrement = ( Math.PI / 6.0 );
            ybottom = -3;
            ytop = 3;
            xcorner = 10;
            ycorner = 230;
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent ( g );
            paintFunction ( g );
            drawUnitCircle ( g );
            displayTrigRatio ( g );
        }
        public void waitForImage ( JPanel component, Image image )
        {
            MediaTracker tracker = new MediaTracker ( component );
            try
            {
                tracker.addImage ( image, 0 );
                tracker.waitForID ( 0 );
            }
            catch ( InterruptedException e )
            {
                e.printStackTrace ( );
            }
        }
        public void drawUnitCircle ( Graphics g )
        {
            int xpos = 50, ypos = 15, radius = 100;
            int col = ((int)(xval * 180 / Math.PI + 0.1) % 360) / 30;
            int oppmovefact = -25;
            if ( Math.cos(xval) > 0.0 )
                oppmovefact *= -1;
            g.drawImage( numbers, (int)(xpos + radius + radius * Math.cos (xval)) - 15 + oppmovefact,
                    (int)(ypos + radius - radius * Math.sin (xval) / 2.0) - 20,
                    (int)(xpos + radius + radius * Math.cos (xval)) + 22 + oppmovefact,
                    (int)(ypos + radius - radius * Math.sin (xval) / 2.0) + 27, col * 38 + 1, 1,
                    ( col + 1 ) * 38, 48, this );
            int adjmovefact = 22;
            if ( Math.sin(xval) - 0.01 > 0.0 )
                adjmovefact *= -1;
            g.drawImage( numbers, (int)(xpos + radius + radius * Math.cos (xval) / 2.0) - 15,
                    (int)(ypos + radius - adjmovefact - 25),
                    (int)(xpos + radius + radius * Math.cos (xval) / 2.0) + 22,
                    (int)(ypos + radius - adjmovefact + 22), col * 38 + 1, 51,
                    ( col + 1 ) * 38, 98, this );
            g.drawImage( numbers, (int)(xpos + radius + radius * Math.cos (xval) / 2.0) - 10,
                    (int)(ypos + radius - radius * Math.sin (xval)) - 10,
                    (int)(xpos + radius + radius * Math.cos (xval) / 2.0) + 27,
                    (int)(ypos + radius - radius * Math.sin (xval)) + 37, col * 38 + 1, 101,
                    ( col + 1 ) * 38, 148, this );
            g.setColor ( Color.gray );
            g.drawOval ( xpos, ypos, 2 * radius, 2 * radius );
            g.drawOval ( xpos + 1, ypos + 1, 2 * radius - 2, 2 * radius - 2 );
            g.drawOval ( xpos + 2, ypos + 2, 2 * radius - 4, 2 * radius - 4 );
            g.setColor ( Color.red );
            for ( int i = -1; i <= 1; i++ )
            {
                if ( choice == 1 || choice == 4 )
                    g.setColor ( Color.lightGray );
                else if ( choice == 3 || choice == 5 )
                    g.setColor ( Color.blue );
                else
                    g.setColor ( Color.red );

                g.drawLine ( xpos + radius + (int)(Math.cos(xval)*radius) + i, ypos + radius,
                        xpos + radius + (int)(Math.cos(xval)*radius) + i,
                        ypos + radius - (int)(Math.sin(xval)*radius) );

                if ( choice == 0 || choice == 3 )
                    g.setColor ( Color.lightGray );
                else if ( choice == 2 || choice == 4 )
                    g.setColor ( Color.blue );
                else
                    g.setColor ( Color.red );
                g.drawLine ( xpos + radius, ypos + radius + i,
                        xpos + radius + (int)(Math.cos(xval)*radius), ypos + radius + i );
                if ( choice == 2 || choice == 5 )
                    g.setColor ( Color.lightGray );
                else if ( choice == 0 || choice == 1 )
                    g.setColor ( Color.blue );
                else
                    g.setColor ( Color.red );
                g.drawLine ( xpos + radius, ypos + radius + i, xpos + radius + (int)(Math.cos(xval)*radius),
                        ypos + radius - (int)(Math.sin(xval)*radius) + i );

            }
        }

        public void displayTrigRatio ( Graphics g )
        {
            g.setColor ( Color.black );
            Font textFont = new Font ( "SansSerif", Font.BOLD, 28 );
            g.setFont ( textFont );
            g.drawString ( name[choice] + " " + (int)(xval * 180 / Math.PI + 0.1) + " =", 300, 65 );
            int row1 = 0, row2 = 0;
            switch (choice)
            {
                case 0:
                    row1 = 0;
                    row2 = 2;
                    break;
                case 1:
                    row1 = 1;
                    row2 = 2;
                    break;
                case 2:
                    row1 = 0;
                    row2 = 1;
                    break;
                case 3:
                    row1 = 2;
                    row2 = 0;
                    break;
                case 4:
                    row1 = 2;
                    row2 = 1;
                    break;
                case 5:
                    row1 = 1;
                    row2 = 0;
                    break;
            }

            int col = ((int)(xval * 180 / Math.PI + 0.1) % 360) / 30;
            g.drawImage( numbers, 450, 5, 487,
                    54, col * 38 + 1, row1 * 50 + 1,
                    ( col + 1 ) * 38, ( row1 + 1 ) * 50, this );
            g.drawImage( numbers, 450, 55, 487,
                    104, col * 38 + 1, row2 * 50 + 1,
                    ( col + 1 ) * 38, ( row2 + 1 ) * 50, this );
            g.fillRect ( 445, 55, 40, 4 );

            if ( ((int)(xval * 180 / Math.PI + 0.1) % 360) % 90 != 0 )
            {
                g.drawString ( "=", 510, 65 );
                row1 += 3;
                row2 += 3;
                g.drawImage( numbers, 540, 13, 577,
                        60, col * 38 + 1, row1 * 50 + 3,
                        ( col + 1 ) * 38, ( row1 + 1 ) * 50, this );
                g.drawImage( numbers, 540, 55, 577,
                        102, col * 38 + 1, row2 * 50 + 3,
                        ( col + 1 ) * 38, ( row2 + 1 ) * 50, this );
                g.fillRect ( 540, 55, 40, 4 );
            }
            g.drawString ( "y =  " + name[choice] + " x =", 300, 180 );
            g.fillRect ( 490, 170, 160, 4 );
            g.setColor ( Color.red );
            g.drawString ( numerator[choice], 490, 155 );
            g.setColor ( Color.blue );
            g.drawString ( denominator[choice], 490, 205 );
        }

        public void paintFunction ( Graphics g )
        {
            g.setColor ( Color.black );
            g.fillRect ( xcorner - 2, ycorner - 2, width + 4, height + 4 );
            g.setColor ( Color.white );
            g.fillRect ( xcorner + 2, ycorner + 2, width - 4, height - 4 );
            g.setColor ( Color.black );
            g.fillRect ( xcorner + (int)(width / (xright - xleft) * (0.0 - xleft)) - 2, ycorner, 4, height );
            g.fillRect ( xcorner, ycorner + (int)(height / (double)(ytop - ybottom) * ytop) - 2, width, 4 );
            for ( double i = xleft; i <= xright; i += xincrement )
            {
                g.fillRect ( xcorner + (int)(width / (xright - xleft) * (i - xleft)) - 2,
                        ycorner + (int)(height / (double)(ytop - ybottom) * ytop) - 8, 4, 16 );
                g.drawLine ( xcorner + (int)(width / (double)(xright - xleft) * (i - xleft)), ycorner,
                        xcorner + (int)(width / (double)(xright - xleft) * (i - xleft)), ycorner + height );
            }
            for ( int i = ybottom; i <= ytop; i++ )
            {
                g.fillRect ( xcorner + (int)(width / (xright - xleft) * (0.0 - xleft)) - 8,
                        ycorner + (int)(height /(double)(ytop - ybottom) * (i - ybottom)) - 2, 16, 4 );
                g.drawLine ( xcorner, ycorner + (int)(height / (double)(ytop - ybottom) * (i - ybottom)),
                        xcorner + width, ycorner + (int)(height / (double)(ytop - ybottom) * (i - ybottom)) );
            }
            double y, prevy, prevx;
            prevx = xleft;
            prevy = getValue ( prevx );
            g.setColor ( Color.blue );
            for ( double x = xleft; x <= xval; x += 0.01 )
            {
                y = getValue ( x );
                for ( int i = -3; i <= 0; i++ )
                {
                    if ( y > -10 && y < 10 )
                        g.drawLine ( xcorner + (int)(width / (xright - xleft) * (x - xleft)),
                                ycorner + height - (int)(height / (double)(ytop - ybottom) * (y - ybottom)) + i,
                                xcorner + (int)(width / (xright - xleft) * (prevx - xleft)),
                                ycorner + height-(int)(height / (double)(ytop - ybottom) * (prevy - ybottom)) + i );
                }
                prevx = x;
                prevy = y;
            }
            g.setColor ( Color.red );
            if ( getValue ( xval ) < 10 && getValue ( xval ) > -10 )
                g.fillOval ( xcorner + (int)(width / (xright - xleft) * (xval - xleft)) - 5,
                        ycorner + height -
                                (int)(height / (double)(ytop - ybottom) * (getValue(xval) - ybottom)) - 5,
                        10, 10 );
            g.setColor ( Color.white );
            g.fillRect ( 0, 0, xcorner - 2, height + ycorner );
            g.fillRect ( 0, 0, width + xcorner, ycorner - 2 );
            g.fillRect ( 0, ycorner + height + 2, width + xcorner, height + ycorner );
            g.fillRect ( xcorner + width + 2, 0, width + xcorner, height + ycorner );
            g.setColor ( Color.black );
        }

        public double getValue ( double x )
        {
            switch ( choice )
            {
                case 0:
                    return ( Math.sin ( x ) );
                case 1:
                    return ( Math.cos ( x ) );
                case 2:
                    return ( Math.tan ( x ) );
                case 3:
                    return ( 1 / Math.sin ( x ) );
                case 4:
                    return ( 1 / Math.cos ( x ) );
                case 5:
                    return ( 1 / Math.tan ( x ) );
            }
            return -1.0;
        }
    }

    class ButtonPanel extends JPanel implements ActionListener
    {

        public ButtonPanel ( )
        {
            setBackground ( Color.blue );

            JButton increasex = new JButton ( "INCREASE x value" );
            increasex.addActionListener ( this );
            this.add ( increasex );

            JButton decreasex = new JButton ( "DECREASE x value" );
            decreasex.addActionListener ( this );
            this.add ( decreasex );

            JButton changetrig = new JButton ( "CHANGE trig function" );
            changetrig.addActionListener ( this );
            this.add ( changetrig );
        }

        public void actionPerformed ( ActionEvent evt )
        {
            String command = evt.getActionCommand();

            if ( command.equals ( "INCREASE x value" ) )
                xval += ( Math.PI / 6.0 );
            else if ( command.equals ( "DECREASE x value" ) )
                xval -= ( Math.PI / 6.0 );
            else if ( command.equals ( "CHANGE trig function" ) )
            {
                choice++;
                choice = choice % 6;
            }
            if ( xval < 0.0 )
                xval = 0.0;
            else if (xval > 19.0 * Math.PI / 6.0 )
                xval = 19.0 * Math.PI / 6.0;
            canvas.repaint ( );

        }
    }
}

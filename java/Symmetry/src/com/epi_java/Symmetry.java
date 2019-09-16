package com.epi_java;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Symmetry
{

    private int x1, y1, x2, y2, x3, y3, x4, y4;
    private int colorchoice, symmetrychoice;
    private boolean clearit;

    private JFrame frame;
    private DisplayPanel canvas;
    private ColorPanel colorpanel;
    private SymmetryPanel symmetrypanel;

    public Symmetry ( )
    {
        x1 = y1 = x2 = y2 = x3 = y3 = x4 = y4 = -20;
        colorchoice = symmetrychoice = 0;
        clearit = true;
    }



    public void run()
    {
        // Create a frame to hold everything
        frame = new JFrame ("Symmetry");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setBackground(Color.black);	// only needed if frame is larger than panel

        // Define panel to draw on
        canvas = new DisplayPanel();		// create a panel to draw on
        canvas.setBackground(Color.gray);
        frame.addMouseListener(canvas);	// connects the MouseListerner to the panel window
        frame.addMouseMotionListener(canvas);

        colorpanel = new ColorPanel ( );
        symmetrypanel = new SymmetryPanel ( );

        // Put frame together
        frame.getContentPane().add(canvas, BorderLayout.CENTER);	// puts panel on frame
        frame.getContentPane().add ( colorpanel, BorderLayout.NORTH );
        frame.getContentPane().add ( symmetrypanel, BorderLayout.SOUTH );
        frame.setVisible(true);
    }
    /*
           public Insets getInsets()
        {
            return new Insets ( 10, 10, 10, 10 );
           }
    */
    class DisplayPanel extends JPanel implements MouseListener, MouseMotionListener
    {

        public DisplayPanel ( )
        {
            setBackground ( Color.gray );
            addMouseListener ( this );
            addMouseMotionListener ( this );
        }

        public void paintComponent(Graphics g)
        {
            if ( clearit )
            {
                super.paintComponent ( g );
                clearit = false;
            }
        }

        public void drawIt ( Graphics g )
        {
            if ( colorchoice == 0 )
                g.setColor ( Color.blue );
            else if ( colorchoice == 1 )
                g.setColor ( Color.red );
            else if ( colorchoice == 2 )
                g.setColor ( Color.green );
            else if ( colorchoice == 3 )
                g.setColor ( Color.yellow );
            else if ( colorchoice == 4 )
                g.setColor ( Color.white );

            if ( symmetrychoice == 0 )
                drawOne ( g );
            else if ( symmetrychoice == 1 )
                drawTwo ( g );
            else if ( symmetrychoice == 2 )
                drawFour ( g );
            else if ( symmetrychoice == 3 )
                drawEight ( g );
        }

        public void drawOne ( Graphics g )
        {
            g.fillOval ( x1 - 5, y1 - 5, 10, 10 );
        }

        public void drawTwo ( Graphics g )
        {
            g.fillOval ( x1 - 5, y1 - 5, 10, 10 );
            x2 = 500 - x1;
            y2 = y1;
            g.fillOval ( x2 - 5, y2 - 5, 10, 10 );
        }

        public void drawFour ( Graphics g )
        {
            g.fillOval ( x1 - 5, y1 - 5, 10, 10 );
            x2 = 500 - x1;
            y2 = y1;
            g.fillOval ( x2 - 5, y2 - 5, 10, 10 );
            x3 = x1;
            y3 = 500 - y1;
            g.fillOval ( x3 - 5, y3 - 5, 10, 10 );
            x4 = 500 - x1;
            y4 = 500 - y1;
            g.fillOval ( x4 - 5, y4 - 5, 10, 10 );
        }

        public void drawEight ( Graphics g )
        {
            g.fillOval ( x1 - 5, y1 - 5, 10, 10 );
            x2 = 500 - x1;
            y2 = y1;
            g.fillOval ( x2 - 5, y2 - 5, 10, 10 );
            x3 = x1;
            y3 = 500 - y1;
            g.fillOval ( x3 - 5, y3 - 5, 10, 10 );
            x4 = 500 - x1;
            y4 = 500 - y1;
            g.fillOval ( x4 - 5, y4 - 5, 10, 10 );
            int x5 = y1;
            int y5 = x1;
            g.fillOval ( x5 - 5, y5 - 5, 10, 10 );
            int x6 = y2;
            int y6 = x2;
            g.fillOval ( x6 - 5, y6 - 5, 10, 10 );
            int x7 = y3;
            int y7 = x3;
            g.fillOval ( x7 - 5, y7 - 5, 10, 10 );
            int x8 = y4;
            int y8 = x4;
            g.fillOval ( x8 - 5, y8 - 5, 10, 10 );
        }


        public void mousePressed(MouseEvent evt)
        {
            x1 = evt.getX ( );
            y1 = evt.getY ( );
            Graphics g = getGraphics ( );
            drawIt ( g );
        }

        public void mouseEntered(MouseEvent evt)  { }
        public void mouseExited(MouseEvent evt)   { }
        public void mouseReleased(MouseEvent evt) { }
        public void mouseClicked(MouseEvent evt)  { }

        public void mouseMoved(MouseEvent evt)    { }
        public void mouseDragged(MouseEvent evt)
        {
            x1 = evt.getX ( );
            y1 = evt.getY ( );
            Graphics g = getGraphics ( );
            drawIt ( g );
        }
    }

    class ColorPanel extends JPanel implements ActionListener
    {

        public ColorPanel ( )
        {
            setBackground ( Color.blue );

            JButton blue = new JButton ( "BLUE" );
            blue.addActionListener ( this );
            this.add ( blue );

            JButton red = new JButton ( "RED" );
            red.addActionListener ( this );
            this.add ( red );

            JButton green = new JButton ( "GREEN" );
            green.addActionListener ( this );
            this.add ( green );

            JButton yellow = new JButton ( "YELLOW" );
            yellow.addActionListener ( this );
            this.add ( yellow );

            JButton white = new JButton ( "WHITE" );
            white.addActionListener ( this );
            this.add ( white );
        }

        public void actionPerformed ( ActionEvent evt )
        {
            String command = evt.getActionCommand();

            if ( command.equals ( "BLUE" ) )
                colorchoice = 0;
            else if ( command.equals ( "RED" ) )
                colorchoice = 1;
            else if ( command.equals ( "GREEN" ) )
                colorchoice = 2;
            else if ( command.equals ( "YELLOW" ) )
                colorchoice = 3;
            else if ( command.equals ( "WHITE" ) )
                colorchoice = 4;
        }
    }

    class SymmetryPanel extends JPanel implements ActionListener
    {

        public SymmetryPanel ( )
        {
            setBackground ( Color.blue );

            JButton one = new JButton ( "ONE" );
            one.addActionListener ( this );
            this.add ( one );

            JButton two = new JButton ( "TWO" );
            two.addActionListener ( this );
            this.add ( two );

            JButton four = new JButton ( "FOUR" );
            four.addActionListener ( this );
            this.add ( four );

            JButton eight = new JButton ( "EIGHT" );
            eight.addActionListener ( this );
            this.add ( eight );

            JButton clear = new JButton ( "CLEAR" );
            clear.addActionListener ( this );
            this.add ( clear );
        }

        public void actionPerformed ( ActionEvent evt )
        {
            String command = evt.getActionCommand();

            if ( command.equals ( "ONE" ) )
                symmetrychoice = 0;
            else if ( command.equals ( "TWO" ) )
                symmetrychoice = 1;
            else if ( command.equals ( "FOUR" ) )
                symmetrychoice = 2;
            else if ( command.equals ( "EIGHT" ) )
                symmetrychoice = 3;
            else if ( command.equals ( "CLEAR" ) )
            {
                clearit = true;
                canvas.repaint ( );
            }
        }
    }
}
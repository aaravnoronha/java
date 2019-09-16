package com.epi_java;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class SimplePaint2
{

    JComboBox colorChoice;  // This is a member variable since the "canvas"
    // needs access to this pop-up menu so that it
    // will know what color to use.

    JFrame frame;
    SimplePaintCanvas canvas;
    JPanel buttonBar;
    public void run()
    {
        // Create a frame to hold everything
        frame = new JFrame ("SimplePaint");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setBackground(Color.black);	// only needed if frame is larger than panel

        // Define panel to draw on
        canvas = new SimplePaintCanvas();		// create a panel to draw on
        canvas.setBackground(Color.gray);
        frame.addMouseListener(canvas);	// connects the MouseListerner to the panel window

        buttonBar = new JPanel();		 // A panel to hold the controls.
        setButtonBar();

        // Put frame together
        frame.getContentPane().add(canvas, BorderLayout.CENTER);	// puts panel on frame
        frame.getContentPane().add(buttonBar, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public void setButtonBar()
    {
        buttonBar.setBackground(Color.gray);
        colorChoice = new JComboBox();  // The pop-up menu of available colors.
        colorChoice.addItem("Black");
        colorChoice.addItem("Red");
        colorChoice.addItem("Green");
        colorChoice.addItem("Blue");
        colorChoice.addItem("Cyan");
        colorChoice.addItem("Magenta");
        colorChoice.addItem("Yellow");
        colorChoice.addItem("White");
        colorChoice.setBackground(Color.white);
        buttonBar.add(colorChoice);

        JButton fill = new JButton("Set Background");  // The first button.
        fill.addActionListener(canvas);
        fill.setBackground(Color.lightGray);
        buttonBar.add(fill);

        JButton clear = new JButton("Clear");	// The second button.
        clear.addActionListener(canvas);
        clear.setBackground(Color.lightGray);
        buttonBar.add(clear);

    }  // end init()


    public Insets getInsets()
    {
        // Specify how wide a border to leave around the edges of the applet.
        return new Insets(3,3,3,3);
    }


    // ----------------- Nested class definition --------------------------------

    class SimplePaintCanvas extends JPanel
            implements MouseListener, MouseMotionListener, ActionListener
    {

        // A SimplePaintCanvas lets the user use the mouse to draw colored
        // curves.  The current color is specified by a pop-up menu which
        // must be created by the applet and assigned to the instance variable
        // colorChoice.  The applet listens for action events from buttons
        // named "Clear" and "Set Background".  The "Clear" button fills
        // the canvas with the current background color.  The "Set Background"
        // sets the background color to the current drawing color and
        // then clears.


        private final static int
                BLACK = 0,
                RED = 1,				// Some constants to make
                GREEN = 2,			 // the code more readable.
                BLUE = 3,			  // These numbers code for
                CYAN = 4,			  // the differnet drawing colors.
                MAGENTA = 5,
                YELLOW = 6;

		/* The following variables are used when the user is sketching a
			curve while dragging a mouse. */

        private int prevX, prevY;	  // The previous location of the mouse.

        private boolean dragging;		// This is set to true while the user is drawing.

        private Graphics graphicsForDrawing;  // A graphics context for the applet
        // that is used to draw the user's curve.
        // (It's not supposed to be good form
        // to draw directly onto a component.
        // The official recommendation is to do
        // all drawing in the paintComponent()
        // method.  But this does work.)

        SimplePaintCanvas()
        {
            // Constructor.  When the canvas is first created, it is set to
            // listen for mouse events and mouse motion events from
            // itself.  The initial background color is white.
            addMouseListener(this);
            addMouseMotionListener(this);
            setBackground(Color.white);
        }


        public void actionPerformed(ActionEvent evt)
        {
            // Respond when the user clicks on a button.
            String command = evt.getActionCommand();
            if (command.equals("Clear"))
            {
                // Clear to current background color.
                repaint();
            }
            else if (command.equals("Set Background"))
            {
                // Set background color, then clear.
                // Also change the selected color so it is
                // not the same as the background color.
                // Change it to black, unless the background is
                // black; in that case change it to white.
                setBackground(getCurrentColor());
                if (colorChoice.getSelectedIndex() == 0)
                    colorChoice.setSelectedIndex(7);
                else
                    colorChoice.setSelectedIndex(0);
                repaint();
            }
        }


        private Color getCurrentColor()
        {
            // Check the colorChoice menu to find the currently
            // selected color, and return the appropriate color
            // object.
            int currentColor = colorChoice.getSelectedIndex();
            switch (currentColor)
            {
                case BLACK:
                    return Color.black;
                case RED:
                    return Color.red;
                case GREEN:
                    return Color.green;
                case BLUE:
                    return Color.blue;
                case CYAN:
                    return Color.cyan;
                case MAGENTA:
                    return Color.magenta;
                case YELLOW:
                    return Color.yellow;
                default:
                    return Color.white;
            }
        }


        public void mousePressed(MouseEvent evt)
        {
            // This is called when the user presses the mouse on the
            // canvas.  This begins a draw operation in which the user
            // sketches a curve.

            if (dragging == true)  // Ignore mouse presses that occur
                return;				//	 when user is already drawing a curve.
            //	 (This can happen if the user presses
            //	 two mouse buttons at the same time.)

            prevX = evt.getX();	 // Start drawing.
            prevY = evt.getY();
            dragging = true;
            graphicsForDrawing = getGraphics();
            graphicsForDrawing.setColor(getCurrentColor());

        } // end mousePressed()


        public void mouseReleased(MouseEvent evt)
        {
            // Called whenever the user releases the mouse button.
            // If the user was drawing a curve, the curve is done,
            // so we should set drawing to false and get rid of
            // the graphics context that we created to use during
            // the drawing.
            if (dragging == false)
                return;  // Nothing to do because the user isn't drawing.
            dragging = false;
            graphicsForDrawing.dispose();
            graphicsForDrawing = null;
        }


        public void mouseDragged(MouseEvent evt)
        {
            // Called whenever the user moves the mouse
            // while a mouse button is held down.  If the
            // user is drawing, draw a line segment from the
            // previous mouse location to the current mouse
            // location, and set up prevX and prevY for the
            // next call.

            if (dragging == false)
                return;  // Nothing to do because the user isn't drawing.

            int x = evt.getX();	// x-coordinate of mouse.
            int y = evt.getY();	// y=coordinate of mouse.

            graphicsForDrawing.drawLine(prevX, prevY, x, y);  // Draw the line.

            prevX = x;  // Get ready for the next line segment in the curve.
            prevY = y;

        } // end mouseDragged.


        public void mouseEntered(MouseEvent evt) { }	// Some empty routines.
        public void mouseExited(MouseEvent evt) { }	 //	 (Required by the MouseListener
        public void mouseClicked(MouseEvent evt) { }	//	 and MouseMotionListener
        public void mouseMoved(MouseEvent evt) { }	  //	 interfaces).


    } // end class SimplePaintCanvas


} // end class SimplePaint2

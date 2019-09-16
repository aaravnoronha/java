package com.epi_java;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Whirligig
{
    private JFrame frame;
    private JTextArea area = new JTextArea("",20,70);
    private DisplayPanel canvas;
    private ButtonPanel buttonPanel;

    public Whirligig ( )
    {
    }
    public static void main(String[] args)
    {
        Whirligig w = new Whirligig();
        w.Run();
    }

    public void Run()
    {
        // Create a frame to hold everything
        frame = new JFrame ("Hear Mouse");
        frame.setSize(750, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setBackground(Color.black);	// only needed if frame is larger than panel

        // Define panel to draw on
        canvas = new DisplayPanel();		// create a panel to draw on
        canvas.setBackground(new Color(130,50,40));
        frame.addMouseListener(canvas);	// connects the MouseListerner to the panel window
        buttonPanel = new ButtonPanel ( );

        // Put frame together
        frame.getContentPane().add(canvas);	// puts panel on frame
        frame.getContentPane().add( buttonPanel, BorderLayout.SOUTH );
        frame.setVisible(true);
        frame.add(area);
    }
    class DisplayPanel extends JPanel implements MouseListener
    {

        public DisplayPanel ( )
        {
        }
        public void paintComponent(Graphics g)
        {

        }
        public void mousePressed(MouseEvent evt){ }
        public void mouseEntered(MouseEvent evt) { }
        public void mouseExited(MouseEvent evt) { }
        public void mouseReleased(MouseEvent evt) { }
        public void mouseClicked(MouseEvent evt) { }
    }

    class ButtonPanel extends JPanel implements ActionListener
    {

        public ButtonPanel ( )
        {
            setBackground ( Color.black );
            JButton button = new JButton ( "Sun Exposure And It's Effects" );
            button.addActionListener ( this );
            this.add ( button );

            JButton button2 = new JButton ( "5 Ways To Treat Sunburn" );
            button2.addActionListener ( this );
            this.add ( button2 );

        }

        public void actionPerformed ( ActionEvent evt )
        {
            String command = evt.getActionCommand();

            if ( command.equals ( "Sun Exposure And It's Effects" ) )
                area.setText(
                                "Ultraviolet (UV) rays are an invisible form of radiation.\n\n" +
                                "They can pass through your skin and damage your skin cells.\n\n"+
                                "Sunburns are a sign of skin damage. Suntans aren't healthy, either.\n\n"+
                                "They appear after the sun's rays have already killed some cells and damaged others.\n\n"+
                                "UV rays can cause skin damage during any season or at any temperature.\n\n"+
                                "They can also cause eye problems, wrinkles, skin spots, and skin cancer.\n\n"+
                                "To protect yourself, stay out of the sun for over an hour, wear protective clothing,\n\n"+
                                "use sunscreen, wear sunglasses, and avoid tanning."
                );
            else if ( command.equals ( "5 Ways To Treat Sunburn" ) )
                area.setText("1.You must act fast to cool it down or it will continue to worsen over time - dip affected area in cold water.\n\n" +
                             "2.Moisturize your skin while it is damp because it will start drying over time.\n\n" +
                             "3.Decrease the inflammantion as soon as you see a sign of a sunburn.\n\n" +
                             "4.Replenish your fluids by drinking lots of water as dehydration may be a contributing factor.\n\n" +
                             "5.See a doctor if it develops into blisters on your skin because this could be a sign of skin cancer"
                );
        }
    }
}

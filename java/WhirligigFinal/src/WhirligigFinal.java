import java.awt.Graphics;									// imports related to displaying text and color in a window
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import java.io.File;												// imports related to displaying an image in a window
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;

import java.awt.event.ActionListener;					// imports used when a component is selected
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


import javax.swing.JFrame;									// imports related to components
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridLayout;								// imports for the layout of various panels
import java.awt.BorderLayout;
import java.awt.CardLayout;

/* Pratyush Shanbhag
 * March 26, 2019
 * WhirligigFinal.java
 *
 * The main class contains the main JFrame and displays all the JPanels. WhirligigPanel allows the user to select JCheckBoxes and see
 * how much SPF is covering an average human. WhirligigPanel2 allows the user to use two JSliders to choose the skin type of an
 * individual and the UV index of the area the individual is in at that moment. Based on the two sliders, the program calculates the maximum
 * amount of time the individual can stay under the sun without any harm to their skin.
 *
 */

public class WhirligigFinal									// the class that has all the other classes nested in it and produces the window
{

    private static JFrame frame;							// main JFrame --> frame to display all the JPanels
    private JFrame buttonsFrame;							// frame to display the buttons to choose a specific JPanel to be shown
    private WhirligigPanel wgp;								// instance of WhirligigPanel that is used to be shown in the window
    private WhirligigPanel2 wp2;							// instance of WhirligigPanel2 that is used to be shown in the window
    private RadioButtonPanel rbp;						// instance of RadioButtonPanel that is used to be shown in the window
    private WPanel wp;											// instance of WPanel that is used to be shown in the window
    private ButtonActionPanel bap;						// instance of ButtonActionPanel that is used to be shown in the buttonsFrame window
    private static CardLayout cl;							// CardLayout instance which will be set as the layout for the main JPanel
    private static JPanel panelContainer;				// main JPanel of the program

    public WhirligigFinal()
    {

        frame = new JFrame("Whirligig");																			// set the descriptions of the main JFrame
        frame.setBounds(50, 10, 700, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        cl = new CardLayout();

        panelContainer = new JPanel();
        panelContainer.setLayout(cl);																					// set the layout of the main JPanel to use CardLayout
        frame.getContentPane().add(panelContainer);															// add the main JPanel to the main JFrame

        wgp = new WhirligigPanel();

        wp2 = new WhirligigPanel2();

        rbp = new RadioButtonPanel();

        wp = new WPanel();

        panelContainer.add(wgp , "1");																				// add the interactive JPanels to the main JPanel
        panelContainer.add(wp2 , "2");
        panelContainer.add(rbp , "3");
        panelContainer.add(wp , "4");

        buttonsFrame = new JFrame("Whirligig Buttons");													// set the descriptions of the frame that holds the buttons
        buttonsFrame.setBounds(800, 10, 400, 600);
        buttonsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonsFrame.setResizable(false);

        bap = new ButtonActionPanel();
        buttonsFrame.getContentPane().add(bap);																// add the buttons JPanel to the buttons JFrame

        cl.show(panelContainer, "1");																					// initially show the first JPanel when the program is executed

        frame.setVisible(true);																								// sets the main JFrame to be visible
        buttonsFrame.setVisible(true);																				// sets the buttons JFrame to be visible
    }

    public static void main(String[] args)
    {
        WhirligigFinal wgf = new WhirligigFinal();
    }

    static class ButtonActionPanel extends JPanel  implements ActionListener
    {

        private SunExposure se;																							// instance of the last JPanel
        private JButton[] buttons;																						// array of buttons. When a button is pressed, a certain JPanel will be shown
        private String[] buttonNames;																				// the array of Strings holding the text displayed on the array of buttons
        private int buttonNum;																							// integer to check

        public ButtonActionPanel()
        {
            setBackground(Color.WHITE);																				// set background color of the JPanel

            se = new SunExposure();

            buttonNames = new String[5];																			// set size of the String array and the Strings
            buttonNames[0] = "1st Panel";
            buttonNames[1] = "2nd Panel";
            buttonNames[2] = "3rd Panel";
            buttonNames[3] = "4th Panel";
            buttonNames[4] = "5th Panel";

            buttons = new JButton[5];																					// set size of the JButton array

            for(int num = 0; num < buttons.length; num++) 												// create the JButtons and add them to the JPanel
            {
                buttons[num] = new JButton(buttonNames[num]);
                add(buttons[num]);
                buttons[num].addActionListener(this);
            }

            buttonNum = 0;
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            Font font = new Font("Arial", Font.BOLD, 13);													// set the font for the text
            g.setFont(font);

            if(buttonNum == 1)																								// check if the first button was selected
            {
                g.drawString("Select one or more accessories to protect the", 10, 200);
                g.drawString(" average human against the sun", 10, 250);

            }
            else if(buttonNum == 2)																						// check if the second button was selected
            {
                g.drawString("Move the sliders to see how long an individual", 10, 200);
                g.drawString("with the chosen skin type at the chosen ", 10, 250);
                g.drawString("temperature can stay in the sun without any ", 10, 300);
                g.drawString("harmful effects. The top slider is about the", 10, 350);
                g.drawString("UV index and the bottom slider is about the", 10, 400);
                g.drawString("skin type of an individual", 10, 450);


            }
            else if(buttonNum == 3)																						// check if the third button was selected
            {
                g.drawString("Press the radio buttons to see how an individual", 10, 200);
                g.drawString("is affected by the sun with and without", 10, 250);
                g.drawString("sunscreen applied", 10, 300);
            }
            else if(buttonNum == 4)																						// check if the fourth button was selected
            {
                g.drawString("Protect Jack from the sun by helping him", 10, 200);
                g.drawString("dress up appropriately for the warm day", 10, 250);
                g.drawString("by pressing the buttons!", 10, 300);

            }
            else if(buttonNum == 5)																						// check if the fifth and last button was selected
            {
                g.drawString("Press the buttons to learn facts related", 10, 200);
                g.drawString("to sun exposure", 10, 250);

            }
        }

        public void actionPerformed(ActionEvent e)
        {

            if(e.getSource().equals(buttons[4]))																		// check if the fifth and last button was selected
            {
                buttonNum = 5;
                frame.setVisible(false);
                se.run();
            }
            else
            {
                se.frame.setVisible(false);
                frame.setVisible(true);

                if(e.getSource().equals(buttons[0]))																	// check if the first button was selected
                {
                    cl.show(panelContainer, "1");
                    buttonNum = 1;
                }
                else if(e.getSource().equals(buttons[1]))															// check if the second button was selected
                {
                    cl.show(panelContainer, "2");
                    buttonNum = 2;
                }
                else if(e.getSource().equals(buttons[2]))															// check if the third button was selected
                {
                    cl.show(panelContainer, "3");
                    buttonNum = 3;
                }
                else if(e.getSource().equals(buttons[3]))															// check if the fourth button was selected
                {
                    cl.show(panelContainer, "4");
                    buttonNum = 4;
                }
            }


            repaint();																												// call the paintComponent method and change the text displayed based on the button selected
        }
    }

    static class WhirligigPanel extends JPanel implements ActionListener
    {

        private JCheckBox[] checkboxes;																				// array of JCheckBoxes
        private int totalSPF;																								// integer of the total SPF
        private String[] str;																									// array of Strings for the information based on the chosen JCheckBoxes

        public WhirligigPanel()
        {
            setBackground(Color.WHITE);																				// set background of the JPanel
            setLayout(new FlowLayout(FlowLayout.LEFT, 500, 75));										// set a layout that is a FlowLayout that is indented to the left
            checkboxes = new JCheckBox[5];																		// set size of JCheckBox array
            for(int index = 0; index < checkboxes.length; index++)										// create the JCheckBoxes and add it to the JPanel
            {
                checkboxes[index] = new JCheckBox();
                checkboxes[index].addActionListener(this);
                add(checkboxes[index]);
            }
            checkboxes[0].setText("Wide brimmed hat");														// set the text for the JCheckBoxes
            checkboxes[1].setText("75 SPF sunscreen");
            checkboxes[2].setText("T-shirt");
            checkboxes[3].setText("Blue jeans");
            checkboxes[4].setText("50 SPF sunscreen");

            totalSPF = 0;
            str = new String[5];																								// set size of the String array

            for(int num = 0; num < str.length; num++)														// create the array of Strings
            {
                str[num] = new String(" ");
            }
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            Font font2 = new Font("Arial", Font.BOLD, 13);													// create and set custom font
            g.setFont(font2);

            for(int i = 0; i < str.length; i++)																			// display the String array
            {
                g.drawString(str[i], 20, 200 + 50*i);
            }


        }


        public void actionPerformed(ActionEvent e)
        {
            boolean[] checkboxChosen = {false, false, false, false, false};						// boolean array to check which JCheckBoxes are chosen
            int[] spf = {45, 75, 20, 60, 50};																			// integer array for the SPF value of each JCheckBox

            for(int num = 0; num < checkboxes.length; num++)
            {
                if(e.getSource().equals(checkboxes[num]))														// if a certain JCheckBox is selected, increment the total SPF by the value
                {
                    if(checkboxes[num].isSelected())
                    {
                        checkboxChosen[num] = true;
                        totalSPF += spf[num];
                    }

                    if(!checkboxes[num].isSelected())															// if a certain JCheckBox is deselected, decrement the total SPF by the value
                    {
                        totalSPF -= spf[num];
                        checkboxChosen[num] = false;
                    }
                }
            }

            int avgSunTime = 25;																							// the average human can stay under the sun without any harm for around 25 minutes
            int totalSunTime = totalSPF * avgSunTime;															// calculate the maximum time the average person can stay in the sun with the SPF


            str[0] = "Based on the chosen accessories to defend against the";
            str[1] = "sun, the individual has a SPF of " + totalSPF + ". If the average";
            str[2] = "human can stay in the sun for 25 minutes, this";
            str[3] = " means that they can stay in the sun for " + totalSunTime + " minutes";
            str[4] = "with these accessories.";

            repaint();
        }


    }



    static class WhirligigPanel2 extends JPanel implements ChangeListener
    {
        private JSlider uvSlider;																						// JSlider for the UV Index
        private JSlider skinColorSlider;																			// JSlider for the skin types
        private JTextArea info;																							// JTextArea for the information based on the JSliders
        private Image uvImage;																						// image for the UV index
        private String uvImageName;																				// name of the file for the UV index image


        public WhirligigPanel2()
        {
            setBackground(Color.WHITE);																			// set background color
            setLayout(new GridLayout(2, 2, 25, 5));															// set layout of the JPanel to a GridLayout with 2 rows and 2 columns
            // with 25 pixels of horizontal spacing and 5 pixels of vertical spacing

            uvSlider = new JSlider(JSlider.HORIZONTAL, 1, 11, 5);									// JSlider that is horizontal with values of 1 - 11
            uvSlider.setMajorTickSpacing(1);
            uvSlider.setPaintTicks(true);
            uvSlider.setPaintLabels(true);
            uvSlider.addChangeListener(this);

            skinColorSlider = new JSlider(JSlider.HORIZONTAL, 1, 6, 3);							// JSlider that is horizontal with values 1 - 6
            skinColorSlider.setMajorTickSpacing(1);
            skinColorSlider.setPaintTicks(true);
            skinColorSlider.setPaintLabels(true);
            skinColorSlider.addChangeListener(this);


            info = new JTextArea();																					// create JTextArea for the information
            info.setWrapStyleWord(true);																			// set JTextArea so that a word will go to the next line if it passes the boundaries
            info.setLineWrap(true);																					// of the JTextArea
            info.setEditable(false);																						// set that the JTextArea can't be edited
            Font font = new Font("Arial", Font.BOLD, 18);												// create and set custom font
            info.setFont(font);

            uvImage = null;

            uvImageName = "UVindex.gif";

            getMyImage();																									// call getMyImage method to create the UV index image

            add(uvSlider);																									// add the two JSliders and the JTextArea to the JPanel
            add(info);
            add(skinColorSlider);

        }

        public void getMyImage()
        {
            try
            {
                uvImage = ImageIO.read(new File(uvImageName));									// check to see if there is a file with the chosen name and try to create an image
            }
            catch(IOException e)																						// if the image can't be found, an error is printed
            {
                System.err.print("\n\n" + uvImageName + " can't be found\n\n");
                e.printStackTrace();
            }

        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            g.drawImage(uvImage, 350, 385, this);															// draw the image onto the window

            g.drawString("Type I - very pale skin", 500, 350);											// draw Strings onto the window about the skin types
            g.drawString("Type II - fair skin", 500, 400);
            g.drawString("Type III - beige skin", 500, 450);
            g.drawString("Type IV - ligth brown skin", 500, 500);
            g.drawString("Type V - dark brown skin", 500, 550);
            g.drawString("Type VI - black skin", 500, 600);
        }


        public void stateChanged(ChangeEvent e)
        {

            int value1  = uvSlider.getValue();																		// get the value of the sliders when the are moved
            int value2  = skinColorSlider.getValue();

            int numMins = 0;																								// integer for the maximum number of minutes a person can
            // stay under the sun with the chosen skin type and UV index

            if(value1 == 1 || value1 == 2)																	// check to see if the UV index is either 1 or 2. If it is, then check to
            {																													// see what skin type is chosen
                if(value2 == 1)
                    numMins = 33;
                else if( value2 == 2)
                    numMins = 50;
                else if( value2 == 3)
                    numMins = 100;
                else if( value2 == 4)
                    numMins = 150;
                else if( value2 == 5)
                    numMins = 200;
                else
                    numMins = 250;
            }
            else if(value1 >= 3 && value1 <= 5)															// check to see if the UV index is between 3 and 5. If it is, then check to
            {																													// see what skin type is chosen
                if(value2 == 1)
                    numMins = 13;
                else if( value2 == 2)
                    numMins = 20;
                else if( value2 == 3)
                    numMins = 40;
                else if( value2 == 4)
                    numMins = 60;
                else if( value2 == 5)
                    numMins = 80;
                else
                    numMins = 100;
            }
            else if(value1 == 6 || value1 == 7)															// check to see if the UV index is either 6 or 7. If it is, then check to
            {																													// see what skin type is chosen
                if(value2 == 1)
                    numMins = 9;
                else if( value2 == 2)
                    numMins = 14;
                else if( value2 == 3)
                    numMins = 28;
                else if( value2 == 4)
                    numMins = 42;
                else if( value2 == 5)
                    numMins = 57;
                else
                    numMins = 76;
            }
            else if(value1 >= 8 && value1 <= 10)														// check to see if the UV index is between 8 and 10. If it is, then check to
            {																													// see what skin type is chosen
                if(value2 == 1)
                    numMins = 7;
                else if( value2 == 2)
                    numMins = 11;
                else if( value2 == 3)
                    numMins = 22;
                else if( value2 == 4)
                    numMins = 33;
                else if( value2 == 5)
                    numMins = 44;
                else
                    numMins = 55;
            }
            else																												// check to see if the UV index is 11. If it is, then check to
            {																													// see what skin type is chosen
                if(value2 == 1)
                    numMins = 6;
                else if( value2 == 2)
                    numMins = 9;
                else if( value2 == 3)
                    numMins = 18;
                else if( value2 == 4)
                    numMins = 27;
                else if( value2 == 5)
                    numMins = 36;
                else
                    numMins = 45;
            }

            // set the text for the JTextArea based on the calculated numMins
            info.setText("Based on the chosen skin type and the sunlight intensity, the individual is presumed to not have applied any sunscreen and"
                    + " can stay out in the sun without any harm for a maximum of " + numMins + " minutes. Remember to apply and reapply sunscreen"
                    + " after swimming, sweating, and rubbing it off!");

        }
    }





    static class RadioButtonPanel extends JPanel implements ActionListener           //RadioButtonPanel uses user input to change what appears on the screen
    {                                                                        														//By Tim
        private JRadioButton radioButton1, radioButton2;                    //two JRadioButtons that can be interacted with
        private JLabel label;                                                //label that shows a description of the image
        private Image image1, image2;                                        //Image(s) that hold the image to be displayed
        private String imageName1, imageName2;                                //imageName strings that help find the image address
        private ButtonGroup group;                                            //buttonGroup makes sure only one JRadioButton is active at one time
        private JPanel imgPanel;                                            //JPanel that holds the image

        public RadioButtonPanel()
        {
            image1 = null;
            image2 = null;

            imageName1 = "IMG0069.jpg";
            imageName2 = "IMG0070.jpg";

            setLayout(null);                                                //sets Layout to null so everything can be placed in the correct spot

            radioButton1 = new JRadioButton("No Sunscreen", false);            //sets the radiobuttons to have a label
            radioButton2  = new JRadioButton("Sunscreen", true);
            label = new JLabel("Skin:");

            radioButton1.setBounds(0, 0, 400, 50);                            //sets the place where radioButtons and labels appear
            radioButton2.setBounds(0, 50, 400, 50);
            label.setBounds(0, 100, 100, 50);

            getImage();                                                        //calls getImage() to put images into the Image variables

            group = new ButtonGroup();                                        //makes a buttonGroup and adds radioButtons into it
            group.add(radioButton1);
            group.add(radioButton2);
            group.clearSelection();

            imgPanel = new ImagePanel(radioButton1, radioButton2, image1, image2);
            imgPanel.setBounds(0, 150, 400, 250);                            //makes an image panel that displays an image according to what
            //radiobutton is selected
            radioButton2.setSelected(true);                                    //sets one of the radioButtons to true to display an image

            radioButton1.addActionListener(this);                            //allows user input on the radioButtons to change the imgPanel
            radioButton2.addActionListener(this);

            add(radioButton1);                                                //adds all components back into the IDPanel to make them appear
            add(radioButton2);
            add(label);
            add(imgPanel);

        }

        public void paintComponent(Graphics g)                                //paintComponent(g) also calls the paintComponent(g) of imgPanel to
        {                                                                    //update when something is pressed
            super.paintComponent(g);
            imgPanel.repaint();
        }

        public void getImage()                                                //loads image using file IO to Image variables
        {                                                                    //By Tim
            File imageFile = new File(imageName1);
            File imageFile2 = new File(imageName2);

            try {
                image1 = ImageIO.read(imageFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                image2 = ImageIO.read(imageFile2);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        public void actionPerformed(ActionEvent e)                            //updates what is selected when a radioButton is clicked and then
        {                                                                    //calls repaint()
            if(radioButton1.isSelected())                                    //By Tim
                radioButton1.setSelected(true);

            else if(radioButton2.isSelected())
                radioButton2.setSelected(true);
            repaint();
        }
    }

    static class ImagePanel extends JPanel                                            //By Tim
    {
        private JRadioButton radioButton1, radioButton2;
        private Image image1, image2;

        public ImagePanel(JRadioButton radioButton1, JRadioButton radioButton2, Image image1, Image image2)
        {                                                                    //gets references from IDPanel so images can update
            this.image1 = image1;
            this.image2 = image2;
            this.radioButton1 = radioButton1;
            this.radioButton2 = radioButton2;
        }
        public void paintComponent(Graphics g)                                //Updates the images according to which radioButton is selected
        {
            int value, extent, min, max;
            if(radioButton1.isSelected())
                g.drawImage(image1, 50, 0, 400, 250, this);
            else if(radioButton2.isSelected())
                g.drawImage(image2, 50, 0, 400, 250, this);
        }

    }



    /* Jasmine Lu
     * 26 March 2019
     * JButtonPanel.java
     * This program allows the user to click on 4 buttons labeled different articles of clothing to protect the stick figure from sun.
     * When all buttons have been pressed, a "Congratulations!..." message will be printed on the panel.
     */
///Testing Plan: When the user presses on anything else on the panel besides the buttons, nothing should happen.
///When the user presses on either of the buttons, the cocrresponding article of clothing should appear on the stick figure,
///and when the user clicks on the "x" on the right right, the program should quit.





    static class WPanel extends JPanel
    {
        private static boolean shirtPressed;        //FV boolean that checks if shirtButton is pressed
        private static boolean pantsPressed;        //FV boolean that checks if pantsButton is pressed
        private static boolean hatPressed;            //FV boolean that checks if hatButton is pressed
        private static boolean shoesPressed;        //FV boolean that checks if shoesButton is pressed

        private PressPanel pressP;        //FV name of PressPanel class to be added
        private static DrawPanel drawP;        //FV name of DrawPanel class to be added

        private Font font;        //FV called font that's used to write "Congratulations!..." message

        public WPanel()
        {
            setBackground(Color.CYAN);
            setLayout(new BorderLayout());    //set BorderLayout

            pressP = new PressPanel();        //initialize pressP as instance of PressPanel() class
            add(pressP, BorderLayout.NORTH);    //add pressP to WPanel (north)

            drawP = new DrawPanel();        //initialize drawP as instance of DrawPanel() class
            add(drawP, BorderLayout.CENTER);    //add drawP to WPanel (center)

            shirtPressed = false;    //initialize all booleans as false
            pantsPressed = false;
            hatPressed = false;
            shoesPressed = false;

            Font font = new Font ("Serif", Font.PLAIN, 20);        //initialize font as "Serif", plain, size 20

        }


        static class PressPanel extends JPanel        //panel that holds the 4 JButtons
        {

            private JButton shirtButton;        //FV JButton that draws shirt on stick figure
            private JButton pantsButton;        //FV JButton that draws pants on stick figure
            private JButton hatButton;        //FV JButton that draws hat on stick figure
            private JButton shoesButton;        //FV JButton that draws shoes on stick figure

            public PressPanel()
            {

                shirtButton = new JButton("shirt");            //create JButton called "shirt"
                ShirtButtonHandler shirtHandler = new ShirtButtonHandler();        //create instance of ShirtButtonHandler() method
                shirtButton.addActionListener(shirtHandler);            //add ActionListener
                add(shirtButton);    //add shirtButton

                pantsButton = new JButton("pants");        //create JButton called "pants"
                PantsButtonHandler pantsHandler = new PantsButtonHandler();    //create instance of PantsButtonHandler() method
                pantsButton.addActionListener(pantsHandler);        //add ActionListener
                add(pantsButton);        //add pantsButton

                hatButton = new JButton("hat");        //create JButton called "hat"
                HatButtonHandler hatHandler = new HatButtonHandler();    //create instance of HatButtonHandler() method
                hatButton.addActionListener(hatHandler);        //add ActionListener
                add(hatButton);        //add hatButton

                shoesButton = new JButton("shoes");        //create JButton called "shoes"
                ShoesButtonHandler shoesHandler = new ShoesButtonHandler();    //create instance of ShoesButtonHandler() method
                shoesButton.addActionListener(shoesHandler);        //add ActionListener
                add(shoesButton);    //add shoesButton

            }

            class ShirtButtonHandler implements ActionListener        //class header for ButtonHandler method
            {
                public void actionPerformed (ActionEvent e)
                {
                    String command = shirtButton.getText();        //find text on shirtButton

                    if (command.equals("shirt"))
                    {
                        shirtPressed = true;        //set shirtPressed to true
                        drawP.repaint();    //call paintComponent in drawP

                    }

                }
            }

            class PantsButtonHandler implements ActionListener        //class header for PantsButtonHandler method
            {
                public void actionPerformed (ActionEvent e)
                {
                    String command = pantsButton.getText();        //find text on pantsButton

                    if (command.equals("pants"))
                    {
                        pantsPressed = true;        //set pantsPressed to true
                        drawP.repaint();    //call paintComponent in drawP
                    }
                }
            }

            class HatButtonHandler implements ActionListener        //class header for HatButtonHandler method
            {
                public void actionPerformed (ActionEvent e)
                {
                    String command = hatButton.getText();        //find text on hatButton

                    if (command.equals("hat"))
                    {
                        hatPressed = true;        //set hatPressed to true
                        drawP.repaint();    //call paintComponent in drawP
                    }

                }
            }

            class ShoesButtonHandler implements ActionListener        //class header for ShoesButtonHandler method
            {
                public void actionPerformed (ActionEvent e)
                {
                    String command = shoesButton.getText();        //find text on shoesButton

                    if (command.equals("shoes"))
                    {
                        shoesPressed = true;        //set shoesPressed to true
                        drawP.repaint();    //call paintComponent in drawP
                    }

                }
            }

        }

        static class DrawPanel extends JPanel        //panel where clothing items are drawn on the stick figure
        {

            public DrawPanel()
            {
                JLabel label = new JLabel ("Click the buttons to protect Jack from the sun!");    //create new JLabel
                Font font = new Font("Arial", Font.BOLD, 15);        //set font
                label.setFont(font);        //use font to write label
                label.setBounds(20, 0, 400, 100);        //Set the location and size of the label
                this.add(label);        //Initialize JLabel and add to panel, font = "Arial", bold, size 40


            }
            public void paintComponent(Graphics g)
            {
                setBackground(Color.CYAN);

                g.drawOval(75, 200, 75, 75);    //draw face
                g.drawLine(112, 275, 112, 400);     //draw body
                g.drawLine(112, 300, 62, 330);        //draw left arm
                g.drawLine(112, 300, 163, 330);       //draw right arm
                g.drawLine(112, 400, 75, 500);        //draw left leg
                g.drawLine(112, 400, 150, 500);       //draw right leg

                g.fillOval(94, 230, 4,4);       //draw left eye
                g.fillOval(130, 230, 4,4);      //draw right eye
                g.drawArc(94, 230, 37, 30, 0, -180);       //draw smile

                g.setColor(Color.YELLOW);
                g.fillOval(350, 50, 100, 100);     //draw sun

                if (shirtPressed)            //draw red shirt
                {
                    int [] x = {112, 70, 75, 93, 93, 133, 133, 151, 156};
                    int[] y = {285, 310, 340, 330, 400, 400, 330, 340, 310};
                    g.setColor(Color.RED);
                    g.fillPolygon(x, y, 9);
                }

                if (pantsPressed)        //draw blue pants
                {
                    int[]x = {93, 70, 85, 112, 140, 155, 133};
                    int[]y = {400, 490, 490, 420, 490, 490, 400};
                    g.setColor(Color.BLUE);
                    g.fillPolygon(x, y, 7);
                }

                if (hatPressed)        //draw orange hat
                {
                    g.setColor(Color.ORANGE);
                    g.fillArc(80,180,65, 50, 0, 180);
                    g.fillRect(80, 205, 80, 5);
                }

                if (shoesPressed)        //draw black shoes
                {
                    g.setColor(Color.BLACK);
                    g.fillRect(60, 490, 25, 15);
                    g.fillRect(140, 490, 25, 15);
                }

                if (shirtPressed && pantsPressed && hatPressed && shoesPressed)        //if all buttons pressed, print "Congratulations!..." method
                {
                    g.drawString("Good job! You protected Jack from sunburn!", 25, 40);
                }

            }
        }


    }

    static class SunExposure
    {
        private JFrame frame;                                   // declaring frame, area, canvas, buttonPanel
        private JTextArea area = new JTextArea("",20,70);
        private ButtonPanel buttonPanel;
        private JPanel panel;
        public SunExposure()                                      //constructor
        {
            frame = new JFrame("Whirligig");
            frame.setSize(700, 650);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            panel = new JPanel();
            frame.add(panel);
            panel.setLayout(new BorderLayout());
            buttonPanel = new ButtonPanel();
            panel.add(buttonPanel, BorderLayout.SOUTH);
            panel.add(area);
        }
        public static void main(String[] args)                 //main method instantiates Whirligig and calls run method
        {
            SunExposure w = new SunExposure();
            w.run();
        }
        public void run()                                     //run method which sets frame and objects on frame
        {

            frame.setVisible(true);


        }


        class ButtonPanel extends JPanel implements ActionListener    //ButtonPanel class sets background, buttons, adds buttons (in constructor)
                //and setting certain text if commands are given with button(actionPerformed method)
        {
            public ButtonPanel()
            {
                setBackground(Color.RED);
                JButton button = new JButton("Sun Exposure And It's Effects");              //adds first button
                button.addActionListener(this);
                this.add(button);
                JButton button2 = new JButton("5 Ways To Treat Sunburn");                   //adds second button
                button2.addActionListener(this);
                this.add(button2);
            }
            public void actionPerformed(ActionEvent evt)
            {
                String command = evt.getActionCommand();         //gets command by user
                if(command.equals("Sun Exposure And It's Effects"))                     //sets text for first button
                {
                    area.setText("Ultraviolet (UV) rays are an invisible form of radiation.\n\n"+
                            "They can pass through your skinand damage your skin cells.\n\n"+
                            "Sunburns are a sign of skin damage. Suntans aren't healthy, either.\n\n"+
                            "They appear after the sun's rays have already killed some cells and damaged others.\n\n"+
                            "UV rays can cause skin damage during any season or at any temperature.\n\n"+
                            "They can also cause eye problems, wrinkles, skin spots, and skin cancer.\n\n"+
                            "To protect yourself, stay out of the sun for over an hour, wear protective clothing,\n\n"+
                            "use sunscreen, wear sunglasses, and avoid tanning.");
                }
                else if(command.equals("5 Ways To Treat Sunburn"))                   //sets text for second button
                {
                    area.setText("1. You must act fast to cool it down or it will continue to worsen over time-dip affected area in cold water.\n\n"+
                            "2.Moisturize your skin while it is damp because it will start drying over time.\n\n"+
                            "3.Decrease the inflammation as soon as you see a sign of a sunburn.\n\n"+
                            "4.Replenish your fluids by drinking lots of water as dehydration can be a contributing factor.\n\n"+
                            "5.See a doctor if it develops into blisters on your skin because this could be a sign of skin cancer.");
                }

            }
        }
    }


}


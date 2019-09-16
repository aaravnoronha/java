package com.epi_java;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Trivia {
    private String[] questions = {
            "What color was the White House when it was built?",
            "Who was the grandfather of Louis XV?",
            "What country was the first to give women the right to vote?",
            "Which was the first state to ratify the U.S. Constitution?",
            "How many people died when the Lusitania (torpedoed by a German submarine) sank?",
            "What was the first city to add flouride to its water supply?",
            "Who was the longest ruling king in history?",
            "How many years did Benjamin Franklin attend school?",
            "When was the first national park created?",
            "Who was the first signer of the Declaration of Independence?",
            "Who was the oldest signer of the Declaration of Independence?",
            "Who gave the longest speech in the history of the United Nations?",
            "What was the first country to issue postage stamps?",
            "Who was the first secretary of state for the United States?",
            "When did the Titanic sink?"
    };

    private String[] answers = {"light gray", "Louis XIV", "New Zealand, in 1893", "Delaware, in 1787",
            "1,200", "Grand Rapids, Michigan, in 1945", "Pepi II, of Egypt, who ruled for 94 years",
            "2", "Yellowstone National Park was created in 1872", "John Hancock",
            "Benjamin Franklin, at 70 years old", "Fidel Castro, in 1960, 4 hours 29 minutes",
            "England", "Thomas Jefferson", "April 15, 1912"};

    private int questionnum;
    private boolean query;

    private JFrame frame;
    private DisplayPanel canvas;
    private ButtonPanel buttonPanel;

    public Trivia() {
        questionnum = 0;
        query = true;
    }

    public void Run() {
        // Create a frame to hold everything
        frame = new JFrame("Trivia");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setBackground(Color.black);	// only needed if frame is larger than panel

        // Define panel to draw on
        canvas = new DisplayPanel();        // create a panel to draw on
        canvas.setBackground(Color.white);

        buttonPanel = new ButtonPanel();
        frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);

        // Put frame together
        frame.getContentPane().add(canvas);    // puts panel on frame
        frame.setVisible(true);
    }

    public Insets getInsets() {
        return new Insets(10, 10, 10, 10);
    }

    class DisplayPanel extends JPanel {

        public DisplayPanel() {
            setBackground(Color.white);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Font textFont = new Font("Serif", Font.BOLD, 36);
            g.setFont(textFont);
            g.setColor(Color.black);
            int k, counter = 0;
            if (query) {
                for (int i = 0; i < questions[questionnum].length(); i += (k - i + 1)) {
                    k = i + 26;
                    if (k < questions[questionnum].length()) {
                        while (questions[questionnum].charAt(k) != ' ')
                            k--;
                        g.drawString(questions[questionnum].substring(i, k), 26,
                                40 + counter * 46);
                    } else
                        g.drawString(questions[questionnum].substring(i), 26,
                                40 + counter * 46);
                    counter++;
                }
            } else {
                counter = 0;
                for (int i = 0; i < answers[questionnum].length(); i += (k - i + 1)) {
                    k = i + 26;
                    if (k < answers[questionnum].length()) {
                        while (answers[questionnum].charAt(k) != ' ')
                            k--;
                        g.drawString(answers[questionnum].substring(i, k), 26,
                                40 + counter * 46);
                    } else
                        g.drawString(answers[questionnum].substring(i), 26,
                                40 + counter * 46);
                    counter++;
                }
            }
        }
    }

    class ButtonPanel extends JPanel implements ActionListener {

        public ButtonPanel() {
            setBackground(Color.blue);

            JButton nextquestion = new JButton("NEXT Q");
            nextquestion.addActionListener(this);
            this.add(nextquestion);

            JButton showanswer = new JButton("SHOW A");
            showanswer.addActionListener(this);
            this.add(showanswer);

            JButton randomq = new JButton("RANDOM Q");
            randomq.addActionListener(this);
            this.add(randomq);

            JButton resetButton = new JButton("RESET");
            resetButton.addActionListener(this);
            this.add(resetButton);
        }

        public void actionPerformed(ActionEvent evt) {
            String command = evt.getActionCommand();

            if (command.equals("NEXT Q")) {
                questionnum++;
                if (questionnum == questions.length)
                    questionnum = 0;
                query = true;
                canvas.repaint();
            } else if (command.equals("SHOW A")) {
                query = false;
                canvas.repaint();
            } else if (command.equals("RANDOM Q")) {
                questionnum = (int) (Math.random() * questions.length);
                query = true;
                canvas.repaint();
            } else if (command.equals("RESET")) {
                questionnum = 0;
                query = true;
                canvas.repaint();

            }
        }
    }
}
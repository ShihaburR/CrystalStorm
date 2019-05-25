package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Creation of a GUI Form to display Scores that have currently been saved in highScores.txt
 */


public class ScoresPanel extends JFrame {
    private JTextArea highScoreText;
    private JPanel scorePanel;
    private JButton backButton;

    /** gets the JPanel ScorePanel from the ScorePanel Class */

    public JPanel getScorePanel(){
        return scorePanel;
    }

    /** Instantiation of ControlPanel with button functionality */
    public ScoresPanel(){
        add(scorePanel);
        setSize(1000,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setVisible(true);
        setResizable(false);
        highScoreText.setFont(new Font("Sans Serif",Font.PLAIN,20));
        loadScores();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MainMenu.main();
            }
        });
    }

    /** Loads the scores from a text file and displays them onto a JTextArea */
    public void loadScores() {
        FileReader fr = null;
        BufferedReader reader = null;
        HighScoreReader hr = new HighScoreReader("data/highScores.txt");
        try {
            fr = new FileReader(hr.getFileName());
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");
                String name = tokens[0];
                int score = Integer.parseInt(tokens[1]);
                highScoreText.append("Name: " + name + ", Score: " + score + "\n");
                line = reader.readLine();

            }
            System.out.println("...done.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /** Initialises the ScoresPanel class, adds the buttons to the Panel and sets it to be visible to the user */
    public static void main(){
        ScoresPanel scoreMenu = new ScoresPanel();
        scoreMenu.add(scoreMenu.getScorePanel(), BorderLayout.CENTER);
        scoreMenu.setVisible(true);
    }
}

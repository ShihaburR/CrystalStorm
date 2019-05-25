package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A GUI controller that allows the GUI to be displayed and functiom in Game. Has a Quit button to exit the game completely,
 * A Start button to create Game() and a High Scores button to display the High Scores that have currently been saved.
 */

public class MainMenu extends JFrame {
    private JButton startButton;
    private JPanel menuPanel;
    private JLabel title;
    private JTextArea Info;
    private JButton highScoresButton;
    private JButton quitButton;

    /** Instantiation of MainMenu with button functionality */

    public MainMenu() {
        add(menuPanel);
        setSize(1000,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setVisible(true);
        setResizable(false);
        title.setFont(new Font("Sans Serif",Font.BOLD,30));
        Info.setFont(new Font("Sans Serif",Font.PLAIN,20));
        Info.setBounds(150,150,250,100);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Game();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure?", "Message",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        highScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ScoresPanel.main();
            }
        });
    }

    /** Initialises the MainMenu class, adds the buttons to the Panel and sets it to be visible to the user */
    public static void main() {
        MainMenu menu = new MainMenu();
        menu.add(menu.getMainPanel(), BorderLayout.CENTER);
        menu.setVisible(true);
    }

    /** gets the MainPanel from Main Menu */
    public JPanel getMainPanel(){
        return menuPanel;
    }
}

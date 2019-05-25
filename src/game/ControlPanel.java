package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * A GUI controller that allows the GUI to be displayed and functiom in Game. Has a Quit button to check if you want to quit the game entirely,
 * then asks if you would like to save your High Score, A pause button to pause the game and change text to Play to allow the game to resume,
 * a Save button to save the current level you are at and a load button to load your previously saved level.
 */

public class ControlPanel extends Container {
    private JPanel mainPanel;
    private JButton pauseButton;
    private JButton restartButton;
    private JButton quitButton;
    private JButton saveButton;
    private JButton loadButton;
    private Game game;
    boolean clicked = false;

    /** Instantiation of ControlPanel with button functionality */

    public ControlPanel(final Game game){
        this.game = game;


        //stops the GameMusic and World and asks if they are sure to close the game
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.stopGameMusic();
                game.getWorld().stop();
                if (JOptionPane.showConfirmDialog(null, "Are you sure?", "Message",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                    //Option to save high scores if user wants to close game.
                    if(JOptionPane.showConfirmDialog(null,"Would you like to save your high score?",
                            "Message",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                        game.saveHighScores();
                        System.exit(0);
                    } else
                        System.exit(0);
                } else {
                    game.startGameMusic();
                    game.getWorld().start();
                }
            }
        });

        // Every 1st click will stop the world. But every 2nd click will start the world again
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clicked) {
                    JOptionPane.showMessageDialog(null, "Game will start now");
                    pauseButton.setText("Pause");
                    game.startGameMusic();
                    game.getWorld().start();
                    clicked = false;
                }else {
                    game.stopGameMusic();
                    game.getWorld().stop();
                    JOptionPane.showMessageDialog(null, "Game has been paused. " +
                            "When you are ready, press Play to resume game");
                    pauseButton.setText("Play");
                    clicked = true;
                }
            }
        });

        //restart the game
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartButton.setBorderPainted(false);
                game.stopGameMusic();
                game.getWorld().stop();
                JOptionPane.showMessageDialog(mainPanel, "Game Restarting");
                game.restartLevel();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    FileWriter writer = new FileWriter("saveLevel.txt");
                    PrintWriter print = new PrintWriter("saveLevel.txt");
                    print.println(game.getLevelNumber());
                    print.close();
                } catch (IOException i) {
                    System.out.println(i);
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            private String levelString, healthString, cashString;
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    FileReader reader = new FileReader("saveLevel.txt");
                    BufferedReader buffer = new BufferedReader(reader);
                    levelString = buffer.readLine();
                    game.setLevel(Integer.parseInt(levelString)-1);
                    game.goNextLevel();
                } catch (IOException m) {
                    System.out.println(m);
                }
            }
        });
    }

    /** gets the MainPanel from ControlPanel */
    public JPanel getMainPanel(){
        return mainPanel;
    }
}

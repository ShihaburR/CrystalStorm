package game;

import Assets.Person;
import Assets.Spaceship;
import Levels.Level1;
import Levels.Level2;
import Levels.Level3;
import city.cs.engine.SoundClip;
import city.cs.engine.Walker;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


/**
 * Creation of the levels' world
 */
public class Game {

    /** Define the classes/variables for the worlds */
    private GameLevel world;
    private MyView view;
    private int level;
    private Keyboard keyboard;
    private ShipMovement shipMovement;
    private SoundClip gameMusic;
    private JFrame frame;

    /** Initialise level 1*/
    public Game() {

        // make the world
        level = 1;
        world = new Level1();
        world.populate(this);

        //allows the game's music to loop in the background
        getGameMusic();
        gameMusic.loop();

        // make a view
        view = new MyView(world, world.getBackground().getImage(),world.getBorder().getImage() ,world.getPlayer(), world.getShip(),
                1000, 640, world.getX(), world.getY(), getLevelNumber());
        view.setZoom(24);


        // display the view in a frame
        frame = new JFrame("Crystal Storm");

        ControlPanel buttons = new ControlPanel(this);
        frame.add(buttons.getMainPanel(), BorderLayout.NORTH);


        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // display the world in the window
        frame.add(view);
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // make the window visible
        frame.setVisible(true);
        frame.requestFocus();
        view.addMouseListener(new GiveFocus(frame));
        // allows keyboard to be focused in the frame
        keyboard = new Keyboard(world.getPlayer());
        frame.addKeyListener(keyboard);
        shipMovement = new ShipMovement(world.getShip());
        frame.addKeyListener(shipMovement);

        // a debugging view
        // JFrame debugView = new DebugViewer(world, 500, 500);


        //increases the intensity of gravity to allow more lifelike jumps
        world.setGravity(15);
        // start!
        world.start();
    }

    /** Controls the gameMusic for each level */
    private void getGameMusic() {
        if(getLevelNumber()== 1) {
            try {
                gameMusic = new SoundClip("data/Lvl1.wav");   // Open an audio input stream
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
            }
        } else if(getLevelNumber() == 2){
            gameMusic.stop();
            try {
                gameMusic = new SoundClip("data/Lvl2.wav");   // Open an audio input stream
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
            }
        } else if(getLevelNumber() == 3){
            gameMusic.stop();
            try {
                gameMusic = new SoundClip("data/Lvl3.wav");   // Open an audio input stream
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
            }
        }
    }
    /**Starts and Stops game's Music */
    public void stopGameMusic(){
        gameMusic.stop();
    }
    public void startGameMusic(){
        gameMusic.loop();
    }


    /** Which walker class is needed */
    public Walker getPlayer() {
        if(getLevelNumber() < 3) {
            return world.getPlayer();
        }
        //At level 3 the Spaceship will be used.
        else if(getLevelNumber() == 3) {
            return world.getShip();
        } else {return world.getPlayer();}
    }

    /** Is the current level of the game finished? */
    public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }

    /** Return the current world it is in */
    public GameLevel getWorld() {
        return world;
    }

    /** Current level */
    public int getLevelNumber() {
        return level;
    }

    /** Restarts current level, storing previous level's score & health (level 1's score & health is reset to default) */
    public void restartLevel() {
        world.stop();
        Person oldMain = world.getPlayer();
        Spaceship finalMain = world.getShip();
        if(level == 1){
            world = new Level1();
            world.populate(this);
            getGameMusic();
            gameMusic.loop();
            MyView.background = world.getBackground().getImage();
            MyView.border = world.getBorder().getImage();
            view.x = world.getX();
            view.y = world.getY();
            keyboard.setBody(world.getPlayer());
            view.p = world.getPlayer();
            world.getPlayer().setCash(0);
            world.getPlayer().setHealth(10);
            view.setWorld(world);
            view.setZoom(24);
            world.setGravity(15);
            world.start();
        }
        else if(level == 2){
            world = new Level2();
            world.populate(this);
            getGameMusic();
            gameMusic.loop();
            MyView.background = world.getBackground().getImage();
            MyView.border = world.getBorder().getImage();
            view.x = world.getX();
            view.y = world.getY();
            keyboard.setBody(world.getPlayer());
            view.p = world.getPlayer();
            world.getPlayer().setCash(oldMain.getCash());
            world.getPlayer().setHealth(oldMain.getHealth());
            view.setWorld(world);
            view.setZoom(24);
            world.setGravity(15);
            world.start();
        }
        else if(level == 3){
            world = new Level3();
            world.populate(this);
            getGameMusic();
            gameMusic.loop();
            MyView.background = world.getBackground().getImage();
            MyView.border = world.getBorder().getImage();
            view.x = world.getX();
            view.y = world.getY();
            shipMovement.setBody(world.getShip());
            view.s = world.getShip();
            world.getShip().setCash(finalMain.getCash());
            world.getShip().setHealth(100);
            view.setWorld(world);
            view.setZoom(24);
            world.setGravity(0);
            world.start();
        }
    }
    /** Checks if character's health <= 0 and allows user to return to main menu or restart level*/
    public void isMainDead() {
        if (getLevelNumber() == 3) {
            if (world.getShip().getHealth() <= 0) {
                world.stop();
                stopGameMusic();
                saveHighScores();
                if (JOptionPane.showConfirmDialog(null, "You died, Click Yes to restart Level or " +
                                "Click No to return to Main Menu ", "Game Over",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    restartLevel();
                } else
                    frame.setVisible(false);
                    MainMenu.main();
            }
        } else if (world.getPlayer().getHealth() <= 0) {
            world.stop();
            stopGameMusic();
            saveHighScores();
            if (JOptionPane.showConfirmDialog(null, "You died, Click Yes to restart Level or " +
                            "Click No to return to Main Menu ", "Game Over",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                restartLevel();
            } else
                frame.setVisible(false);
                MainMenu.main();
        }
    }

    /** Setter of game's level*/
    public void setLevel(int level) {
        this.level = level;
    }


    /** Saves the high scores onto a text file*/
    public void saveHighScores(){
        //try catch exception to handle errors for writing data to txt file
            try {
                if (level == 3) {
                    HighScoreWriter HSW = new HighScoreWriter("data/highScores.txt");
                    HSW.writeHighScore("Ship Player", world.getShip().getCash());
                } else {
                    HighScoreWriter HSW = new HighScoreWriter("data/highScores.txt");
                    HSW.writeHighScore("Player", world.getPlayer().getCash());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        JOptionPane.showMessageDialog(null, "Your High Score has been saved");
    }


    /** Advance to the next level of the game. */
    public void goNextLevel() {
        world.stop();
        Person oldMain = world.getPlayer();
        if (level == 3) {
            JOptionPane.showMessageDialog(null,"Game over you have beaten the Game and your score is " + world.getShip().getCash());
            saveHighScores();
            frame.setVisible(false);
            stopGameMusic();
            MainMenu.main();
        }
        else if(level == 1) {
            level++;
            // get a new world
            world = new Level2();
            // fill it with bodies
            world.populate(this);
            //set the Music for the level and play in a loop
            getGameMusic();
            gameMusic.loop();
            // set a new background via the getBackground method in Level2
            MyView.background = world.getBackground().getImage();
            // set a new border via the getBorder method in Level2
            MyView.border = world.getBorder().getImage();
            //Allows the x & y to be used to display next level text
            view.x = world.getX();
            view.y = world.getY();
            // switch the keyboard control to the new player
            keyboard.setBody(world.getPlayer());
            //Loads the previous level's score & health
            view.p = world.getPlayer();
            world.getPlayer().setCash(oldMain.getCash());
            world.getPlayer().setHealth(oldMain.getHealth());
            // show the new world in the view
            view.setWorld(world);
            view.setZoom(24);
            world.setGravity(15);
            world.start();
        }
        else if(level == 2) {
            level++;
            world = new Level3();
            world.populate(this);
            getGameMusic();
            gameMusic.loop();
            MyView.background = world.getBackground().getImage();
            MyView.border = world.getBorder().getImage();
            view.x = world.getX();
            view.y = world.getY();
            shipMovement.setBody(world.getShip());
            world.getShip().setCash(oldMain.getCash());
            view.setWorld(world);
            view.setZoom(24);
            world.setGravity(0);
            world.start();
        }
    }
    /** Run the game */
    public static void main(String[] args) {
        MainMenu.main();
    }
}

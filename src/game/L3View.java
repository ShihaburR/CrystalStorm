package game;

import Assets.Person;
import Assets.Spaceship;
import city.cs.engine.UserView;

import javax.swing.*;
import java.awt.*;

/**
 * Adds extra graphical interfaces/layouts to be viewed in Game
 */

public class L3View extends UserView {

    public Spaceship s;
    public int x,y;

    //Instantiation of MyView
    L3View(GameLevel w, Spaceship s, int width, int height, int x, int y) {
        super(w, width, height);
        this.s = s;
        this.x = x;
        this.y = y;
    }

    //Draws the image onto the background in World.
    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(new ImageIcon("data/Lvl3.png").getImage(), 0, 0, this);
    }
    @Override
    protected void paintForeground(Graphics2D g){
        //Properties of the String
        Font myFont = new Font ("Helvetica", Font.BOLD, 17);
        g.setFont(myFont);

        //background border for display
        g.drawImage(new ImageIcon("data/L3Border.png").getImage(), 4, 5, this);

        //display coin icon with current Cash collected
        g.drawImage(new ImageIcon("data/coin.png").getImage(), 55, 38, this);
        g.drawString("x" + s.getCash(), 90, 60);

        //display health icon with current Health
        g.drawImage(new ImageIcon("data/health.png").getImage(), 135, 38, this);
        g.drawString("x" + s.getHealth(), 170, 60);

        //display the position of Door to access next Level
        g.drawString("NEXT LEVEL -->", x, y);

    }
}
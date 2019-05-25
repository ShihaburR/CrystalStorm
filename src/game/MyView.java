package game;
import Assets.Person;
import Assets.Spaceship;
import city.cs.engine.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Shape;

/**
 * Adds extra graphical interfaces/layouts to be viewed in Game
 */
public class MyView extends UserView {

    static Image background;
    static Image border;
    public Person p;
    public Spaceship s;
    public int x,y,level;
    GameLevel w;

    /** Instantiation of MyView */
    MyView(GameLevel w, Image background, Image border, Person p, Spaceship s,int width, int height, int x, int y, int level) {
        super(w, width, height);
        MyView.background = background;
        MyView.border = border;
        this.s = s;
        this.w = w;
        this.p = p;
        this.x = x;
        this.y = y;
        this.level = level;
    }

    /**Draws an image onto the background in World. */
    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }

    /**Draws a display for Health and Score onto the foreground in World. */
    @Override
    protected void paintForeground(Graphics2D g){

        //Properties of the String

        Font myFont = new Font ("Helvetica", Font.BOLD, 17);
        g.setFont(myFont);
        //background border for display
        g.drawImage(border, 4, 5, this);

        //if its level 3, then the spaceship coin & health will be displayed
        if(level < 3){
            //display coin icon with current Cash collected
            g.drawImage(new ImageIcon("data/coin.png").getImage(), 55, 38, this);
            g.drawString("x" + p.getCash(), 90, 60);

            //display health icon with current Health
            g.drawImage(new ImageIcon("data/health.png").getImage(), 135, 38, this);
            g.drawString("x" + p.getHealth(), 170, 60);

            //display the position of Door to access next Level
            g.drawString("NEXT LEVEL -->", x, y);
        } else {
            g.drawImage(new ImageIcon("data/coin.png").getImage(), 55, 38, this);
            g.drawString("x" + s.getCash(), 90, 60);
            g.drawImage(new ImageIcon("data/health.png").getImage(), 135, 38, this);
            g.drawString("x" + s.getHealth(), 170, 60);
        }
    }
}
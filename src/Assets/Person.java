package Assets;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

/**
 * A walker class that can flip its BodyImage horizontally to correctly position the image's direction. The walker class can move,
 * has getters and setters for health and score. When another class (dynamic body or walker) collides with Person, a hit sound can be made.
 */

public class Person extends Walker {
    //defined attributes of Person
    private int cash;
    private int health;
    private static SoundClip hit;

    static {
        try {
            hit = new SoundClip("data/hit.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /** The dimensions of the body(image) */
    private static final Shape shape = new PolygonShape(0.25f,1.31f, 0.77f,0.78f, 0.75f,-0.15f, 0.3f,-1.36f,
            -0.18f,-1.36f, -0.73f,-0.25f, -0.9f,0.68f, -0.26f,1.31f);

    /** Location of the image to be added onto the body */
    private static final BodyImage image =
            new BodyImage("data/MainCharacter.png", 3);

    /** Instantiation of Person */
    public Person(World world) {
        super(world,shape);
        addImage(image);
    }

    /** The image of the class will flip horizontally facing the opposite direction */
    public void Flip(Walker w) {
        final AttachedImage i = new AttachedImage(w,image,1.0f, 0,new Vec2(0,0));
        i.flipHorizontal();
    }

    /** The image of the class returns to its original layout */
    public void Reset(Walker w) {
        final AttachedImage i = new AttachedImage(w,image,1.0f, 0,new Vec2(0,0));
        i.reset();
    }

    /** getter method for character's cash */
    public int getCash() {return cash;}

    /** setter method for character's cash */
    public void setCash(int cash) {this.cash = cash;}

    /** getter method for character's health */
    public int getHealth() {return health;}

    /** setter method for character's health */
    public void setHealth(int health) {this.health = health;}

    /** The character has obtained a coin and cash increase by 1 */
    public void increaseCash() {
        cash++;
    }

    /** The character has collided with monster so cash decreases by 1 */
    public void decreaseCash() {
        cash--;
    }

    /** The character loses health by 10 */
    public void decreaseHealth() {
        health -= 1;
        hit.play();
    }

}


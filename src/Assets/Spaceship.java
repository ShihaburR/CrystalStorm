package Assets;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

/**
 * A walker class, that can move, flip its bodyImage on horizontally and has a bodyShape of a SpaceShip gif
 */
public class Spaceship extends Walker {
    private int cash;
    private int health = 0;

    private static final Shape shape = new PolygonShape(0.19f,1.24f, 1.19f,0.32f, 1.21f,-0.62f, 0.08f,-1.33f,
            -0.06f,-1.34f, -1.23f,-0.62f, -1.22f,0.34f, -0.26f,1.23f);

    /** location of image to be set on the body */
    private static final BodyImage image =
            new BodyImage("data/Spaceship.gif", 3);

    /** Instantiation of Spaceship */
    public Spaceship(World world) {
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

    /** getter method for Spaceship's cash */
    public int getCash() {return cash;}

    /** setter method for Spaceship's cash */
    public void setCash(int cash) {this.cash = cash;}

    /** getter method for Spaceship's health */
    public int getHealth() {return health;}

    /** setter method for Spaceship's health */
    public void setHealth(int health) {this.health = health;}

    /** The character has obtained a coin and cash increase by 1 */
    public void increaseCash() {
        cash++;
    }

    /** The character has collided with EnemyShip so cash decreases by 1 */
    public void decreaseHealth() {
        health -= 1;
    }

}


package Assets;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;


/**
 * Monster Walker Class which collides with MainCharacter and can walk side to side via StepListener
 */

public class Monster extends Walker implements StepListener {
    private static float WALKING_SPEED = 3f;

    /** The dimensions of the body(image) */
    private static final Shape shape = new PolygonShape(0.28f, 1.21f, 0.93f, 0.56f, 1.4f, -0.25f, 1.43f, -1.04f,
            0.45f, -1.43f, -0.56f, -1.41f, -1.59f, -0.72f, -0.39f, 1.17f);

    /** Location of the image to be added onto the body */
    private static final BodyImage image =
            new BodyImage("data/Monster.png", 4f);

    /** Instantiation of Monster */
    public Monster(World w) {
        super(w, shape);
        addImage(image);
        w.addStepListener(this);
        this.startWalking(WALKING_SPEED);
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

    /** the Body will move at set Speed between a set distance forever until the level has ended */
    @Override
    public void preStep(StepEvent stepEvent) {
        if(this.getPosition().x > 17) {
            WALKING_SPEED = -3.2f;
            this.startWalking(WALKING_SPEED);
            Flip(this);
        } else if(this.getPosition().x <-17){
            WALKING_SPEED = 3.2f;
            this.startWalking(WALKING_SPEED);
            Reset(this);
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
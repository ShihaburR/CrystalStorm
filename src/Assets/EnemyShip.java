package Assets;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * An EnemyShip that travels side to side in Level 3 via StepListener
 */

public class EnemyShip extends Walker implements StepListener {
    private static float SPEED = 3f;

    /** dimensions of EnemyShip */
    public static final Shape shape = new PolygonShape(0.25f,1.45f, 1.46f,0.22f, 0.93f,-1.25f, 0.22f,-1.45f,
            -0.21f,-1.45f, -0.96f,-1.22f, -1.47f,0.28f, -0.34f,1.43f);

    /** location of image to be set on the body */
    public static final BodyImage image =
            new BodyImage("data/EvilShip.gif",3);

    /** Instantiation of EnemyShip */
    public EnemyShip(World w) {
        super(w, shape);
        addImage(image);
        w.addStepListener(this);
        this.startWalking(SPEED);
    }

    /** The image of the class will flip vertically */
    public void Flip(Walker w) {
        final AttachedImage i = new AttachedImage(w,image,1.0f, 0,new Vec2(0,0));
        i.flipVertical();
    }

    /** the Body will move at set Speed between a set distance forever until the level has ended */
    @Override
    public void preStep(StepEvent stepEvent) {
        Flip(this);
        if(this.getPosition().x > 17) {
            SPEED = -3.2f;
            this.startWalking(SPEED);
        } else if(this.getPosition().x <-17){
            SPEED = 3.2f;
            this.startWalking(SPEED);

        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}

package Assets;
import city.cs.engine.*;

/**
 * Allows platform.png to be used for multiple static bodies in GameWorld
 */

public class L2Platform extends StaticBody {
    private static final Shape shape = new PolygonShape(5.24f,1.3f, 5.42f,0.77f, 4.99f,-0.14f, 1.75f,-1.39f,
            -1.31f,-1.27f, -4.42f,-0.25f, -5.48f,0.48f, -5.42f,1.23f);

    /** location of image to be set on the body */
    private static final BodyImage image =
            new BodyImage("data/L2Platform.png", 3f);

    /** Instantiation of L2Platform */
    public L2Platform(World w) {
        super(w, shape);
        addImage(image);
    }
}
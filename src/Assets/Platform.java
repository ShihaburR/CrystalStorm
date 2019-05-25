package Assets;
import city.cs.engine.*;

/**
 * Allows platform.png to be used for multiple static bodies in GameWorld
 */

public class Platform extends StaticBody {
    private static final Shape shape = new PolygonShape(6.08f, 0.86f, 6.37f, 0.65f, 6.4f, -0.47f, 5.47f, -0.95f,
            -5.44f, -0.98f, -6.19f, -0.63f, -6.37f, 0.41f, -6.03f, 0.89f);

    /** location of image to be set on the body */
    private static final BodyImage image =
            new BodyImage("data/Platform.png", 3f);

    /** Instantiation of Platform */
    public Platform(World w) {
        super(w, shape);
        addImage(image);
    }
}
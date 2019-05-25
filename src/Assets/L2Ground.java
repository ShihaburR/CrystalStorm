package Assets;

import city.cs.engine.*;

/**
 * A floor.png to be used multiple times in GameWorld
 */

public class L2Ground extends StaticBody {
    private static final Shape shape = new PolygonShape(32.67f,1.47f, 33.2f,0.8f, 33.2f,-0.67f, 32.67f,-1.87f,
            -32.53f,-1.6f, -33.07f,-0.67f, -32.93f,0.53f, -32.53f,1.47f);

    /** location of image to be set on the body */
    private static final BodyImage image =
            new BodyImage("data/Floor2.png", 4f);

    /** Instantiation of L2Ground */
    public L2Ground(World w) {
        super(w, shape);
        addImage(image);
    }
}

package Assets;

import city.cs.engine.*;

/**
 * A floor.png to be used multiple times in GameWorld
 */

public class Ground extends StaticBody {

    /** The dimensions of the body(image) */
    private static final Shape shape = new PolygonShape(25.76f,1.79f, 25.97f,1.16f, 26.08f,-0.96f,
            25.44f,-1.59f, -25.76f,-1.59f, -26.29f,-0.53f, -26.29f,1.26f, -25.76f,1.89f);

    /** Location of the image to be added onto the body */
    private static final BodyImage image =
            new BodyImage("data/Floor.png", 4f);

    /** Instantiation of Ground */
    public Ground(World w) {
        super(w, shape);
        addImage(image);
    }
}

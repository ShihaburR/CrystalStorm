package Assets;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * A class that implements DynamicBody. The class has an gif of a Coin that can be collided with Person and
 * Spaceship to increase score. A ding sound is made when the Coin is collected.
 */

public class Coin extends DynamicBody {
    private static SoundClip ding;

    static {
        try {
            ding = new SoundClip("data/ding.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    @Override
    public void destroy()
    {
        ding.play();
        super.destroy();
    }
    

    /** dimensions of coin.gif */
    public static final Shape shape = new PolygonShape(0.106f,0.472f, 0.394f,0.182f, 0.394f,-0.12f, 0.114f,-0.41f,
            -0.098f,-0.412f, -0.382f,-0.124f, -0.41f,0.168f, -0.118f,0.47f);

    /** location of image to be set on the body */
    public static final BodyImage image =
            new BodyImage("data/coin.gif");

    /** instantiation of Coin */
    public Coin(World w) {
        super(w, shape);
        addImage(image);
    }
}

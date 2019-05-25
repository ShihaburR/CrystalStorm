package game;

import Assets.Person;
import city.cs.engine.Walker;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This allows the MainCharacter to move via the keyboard
 */
public class Keyboard extends KeyAdapter {
    /** define the walking & jump speed and the body */
    private static final float JUMPING_SPEED = 15;
    private static final float WALKING_SPEED = 4;
    private Person mainCharacter;

    /**Instantiation of Keyboard*/
    public Keyboard(Person mainCharacter) { this.mainCharacter = mainCharacter; }


    /** when a key is pressed the body will walk (UP,LEFT,RIGHT)
     *  When the Walker is moving left,
     *  the body will face left instead of right due to flip function
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            mainCharacter.Flip(mainCharacter);
            mainCharacter.startWalking(-WALKING_SPEED);

        } else if (code == KeyEvent.VK_D) {
            mainCharacter.Reset(mainCharacter);
            mainCharacter.startWalking(WALKING_SPEED);

        } else if (code == KeyEvent.VK_W) {
            Vec2 v = mainCharacter.getLinearVelocity();
            // only jump if body is not jumping
            if (Math.abs(v.y) < 0.01f) {
                mainCharacter.jump(JUMPING_SPEED);
            }
        }
    }
    /** when you let go of the key the body will stop walking */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        //linearVelocity is set to prevent extra movement on key-release.
        if (code == KeyEvent.VK_A) {
            mainCharacter.setLinearVelocity(new Vec2(0,0));
            mainCharacter.stopWalking();

        } else if (code == KeyEvent.VK_D) {
            mainCharacter.setLinearVelocity(new Vec2(0,0));
            mainCharacter.stopWalking();
        }
    }

    /** Sets the person field to current person. */
    public void setBody(Person mainCharacter) {
        this.mainCharacter = mainCharacter;
    }
}


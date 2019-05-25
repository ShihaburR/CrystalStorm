package game;

import Assets.Spaceship;
import city.cs.engine.Walker;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Allows the Spaceship class to have the ability to move in mid air based on its set Gravity.
 */

public class ShipMovement extends KeyAdapter {
    private static final float WALKING_SPEED = 12;
    private static final float JUMPING_SPEED = 10;
    private Spaceship mainShip;

    /**Instantiation of ShipMovement */
    public ShipMovement(Spaceship mainShip) {
        this.mainShip = mainShip;
    }

    /** when a key is pressed the body will move (UP,LEFT,RIGHT)
     * if it goes up GravityScale is set to -0.6 to move towards the top of the screen
     * if it goes down GravityScale is set to 0.6 to move towards the bottom of the screen
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            mainShip.Flip(mainShip);
            mainShip.startWalking(-WALKING_SPEED);

        } else if (code == KeyEvent.VK_RIGHT) {
            mainShip.Reset(mainShip);
            mainShip.startWalking(WALKING_SPEED);

        } else if (code == KeyEvent.VK_UP) {
            mainShip.setGravityScale(-0.6f);
            mainShip.jump(JUMPING_SPEED);

        } else if (code == KeyEvent.VK_DOWN) {
            mainShip.setGravityScale(0.6f);
            mainShip.jump(-JUMPING_SPEED);

        }
    }

    /** when a key is released the body stop
     * if up is released GravityScale is set to 0.6 to counter its previous GravityScale to stop the body.
     * if down is released  GravityScale is set to -0.6 to counter its previous GravityScale to stop the body.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_LEFT) {
            mainShip.setLinearVelocity(new Vec2(0,0));
            mainShip.stopWalking();

        } else if (code == KeyEvent.VK_RIGHT) {
            mainShip.setLinearVelocity(new Vec2(0, 0));
            mainShip.stopWalking();

        } else if (code == KeyEvent.VK_UP){
            mainShip.setGravityScale(-0.6f);

        } else if (code == KeyEvent.VK_DOWN) {
            mainShip.setGravityScale(-0.6f);
            mainShip.jump(-JUMPING_SPEED);
        }
    }

    /** Sets the Spaceship field to current Spaceship. */
    public void setBody(Spaceship mainShip) {
        this.mainShip = mainShip;
    }
}

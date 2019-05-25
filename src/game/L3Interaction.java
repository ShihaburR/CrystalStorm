package game;

import Assets.*;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

/**
 * This allows collisions to occur and have an effect on the mainShip's attributes (Level 3 only)
 */

public class L3Interaction implements CollisionListener {
    private Spaceship mainShip;

    public L3Interaction(Spaceship mainShip) {
        this.mainShip = mainShip;
    }

    /** Controls collisions of multiple body classes in many possibilities for Level 3 only*/

    @Override
    public void collide(CollisionEvent e) {
        //When mainCharacter collides with coin,it will take the coin.
        if (e.getReportingBody() instanceof Coin && e.getOtherBody() == mainShip) {
            mainShip.increaseCash();
            e.getReportingBody().destroy();
        }
        //When mainCharacter collides with Monster, lose a coin and 10 health
        else if (e.getReportingBody() instanceof EnemyShip && e.getOtherBody() == mainShip){
            mainShip.decreaseHealth();
        }
    }
}

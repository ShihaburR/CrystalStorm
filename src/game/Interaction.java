package game;

import Assets.Coin;
import Assets.Monster;
import Assets.Person;
import city.cs.engine.*;

/**
 * This allows collisions to occur and have an effect on the mainCharacter's attributes
 */

public class Interaction implements CollisionListener {
    private Person mainCharacter;
    private Game game;

    /** Instantiation of Interaction */
    public Interaction(Person mainCharacter, Game game) {
        this.game = game;
        this.mainCharacter = mainCharacter;
    }

    /** Controls collisions of multiple body classes in many possibilities*/

    @Override
    public void collide(CollisionEvent e) {
        //When mainCharacter collides with coin,it will take the coin.
        if (e.getReportingBody() instanceof Coin && e.getOtherBody() == mainCharacter) {
            mainCharacter.increaseCash();
            e.getReportingBody().destroy();
        }
        //When mainCharacter collides with Monster, lose a coin and 10 health
        else if (e.getReportingBody() instanceof Monster && e.getOtherBody() == mainCharacter){
            mainCharacter.decreaseHealth();
            mainCharacter.decreaseCash();
            game.isMainDead();
        }
    }
}

package game;

import city.cs.engine.*;

import javax.swing.*;

/**
 * Listener for collision with a door.  When the player collides with a door,
 * if the current level is complete the game is advanced to the next level. 
 */
public class DoorListener implements CollisionListener {
    private Game game;
    
    public DoorListener(Game game) {
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        Walker mainCharacter = game.getPlayer();
        Walker mainShip = game.getWorld().getShip();
        game.isMainDead();
        if (e.getOtherBody() == mainCharacter && game.isCurrentLevelCompleted()) {
            System.out.println("Going to next level...");
            game.goNextLevel();
        } else if(e.getOtherBody() == mainShip && game.isCurrentLevelCompleted()){
            System.out.println("Final level completed");
            game.goNextLevel();
        } else {
            JOptionPane.showMessageDialog(null,"Please collect at least 5 coins in this level" +
                    " to move onto the next level.");
        }
    }
}

package game;

import Assets.*;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;

/**
 * A level of the game.
 */
public abstract class GameLevel extends World {
    private Person mainCharacter;
    private Monster monster;
    private Spaceship mainShip;

    public Person getPlayer() {
        return mainCharacter;
    }

    public Spaceship getShip() {
        return mainShip;
    }

    /**
     * Populate the world of this level.
     * Child classes should this method with additional bodies.
     */
    public void populate(Game game) {
        if (game.getLevelNumber() < 3) {
            mainCharacter = new Person(this);
            mainCharacter.setHealth(10);
            mainCharacter.setCash(0);
            mainCharacter.setPosition(startPosition());

            monster = new Monster(this);
            monster.setPosition(new Vec2(-5, 4f));
            monster.addCollisionListener(new Interaction(mainCharacter,game));

            Door door = new Door(this);
            door.setPosition(doorPosition());
            door.addCollisionListener(new DoorListener(game));

        } else if (game.getLevelNumber() == 3) {
            mainShip = new Spaceship(this);
            mainShip.setHealth(100);
            mainShip.setPosition(startPosition());

            Door door = new Door(this);
            door.setPosition(doorPosition());
            door.addCollisionListener(new DoorListener(game));
        }
    }

    /** The initial position of the player. */
    public abstract Vec2 startPosition();

    /** The position of the exit door. */
    public abstract Vec2 doorPosition();

    /** Is this level complete? */
    public abstract boolean isCompleted();

    /** The background of the Level */
    public abstract ImageIcon getBackground();

    /** The Game UI Border for Health and Score of the Level */
    public abstract ImageIcon getBorder();

    /** The x to display the next level graphic */
    public abstract int getX();

    /** The y to display the next level graphic */
    public abstract int getY();
}

package Levels;

import Assets.Coin;
import Assets.EnemyShip;
import Assets.Platform;
import city.cs.engine.*;
import game.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;

/**
 * Level 3 of the game
 */
public class Level3 extends GameLevel {

    private static final int NUM_COINS = 15;
    private int Time = 180;

    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);
        //12 coins drop constantly until player's health reaches 0

        for (int i = 0; i < 10; i++) {
            DynamicBody coin = new Coin(this);
            coin.setPosition(new Vec2((i - 2.5f) * 4, 11f));
            coin.addCollisionListener(new L3Interaction(getShip()));
            coin.setGravityScale(15);
        }
        for (int i = 0; i < 10; i++) {
            DynamicBody coin = new Coin(this);
            coin.setPosition(new Vec2((i - 2.5f) * 4, 3f));
            coin.addCollisionListener(new L3Interaction(getShip()));
            coin.setGravityScale(15);
        }


        //Enemy Ships will move left to right in its own line.
        EnemyShip enemyBottom = new EnemyShip(this);
        enemyBottom.setPosition(new Vec2(2,-6));

        EnemyShip enemyMiddle = new EnemyShip(this);
        enemyMiddle.setPosition(new Vec2(10,6));

        //EnemyShip enemyTop = new EnemyShip(this);
        //enemyTop.setPosition(new Vec2(-5,11));

        enemyBottom.setGravityScale(0);
        enemyMiddle.setGravityScale(0);
        //enemyTop.setGravityScale(0);

        // Barriers to prevent characters leaving the windows
        Shape wallShape = new BoxShape(0.5f, 20);
        Body leftWall = new StaticBody(this, wallShape);
        leftWall.setPosition(new Vec2(-21.5f, -6));

        Body rightWall = new StaticBody(this, wallShape);
        rightWall.setPosition(new Vec2(21.5f, -6));

        Body topWall = new StaticBody(this, wallShape);
        topWall.setPosition(new Vec2(0, 14));
        topWall.rotateDegrees(90);

        Body bottomWall = new StaticBody(this, wallShape);
        bottomWall.setPosition(new Vec2(0, -18));
        bottomWall.rotateDegrees(90);
    }
    @Override
    public Vec2 startPosition () {
        return new Vec2(0, -5f);
    }

    @Override
    public Vec2 doorPosition () {
        return new Vec2(0, 14);
    }

    @Override
    public boolean isCompleted () { return getShip().getCash() > NUM_COINS;}

    @Override
    public ImageIcon getBackground() {
        return new ImageIcon("data/Lvl3.png");
    }

    @Override
    public ImageIcon getBorder() {
        return new ImageIcon("data/L3Border.png");
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }
}

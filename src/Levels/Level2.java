package Levels;

import Assets.Coin;
import Assets.L2Ground;
import Assets.L2Platform;
import city.cs.engine.*;
import city.cs.engine.Shape;
import game.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;

/**
 * Level 2 of the game
 */
public class Level2 extends GameLevel {

    private static final int NUM_COINS = 10;
    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

        // make the ground

        L2Ground ground = new L2Ground(this);
        ground.setPosition(new Vec2(2,-12.5f));


        // make a platform
        L2Platform platform = new L2Platform(this);
        L2Platform platform1 = new L2Platform(this);
        L2Platform platform2 = new L2Platform(this);
        L2Platform platform3 = new L2Platform(this);
        L2Platform platform4 = new L2Platform(this);
        L2Platform platform5 = new L2Platform(this);
        L2Platform platform6 = new L2Platform(this);

        platform.setPosition(new Vec2(-16, -2.3f));
        platform1.setPosition(new Vec2(16, -5));
        platform2.setPosition(new Vec2(-4, -6));
        platform3.setPosition(new Vec2(1, 8.1f));
        platform4.setPosition(new Vec2(10, 2.4f));
        platform5.setPosition(new Vec2(22, 9));
        platform6.setPosition(new Vec2(-9, 4.2f));


        for (int i = 0; i < 9; i++) {
            DynamicBody coin = new Coin(this);
            coin.setPosition(new Vec2((i - 1.6f) * 6, -3f));
            coin.addCollisionListener(new Interaction(getPlayer(),game));
        }
        for (int i = 0; i < 9; i++) {
            DynamicBody coin = new Coin(this);
            coin.setPosition(new Vec2((i - 2.5f) * 6, 12));
            coin.addCollisionListener(new Interaction(getPlayer(),game));
        }


        // Barriers to prevent characters leaving the windows
        Shape wallShape = new BoxShape(0.5f, 20);
        Body leftWall = new StaticBody(this, wallShape);
        leftWall.setPosition(new Vec2(-21.5f, -6));

        Body rightWall = new StaticBody(this, wallShape);
        rightWall.setPosition(new Vec2(24.5f, -6));

        Body topWall = new StaticBody(this, wallShape);
        topWall.setPosition(new Vec2(0, 14));
        topWall.rotateDegrees(90);
    }
    @Override
    public Vec2 startPosition () {
        return new Vec2(0, -10f);
    }

    @Override
    public Vec2 doorPosition () {
        return new Vec2(23, 9.4f);
    }

    @Override
    public boolean isCompleted () { return getPlayer().getCash() >= NUM_COINS; }

    @Override
    public ImageIcon getBackground() {
        return new ImageIcon("data/Lvl2.png");
    }
    @Override
    public ImageIcon getBorder() {
        return new ImageIcon("data/border.png");
    }
    @Override
    public int getX() {
        return 850;
    }

    @Override
    public int getY() {
        return 50;
    }
}

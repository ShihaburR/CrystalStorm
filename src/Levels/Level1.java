package Levels;

import Assets.Coin;
import Assets.Ground;
import Assets.Platform;
import city.cs.engine.*;
import city.cs.engine.Shape;
import game.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;

/**
 * Level 1 of the game
 */
public class Level1 extends GameLevel {

    private static final int NUM_COINS = 5;

    /**
     * Populate the world.
     */

    @Override
    public void populate(Game game) {
        super.populate(game);

        // make the ground

        Ground ground = new Ground(this);
        ground.setPosition(new Vec2(2,-12.5f));

        // make a platform
        Platform platform = new Platform(this);
        Platform platform1 = new Platform(this);
        Platform platform2 = new Platform(this);
        Platform platform3 = new Platform(this);
        Platform platform4 = new Platform(this);


        platform.setPosition(new Vec2(0, 7));
        platform1.setPosition(new Vec2(13, -2));
        platform2.setPosition(new Vec2(-6, -6));
        platform3.setPosition(new Vec2(-16, 1));
        platform4.setPosition(new Vec2(17, 4));


        /* create the bodies */


        for (int i = 0; i < 4; i++) {
            DynamicBody coin = new Coin(this);
            coin.setPosition(new Vec2((i - 1.5f) * 9, -10f));
            coin.addCollisionListener(new Interaction(getPlayer(),game));
        }
        for (int i = 0; i < 6; i++) {
            DynamicBody coin = new Coin(this);
            coin.setPosition(new Vec2((i - 2.5f) * 6, 12));
            coin.addCollisionListener(new Interaction(getPlayer(),game));
        }


        // Barriers to prevent characters leaving the windows
        Shape wallShape = new BoxShape(0.5f, 20);
        Body leftWall = new StaticBody(this, wallShape);
        leftWall.setPosition(new Vec2(-21.5f, -6));

        Body rightWall = new StaticBody(this, wallShape);
        rightWall.setPosition(new Vec2(23.5f, -6));

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
            return new Vec2(23f, -9.6f);
        }

        @Override
        public boolean isCompleted () {
            return getPlayer().getCash() >= NUM_COINS;
        }

        @Override
        public ImageIcon getBackground() {
            return new ImageIcon("data/background.png");
        }

        @Override
        public ImageIcon getBorder() {
            return new ImageIcon("data/border.png");
        }

        @Override
        public int getX() {
            return 800;
        }

        @Override
        public int getY() {
            return 520;
        }
}

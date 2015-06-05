package com.petree.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Pipe extends Scrollable {

    public Rectangle skullUp, skullDown, barUp, barDown;

    public static final int VERTICAL_GAP = 150;
    public static final int SKULL_WIDTH = 63;
    public static final int SKULL_HEIGHT = 400;
    private float groundY;
    private int rand;
    private boolean isScored = false;

    // When Pipe's constructor is invoked, invoke the super (Scrollable)
    // constructor
    public Pipe(float x, float y, int width, int height, float scrollSpeed,
            float groundY) {
        super(x, y, width, height, scrollSpeed);
        // Initialize a Random object for Random number generation
        rand = (int)(Math.random()*300+50);
        skullUp = new Rectangle();
        skullDown = new Rectangle();
        this.groundY = groundY;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        skullUp.set(getX(), getY()-rand(), 65, 400);
        skullDown.set(getX(),getY()-rand()+525, 65, 400);

    }
    public boolean collides(Bird bird) {
        if (position.x < bird.getX() + bird.getWidth()) {
            return (Intersector.overlaps(bird.getBoundingCircle(), skullUp) || Intersector
                        .overlaps(bird.getBoundingCircle(), skullDown));
        }
        return false;
    }
    @Override
    public void reset(float newX) {
        super.reset(newX);
        isScored = false;
        rand = (int)(Math.random()*300+50);
    }
    public void onRestart(float x, float scrollSpeed) {
        velocity.x = scrollSpeed;       
        isScored = false;
        rand = (int)(Math.random()*300+50);
        position.x = x;
    }

    public Rectangle getSkullUp() {
        return skullUp;
    }

    public Rectangle getSkullDown() {
        return skullDown;
    }

    public int rand() {
    	return rand;
    	
    }
    public boolean isScored() {
        return isScored;
    }

    public void setScored(boolean b) {
        isScored = b;
    }

}

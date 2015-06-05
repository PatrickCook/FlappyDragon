package com.petree.GameObjects;

import com.petree.DHelpers.*;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Bird {

	private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation;
    private int width;
    private double count = 0;
    private float height;

    private float originalY;

    public boolean isAlive, sound;

    private Circle boundingCircle;

	public Bird(float x, float y, int width, int height) {
		this.width = width;
        this.height = height;
        this.originalY = y;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 1000);
        boundingCircle = new Circle();
        isAlive = true;
	}

	public void update(float delta) {
		velocity.add(acceleration.cpy().scl(delta));
			if (velocity.y > 800) 
			velocity.y = 800;
			position.add(velocity.cpy().scl(delta));
			if (position.y < 0) {
				position.y = 0;
				velocity.y = 0;
			}
			// Set the circle's center to be (9, 6) with respect to the bird.
			// Set the circle's radius to be 6.5f;
			boundingCircle.set(position.x + 27, position.y + 16, 16f);

			if (velocity.y < 0) {
				rotation -= 600 * delta;

				if (rotation < -15) {
					rotation = -15;
				}
			}

			// Rotate clockwise
			if (isFalling() || !isAlive) {
				rotation += 480 * delta;
				if (rotation > 90) 
					rotation = 90;

			}	
	}
	public void updateReady(float runTime) {
        position.y = (int) (20.0 * Math.sin(count)) + originalY;
        if(count < 2*Math.PI)
        	count+=Math.PI/40;
        else 
        	count = 0;
        rotation = (float)((Math.atan(Math.cos(count)))*90/Math.PI);
        
    }
	public void onRestart(int y) {
        rotation = 0;
        position.y = y;
        velocity.x = 0;
        velocity.y = 0;
        acceleration.x = 0;
        acceleration.y = 1000;
        isAlive = true;
    }
	public boolean isFalling() {
		return velocity.y > 399;
	}

	public boolean shouldntFlap() {
		return velocity.y > 600 || !isAlive;
	}

	public void onClick() {
		if (isAlive) {
			if (sound)
				AssetLoader.flap.play();
			velocity.y = -380;
		}
	}

	public void die() {

		isAlive = false;
		velocity.y = 0;
	}

	public void decelerate() {
		acceleration.y = 0;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getRotation() {
		return rotation;
	}

	public Circle getBoundingCircle() {
		return boundingCircle;
	}

	public boolean isAlive() {
		return isAlive;
	}
	public void soundPlay(){
		sound = true;
	}
	public void soundStop(){
		sound = false;
	}
}

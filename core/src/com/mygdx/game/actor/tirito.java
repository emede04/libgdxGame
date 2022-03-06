package com.mygdx.game.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class tirito {

    public static final int SPEED = 500;
    public static final int DEFAULT_Y = 40;
    public static final int WIDTH = 3;
    public static final int HEIGHT = 12;
    private static Texture texture;

    float x, y;
    CollisionRect rect;
    public boolean remove = false;

    public tirito(float x) {
        this.x = x;
        this.y = DEFAULT_Y;
        this.rect = new CollisionRect(x, y, WIDTH, HEIGHT);

        if (texture == null)
            texture = new Texture("bullet.png");
    }

    public void update(float deltaTime) {
        y += SPEED * deltaTime;
        if (y > HEIGHT)
            remove = true;

        rect.move(x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public CollisionRect getCollisionRect() {
        return rect;
    }

    public class CollisionRect {

        float x, y;
        int width, height;

        public CollisionRect (float x, float y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public void move (float x, float y) {
            this.x = x;
            this.y = y;
        }

        public boolean collidesWith (CollisionRect rect) {
            return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
        }

    }

}

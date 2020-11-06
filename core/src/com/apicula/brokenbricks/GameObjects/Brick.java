package com.apicula.brokenbricks.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Brick {
    public int posX;
    public int posY;
    public boolean isDestroyed;

    private SpriteBatch batch;
    private Texture texture;

    public Brick(SpriteBatch batch, int posX, int posY) {
        this.batch = batch;
        this.posY = posY;
        this.posX = posX;
        texture = new Texture("Resources/GameObjectImages/brick.png");
        isDestroyed = false;
    }


    public void draw() {
        if (!isDestroyed) {
            batch.draw(texture, posX, posY);
        }
    }


}

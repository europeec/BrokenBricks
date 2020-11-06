package com.apicula.brokenbricks.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

enum sideOfMove {
    STAY,
    RIGHT,
    LEFT
}

public class MainPerson {
    public int posX = 0;
    private int ac = 0;
    private Texture img;
    private sideOfMove side = sideOfMove.STAY;
    private SpriteBatch batch;
    private Texture texture;

    public MainPerson(SpriteBatch batch) {
        this.batch = batch;
        this.texture = new Texture("Resources/GameObjectImages/main.png");
    }

    public void update() {
        checkMove();
    }

    public void draw() {
        batch.draw(texture, this.posX, 0);
    }

    private void checkMove() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (posX > 0) {
                switch (side) {
                    case LEFT:
                        ac += 0.85;
                        break;
                    case RIGHT:
                        ac = 0;
                        break;
                }

                this.side = sideOfMove.LEFT;
                this.posX -= 5 + ac;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (posX < 365) {
                switch (side) {
                    case LEFT:
                        ac = 0;
                        break;
                    case RIGHT:
                        ac += 0.85;
                        break;
                }

                this.side = sideOfMove.RIGHT;
                this.posX += 5 + ac;
            }
        }
    }
}

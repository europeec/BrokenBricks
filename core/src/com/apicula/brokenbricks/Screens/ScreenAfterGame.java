package com.apicula.brokenbricks.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScreenAfterGame {
    public boolean isPlayAgain = false;
    public boolean isBackToMenu = false;

    private Texture win = new Texture("ScreenAfterGame/win.png");
    private Texture lose = new Texture("ScreenAfterGame/lose.png");
    private Texture playAgainButton = new Texture("ScreenAfterGame/playAgainButton.png");
    private Texture backToMenuButton = new Texture("ScreenAfterGame/backToMenuButton.png");

    private SpriteBatch batch;

    public ScreenAfterGame(SpriteBatch batch) {
        this.batch = batch;
    }

    public void draw (boolean isWin) {
        checkMouseMove();
        batch.draw(backToMenuButton, 90, 100);
        batch.draw(playAgainButton, 100, 200);

        if (isWin) {
            batch.draw(win, 135, 300);
        } else {
            batch.draw(lose, 110, 300);
        }
    }

    private void checkMouseMove() {
        if (Gdx.input.getY() > 530 && Gdx.input.getY() < 700) {
            if (Gdx.input.getY() <  600) {
                if (Gdx.input.getX() > 100 && Gdx.input.getX() < 400) {
                    if (Gdx.input.isTouched()) {
                        isPlayAgain = true;
                    }
                }
            }
            if (Gdx.input.getY() > 630) {
                if (Gdx.input.getX() > 90 && Gdx.input.getX() < 410) {
                    if (Gdx.input.isTouched()) {
                        isBackToMenu = true;
                    }
                }
            }
        }

    }
}

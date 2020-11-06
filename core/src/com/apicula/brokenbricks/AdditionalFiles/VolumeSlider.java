package com.apicula.brokenbricks.AdditionalFiles;

import com.apicula.brokenbricks.Models.UserDefaults;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class VolumeSlider {
    private int posX;
    public float volume;
    private boolean isTouched = false;
    private final Texture slider = new Texture("Menu/VolumeSetting/sliderBackground.png");
    private final Texture touchNonActive = new Texture("Menu/VolumeSetting/TouchNonActive.png");
    private final Texture touchActive = new Texture("Menu/VolumeSetting/TouchActive.png");
    private Texture[] volumes = new Texture[3];
    private SpriteBatch batch;
    private UserDefaults user = new UserDefaults();

    public VolumeSlider(SpriteBatch batch) {
        this.batch = batch;
        create();
    }

    private void create() {
        volumes[0] = new Texture("Menu/VolumeSetting/Volume0.png");
        volumes[1] = new Texture("Menu/VolumeSetting/Volume1.png");
        volumes[2] = new Texture("Menu/VolumeSetting/Volume2.png");
        volume = user.volume;
        posX = (int) (165  +  200 * volume);

    }

    public void draw() {
        if (volume < 0.33f) {
            batch.draw(volumes[0], 110,407);
        } else if (volume < 0.67f) {
            batch.draw(volumes[1], 110,407);
        } else {
            batch.draw(volumes[2], 110,407);
        }
        batch.draw(slider, 170, 423);
        if (isTouched){
            batch.draw(touchActive, posX, 395, 60,60);
        } else {
            batch.draw(touchNonActive, posX,400);
        }
        checkPosition();
    }

    public void checkPosition() {
        checkMouseMove();
    }

    private void moveTouch(int posX) {
        if (posX <= 365 && posX >= 165){
            this.posX = posX;
        } else {
            if (posX > 365) {
                this.posX = 365;
            } else {
                this.posX = 165;
            }
        }
    }

    public void checkMouseMove() {
        if (Gdx.input.getX() < 380 && Gdx.input.getX() > 150 && (Gdx.input.getY() < 395 && Gdx.input.getY() > 340)){
            if (Gdx.input.isTouched()){
                moveTouch(Gdx.input.getX() - 25);
                convertToVolume();
                user.volume = volume;
                user.wasChanging = true;
                isTouched = true;
            } else {
                isTouched = false;
            }
        }
    }

    private void convertToVolume() {
        volume = (float) (posX - 165)/200;
    }
}

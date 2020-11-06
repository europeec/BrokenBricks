package com.apicula.brokenbricks.AdditionalFiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StartAnnotation {
    private Texture one = new Texture("Resources/TextImages/1.png");
    private Texture two = new Texture("Resources/TextImages/2.png");
    private Texture three = new Texture("Resources/TextImages/3.png");
    private Texture ready = new Texture("Resources/TextImages/ready.png");
    private Texture go = new Texture("Resources/TextImages/go.png");

    private int scaleValue = 70;

    private boolean isOneEnded = false;
    private boolean isTwoEnded = false;
    private boolean isThreeEnded = false;
    private boolean isReadyEnded = false;
    public boolean isAnimationEnded = false;
    private SpriteBatch batch;
    public StartAnnotation(SpriteBatch batch){
        this.batch = batch;
    }

    public void draw(){
        if (!isAnimationEnded) {
            resize();
        }
    }

    private void resize() {
        if (scaleValue >= 0) {
            if (!isReadyEnded){
                if (scaleValue != 0) {
                    batch.draw(ready, 1 + (75 - scaleValue) * 3, 300, (int) 300 * scaleValue / 20, (int) 70 * scaleValue / 20);
                } else {
                    isReadyEnded = true;
                }
            } else if (!isThreeEnded) {
                if (scaleValue != 0) {
                    batch.draw(three, 1 + (75 - scaleValue) * 3, 300, (int) 300 * scaleValue / 20, (int)400 * scaleValue / 20);
                } else {
                    isThreeEnded = true;
                }
            } else if (!isTwoEnded) {
                if (scaleValue != 0) {
                    batch.draw(two, 1 + (75 - scaleValue) * 3, 300, (int) 300 * scaleValue / 20, (int)400 * scaleValue / 20);
                } else {
                    isTwoEnded = true;
                }
            } else if (!isOneEnded) {
                if (scaleValue != 0) {
                    batch.draw(one, 1 + (75 - scaleValue) * 3, 300, (int) 300 * scaleValue / 20, (int)400 * scaleValue / 20);
                } else {
                    isOneEnded = true;
                }
            } else {
                if (scaleValue != 0) {
                    batch.draw(go, 1 + (70 - scaleValue) * 3, 300, (int) 300 * scaleValue / 20, (int)170 * scaleValue / 20);
                } else {
                    isAnimationEnded = true;
                }
            }
            scaleValue -= 1;
        } else {
            scaleValue = 70;
        }
    }

}

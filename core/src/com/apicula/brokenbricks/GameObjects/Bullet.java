package com.apicula.brokenbricks.GameObjects;

import com.apicula.brokenbricks.AdditionalFiles.Const;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;


public class Bullet {
    public int posX;
    public int posY;
    private int Vy = 5;
    private int Vx;
    private Texture texture;
    private SpriteBatch batch;
    private Const CONST;

    public Bullet (SpriteBatch batch) {
        this.batch = batch;
        texture = new Texture("Resources/GameObjectImages/bullet.png");
        Random random = new Random();
        posX = 150 + random.nextInt(7) * 25;
        if (posX > 250) {
            Vx = -5;
        } else {
            Vx = 5;
        }
        posY = 100;
    }

    public void move() {
        if (posY == (CONST.SCREENHEIGHT - CONST.BALLSIZE)) {
            invert();
        }
        if (posY == 0) {
            invert();
        }
        if (posX == 0) {
            bounce();
        }
        if (posX == (CONST.SCREENWIDTH - CONST.BALLSIZE)) {
            bounce();
        }
        posX += Vx;
        posY += Vy;
    }

    public void draw(){
        batch.draw(texture, posX, posY);
    }

    public void stop() {
        Vx = 0;
        Vy = 0;
    }

    public void invert(){
        Vy = -Vy;
    }

    public void bounce() { Vx = -Vx; }



}

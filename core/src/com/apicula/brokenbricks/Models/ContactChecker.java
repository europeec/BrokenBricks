package com.apicula.brokenbricks.Models;

import com.apicula.brokenbricks.AdditionalFiles.Const;
import com.apicula.brokenbricks.GameObjects.Brick;
import com.apicula.brokenbricks.GameObjects.Bullet;
import com.apicula.brokenbricks.GameObjects.MainPerson;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class ContactChecker {
    public boolean isLose = false;

    private MainPerson main;
    private Bullet bullet;
    private Brick[] bricks;
    private static Const CONST;
    private int indexDestroyed = -1;
    private Sound sound;

    public ContactChecker(MainPerson main, Bullet bullet, Brick[] bricks) {
        this.main = main;
        this.bullet = bullet;
        this.bricks = bricks;
        sound = Gdx.audio.newSound(Gdx.files.internal("Sound/crack.wav"));
    }

    public void update() {
        if (bullet.posY < CONST.HEIGHTMAIN) {
            if (isContactWithMain()) {
                if (isLose) {
                    //				LOSE
                    bullet.stop();
                }
            }
        } else if (bullet.posY > 250) {
            if (isContactWithBricks()) {
                bricks[indexDestroyed].isDestroyed = true;
                sound.play(1);
            }
        }
    }

    public boolean isContactWithMain() {
      if (bullet.posY <= CONST.HEIGHTMAIN && bullet.posY > 15) {
          if (main.posX + CONST.WIDTHMAIN > bullet.posX && main.posX < bullet.posX + CONST.BALLSIZE) {
              bullet.invert();
              return true;
          } else {
              if (bullet.posX == main.posX + CONST.WIDTHMAIN) {
//                  правая стенка
                  bullet.bounce();
              }
              if (bullet.posX + 25 == main.posX) {
//                  левая стенка
                  bullet.bounce();
              }
          }
      } else {
          if (bullet.posY == 0) {
              isLose = true;
              return true;
          }
      }
      return false;
    }

    public boolean isContactWithBricks() {
        if (bullet.posY > 250) {
            for (int i = 0; i < bricks.length - 1; i++) {
                if (!bricks[i].isDestroyed) {
                   if (((bullet.posY + CONST.BALLSIZE == bricks[i].posY) != (bullet.posY == bricks[i].posY + CONST.HEIGHTBRICK)) &&
                        bullet.posX + (int) CONST.BALLSIZE/2 >= bricks[i].posX &&
                        bullet.posX + (int) CONST.BALLSIZE/2 <= bricks[i].posX + CONST.WIDTHBRICK) {
                        indexDestroyed = i;
                        bullet.invert();
                        return true;
                    }
                    if (bullet.posY + (int) CONST.BALLSIZE/2 >= bricks[i].posY && bullet.posY  <= bricks[i].posY + CONST.HEIGHTBRICK){
                        if (bullet.posX + CONST.BALLSIZE == bricks[i].posX) {
                            indexDestroyed = i;
                            bullet.bounce();
                            return true;
                        }
                        if (bullet.posX == bricks[i].posX + CONST.WIDTHBRICK) {
                            indexDestroyed = i;
                            bullet.bounce();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}

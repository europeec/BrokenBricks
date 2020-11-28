package com.apicula.brokenbricks.Models;

import com.apicula.brokenbricks.AdditionalFiles.Const;
import com.apicula.brokenbricks.GameObjects.Brick;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Maps {
    private Brick[] bricks;
    private Const CONST;
    private SpriteBatch batch;
    private UserDefaults user = new UserDefaults();

    public Maps(SpriteBatch batch){
        this.batch = batch;
    }

    public Brick[] create() {
        if (user.isRandomlyMap) {
            bricks = generate();
        } else if (user.isAdvancedMap) {
            bricks = advanced();
        } else {
            bricks = standard();
        }

        return bricks;
    }


//  Генерация карты кирпичиков
    private Brick[] generate() {
        Random rand = new Random();
        int stepsBlock = rand.nextInt(2) + 4;
        int[] stepsCountsBlocks = new int[stepsBlock];
        boolean[] isMiddleBlocks = new boolean[stepsBlock];
        int counter = 0;

        for (int i = 0; i < stepsBlock; i++) {
            stepsCountsBlocks[i] = rand.nextInt(3) * 2;
            isMiddleBlocks[i] = rand.nextBoolean();
            counter += stepsCountsBlocks[i];
            if (isMiddleBlocks[i]) {
                counter++;
            }
        }

        bricks = new Brick[counter + 1];
        counter = 0;

        for (int i = 0; i < stepsBlock; i++){
            switch (stepsCountsBlocks[i]) {
                case 0:
                    if (isMiddleBlocks[i]) {
                        bricks[counter] = new Brick(batch, 2 * CONST.WIDTHBRICK, 300 + (i * CONST.HEIGHTBRICK));
                        counter ++;
                    }
                    break;
                case 2:
                    boolean isLeading = rand.nextBoolean();
                    if (isLeading) {
                        bricks[counter] = new Brick(batch, 0, 300 + (i * CONST.HEIGHTBRICK));
                        bricks[counter + 1] = new Brick(batch, 4 * CONST.WIDTHBRICK, 300 + (i * CONST.HEIGHTBRICK));
                    } else {
                        bricks[counter] = new Brick(batch, CONST.WIDTHBRICK, 300 + (i * CONST.HEIGHTBRICK));
                        bricks[counter + 1] = new Brick(batch, 3 * CONST.WIDTHBRICK, 300 + (i * CONST.HEIGHTBRICK));
                    }
                    counter += 2;
                    if (isMiddleBlocks[i]) {
                        bricks[counter] = new Brick(batch, 2 * CONST.WIDTHBRICK, 300 + (i * CONST.HEIGHTBRICK));
                        counter ++;
                    }
                    break;
                case 4:

                    bricks[counter] = new Brick(batch, 0, 300 + (i * CONST.HEIGHTBRICK));
                    bricks[counter + 1] = new Brick(batch, 4 * CONST.WIDTHBRICK, 300 + (i * CONST.HEIGHTBRICK));
                    bricks[counter + 2] = new Brick(batch, CONST.WIDTHBRICK, 300 + (i * CONST.HEIGHTBRICK));
                    bricks[counter + 3] = new Brick(batch, 3 * CONST.WIDTHBRICK, 300 + (i * CONST.HEIGHTBRICK));
                    counter += 4;

                    if (isMiddleBlocks[i]) {
                        bricks[counter] = new Brick(batch, 2 * CONST.WIDTHBRICK, 300 + (i * CONST.HEIGHTBRICK));
                        counter ++;
                    }
                    break;
                default:
                    System.out.println(":: err generation map");
                    break;
            }
        }
        return bricks;
    }
//  Стандартная карта
    private Brick[] standard () {
        bricks = new Brick[21];
        int t_index = 0;
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                if (t_index < bricks.length) {
                    bricks[t_index] = new Brick(batch,k * CONST.WIDTHBRICK, 330 + j * CONST.HEIGHTBRICK);
                    t_index++;
                }
            }
        }
        return bricks;
    }

//    Продвинутая карта
    private Brick[] advanced () {
        bricks = new Brick[23];
        bricks[0] = new Brick(batch, 0, 300);
        bricks[1] = new Brick(batch, 4 * CONST.WIDTHBRICK, 300);
        bricks[2] = new Brick(batch, 0, 300 + CONST.HEIGHTBRICK);
        bricks[3] = new Brick(batch, 1 * CONST.WIDTHBRICK, 300 + CONST.HEIGHTBRICK);
        bricks[4] = new Brick(batch, 3 * CONST.WIDTHBRICK, 300 + CONST.HEIGHTBRICK);
        bricks[5] = new Brick(batch, 4 * CONST.WIDTHBRICK, 300 + CONST.HEIGHTBRICK);
        bricks[6] = new Brick(batch, 0 * CONST.WIDTHBRICK, 300 + 2 * CONST.HEIGHTBRICK);
        bricks[7] = new Brick(batch, 1 * CONST.WIDTHBRICK, 300 + 2 * CONST.HEIGHTBRICK);
        bricks[8] = new Brick(batch, 2 * CONST.WIDTHBRICK, 300 + 2 * CONST.HEIGHTBRICK);
        bricks[9] = new Brick(batch, 3 * CONST.WIDTHBRICK, 300 + 2 * CONST.HEIGHTBRICK);
        bricks[10] = new Brick(batch, 4 * CONST.WIDTHBRICK, 300 + 2 * CONST.HEIGHTBRICK);
        bricks[11] = new Brick(batch, 0 * CONST.WIDTHBRICK, 300 + 3 * CONST.HEIGHTBRICK);
        bricks[12] = new Brick(batch, 1 * CONST.WIDTHBRICK, 300 + 3 * CONST.HEIGHTBRICK);
        bricks[13] = new Brick(batch, 2 * CONST.WIDTHBRICK, 300 + 3 * CONST.HEIGHTBRICK);
        bricks[14] = new Brick(batch, 3 * CONST.WIDTHBRICK, 300 + 3 * CONST.HEIGHTBRICK);
        bricks[15] = new Brick(batch, 4 * CONST.WIDTHBRICK, 300 + 3 * CONST.HEIGHTBRICK);
        bricks[16] = new Brick(batch, 0 * CONST.WIDTHBRICK, 300 + 5 * CONST.HEIGHTBRICK);
        bricks[17] = new Brick(batch, 4 * CONST.WIDTHBRICK, 300 + 5 * CONST.HEIGHTBRICK);
        bricks[18] = new Brick(batch, 0, 300 + 4 * CONST.HEIGHTBRICK);
        bricks[19] = new Brick(batch, 1 * CONST.WIDTHBRICK, 300 + 4 * CONST.HEIGHTBRICK);
        bricks[20] = new Brick(batch, 3 * CONST.WIDTHBRICK, 300 + 4 * CONST.HEIGHTBRICK);
        bricks[21] = new Brick(batch, 4 * CONST.WIDTHBRICK, 300 + 4 * CONST.HEIGHTBRICK);
        return bricks;
    }
}

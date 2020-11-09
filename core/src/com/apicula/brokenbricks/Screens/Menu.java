package com.apicula.brokenbricks.Screens;

import com.apicula.brokenbricks.AdditionalFiles.VolumeSlider;
import com.apicula.brokenbricks.Models.UserDefaults;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu implements Screen {
    private final Texture backgroundMenu = new Texture("Menu/backgroundMenu.png");
    private final Texture settingLabel = new Texture("Menu/settingsLabel.png");
    private final Texture backgroundSettings = new Texture("Menu/settingBackground.png");
    private final VolumeSlider slider;
    private final Texture mapLabel = new Texture("Menu/MapSetting/mapLabel.png");
    private final Texture mapButtonNonActive = new Texture("Menu/MapSetting/buttonMapNonActive.png");
    private final Texture mapButtonActive = new Texture("Menu/MapSetting/buttonMapActive.png");
    private final Texture[] buttonLabels = new Texture[3];
    private final Texture backButton = new Texture("Menu/backButton.png");
    private final Texture playButton = new Texture("Menu/play.png");
    private static UserDefaults user;
    private final Music music;

    private boolean isSettings = false;
    private int indexMap = 1; // индекс в массиве buttonLabels для смены карты
    private boolean isButtonMapTouched = false;
    private int delayButton = 50; // задержка на кнопку смены карты
    private SpriteBatch batch;
    public boolean isGame = false;

    public Menu(SpriteBatch batch){
        this.batch = batch;
        slider = new VolumeSlider(batch);
        buttonLabels[0] = new Texture("Menu/MapSetting/oneMore.png");
        buttonLabels[1] = new Texture("Menu/MapSetting/random.png");
        buttonLabels[2] = new Texture("Menu/MapSetting/standard.png");
        music = Gdx.audio.newMusic(Gdx.files.internal("Sound/background.mp3"));
    }

    @Override
    public void show() {
        if (music.isPlaying()) {
            music.setVolume(user.volume);
        } else {
            music.play();
            music.setVolume(user.volume);
        }
        batch.draw(backgroundMenu, 100, 150, 300, 500);
        checkMouseMoveSettings();
        if (isSettings) {
            batch.draw(backgroundSettings,50, 200, 400, 310);
            batch.draw(settingLabel, 148,485, 205, 58);
            slider.draw();
            batch.draw(mapLabel, 110, 340);
            if (isButtonMapTouched) {
                batch.draw(mapButtonActive, 218, 323, 190, 80);
            } else {
                batch.draw(mapButtonNonActive, 220, 325);
            }

            batch.draw(buttonLabels[indexMap], 235, 340);
            batch.draw(backButton, 150, 220);
        } else {
            checkMouseMoveMenu();
            batch.draw(playButton, 45, 450,407, 115);
            batch.draw(settingLabel, 45,300, 410, 115);
        }

    }

//  Проверка мыши в настройках
    private void checkMouseMoveSettings() {
        if (isSettings) {
            if (Gdx.input.getY() > 400 && Gdx.input.getY() < 475) {
                if (Gdx.input.getX() > 220 && Gdx.input.getX() < 405) {
                    if (Gdx.input.isTouched() && delayButton > 0) {
                        isButtonMapTouched = true;
                        if (delayButton == 50) {
                            if (indexMap == 2) {
                                indexMap = 0;
                            } else {
                                indexMap++;
                            }
//                          Устанавливаем значения предпочитаемой карты для пользователя
                            switch (indexMap) {
                                case 0:
                                    user.isRandomlyMap = false;
                                    user.isAdvancedMap = true;
                                    break;
                                case 1:
                                    user.isRandomlyMap = true;
                                    user.isAdvancedMap = false;
                                case 2:
                                    user.isRandomlyMap = false;
                                    user.isAdvancedMap = false;
                            }
                            user.wasChanging = true;
                        }
                        delayButton--;
                    } else {
//                      Сделано для того, чтобы при зажатой ЛКМ не начиналось безумие
                        delayButton = 50;
                        isButtonMapTouched = false;
                    }
                }
            }
            if (Gdx.input.getY() > 510 && Gdx.input.getY() < 575) {
                if (Gdx.input.getX() > 150 && Gdx.input.getX() < 330) {
                    if (Gdx.input.isTouched()) {
                        isSettings = false;
                    }
                }
            }
        }
    }

//  Проверка мыши в главном меню
    private void checkMouseMoveMenu() {
        if (Gdx.input.getY() > 390 && Gdx.input.getY() < 490) {
            if (Gdx.input.getX() > 45 && Gdx.input.getX() < 455) {
                if (Gdx.input.isTouched()){
                    isSettings = true;
                }
            }
        }
        if (Gdx.input.getY() > 240 && Gdx.input.getY() < 340) {
            if (Gdx.input.getX() > 45 && Gdx.input.getX() < 455) {
                if (Gdx.input.isTouched()){
                    isGame = true;
                    music.stop();
                }
            }
        }
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}

package com.apicula.brokenbricks.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.Random;

public class SoundClass {
    private static int indexMusic = -1;
    private static Music[] backgroundMusics;
    public static boolean isPlaying = false;

    public static void create() {
        backgroundMusics = new Music[3];
        backgroundMusics[0] = Gdx.audio.newMusic(Gdx.files.internal("Sound/katyusha.mp3"));
        backgroundMusics[1] = Gdx.audio.newMusic(Gdx.files.internal("Sound/rukiVerh.mp3"));
        backgroundMusics[2] = Gdx.audio.newMusic(Gdx.files.internal("Sound/starWars.mp3"));
    }

    public static void play() {
        isPlaying = true;
        if (indexMusic == -1) {
            create();
        }
        Random random = new Random();
        indexMusic = random.nextInt(backgroundMusics.length);
        backgroundMusics[indexMusic].play();
    }

    public static void setVolume(Float volume) {
        for (Music sound: backgroundMusics) {
            sound.setVolume(volume);
        }
    }


    public static void dispose() {
        isPlaying = false;
        backgroundMusics[indexMusic].dispose();
    }
}

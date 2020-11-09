package com.apicula.brokenbricks.Models;

import com.apicula.brokenbricks.GameObjects.Brick;
import com.apicula.brokenbricks.GameObjects.Bullet;
import com.apicula.brokenbricks.GameObjects.MainPerson;
import com.apicula.brokenbricks.AdditionalFiles.StartAnnotation;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	MainPerson main;
	Bullet bullet;
	Brick[] bricks;
	ContactChecker cc;
	SoundClass music;
	StartAnnotation startAnnotation;
	Maps maps;
	UserDefaults user;

	private boolean isGameStarted = false;
	public boolean isLose = false;
	public boolean isRestart = false;
	public boolean isWin = false;

	public Game(SpriteBatch batch) {
		this.batch = batch;
	}

	public void create () {
		main = new MainPerson(batch);
		bullet = new Bullet(batch);
		startAnnotation = new StartAnnotation(batch);
		maps = new Maps(batch);
		bricks = maps.create();
		cc = new ContactChecker(main, bullet, bricks);
	}


	public void render () {
//		для измения карты или громкости
		checkUpdate();

		main.draw();
		for (int i = 0; i < bricks.length - 1; i++) {
			bricks[i].draw();
		}
		bullet.draw();
		if (isGameStarted) {
			update();
		} else {
			startAnnotation.draw();
		}
	}

	public void update (){
		main.update();
		bullet.move();
		cc.update();

		if (cc.isWin) {
			music.dispose();
			isWin = true;
		}

		if (cc.isLose) {
			music.dispose();
			isLose = true;
		}
	}

	public int getBulletPosX () {
		return bullet.posX;
	}

	public int getBulletPosY () {
		return bullet.posY;
	}

	public int getMainPosX () {
		return main.posX;
	}

	private void checkUpdate() {
		if (!music.isPlaying) {
			music.play();
			music.setVolume(user.volume);
		}
		if (user.wasChanging) {
			bricks = maps.create();
			cc = new ContactChecker(main, bullet, bricks);
			music.setVolume(user.volume);
			user.wasChanging = false;
		}
		if (isRestart) {
			create();
			isRestart = false;
		}
		isGameStarted = startAnnotation.isAnimationEnded;
	}
}

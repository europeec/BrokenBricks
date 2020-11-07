package com.apicula.brokenbricks;

import com.apicula.brokenbricks.GameObjects.Brick;
import com.apicula.brokenbricks.GameObjects.Bullet;
import com.apicula.brokenbricks.GameObjects.MainPerson;
import com.apicula.brokenbricks.Models.ContactChecker;
import com.apicula.brokenbricks.Models.Game;
import com.apicula.brokenbricks.Models.Menu;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BrokenBricks extends ApplicationAdapter {
	SpriteBatch batch;
	MainPerson main;
	Bullet bullet;
	Brick[] bricks;
	ContactChecker cc;
	Game game;
	Menu menu;
	Server server;
	Texture test;
	@Override
	public void create () {
		batch = new SpriteBatch();
		game = new Game(batch);
		menu = new Menu(batch);
		test = new Texture("Resources/GameObjectImages/bullet.png");
		game.create();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();



		if (game.isLose) {
			menu.isGame = false;
			game.isLose = false;
			game.isRestart = true;
		}
		if (menu.isGame) {
//			game.render();
			if (server.isGame) {
				server.gameRequest(game.getBulletPosX(), game.getBulletPosY(), game.getMainPosX());
				game.render();
				batch.draw(test, server.bulletPosX, 400 + server.bulletPosY);
			} else {
				server.startRequest();
			}
		} else {
			menu.show();
		}


		batch.end();

	}

	public void update (){
		game.update();
	}
}

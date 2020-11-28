package com.apicula.brokenbricks;

import com.apicula.brokenbricks.GameObjects.Brick;
import com.apicula.brokenbricks.GameObjects.Bullet;
import com.apicula.brokenbricks.GameObjects.MainPerson;
import com.apicula.brokenbricks.Models.ContactChecker;
import com.apicula.brokenbricks.Models.Game;
import com.apicula.brokenbricks.Screens.Menu;
import com.apicula.brokenbricks.Screens.ScreenAfterGame;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BrokenBricks extends ApplicationAdapter {
	SpriteBatch batch;
	MainPerson main;
	Bullet bullet;
	Brick[] bricks;
	ContactChecker cc;
	Game game;
	Menu menu;
	Server server;
	ScreenAfterGame postScreen;
	@Override
	public void create () {
		batch = new SpriteBatch();
		game = new Game(batch);
		menu = new Menu(batch);
		postScreen = new ScreenAfterGame(batch);
		game.create();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		if (postScreen.isBackToMenu) {
			menu.isGame = false;
			postScreen.isBackToMenu = false;
			game.isLose = false;
			game.isRestart = true;
			game.isWin = false;
		}
		if (postScreen.isPlayAgain) {
			game.isRestart = true;
			game.isWin = false;
			game.isLose = false;
			postScreen.isPlayAgain = false;
			menu.isGame = true;
		}

		if (menu.isGame) {
			if (game.isLose  || game.isWin) {
				postScreen.draw(game.isWin);
			} else {
				game.render();
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

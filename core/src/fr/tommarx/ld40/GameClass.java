package fr.tommarx.ld40;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import java.awt.font.GlyphJustificationInfo;

import fr.tommarx.gameengine.Game.Game;

public class GameClass extends Game {

	public static BitmapFont font1;
	public static GlyphLayout gl;

	public void init() {
		font1 = new BitmapFont();
		gl = new GlyphLayout();
		setScreen(new GameScreen(this));
	}

}

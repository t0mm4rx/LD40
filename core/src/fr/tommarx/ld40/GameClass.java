package fr.tommarx.ld40;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Util.Util;

public class GameClass extends Game {

	public static BitmapFont font25, font30, font50;
	public static GlyphLayout gl;

	public static TextureRegion[] animIdle, animWalk, coopWalk;
	public static TextureRegion santa, santaWasted, house, mat, mat2, exclamation, sleigh, bgMenu, title;

	public void init() {
		font25 = Util.ttfToBitmap(Gdx.files.internal("Gameplay.ttf"), 25);
		font30 = Util.ttfToBitmap(Gdx.files.internal("Gameplay.ttf"), 40);
		font50 = Util.ttfToBitmap(Gdx.files.internal("Gameplay.ttf"), 50);
		gl = new GlyphLayout();

		santa = new TextureRegion(new Texture("Santa.png"));
		santaWasted = new TextureRegion(new Texture("santa_wasted.png"));
		house = new TextureRegion(new Texture("house.png"));
		mat = new TextureRegion(new Texture("mat.png"));
		mat2 = new TextureRegion(new Texture("mat2.png"));
		exclamation = new TextureRegion(new Texture("exclamation.png"));
		sleigh = new TextureRegion(new Texture("sleigh.png"));
		bgMenu = new TextureRegion(new Texture("bg_menu.png"));
		title = new TextureRegion(new Texture("title.png"));
		animIdle = new TextureRegion[] {
				new TextureRegion(new Texture("idle_anim/1.png")),
				new TextureRegion(new Texture("idle_anim/2.png"))
		};
		animWalk = new TextureRegion[] {
				new TextureRegion(new Texture("walk_anim/1.png")),
				new TextureRegion(new Texture("walk_anim/2.png")),
				new TextureRegion(new Texture("walk_anim/3.png")),
				new TextureRegion(new Texture("walk_anim/4.png")),
				new TextureRegion(new Texture("walk_anim/5.png")),
				new TextureRegion(new Texture("walk_anim/6.png"))
		};
		coopWalk = new TextureRegion[] {
				new TextureRegion(new Texture("walk_anim_coop/1.png")),
				new TextureRegion(new Texture("walk_anim_coop/2.png")),
				new TextureRegion(new Texture("walk_anim_coop/3.png")),
				new TextureRegion(new Texture("walk_anim_coop/4.png"))
		};

		setScreen(new MenuScreen(this));
	}

}

package fr.tommarx.gameengine.Util;

import com.badlogic.gdx.math.Vector2;

import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.ld40.GameClass;
import fr.tommarx.ld40.GameScreen;
import fr.tommarx.ld40.Santa;

public class Sleigh extends AbstractGameObject{

    SpriteRenderer sr;

    public Sleigh(Vector2 pos) {
        super(new Transform(pos));

        sr = new SpriteRenderer(this, GameClass.sleigh, 0, 0, 3, 3);
        addComponent(sr);
    }

    protected void drawBefore() {

    }

    protected void drawAfter() {

    }

    protected void update(float delta) {
        Vector2 pos = ((Santa) Game.getCurrentScreen().getGameObjectByClass("Santa")).body.getBody().getPosition().cpy();
        float w = .64f, h = .64f;
        if (Util.AABB(pos, w, h, getTransform().getPosition().cpy(), 3, 3) && ((Santa) Game.getCurrentScreen().getGameObjectByClass("Santa")).presents == 0) {
            ((GameScreen) Game.getCurrentScreen()).win();
        }
    }
}

package fr.tommarx.ld40;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;

import fr.tommarx.gameengine.Collisions.CollisionsListener;
import fr.tommarx.gameengine.Collisions.CollisionsManager;
import fr.tommarx.gameengine.Components.Body;
import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Game.Draw;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.IO.Keys;
import fr.tommarx.gameengine.Util.Util;

public class Mat extends AbstractGameObject{

    Body body;
    BoxRenderer box;
    SpriteRenderer sr;
    boolean isActivated = false, hasPresents = false;

    public Mat(Vector2 pos) {
        super(new Transform(pos));
        setLayout(2);
        setTag("Mat");
        box = new BoxRenderer(this, 1, .5f, Color.GRAY);
        addComponent(box);

        sr = new SpriteRenderer(this, GameClass.mat, 0, 0, 1f, .5f);
        addComponent(sr);
    }

    protected void drawBefore() {

    }

    protected void drawAfter() {
        if (isActivated) {
            Draw.text("X to drop a present", getTransform().getPosition().x, getTransform().getPosition().y - 1, Color.WHITE, GameClass.font25, GameClass.gl);
        }
    }

    protected void update(float delta) {
        Vector2 pos = ((Santa)Game.getCurrentScreen().getGameObjectByClass("Santa")).body.getBody().getPosition().cpy();
        float w = .64f, h = .64f;
        if (Util.AABB(pos, w, h, getTransform().getPosition().cpy(), 1, .5f) && !hasPresents) {
            isActivated = true;
        } else {
            isActivated = false;
        }
        if (isActivated && Keys.isKeyJustPressed(Input.Keys.X) && !hasPresents) {
            ((Santa)Game.getCurrentScreen().getGameObjectByClass("Santa")).presents -= 2;
            sr.setTexture(GameClass.mat2);
            hasPresents = true;
        }
    }
}

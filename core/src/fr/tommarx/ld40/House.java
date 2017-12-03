package fr.tommarx.ld40;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import fr.tommarx.gameengine.Components.Body;
import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Game.Game;

public class House extends AbstractGameObject{

    Body body;
    SpriteRenderer sr;

    public House(Vector2 pos) {
        super(new Transform(pos));
        setLayout(2);

        body = new BoxBody(this, 4, 4, BodyDef.BodyType.StaticBody, false);
        addComponent(body);
        sr = new SpriteRenderer(this, GameClass.house, 0, 0, 4, 4);
        addComponent(sr);

        Game.getCurrentScreen().add(new Mat(new Vector2(pos.cpy().add(0, -2.25f))));
    }

    protected void drawBefore() {

    }

    protected void drawAfter() {

    }

    protected void update(float delta) {

    }
}

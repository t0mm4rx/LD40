package fr.tommarx.ld40;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import fr.tommarx.gameengine.Components.Body;
import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.AbstractGameObject;

public class House extends AbstractGameObject{

    Body body;
    BoxRenderer box;

    public House(Vector2 pos) {
        super(new Transform(pos));
        body = new BoxBody(this, 4, 4, BodyDef.BodyType.StaticBody, false);
        addComponent(body);
        box = new BoxRenderer(this, 4, 4, Color.BROWN);
        addComponent(box);
    }

    protected void drawBefore() {

    }

    protected void drawAfter() {

    }

    protected void update(float delta) {

    }
}

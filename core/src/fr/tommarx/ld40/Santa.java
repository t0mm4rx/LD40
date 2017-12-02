package fr.tommarx.ld40;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;

import fr.tommarx.gameengine.Components.Body;
import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.IO.Keys;

public class Santa extends AbstractGameObject{

    Body body;
    BoxRenderer box;
    float speed, friction;

    public Santa(Transform transform) {
        super(transform);
        body = new BoxBody(this, .64f, .64f, BodyDef.BodyType.DynamicBody, false);
        body.getBody().setSleepingAllowed(false);
        body.getBody().setFixedRotation(true);
        addComponent(body);
        box = new BoxRenderer(this, .64f, .64f, Color.RED);
        addComponent(box);
        speed = .2f;
        friction = 20;
    }

    protected void drawBefore() {

    }

    protected void drawAfter() {

    }

    protected void update(float delta) {
        if (Keys.isKeyPressed(Input.Keys.RIGHT) || Keys.isKeyPressed(Input.Keys.D)) {
            body.getBody().applyLinearImpulse(speed, 0, body.getBody().getPosition().x, body.getBody().getPosition().y, false);
        }
        if (Keys.isKeyPressed(Input.Keys.LEFT) || Keys.isKeyPressed(Input.Keys.A)) {
            body.getBody().applyLinearImpulse(-speed, 0, body.getBody().getPosition().x, body.getBody().getPosition().y, false);
        }
        if (Keys.isKeyPressed(Input.Keys.UP) || Keys.isKeyPressed(Input.Keys.W)) {
            body.getBody().applyLinearImpulse(0, speed, body.getBody().getPosition().x, body.getBody().getPosition().y, false);
        }
        if (Keys.isKeyPressed(Input.Keys.DOWN) || Keys.isKeyPressed(Input.Keys.S)) {
            body.getBody().applyLinearImpulse(0, -speed, body.getBody().getPosition().x, body.getBody().getPosition().y, false);
        }
        body.getBody().applyLinearImpulse(-body.getBody().getLinearVelocity().x / friction, -body.getBody().getLinearVelocity().y / friction, body.getBody().getPosition().x, body.getBody().getPosition().y, false);
        Vector3 dir = new Vector3(new Vector3(body.getBody().getPosition().x, body.getBody().getPosition().y, 0).sub(Game.getCurrentScreen().camera.position)).scl(.05f);
        Game.getCurrentScreen().camera.position.add(dir);

    }

}

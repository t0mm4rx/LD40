package fr.tommarx.ld40;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;

import fr.tommarx.gameengine.Components.AnimationManager;
import fr.tommarx.gameengine.Components.Body;
import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.IO.Keys;
import fr.tommarx.gameengine.Util.Animation;

public class Santa extends AbstractGameObject{

    public Body body;
    AnimationManager am;
    SpriteRenderer sr;
    float speed, friction;
    public int presents;
    boolean hasBeenSeen = false;

    public Santa(Transform transform) {
        super(transform);
        setTag("Santa");
        setLayout(3);
        body = new BoxBody(this, .50f, .64f, BodyDef.BodyType.DynamicBody, false);
        body.getBody().setSleepingAllowed(false);
        body.getBody().setFixedRotation(true);
        addComponent(body);
        speed = .2f;
        friction = 20;
        presents = 8;

        sr = new SpriteRenderer(this, GameClass.animIdle[0], 0, 0, 1, 1);
        addComponent(sr);

        am = new AnimationManager(this, sr, GameClass.santa);
        am.addAnimation(new Animation(this, GameClass.animIdle, 2, true, 0));
        am.addAnimation(new Animation(this, GameClass.animWalk, .16f, true, 1));
        am.setCurrentAnimation(0);
        addComponent(am);
    }

    protected void drawBefore() {

    }

    protected void drawAfter() {

    }

    protected void update(float delta) {
        if (!hasBeenSeen){

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
            if (Math.abs(body.getBody().getLinearVelocity().len()) > .1f && am.getCurrentAnimation() != 1) {
                am.setCurrentAnimation(1);
            } else if (am.getCurrentAnimation() != 0 && Math.abs(body.getBody().getLinearVelocity().len()) < .1f) {
                am.setCurrentAnimation(0);
            }
            if (body.getBody().getLinearVelocity().x < 0) {
                sr.flip(true, false);
            } else {
                sr.flip(false, false);
            }
            am.update();
        }
        Vector3 dir = new Vector3(new Vector3(body.getBody().getPosition().x, body.getBody().getPosition().y, 0).sub(Game.getCurrentScreen().camera.position)).scl(.05f);
        Game.getCurrentScreen().camera.position.add(dir);
    }

    public void seen() {
        am.setCurrentAnimation(-1);
        sr.setTexture(GameClass.santaWasted);
        hasBeenSeen = true;
    }

}

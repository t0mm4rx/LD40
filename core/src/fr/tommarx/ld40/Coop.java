package fr.tommarx.ld40;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;

import fr.tommarx.gameengine.Collisions.CollisionsManager;
import fr.tommarx.gameengine.Components.AnimationManager;
import fr.tommarx.gameengine.Components.Body;
import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.ConeLight;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Game.Draw;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Util.Animation;

public class Coop extends AbstractGameObject{

    private int currentPoint = 1;
    Body body;
    SpriteRenderer sr, sr2;
    AnimationManager am;
    float speed;
    Vector2[] points;
    ConeLight light;
    int frameSantaSeen = 0;

    public Coop(Vector2[] points) {
        super(new Transform(points[0]));
        this.points = points;
        body = new BoxBody(this, .64f, .64f, BodyDef.BodyType.KinematicBody, false);
        addComponent(body);
        light = new ConeLight(this, 200, 10, new Color(.7f, .7f, .3f, 1f), 0, 30);
        addComponent(light);

        sr2 = new SpriteRenderer(this, GameClass.exclamation, -.05f, .8f, .64f, .64f);
        sr2.isActive = false;
        addComponent(sr2);

        sr = new SpriteRenderer(this, GameClass.coopWalk[1], 0, 0, 1f, 1f);
        addComponent(sr);
        am = new AnimationManager(this, sr, GameClass.coopWalk[1]);
        am.addAnimation(new Animation(this, GameClass.coopWalk, .16f, true, 1));
        am.setCurrentAnimation(1);
        addComponent(am);

        speed = 1f;
    }

    protected void drawBefore() {

    }

    protected void drawAfter() {
        rayCast();
    }

    protected void update(float delta) {
        if (!((GameScreen) Game.getCurrentScreen()).isFinish) {
            body.getBody().setLinearVelocity(points[currentPoint].cpy().sub(body.getBody().getPosition()).nor().scl(speed));
            if (body.getBody().getPosition().dst(points[currentPoint]) < .1f) {
                if (currentPoint >= points.length - 1) {
                    currentPoint = 0;
                } else {
                    currentPoint++;
                }
            }
            light.getLight().setPosition(body.getBody().getPosition());
            light.setDirection(getAngle());
        } else {
            body.getBody().setLinearVelocity(0, 0);
            am.setCurrentAnimation(-1);
        }

    }

    private float getAngle() {
        float angle = points[currentPoint].cpy().sub(body.getBody().getPosition()).angle();
        return angle;
    }

    private void rayCast() {
        for (float a = -light.getAngle(); a < light.getAngle(); a += 4) {
            Game.getCurrentScreen().world.rayCast(new RayCastCallback() {
                public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
                    if (Game.debugging)
                        Draw.circle(point.x, point.y, .1f, Color.RED);
                    if (CollisionsManager.getGameObjectByBody(fixture.getBody()) != null) {
                        if (CollisionsManager.getGameObjectByBody(fixture.getBody()).getClassName().equals("Santa")) {
                            Game.debug(2, "Saw");
                            frameSantaSeen++;
                            if (frameSantaSeen > 5) {
                                sr2.isActive = true;
                                ((Santa)CollisionsManager.getGameObjectByBody(fixture.getBody())).seen();
                                ((GameScreen) Game.getCurrentScreen()).isFinish = true;
                            }
                        }
                    }
                    return 0;
                }
            },  body.getBody().getPosition(), body.getBody().getPosition().cpy().add(new Vector2(4, 4).setAngle(getAngle() + a)));
            if (Game.debugging)
                Draw.line(body.getBody().getPosition(), body.getBody().getPosition().cpy().add(new Vector2(5, 5).setAngle(getAngle() + a)), Color.WHITE);
        }
    }
}

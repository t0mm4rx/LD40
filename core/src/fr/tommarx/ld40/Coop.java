package fr.tommarx.ld40;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;

import fr.tommarx.gameengine.Collisions.CollisionsManager;
import fr.tommarx.gameengine.Components.Body;
import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.ConeLight;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Game.Draw;
import fr.tommarx.gameengine.Game.Game;

public class Coop extends AbstractGameObject{

    private int currentPoint = 1;
    Body body;
    BoxRenderer box;
    float speed;
    Vector2[] points;
    ConeLight light;
    int frameSantaSeen = 0;

    public Coop(Vector2[] points) {
        super(new Transform(points[0]));
        this.points = points;
        body = new BoxBody(this, .64f, .64f, BodyDef.BodyType.KinematicBody, false);
        addComponent(body);
        box = new BoxRenderer(this, .64f, .64f, Color.BLUE);
        addComponent(box);
        light = new ConeLight(this, 200, 10, new Color(.7f, .7f, .3f, 1f), 0, 30);
        speed = 1f;
    }

    protected void drawBefore() {

    }

    protected void drawAfter() {
        rayCast();
    }

    protected void update(float delta) {
        body.getBody().setLinearVelocity(points[currentPoint].cpy().sub(body.getBody().getPosition()).nor().scl(speed));
        if (body.getBody().getPosition().dst(points[currentPoint]) < .1f) {
            if (currentPoint >= points.length -1) {
                currentPoint = 0;
            } else {
                currentPoint++;
            }
        }
        light.getLight().setPosition(body.getBody().getPosition());
        light.setDirection(getAngle());
        if (frameSantaSeen > 5) {

        }

    }

    private float getAngle() {
        float angle = light.getDirection() + (points[currentPoint].cpy().sub(body.getBody().getPosition()).angle() - light.getDirection()) / 10;
        if (angle < light.getDirection()) {
            return angle - 330;
        }
        return angle;
    }

    private void rayCast() {
        for (float a = -light.getAngle(); a < light.getAngle(); a += 4) {
            Game.getCurrentScreen().world.rayCast(new RayCastCallback() {
                public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
                    if (Game.debugging)
                        Draw.circle(point.x, point.y, .1f, Color.RED);
                    if (CollisionsManager.getGameObjectByBody(fixture.getBody()).getClassName().equals("Santa")) {
                        Game.debug(2, "Saw");
                        frameSantaSeen++;
                    }
                    return 0;
                }
            }, body.getBody().getPosition().cpy().add(new Vector2(5, 5).setAngle(getAngle() + a)), body.getBody().getPosition());
            if (Game.debugging)
                Draw.line(body.getBody().getPosition(), body.getBody().getPosition().cpy().add(new Vector2(5, 5).setAngle(getAngle() + a)), Color.WHITE);
        }
    }
}

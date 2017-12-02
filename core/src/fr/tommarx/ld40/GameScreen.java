package fr.tommarx.ld40;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.Draw;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.IO.Keys;
import fr.tommarx.gameengine.IO.Touch;

public class GameScreen extends Screen{

    Santa santa;
    Coop coop;

    public GameScreen(Game game) {
        super(game);

        Game.debugging = true;
        areLightsEnabled(true);
        setLightsLayout(50);
        world.setGravity(new Vector2(0, 0));
        this.getRayHandler().setAmbientLight(new Color(.5f, .5f, .7f, .3f));
    }

    public void show() {
        santa = new Santa(new Transform(new Vector2(0, -2)));
        add(santa);

        coop = new Coop(new Vector2[] {
                new Vector2(3, 6),
                new Vector2(3, 3),
                new Vector2(6, 3),
                new Vector2(6, 6),

        });
        add(coop);

        add(new House(new Vector2(0, 0)));
        camera.zoom = 1.3f;
    }

    public void renderBefore() {

    }

    public void renderAfter() {
        Draw.text(santa.presents + " presents", Game.getCurrentScreen().camera.position.x - 4, Game.getCurrentScreen().camera.position.y - 4, Color.WHITE, GameClass.font1, GameClass.gl);
    }

    public void update() {
        if (Keys.isKeyJustPressed(Input.Keys.F1)) {
            Game.debugging = !Game.debugging;
        }
        Game.debug(1, Touch.getProjectedPosition());
    }

}

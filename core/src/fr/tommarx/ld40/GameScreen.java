package fr.tommarx.ld40;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.Draw;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.IO.Keys;
import fr.tommarx.gameengine.IO.Touch;
import fr.tommarx.gameengine.Util.Sleigh;

public class GameScreen extends Screen{

    Santa santa;
    public boolean isFinish = false;
    public boolean hasWon = false;
    private boolean hasStarted = false;

    public GameScreen(Game game) {
        super(game);

        Game.debugging = true;
        areLightsEnabled(true);
        setLightsLayout(50);
        world.setGravity(new Vector2(0, 0));
        this.getRayHandler().setAmbientLight(new Color(.5f, .5f, .7f, .3f));
    }

    public void show() {
        santa = new Santa(new Transform(new Vector2(11, -4)));
        add(santa);

        add(new Coop(new Vector2[] {
                new Vector2(9, 3),
                new Vector2(9, 9),
                new Vector2(-3, 9),
                new Vector2(-3, 3),

        }));
        add(new Coop(new Vector2[] {
                new Vector2(5, -4),
                new Vector2(-2, -4)

        }));
        add(new Coop(new Vector2[] {
                new Vector2(3, -3),
                new Vector2(3, 8)

        }));

        add(new House(new Vector2(0, 0)));
        add(new House(new Vector2(6, 0)));
        add(new House(new Vector2(0, 6)));
        add(new House(new Vector2(6, 6)));
        add(new Sleigh(new Vector2(13, -5)));
        camera.zoom = 1.3f;
    }

    public void renderBefore() {

    }

    public void renderAfter() {
        Draw.text(santa.presents + " presents", Game.getCurrentScreen().camera.position.x - 5f, Game.getCurrentScreen().camera.position.y + 5, Color.WHITE, GameClass.font25, GameClass.gl);
        if (isFinish) {
            Draw.text("Busted", Game.getCurrentScreen().camera.position.x, Game.getCurrentScreen().camera.position.y, Color.RED, GameClass.font50, GameClass.gl);
            if (!hasStarted) {
                hasStarted = true;
                Game.waitAndDo(2000f, () -> {
                    setScreen(new GameScreen(game));
                    return 0;
                });
            }
        }
        if (hasWon) {
            Draw.text("Won !", Game.getCurrentScreen().camera.position.x, Game.getCurrentScreen().camera.position.y, Color.RED, GameClass.font50, GameClass.gl);
            if (!hasStarted) {
                hasStarted = true;
                Game.waitAndDo(2000f, () -> {
                    setScreen(new GameScreen(game));
                    return 0;
                });
            }
        }
    }

    public void update() {
        if (Keys.isKeyJustPressed(Input.Keys.F1)) {
            Game.debugging = !Game.debugging;
        }
        Game.debug(1, Touch.getProjectedPosition());
    }

    public void win() {
        hasWon = true;
    }

}

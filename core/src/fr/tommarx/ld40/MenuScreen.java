package fr.tommarx.ld40;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;

import fr.tommarx.gameengine.Easing.Tween;
import fr.tommarx.gameengine.Easing.TweenListener;
import fr.tommarx.gameengine.Game.Draw;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.IO.Keys;

public class MenuScreen extends Screen{

    int selection = 0;
    float a = 1;

    public MenuScreen(Game game) {
        super(game);
    }

    public void show() {
        new Tween(Tween.LINEAR_EASE_INOUT, 1f, 0f, true, new TweenListener() {
            public void onValueChanged(float v) {
                a = v / 10 + 1;
            }

            public void onFinished() {

            }
        });
    }

    public void renderBefore() {
        Draw.texture(GameClass.bgMenu.getTexture(), Game.center.x - 1.12f, Game.center.y + 1.12f, Game.size.x, Game.size.y);
        Draw.texture(GameClass.title.getTexture(), Game.center.x + .3f - (3.5f * a / 2), Game.center.y + 4.2f + (.6f * a / 2), 3.5f * a, .6f * a);

        Draw.text("Play", 2.5f, 1, Color.WHITE, (selection == 0 ? GameClass.font30 : GameClass.font25), GameClass.gl);
        Draw.text("Level editor", 5, 1, Color.WHITE, (selection == 1 ? GameClass.font30 : GameClass.font25), GameClass.gl);
        Draw.text("Exit", 8, 1, Color.WHITE, (selection == 2 ? GameClass.font30 : GameClass.font25), GameClass.gl);
    }

    public void renderAfter() {

    }

    public void update() {
        if (Keys.isKeyJustPressed(Input.Keys.RIGHT) || Keys.isKeyJustPressed(Input.Keys.D)) {
            selection++;
            if (selection > 2) {
                selection = 0;
            }
        }
        if (Keys.isKeyJustPressed(Input.Keys.LEFT) || Keys.isKeyJustPressed(Input.Keys.A)) {
            selection--;
            if (selection < 0) {
                selection = 2;
            }
        }
        if (Keys.isKeyJustPressed(Input.Keys.SPACE) || Keys.isKeyJustPressed(Input.Keys.ENTER)) {
            switch (selection) {
                case 0:
                    setScreen(new GameScreen(game));
                    break;
                case 1:
                    setScreen(new GameScreen(game));
                    break;
                case 2:
                    System.exit(0);
                    break;
            }
        }
    }

}

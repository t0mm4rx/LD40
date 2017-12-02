package fr.tommarx.gameengine.Util;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Game.GameObject;

public class Util {

    public static BitmapFont ttfToBitmap(FileHandle file, int size) {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(file);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;
        return fontGenerator.generateFont(parameter);
    }

    public static boolean areGameObjectsByTag(AbstractGameObject a, AbstractGameObject b, String tagA, String tagB) {
        if ((a.getTag().equals(tagA) && b.getTag().equals(tagB)) || (b.getTag().equals(tagA) && a.getTag().equals(tagB))) {
            return true;
        }
        return false;
    }

    public static boolean AABB(Vector2 pos1, float w1, float h1, Vector2 pos2, float w2, float h2) {
        if (pos1.x + w1 >= pos2.x && pos1.x <= pos2.x + w2) {
            if (pos1.y + h1 >= pos2.y && pos1.y <= pos2.y + h2) {
                return true;
            }
        }
        return false;
    }

}

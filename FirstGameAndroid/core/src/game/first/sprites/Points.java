package game.first.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by joaop on 29-12-2017.
 */

public class Points {
    public static final int POINTS_WIDTH = 52;
    private static final int MOVE = 40; // Entre 0 e 70 que pode andar.
    private Texture points1;
    private Vector2 posPoints1;
    private Rectangle bounds; //Criar retângulo invisível para colisões.
    private Random rand;
    public boolean destroyed;

    public Points(float x){
        points1 = new Texture("mirtilo.png");
        rand = new Random();
        destroyed = false;

        posPoints1 = new Vector2(x, rand.nextInt(MOVE + 30) + 50);

        bounds = new Rectangle(posPoints1.x, posPoints1.y, points1.getWidth(), points1.getHeight());
    }

    public Texture getPoints1() {
        return points1;
    }

    public Vector2 getPosPoints1() {
        return posPoints1;
    }

    public void repositionPoints(float x){
        posPoints1.set(x, rand.nextInt(MOVE));
        bounds.setPosition(posPoints1.x, posPoints1.y);
    }

    //Colisões.
    public boolean collides(Rectangle player){
        return player.overlaps(bounds);
    }
}

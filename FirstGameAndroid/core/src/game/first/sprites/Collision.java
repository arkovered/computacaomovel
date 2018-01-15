package game.first.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by joaop on 29-12-2017.
 */

public class Collision {
    public static final int COLLISIONS_WIDTH = 52; //Espaço entre colisões.
    private static final int MOVE = 40; // Entre 0 e 70 que pode andar.
    private static final int COLLISIONS_GAP = 60; // Espaço entre obstáculos.
    private static final int LOWEST_GAP = 40;
    private Texture collision1, collision2;
    private Vector2 posCollision1, posCollision2;
    private Rectangle boundsTop, boundsBot; //Criar retângulo invisível para colisões.
    private Random rand;

    public Collision(float x){
        collision1 = new Texture("rock.png");
        collision2 = new Texture("wheel.png");
            rand = new Random();

        posCollision1 = new Vector2(x, rand.nextInt(MOVE + 30) + COLLISIONS_GAP + LOWEST_GAP);
        posCollision2 = new Vector2(x + 80, posCollision1.y - COLLISIONS_GAP - collision2.getHeight());

        boundsTop = new Rectangle(posCollision1.x, posCollision1.y, collision1.getWidth(), collision1.getHeight());
        boundsBot = new Rectangle(posCollision2.x, posCollision2.y, collision2.getWidth(), collision2.getHeight());

    }

    public Texture getCollision1() {
        return collision1;
    }

    public Texture getCollision2() {
        return collision2;
    }

    public Vector2 getPosCollision1() {
        return posCollision1;
    }

    public Vector2 getPosCollision2() {
        return posCollision2;
    }

    public void reposition1(float x){
        posCollision1.set(x, rand.nextInt(MOVE) + COLLISIONS_GAP + LOWEST_GAP);
        boundsTop.setPosition(posCollision1.x, posCollision1.y);

    }

    public void reposition2(float x){
        posCollision2.set(x + 80, rand.nextInt(MOVE) + COLLISIONS_GAP + LOWEST_GAP);
        boundsBot.setPosition(posCollision2.x, posCollision2.y);
    }

    //Colisões.
    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }
}

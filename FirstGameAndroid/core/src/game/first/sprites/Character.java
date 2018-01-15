package game.first.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import game.first.States.PlayState;

/**
 * Created by joaop on 29-12-2017.
 */

public class Character {
    private static final int GRAVITY = -7;
    private static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation characterAnimation;


    private PlayState playState;
    private SpriteBatch sb;
    private Array<Points> points;

    BitmapFont font;
    public int score;

    public Character(int x, int y){
        position = new Vector3 (x, y, 0);
        velocity = new Vector3 (0, 0, 0);
        Texture texture = new Texture("characteranimation.png");
        characterAnimation = new Animation(new TextureRegion(texture),4,0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 4, texture.getHeight() - 5);

        points = new Array<Points>();
        sb = new SpriteBatch();

        font = new BitmapFont();
        font.setColor(0f,0f,0,1);
        font.getData().setScale(5);
        score = 0;

    }

    public void update(float dt){
        characterAnimation.update(dt);
        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);

        if(position.y < 25)
            position.y = 25; //Limite até onde pode subir.
        if(position.y >160)
            position.y = 160; //Limite até onde pode descer.

        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }


    public void setPlayState(PlayState playState) {
        this.playState = playState;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public TextureRegion getTexture() {return characterAnimation.getFrame(); }

    public void move(){
        velocity.y = 90; //O quanto ele salta.
    }

    public void render () {
        sb.begin();
        font.draw(sb, "Score = " + score, 720, 1500);
        sb.end();
    }
}


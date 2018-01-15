package game.first.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by joaop on 29-12-2017.
 */

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 touch;
    protected GameStateManager gsm;

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        touch = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float dt); //deltatime
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}


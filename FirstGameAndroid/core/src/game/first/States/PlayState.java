package game.first.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import game.first.GameMain;
import game.first.sprites.Character;
import game.first.sprites.Collision;
import game.first.sprites.Points;

import static game.first.sprites.Points.POINTS_WIDTH;


/**
 * Created by joaop on 29-12-2017.
 */

public class PlayState extends State {
    private static final int COLLISIONS_SPACING = 60; //Espaço entre aparecerem colisões.
    private static final int COLLISIONS_COUNT = 50;   //Conta o número de obstáculos que apareceram.
    private static final int POINTS_SPACING = 60;     //Espaço entre os pontos.
    private static final int POINTS_COUNT = 50;       //Conta o número de pontos.

    private Character character;
    private Character getCharacter;
    private Texture world;

    private Array<Collision> collisions;
    private Array<Points> points;
    private boolean destroyed;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        character = new Character(10, 50); //Local onde a personagem é iniciada.
        cam.setToOrtho(false, GameMain.WIDTH / 2 - 30, GameMain.HEIGHT / 2);
        world = new Texture("world.png"); //Imagem para o fundo do jogo.
        destroyed = false;

        collisions = new Array<Collision>();
        points = new Array<Points>();

        for (int i = 1; i <= COLLISIONS_COUNT; i++){
            collisions.add(new Collision(i * (COLLISIONS_SPACING + Collision.COLLISIONS_WIDTH)));
        }

        for (int j = 1; j<=POINTS_COUNT; j++){
            points.add(new Points(j * (POINTS_SPACING + POINTS_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched())
            character.move();
    }

    @Override
    public void update(float dt) {
        handleInput();
        character.update(dt);

        //Cam follow character.
        cam.position.x = character.getPosition().x + 100;


        //Para quando sair da visão da câmera reposicionar para outro sítio.
        for (Collision collision : collisions){
            if(cam.position.x - (cam.viewportWidth / 2) > collision.getPosCollision1().x + collision.getCollision1().getWidth()){
                collision.reposition1(collision.getPosCollision1().x + ((Collision.COLLISIONS_WIDTH + COLLISIONS_SPACING) * COLLISIONS_COUNT));
            }
            if(cam.position.x - (cam.viewportWidth / 2) > collision.getPosCollision2().x + collision.getCollision2().getWidth()){
                collision.reposition2(collision.getPosCollision1().x + ((Collision.COLLISIONS_WIDTH + COLLISIONS_SPACING) * COLLISIONS_COUNT));
            }
            if(collision.collides(character.getBounds()))
                gsm.set(new PlayState(gsm));
        }

        for (Points points : points){
            if(cam.position.x - (cam.viewportWidth / 2) > points.getPosPoints1().x + points.getPoints1().getWidth()){
                points.repositionPoints(points.getPosPoints1().x + ((Points.POINTS_WIDTH + POINTS_SPACING) * POINTS_COUNT));
                points.destroyed = false;
            }
            if(points.collides(character.getBounds()) && !points.destroyed) {
                character.score += 1;
                points.destroyed = true;
                break;
            }
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw((world), cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(character.getTexture(), character.getPosition().x, character.getPosition().y);

        for (Collision collision : collisions) {
            sb.draw(collision.getCollision1(), collision.getPosCollision1().x, collision.getPosCollision1().y);
            sb.draw(collision.getCollision2(), collision.getPosCollision2().x, collision.getPosCollision2().y);
        }

        for (Points points : points) {
            if (points.destroyed == false)
            sb.draw(points.getPoints1(), points.getPosPoints1().x, points.getPosPoints1().y);
        }

        sb.end();
        character.render();
    }


    @Override
    public void dispose() {
    }
}

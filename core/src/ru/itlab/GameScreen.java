package ru.itlab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
    Player player;
    Array<Bullet> bullets = new Array<Bullet>();
    World world;
    SpriteBatch batch;
    Box2DDebugRenderer b2dr;
    Camera camera;
    long reload = TimeUtils.nanoTime();
    //public boolean

    @Override
    public void show() {
        world = new World(new Vector2(0,0), false);
        /*world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fa = contact.getFixtureA(), fb = contact.getFixtureB();
                if((fa.getUserData() == "bullet" && fb.getUserData() == "player")
                        || (fb.getUserData() == "bullet" && fa.getUserData() == "player")){

                }
            }
            @Override
            public void endContact(Contact contact) {}
            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {}
            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {}
        });*/
        b2dr = new Box2DDebugRenderer();
        player = new Player(world);
        camera = new Camera(player.body.getPosition());
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        //Update
        world.step(1000f, 6, 2);
        b2dr.render(world, camera.camera.combined);
        player.update(delta);
        if((player.bulletRot.x != 0 || player.bulletRot.y != 0)
                && MathUtils.nanoToSec*(TimeUtils.nanoTime()-reload) >= 60/Constants.SHOOT_RATE){
            reload = TimeUtils.nanoTime();
            bullets.add(new Bullet(player.bulletRot, world, player.body.getPosition()));
        }
        for(Bullet bullet : bullets){
            bullet.update(delta);
            if(!bullet.inGame)
                bullets.removeValue(bullet, false);
        }

        //Render
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        player.render(batch);
        for(Bullet bullet : bullets)
            bullet.render(batch);
        batch.end();

        //Game Over
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))Gdx.app.exit();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        world.dispose();
        batch.dispose();
    }
}

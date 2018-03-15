package ru.itlab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen implements Screen {
    Player player;
    World world;
    SpriteBatch batch;
    Box2DDebugRenderer b2dr;
    Camera camera;

    @Override
    public void show() {
        world = new World(new Vector2(0,0), false);
        b2dr = new Box2DDebugRenderer();
        player = new Player(world);
        camera = new Camera(player.body.getPosition());
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        world.step(1000f, 6, 2);
        b2dr.render(world, camera.camera.combined);
        player.update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        player.render(batch);
        batch.end();

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

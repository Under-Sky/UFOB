package ru.itlab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import static ru.itlab.Constants.SCORE;


public class GameOverScreen implements Screen {

    public static BitmapFont font, shadow;
    SpriteBatch batch;
    OrthographicCamera camera;
    StretchViewport viewport;
    Stage stage;
    Vector3 touchPos;

    @Override
    public void show() {
        touchPos = new Vector3();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));

        viewport = new StretchViewport(1280, 720, camera);
        stage = new Stage(viewport);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getViewport().apply();

        batch.setProjectionMatrix(stage.getCamera().combined);
        batch.begin();
        shadow.draw(batch, "Score", 540, 600);
        font.draw(batch, "Score", 540, 600);

        shadow.draw(batch, SCORE + "", 640, 360);
        font.draw(batch, SCORE + "", 640, 360);
        batch.end();

        if(Gdx.input.justTouched()){
            //game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        font.dispose();
        shadow.dispose();
        stage.dispose();
    }
}

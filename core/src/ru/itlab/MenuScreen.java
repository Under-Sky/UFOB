package ru.itlab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static ru.itlab.Constants.C_VISION;
import static ru.itlab.Constants.PPM;

public class MenuScreen implements Screen {

    Texture texture;
    SpriteBatch batch;
    float scale, drawX;

    @Override
    public void show(){
        batch = new SpriteBatch();
        texture = new Texture("background.png");
        scale = (C_VISION.y * texture.getWidth()) / texture.getHeight();
        drawX = texture.getWidth() / 2 - C_VISION.x * PPM / 2;
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 1, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(texture,
                drawX,
                0,
                scale * PPM,
                C_VISION.y * PPM);
        batch.end();
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

    }
}

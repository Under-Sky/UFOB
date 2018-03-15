package ru.itlab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class Camera{
    OrthographicCamera camera;
    public Camera(Vector2 pos){
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(pos.x, pos.y, camera.position.z);
    }
}

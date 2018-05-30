package ru.itlab;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class Camera{
    OrthographicCamera camera;
    public Camera(Vector2 pos){
        camera = new OrthographicCamera(Constants.C_VISION.x, Constants.C_VISION.y);
        camera.position.set(pos.x, pos.y, camera.position.z);
    }
    public void update(Vector2 pos){
        camera.position.set(pos.x, pos.y, camera.position.z);
        camera.update();
    }
}

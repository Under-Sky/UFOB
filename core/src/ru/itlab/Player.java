package ru.itlab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static ru.itlab.Constants.C_SPEED;
import static ru.itlab.Constants.SIZE;


public class Player {
    public Body body;
    Texture texture;
    public Vector2 bulletRot = new Vector2(0,0);

    public Player(World world){
        body = Utils.createBox(world, (Gdx.graphics.getWidth()-SIZE.x)/2, (Gdx.graphics.getHeight()-SIZE.y)/2,
                SIZE.x, SIZE.y, false);
        texture = new Texture("blue1.png");
    }

    public void update(float delta){
        float x, y;
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            x = -1;
        else if(Gdx.input.isKeyPressed(Input.Keys.D))
            x = 1;
        else x = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.W))
            y = 1;
        else if(Gdx.input.isKeyPressed(Input.Keys.S))
            y = -1;
        else y = 0;

        body.setLinearVelocity(delta*C_SPEED*x, delta*C_SPEED*y);
        Gdx.app.log("Position", body.getPosition().x+" "+body.getPosition().y);
    }

    public void render(SpriteBatch batch){
        batch.draw(texture,
                body.getPosition().x,
                body.getPosition().y,
                SIZE.x,
                SIZE.y);
    }
}

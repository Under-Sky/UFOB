package ru.itlab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static ru.itlab.Constants.C_SPEED;
import static ru.itlab.Constants.C_SIZE;


public class Player {
    public Body body;
    Texture texture;
    public Vector2 bulletRot = new Vector2(0,0);

    public Player(World world){
        body = Utils.createBox(world, (Gdx.graphics.getWidth()- C_SIZE.x)/2, (Gdx.graphics.getHeight()- C_SIZE.y)/2,
                C_SIZE.x, C_SIZE.y, false);
        body.setUserData("player");
        texture = new Texture("blue1.png");
    }

    public void update(float delta){
        move(delta);
        shoot();
    }

    public void render(SpriteBatch batch){
        batch.draw(texture,
                body.getPosition().x,
                body.getPosition().y,
                C_SIZE.x,
                C_SIZE.y);
    }

    public void shoot(){
        bulletRot = new Vector2(0,0);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            bulletRot.x--;
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            bulletRot.x++;

        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            bulletRot.y++;
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            bulletRot.y--;
    }

    public void move(float delta){
        float x = 0, y = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            x--;
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            x++;

        if(Gdx.input.isKeyPressed(Input.Keys.W))
            y++;
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            y--;

        if(y != 0 && x != 0)
            body.setLinearVelocity(delta*C_SPEED*x/(float)Math.sqrt(2), delta*C_SPEED*y/(float)Math.sqrt(2));
        else
            body.setLinearVelocity(delta*C_SPEED*x, delta*C_SPEED*y);
    }
}

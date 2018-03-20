package ru.itlab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import static ru.itlab.Constants.C_SPEED;
import static ru.itlab.Constants.C_SIZE;
import static ru.itlab.Constants.C_VISION;

public class Player {
    public Fixture body;
    Texture texture;
    public Vector2 bulletRot = new Vector2(0,0);

    public Player(World world){
        body = Utils.createBox(world, (C_VISION.x - C_SIZE.x)/2, (C_VISION.y - C_SIZE.y)/2,
                C_SIZE.x, C_SIZE.y, false, "player");
        texture = new Texture("PNG/blue1.png");
    }

    public void update(float delta){
        move(delta);
        shoot();
    }

    public void render(SpriteBatch batch){
        batch.draw(texture,
                body.getBody().getPosition().x,
                body.getBody().getPosition().y,
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
            body.getBody().setLinearVelocity(delta*C_SPEED*x/(float)Math.sqrt(2), delta*C_SPEED*y/(float)Math.sqrt(2));
        else
            body.getBody().setLinearVelocity(delta*C_SPEED*x, delta*C_SPEED*y);
    }
}

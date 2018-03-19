package ru.itlab;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static ru.itlab.Constants.B_SIZE;
import static ru.itlab.Constants.C_SIZE;
import static ru.itlab.Constants.B_SPEED;
import static ru.itlab.Constants.C_SPEED;

public class Bullet {

    Vector2 rot;
    Body body;
    Texture texture;
    public boolean inGame = true;

    public Bullet(Vector2 rot, World world, Vector2 pos){
        this.rot = rot;
        body = Utils.createBox(world, pos.x+C_SIZE.x/2-B_SIZE.x/2, pos.y+C_SIZE.y/2-B_SIZE.y/2,
                B_SIZE.x, B_SIZE.y, false);
        body.setUserData("bullet");
        texture = new Texture("badlogic.jpg");
    }

    public void update(float delta){
        body.setLinearVelocity(delta*B_SPEED*rot.x, delta*B_SPEED*rot.y);
    }

    public void render(SpriteBatch batch){
        batch.draw(texture,
                body.getPosition().x,
                body.getPosition().y,
                B_SIZE.x,
                B_SIZE.y);
    }
}

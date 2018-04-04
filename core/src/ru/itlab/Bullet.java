package ru.itlab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import static ru.itlab.Constants.B_SIZE;
import static ru.itlab.Constants.C_SIZE;
import static ru.itlab.Constants.B_SPEED;
import static ru.itlab.Constants.C_VISION;

public class Bullet {

    Vector2 rot;
    public Fixture body;
    Texture texture;
    public boolean inGame = true;
    Sprite sprite;

    public Bullet(Vector2 rot, World world, Vector2 pos){
        this.rot = rot;
        body = Utils.createBox(world, check(pos).x, check(pos).y, B_SIZE.x, B_SIZE.y, false, "bullet");
        texture = new Texture("bullet.png");
        sprite = new Sprite(texture);

        rotat(rot);
    }

    public void update(float delta, Vector2 pos){
        body.getBody().setLinearVelocity(delta*B_SPEED*rot.x, delta*B_SPEED*rot.y);
        if(body.getBody().getPosition().x > pos.x+C_VISION.x*1.5
                || body.getBody().getPosition().y > pos.y+C_VISION.y*1.5
                || body.getBody().getPosition().x < pos.x-C_VISION.x*1.5
                || body.getBody().getPosition().y < pos.y-C_VISION.y*1.5)
            inGame = false;
        if(!inGame)Gdx.app.log("Bullet", "deleted");
    }

    public void render(SpriteBatch batch){
        batch.draw(sprite,
                body.getBody().getPosition().x,
                body.getBody().getPosition().y,
                B_SIZE.x,
                B_SIZE.y);
    }
    public Vector2 check(Vector2 pos){
        float x, y;
        if(rot.x > 0)
            x = pos.x + C_SIZE.x;
        else if(rot.x < 0)
            x = pos.x - B_SIZE.x;
        else
            x = pos.x + C_SIZE.x/2 - B_SIZE.x / 2;

        if(rot.y > 0)
            y = pos.y + C_SIZE.y - B_SIZE.y / 2;
        else if(rot.y < 0)
            y = pos.y - B_SIZE.y / 2;
        else
            y = pos.y + C_SIZE.y/2 - B_SIZE.y / 2;

        return new Vector2(x,y);
    }

    public void rotat(Vector2 rot){
        if(rot.x == 0 && rot.y == 0)
            inGame = false;
        switch((int)rot.x){
            case 1:
                switch((int)rot.y){
                    case 1:sprite.setRotation(135);
                    case -1:sprite.setRotation(45);
                    case 0:sprite.setRotation(90);
                }
            case -1:
                switch((int)rot.y){
                    case 1:sprite.setRotation(45);
                    case -1:sprite.setRotation(135);
                    case 0:sprite.setRotation(90);
                }
        }
    }
}

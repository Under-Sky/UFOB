package ru.itlab;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import static ru.itlab.Constants.C_VISION;
import static ru.itlab.Constants.E_SIZE;
import static ru.itlab.Constants.E_SPEED;

public class Enemy {

    public Fixture body;
    Texture texture;
    Vector2 rot = new Vector2(0,0);
    public boolean inGame = true;
    public int live = 9;
    String path;

    public Enemy(World world){
        body = Utils.createBox(world, rand(),
                E_SIZE.x, E_SIZE.y, false, "enemy");
        switch((int)Math.random()*(4)+1){
            case 1:path = "PNG/green";break;
            case 2:path = "PNG/pink";break;
            case 3:path = "PNG/sand";break;
            case 4:path = "PNG/yellow";break;
        }
        texture = new Texture(path + "1.png");
        calcRot();
    }

    public void update(float delta){
        body.getBody().setLinearVelocity(delta*E_SPEED*rot.x, delta*E_SPEED*rot.y);
        if(body.getBody().getPosition().x > C_VISION.x*2 || body.getBody().getPosition().y > C_VISION.y*2
                || body.getBody().getPosition().x < 0 || body.getBody().getPosition().y < 0)
            inGame = false;
    }
    public void render(SpriteBatch batch){
        batch.draw(texture,
                body.getBody().getPosition().x,
                body.getBody().getPosition().y,
                E_SIZE.x,
                E_SIZE.y);
    }

    public Vector2 rand(){
        float x,y;
        do{
            x = (float) (Math.random()*C_VISION.x*3-C_VISION.x);
        }while((x < C_VISION.x || x > C_VISION.x*2) && x > 0);
        do{
            y = (float) (Math.random()*C_VISION.y*3-C_VISION.y);
        }while((y < C_VISION.y || y > C_VISION.y*2) && y > 0);
        return new Vector2(x,y);
    }
    public void calcRot(){
        float x = body.getBody().getPosition().x, y = body.getBody().getPosition().y;
        if(x > C_VISION.x)
            rot.x = -1;
        else if(x < 0)
            rot.x = 1;
        else rot.x = 0;

        if(y > C_VISION.y)
            rot.y = -1;
        else if(y < 0)
            rot.y = 1;
        else rot.y = 0;
    }

    public void damaged(){
        live--;
        if(live > 6)
            texture = new Texture("PNG/blue1.png");
        else if(live > 3)
            texture = new Texture("PNG/blue1.png");
        else if(live > 0)
            texture = new Texture("PNG/blue1.png");
        else inGame = false;
    }
}

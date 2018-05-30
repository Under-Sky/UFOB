package ru.itlab;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import static ru.itlab.Constants.C_VISION;
import static ru.itlab.Constants.PPM;

public class BackgroundRenderer {

    Texture texture = new Texture("background.png");
    final Vector2 size = new Vector2(texture.getWidth() / PPM / 2, texture.getHeight() / PPM / 2);//Размер 1 плитки фона
    int numX = (int)(C_VISION.x / size.x+1), numY = (int)(C_VISION.y / size.y+1);
    Vector2 poses[][] = new Vector2[numX][numY];
    //(сверху) Количество плиток в зависимости от их размера и размера экрана

    public BackgroundRenderer(Vector2 pos){
        int centX, centY;
        if(numX % 2 == 0)centX = numX / 2;
        else centX = (numX + 1) / 2;
        if(numY % 2 == 0)centY = numY / 2;
        else centY = (numY + 1) / 2;

        poses[centX][centY] = new Vector2(pos.x-size.x/2, pos.y-size.y/2);
        float posX = pos.x - size.x/2 - (centX - 1) * size.x;
        float posY = pos.y - size.y/2 - (centY - 1) * size.y;

        for(int i = 0; i < numX; i++)
            for(int j = 0; j < numY; j++)
                poses[i][j] = new Vector2(posX + i * size.x, posY + j * size.y);
    }

    public void render(SpriteBatch batch){
        for(int i = 0; i < numX; i++)
            for(int j = 0; j < numY; j++)
                batch.draw(texture,
                        poses[i][j].x,
                        poses[i][j].y,
                        size.x,
                        size.y);
    }

    public void update(Vector2 pos){

    }
}

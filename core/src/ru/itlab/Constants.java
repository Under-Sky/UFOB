package ru.itlab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public final class Constants {
    public static final Vector2 C_SIZE = new Vector2(32,32);
    public static final Vector2 B_SIZE = new Vector2(16,16);
    public static final float C_SPEED = 1000;
    public static final float B_SPEED = 1500;
    public static final Vector2 C_VISION = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    public static final float SHOOT_RATE = 60; // Выстрелов в минуту

}

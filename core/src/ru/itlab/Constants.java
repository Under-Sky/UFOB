package ru.itlab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public final class Constants {
    public static final float PPM = 32;
    public static final Vector2 C_SIZE = new Vector2(32 / PPM,32 / PPM);
    public static final Vector2 B_SIZE = new Vector2(16 / PPM,16 / PPM);
    public static final Vector2 E_SIZE = new Vector2(32 / PPM,32 / PPM);
    public static final float C_SPEED = 5 / PPM;
    public static final float B_SPEED = 10 / PPM;
    public static final float E_SPEED = 6 / PPM;
    public static long SCORE = 0;
    public static final Vector2 C_VISION = new Vector2(Gdx.graphics.getWidth() / PPM, Gdx.graphics.getHeight() / PPM);
    public static final float SHOOT_RATE = 60; // Выстрелов в минуту
}

package com.mygdx.game.states;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Main;
import com.mygdx.game.managers.GameStateManager;

public abstract class GameState {

    //References
    protected GameStateManager gsm;
    protected Main app;

    protected SpriteBatch batch;
    protected OrthographicCamera camera;

    protected GameState(GameStateManager gsm){
        this.gsm=gsm;
        this.app=gsm.main();
        batch=new SpriteBatch();
        camera=app.getCamera();

    }

    public void resize(int w, int h){

        camera.setToOrtho(false,w,h);

    }

    public abstract void  update(float delta);
    public  abstract  void render();
    public abstract void dispose();
    public abstract void handleInput();



}

package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.managers.GameStateManager;
import static com.mygdx.game.utils.Constants.*;


public class SplashState extends GameState{


    float acc=0f;
    Texture tex;


    /**
     *
     * @param gsm
     */
    public SplashState(GameStateManager gsm) {
        super(gsm);
        tex=new Texture("Images/loading.png");

    }


    /**
     *
     * @param delta
     */
    @Override
    public void update(float delta) {
        acc+=delta;
        if(acc>=1) {
            gsm.setState((GameStateManager.State.PLAY));
        }

    }



    @Override
    public void render() {
        Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(tex,V_WIDTH/4-tex.getWidth()/2,V_HEIGHT/2-tex.getHeight());
        batch.end();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void handleInput() {

    }
}

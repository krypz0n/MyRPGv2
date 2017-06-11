package com.mygdx.game.input;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.mygdx.game.states.PlayState;

import static com.mygdx.game.utils.Constants.*;


public class DesktopInputProcessor implements InputProcessor {

    private PlayState game;

    /**
     *
     * @param game
     */
    public DesktopInputProcessor(PlayState game){this.game=game;}
    private Camera camera;

    /**
     *
     * @param keycode
     * @return
     */
    @Override
    public boolean keyDown(int keycode) {
        switch(keycode)
        {
            case Input.Keys.A:
                game.inputUpdateX(-0.4f);
                break;
            case Input.Keys.D:
                game.inputUpdateX(0.4f);

                break;
            case Input.Keys.W:
                game.inputUpdateY(0.4f);

                break;
            case Input.Keys.S:
                game.inputUpdateY(-0.4f);

                break;
            /*case Input.Keys.SPACE:
                game.inputUpdate(0.6f,0);
                break;*/

        }

        return false;
    }


    /**
     *
     * @param keycode
     * @return
     */
    @Override
    public boolean keyUp(int keycode) {
        switch(keycode)
        {
            case Input.Keys.A:
                game.inputUpdateX(0.4f);
                break;
            case Input.Keys.D:
                game.inputUpdateX(-0.4f);
                break;
            case Input.Keys.W:
                game.inputUpdateY(-0.4f);
                break;
            case Input.Keys.S:
                game.inputUpdateY(0.4f);
                break;
        }

        return false;
    }


    /**
     *
     * @param character
     * @return
     */
    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    /**
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        //A
        if(((screenX < Gdx.graphics.getWidth()/10.7) && (screenX > Gdx.graphics.getWidth()/32)) && ((screenY < Gdx.graphics.getHeight()/6.3) && (screenY > Gdx.graphics.getHeight()/14)))    //A Button
        {
            game.inputUpdateX(-0.6f);
        }
        //D
        else if(((screenX < Gdx.graphics.getWidth()/4.6) && (screenX > Gdx.graphics.getWidth()/6.4)) && ((screenY < Gdx.graphics.getHeight()/6.3) && (screenY > Gdx.graphics.getHeight()/14)))    //D Button
        {
            game.inputUpdateX(0.6f);
        }
        //W
        else if(((screenX < Gdx.graphics.getWidth()/6.4) && (screenX > Gdx.graphics.getWidth()/10.7)) && ((screenY < Gdx.graphics.getHeight()/4.9) && (screenY > Gdx.graphics.getHeight()/7.9)))    //W Button
        {
            game.inputUpdateY(0.6f);
        }
        //S
        else if(((screenX < Gdx.graphics.getWidth()/6.4) && (screenX > Gdx.graphics.getWidth()/10.7)) && ((screenY < Gdx.graphics.getHeight()/10.6) && (screenY > Gdx.graphics.getHeight()/60)))    //S Button
        {
            game.inputUpdateY(-0.6f);
        }
        return false;
    }


    /**
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        //A
        if(((screenX < Gdx.graphics.getWidth()/10.7) && (screenX > Gdx.graphics.getWidth()/32)) && ((screenY < Gdx.graphics.getHeight()/6.3) && (screenY > Gdx.graphics.getHeight()/14)))    //A Button
        {
            game.inputUpdateX(0.6f);
        }
        //D
        else if(((screenX < Gdx.graphics.getWidth()/4.6) && (screenX > Gdx.graphics.getWidth()/6.4)) && ((screenY < Gdx.graphics.getHeight()/6.3) && (screenY > Gdx.graphics.getHeight()/14)))    //D Button
        {
            game.inputUpdateX(-0.6f);
        }
        //W
        else if(((screenX < Gdx.graphics.getWidth()/6.4) && (screenX > Gdx.graphics.getWidth()/10.7)) && ((screenY < Gdx.graphics.getHeight()/4.9) && (screenY > Gdx.graphics.getHeight()/7.9)))    //W Button
        {
            game.inputUpdateY(-0.6f);
        }
        //S
        else if(((screenX < Gdx.graphics.getWidth()/6.4) && (screenX > Gdx.graphics.getWidth()/10.7)) && ((screenY < Gdx.graphics.getHeight()/10.6) && (screenY > Gdx.graphics.getHeight()/60)))    //S Button
        {
            game.inputUpdateY(0.6f);
        }
        return false;
    }

    /**
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @return
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }


    /**
     *
     * @param screenX
     * @param screenY
     * @return
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }


    /**
     *
     * @param amount
     * @return
     */
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

/*
    public void inputUpdate(float delta) { //converter isto
        float horizontalForce=0;
        float verticalForce=0;

        if(Gdx.input.isKeyPressed(Input.Keys.A)){horizontalForce-=0.6f;}
        if(Gdx.input.isKeyPressed(Input.Keys.D)){horizontalForce+=0.6f;}
        if(Gdx.input.isKeyPressed(Input.Keys.W)){verticalForce+=0.6f;}
        if(Gdx.input.isKeyPressed(Input.Keys.S)){verticalForce-=0.6f;}

        player.setLinearVelocity(horizontalForce*5,verticalForce*5);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
    }
*/
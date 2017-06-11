package com.mygdx.game.input;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.states.PlayState;

public class DesktopInputProcessor implements InputProcessor {

    private PlayState game;

    public DesktopInputProcessor(PlayState game){this.game=game;}

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode)
        {
            case Input.Keys.A:
                game.inputUpdate(-0.6f,0);
                break;
            case Input.Keys.D:
                game.inputUpdate(0.6f,0);
                break;
            case Input.Keys.W:
                game.inputUpdate(0,0.6f);
                break;
            case Input.Keys.S:
                game.inputUpdate(0,-0.6f);
                break;
            /*case Input.Keys.SPACE:
                game.inputUpdate(0.6f,0);
                break;*/

        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode)
        {
            case Input.Keys.A:
                game.inputUpdate(0,0);

                break;
            case Input.Keys.D:
                game.inputUpdate(0,0);
                break;
            case Input.Keys.W:
                game.inputUpdate(0,0);
                break;
            case Input.Keys.S:
                game.inputUpdate(0,0);
                break;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

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
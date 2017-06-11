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
                game.inputUpdateX(-0.6f);
                break;
            case Input.Keys.D:
                game.inputUpdateX(0.6f);

                break;
            case Input.Keys.W:
                game.inputUpdateY(0.6f);

                break;
            case Input.Keys.S:
                game.inputUpdateY(-0.6f);

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
                game.inputUpdateX(0.6f);
                break;
            case Input.Keys.D:
                game.inputUpdateX(-0.6f);
                break;
            case Input.Keys.W:
                game.inputUpdateY(-0.6f);
                break;
            case Input.Keys.S:
                game.inputUpdateY(0.6f);
                break;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if((screenX < Gdx.graphics.getWidth()/16) && (screenY < Gdx.graphics.getHeight()/8))    //A Button
        {
            game.inputUpdateX(-0.6f);
        }
        else if((screenX < Gdx.graphics.getWidth()/5.325) && (screenY < Gdx.graphics.getHeight()/8))    //D Button
        {
            game.inputUpdateX(0.6f);
        }
        else if((screenX < Gdx.graphics.getWidth()/8) && (screenY < Gdx.graphics.getHeight()/6))    //W Button
        {
            game.inputUpdateY(0.6f);
        }
        else if((screenX < Gdx.graphics.getWidth()/8) && (screenY < Gdx.graphics.getHeight()/14))    //S Button
        {
            game.inputUpdateY(-0.6f);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if((screenX < Gdx.graphics.getWidth()/16) && (screenY < Gdx.graphics.getHeight()/8))    //A Button
        {
            game.inputUpdateX(-0.6f);
        }
        else if((screenX < Gdx.graphics.getWidth()/5.325) && (screenY < Gdx.graphics.getHeight()/8))    //D Button
        {
            game.inputUpdateX(0.6f);
        }
        else if((screenX < Gdx.graphics.getWidth()/8) && (screenY < Gdx.graphics.getHeight()/6))    //W Button
        {
            game.inputUpdateY(0.6f);
        }
        else if((screenX < Gdx.graphics.getWidth()/8) && (screenY < Gdx.graphics.getHeight()/14))    //S Button
        {
            game.inputUpdateY(-0.6f);
        }
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
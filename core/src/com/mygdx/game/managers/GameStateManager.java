package com.mygdx.game.managers;

import com.mygdx.game.Main;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.MenuState;
import com.mygdx.game.states.PlayState;
import com.mygdx.game.states.SplashState;

import java.util.Stack;


public class GameStateManager {

    //Application Reference
    private final Main app;

    private Stack<GameState> states;


    public enum State{
        SPLASH,
        MAINMENU,
        PLAY
    }


    /**
     *
     * @param app
     */
    public GameStateManager(final Main app){
        this.app=app;
        this.states=new Stack<GameState>();
        this.setState(State.MAINMENU);
    }


    /**
     *
     * @return
     */
    public Main main(){
        return app;
    }


    /**
     *
     * @param delta
     */
    public void update(float delta){
        states.peek().update(delta);
    }



    public void render(){
        states.peek().render();
    }


    /**
     *
     * @param w
     * @param h
     */
    public void resize(int w, int h){
        states.peek().resize(w,h);
    }


    /**
     *
     * @param state
     */
    public void setState(State state){
        if(states.size()>=1) {
            states.pop().dispose();
        }
        states.push(getState(state));


    }


    /**
     *
     * @param state
     * @return
     */
    private GameState getState(State state){
        switch(state){
            case SPLASH: return new SplashState(this);
            case MAINMENU: return new MenuState(this);
            case PLAY: return new PlayState(this);

        }
        return null;
    }


    /**
     *
     */
    public void dispose(){
        for (GameState gs: states){
            gs.dispose();
        }
    }
}

package com.mygdx.game.gamelogic;



public class GameObject {

    protected int x,y;

    /**
     *
     * @param x
     * @param y
     */
    public GameObject(int x,int y) {
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
}
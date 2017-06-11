package com.mygdx.game.gamelogic;

import com.badlogic.gdx.math.Vector3;

public class DynamicObject extends GameObject {

    protected static final double SPEEDX=75;
    protected boolean alive;
    protected Vector3 position;
    protected Vector3 velocity;

    /**
     *
     * @param x
     * @param y
     */
    public DynamicObject(int x,int y)
    {
        super(x, y);
        alive=true;
    }

    public void getHit(){
        alive=false;
    }
    public boolean isAlive(){
        return alive;
    }
    public void dispose(){ }
    public void update(float dt){}

//    public void move(float dt){
//        position.add(velocity.x*dt,velocity.y*dt,0);
//    }
//    /* ****************************** */
//    public void moveLeft(){
//        velocity.x-=SPEEDX;
//    }
//    public void moveRight(){
//        velocity.x+=SPEEDX;
//    }
//    public void moveUp(){
//        velocity.y+=SPEEDX;
//    }
//    public void moveDown(){ //1
//        velocity.y-=SPEEDX;
//    }
//    /* ****************************** */
//    public void stopMoveDown(){velocity.y+=SPEEDX;}
//    public void stopMoveLeft(){
//        velocity.x+=SPEEDX;
//    }
//    public void stopMoveRight(){
//        velocity.x-=SPEEDX;
//    }
//    public void stopMoveUp(){velocity.y-=SPEEDX;    }
    /* ****************************** */


}
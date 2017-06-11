package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.input.DesktopInputProcessor;
import com.mygdx.game.managers.GameStateManager;
import com.mygdx.game.utils.TiledObjectUtil;

import static com.mygdx.game.utils.Constants.*;

public class PlayState extends GameState{

    private OrthogonalTiledMapRenderer tmr,tmr2;
    private TiledMap map,map2;

    private Box2DDebugRenderer b2dr;
    private World world;
    private Body player;
    private Body ogre;

    private Boolean running=true;
    private int lastdirection=2; //a-0 s-1 d-2 w-3
    private int movingDir;


    private double ogreTexTime=0;
    private double playerTexTime=0;
    private float inputUpdtX=0, inputUpdtY=0;

    private Texture playerTex;
    private Texture hero_down1;
    private Texture hero_down2;
    private Texture hero_down3;
    private Texture hero_up1;
    private Texture hero_up2;
    private Texture hero_up3;
    private Texture hero_left1;
    private Texture hero_left2;
    private Texture hero_left3;
    private Texture hero_right1;
    private Texture hero_right2;
    private Texture hero_right3;

    private Texture ogreTex;
    private Texture ogreTex1;
    private Texture ogreTex2;
    private Texture arrowleft;
    private Texture arrowright;
    private Texture arrowup;
    private Texture arrowdown;


    float ogreInputUpdtX=0;
    float ogreInputUpdtY=0;


    /**
     *
     * @param gsm
     */
    public PlayState(GameStateManager gsm) {
        super(gsm);
        world= new World(new Vector2(0,0),false); //gravidade
		b2dr=new Box2DDebugRenderer();

		//player=createBox(240,1328,16,16,false);
        player=createBox(900,1072,16,16,false);
        ogre=createBox(1120,1072,16,16,false);
		//platform=createBox(0,0,64,32,true); //isstatic ultimo

		batch=new SpriteBatch();
		playerTex=new Texture("Images/hero_down1.png");
        hero_down1=new Texture("Images/hero_down1.png");
        hero_down2=new Texture("Images/hero_down2.png");
        hero_down3=new Texture("Images/hero_down3.png");
        hero_up1=new Texture("Images/hero_up1.png");
        hero_up2=new Texture("Images/hero_up2.png");
        hero_up3=new Texture("Images/hero_up3.png");
        hero_right1=new Texture("Images/hero_right1.png");
        hero_right2=new Texture("Images/hero_right2.png");
        hero_right3=new Texture("Images/hero_right3.png");
        hero_left1=new Texture("Images/hero_left1.png");
        hero_left2=new Texture("Images/hero_left2.png");
        hero_left3=new Texture("Images/hero_left3.png");

        ogreTex =new Texture("Images/ogre_down.png");
        ogreTex1 =new Texture("Images/ogre_down.png");
        ogreTex2 =new Texture("Images/ogre_up.png");

        arrowleft=new Texture("Images/arrow_left.png");
        arrowright=new Texture("Images/arrow_right.png");
        arrowup=new Texture("Images/arrow_up.png");
        arrowdown=new Texture("Images/arrow_down.png");

		map= new TmxMapLoader().load("Maps/FinalMap.tmx");
        map2= new TmxMapLoader().load("Maps/FinalMap2.tmx");

        tmr= new OrthogonalTiledMapRenderer(map);
        tmr2= new OrthogonalTiledMapRenderer(map2);

        TiledObjectUtil.parseTiledObjectLayer(world,map.getLayers().get("SOLIDLAYER").getObjects());

        DesktopInputProcessor proc = new DesktopInputProcessor(this);
        Gdx.input.setInputProcessor(proc);

        createCollisionListener();
    }


    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param isStatic
     * @return
     */
    public Body createBox(int x, int y,int width, int height, boolean isStatic) { //definir no mundo o player?
        Body pBody;
        BodyDef def = new BodyDef();

        if(isStatic) {def.type= BodyDef.BodyType.StaticBody;}
        else{
            def.type= BodyDef.BodyType.DynamicBody;}

        def.position.set(x/PPM,y/PPM);
        def.fixedRotation=true; //impede rotaçao ao atingir objetos;
        pBody=world.createBody(def);

        PolygonShape shape=new PolygonShape();
        shape.setAsBox(width/2/PPM,height/2/PPM);//tamanho caixa, 32=64

        pBody.createFixture(shape,1.0f);
        shape.dispose();

        return pBody;
    }



    public void createCollisionListener(){
        world.setContactListener(new ContactListener() {

            /**
             *
             * @param contact
             */
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
               // Gdx.app.log("beginContact", "between " + fixtureA.toString() + " and " + fixtureB.toString());
                if(fixtureA.getBody()==player && fixtureB.getBody()==ogre) {
                    running=false;
                }
            }

            /**
             *
             * @param contact
             */
            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
               // Gdx.app.log("endContact", "between " + fixtureA.toString() + " and " + fixtureB.toString());
            }

            /**
             *
             * @param contact
             * @param oldManifold
             */
            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }


            /**
             *
             * @param contact
             * @param impulse
             */
            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }


    /**
     *
     * @param delta
     */
    @Override
    public void update(float delta) {

        gameCheck();
		world.step(1/60f,6,2);
		cameraUpdate();
		tmr.setView(camera);
        tmr2.setView(camera);
		batch.setProjectionMatrix(camera.combined);
        inputUpdate();
        heroUpdate(delta);
        ogreUpdate(delta);

    }


    private void gameCheck() {
        if(running==false)
        {
            gsm.setState((GameStateManager.State.MAINMENU));
            System.out.println("gg");

        }
    }

    /**
     *
     * @param delta
     */
    private void ogreUpdate(float delta) {
        ogreCheck();
        ogreSpeedUpdate(delta);
    }




    public void cameraUpdate() {
        //a+(b-a) * lerp
        //b=target; a=current camera position
        Vector3 position = camera.position;
        position.x=camera.position.x+(player.getPosition().x*PPM-camera.position.x)*.13f;
        position.y=camera.position.y+(player.getPosition().y*PPM-camera.position.y)*.13f;
        camera.position.set(position);
        camera.update();
    }


    public void inputUpdate() { //converter isto
        player.setLinearVelocity(inputUpdtX*5,inputUpdtY*5);
    }
    public void inputUpdateY(float y)
    {
        inputUpdtY+=y;
    }
    public void inputUpdateX(float x)
    {
        inputUpdtX+=x;
    } //0.6f


    /**
     *
     * @param delta
     */
    private void ogreSpeedUpdate(float delta) {

        ogre.setLinearVelocity(ogreInputUpdtX,ogreInputUpdtY);
        if((ogreInputUpdtY!=0 )|| (ogreInputUpdtY!=0))
            ogreChangeAppearance(delta);
        }



    private void ogreCheck() {
        if(player.getPosition().x>30)
            ogreChase();
        else
            ogreReturn();

    }



    private void ogreReturn() {
        if(ogre.getPosition().x>targetX1 && ogre.getPosition().x<targetX2)
        {
            ogreInputUpdtX=0f;
        }
        else if((ogre.getPosition().x>targetX1))
        {
            ogreInputUpdtX= -0.5f;
        }
        else if(ogre.getPosition().x<targetX1) {

            ogreInputUpdtX=0.5f;
        }
        else ogreInputUpdtX=0f;

        if(ogre.getPosition().y>targetY1 && ogre.getPosition().y<targetY2)
        {
            ogreInputUpdtY=0;
        }
        else if(ogre.getPosition().y>targetY1)
        {
            ogreInputUpdtY= -0.5f;
        }
        else if(ogre.getPosition().y<targetY2) {
            ogreInputUpdtY=0.5f;
        }
        else ogreInputUpdtY=0f;
    }




    private void ogreChase() {

        float targetX=player.getPosition().x;
        float targetY=player.getPosition().y;

        if(ogre.getPosition().x>targetX)
        {
             ogreInputUpdtX= -0.9f;
        }
        else if(ogre.getPosition().x<targetX) {

            ogreInputUpdtX=0.9f;
        }
        else ogreInputUpdtX=0f;

        if(ogre.getPosition().y>targetY)
        {
            ogreInputUpdtY= -0.9f;
        }
        else if(ogre.getPosition().y<targetY) {
            ogreInputUpdtY=0.9f;
        }
        else ogreInputUpdtY=0f;
    }


    /**
     *
     * @param delta
     */
    private void ogreChangeAppearance(float delta) {
        ogreTexTime+=delta;
        if (ogreTexTime>0.3)
        {
            ogreTexTime=0;
        }
        if(ogreTexTime>0.15) {
            ogreTex = ogreTex1;
        }
        else
            ogreTex = ogreTex2;

    }

    public void notMoving(int dir){lastdirection=dir;}

    private void heroUpdate(float delta) {
        playerTexTime+=delta;
        if (playerTexTime>0.4)
        {playerTexTime=0;}
        Vector2 vec=player.getLinearVelocity();
        System.out.println(vec.x+" "+vec.y);

        if(vec.x==0.0&&vec.y==0.0) {

            switch (lastdirection) {
                case 0:
                    playerTex = hero_left1;
                    break;
                case 1:
                    playerTex = hero_down1;
                    break;
                case 2:
                    playerTex = hero_right1;
                    break;
                case 3:
                    playerTex = hero_up1;
                    break;
            }
        }
        else {

            if(vec.x>0){movingDir=2;}
            if(vec.x<0){movingDir=0;}
            if(vec.y>0){movingDir=3;}
            if(vec.y<0){movingDir=1;}

            switch (movingDir) {
                case 0:
                    if (playerTexTime > 0.2)
                    {
                        playerTex = hero_left2;
                     }
                    else {playerTex=hero_left1;
                    }
                    break;
                case 1:
                    if(playerTexTime>0.2)
                        playerTex = hero_down2;
                    else
                        playerTex=hero_down3;
                    break;
                case 2:
                    if(playerTexTime>0.2)
                        playerTex = hero_right2;
                    else playerTex=hero_right1;
                    break;
                case 3:
                    if(playerTexTime>0.2)
                        playerTex = hero_up2;
                    else
                        playerTex=hero_up3;
                    break;
            }
        }
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        b2dr.render(world,camera.combined.scl(PPM));
        tmr.render();
        batch.begin();  //nao meter gamelogic aqui
        batch.draw(playerTex,player.getPosition().x*PPM-(playerTex.getWidth()/2),player.getPosition().y*PPM-(playerTex.getHeight()/3));
//        batch.draw(arrowleft,camera.position.x-(V_WIDTH/4),camera.position.y-(V_HEIGHT/4));
//        batch.draw(arrowright,camera.position.x-(V_WIDTH/4)+(arrowright.getWidth()*2),camera.position.y-(V_HEIGHT/4));
//        batch.draw(arrowdown,camera.position.x-(V_WIDTH/4)+(arrowright.getWidth()),camera.position.y-(V_HEIGHT/4));
//        batch.draw(arrowup,camera.position.x-(V_WIDTH/4)+(arrowright.getWidth()),camera.position.y-(V_HEIGHT/4)+(arrowup.getHeight()));
        batch.draw(ogreTex,ogre.getPosition().x*PPM-(ogreTex.getWidth()/2),ogre.getPosition().y*PPM-(ogreTex.getHeight()/2));
        batch.end();
        tmr2.render();

    }
    public void handleInput(){}
    public Camera getCamera(){
        return camera;
    }


    @Override
    public void dispose() {

        b2dr.dispose();
        world.dispose();
        batch.dispose();
        tmr.dispose();
        tmr2.dispose();
        map.dispose();
        map2.dispose();

    }



}

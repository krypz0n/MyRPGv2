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
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.input.DesktopInputProcessor;
import com.mygdx.game.managers.GameStateManager;
import com.mygdx.game.utils.TiledObjectUtil;

import static com.mygdx.game.utils.Constants.*;

public class PlayState extends GameState{

    private OrthogonalTiledMapRenderer tmr;
    private TiledMap map;

    private Box2DDebugRenderer b2dr;
    private World world;
    private Body player;
    private Body ogre;

    private float inputUpdtX=0, inputUpdtY=0;

    private Texture playerTex;
    private Texture ogreTex;
    private Texture arrowleft;
    private Texture arrowright;
    private Texture arrowup;
    private Texture arrowdown;

    float ogreInputUpdtX=0;
    float ogreInputUpdtY=0;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        world= new World(new Vector2(0,0),false); //gravidade
		b2dr=new Box2DDebugRenderer();

		player=createBox(240,1328,16,16,false);
        ogre=createBox(1120,1072,16,16,false);
		//platform=createBox(0,0,64,32,true); //isstatic ultimo

		batch=new SpriteBatch();
		playerTex=new Texture("Images/hero_down1.png");
        ogreTex =new Texture("Images/ogre_down.png");

        arrowleft=new Texture("Images/arrow_left.png");
        arrowright=new Texture("Images/arrow_right.png");
        arrowup=new Texture("Images/arrow_up.png");
        arrowdown=new Texture("Images/arrow_down.png");


		map= new TmxMapLoader().load("Maps/FinalMap.tmx");
		tmr= new OrthogonalTiledMapRenderer(map);

		TiledObjectUtil.parseTiledObjectLayer(world,map.getLayers().get("SOLIDLAYER").getObjects());

        DesktopInputProcessor proc = new DesktopInputProcessor(this);
        Gdx.input.setInputProcessor(proc);
    }

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

    @Override
    public void update(float delta) {

		world.step(1/60f,6,2);

		cameraUpdate();
		tmr.setView(camera);
		batch.setProjectionMatrix(camera.combined);
        inputUpdate();
        heroUpdate();
        ogreCheck();
        ogreUpdate();
        System.out.println("x: "+ogre.getPosition().x+" y: "+ogre.getPosition().y);

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

    private void ogreUpdate() {
        ogre.setLinearVelocity(ogreInputUpdtX,ogreInputUpdtY);
        }

    private void ogreCheck() {
        if(player.getPosition().x>30)
            ogreChase();
        else
            ogreReturn();

    }

    private void ogreReturn() {
        double targetX1=35;
        double targetX2=35.2;
        double targetY1=33.5;
        double targetY2=33.7;
        System.out.println("bang");
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
             ogreInputUpdtX= -0.7f;
        }
        else if(ogre.getPosition().x<targetX) {

            ogreInputUpdtX=0.7f;
        }
        else ogreInputUpdtX=0f;

        if(ogre.getPosition().y>targetY)
        {
            ogreInputUpdtY= -0.7f;
        }
        else if(ogre.getPosition().y<targetY) {
            ogreInputUpdtY=0.7f;
        }
        else ogreInputUpdtY=0f;
    }


    private void heroUpdate() {
//TEXTURAS
    }
    @Override
    public void render() {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        b2dr.render(world,camera.combined.scl(PPM));
        //tmr.render();
        batch.begin();  //nao meter gamelogic aqui
        batch.draw(playerTex,player.getPosition().x*PPM-(playerTex.getWidth()/2),player.getPosition().y*PPM-(playerTex.getHeight()/3));
//        batch.draw(arrowleft,camera.position.x-(V_WIDTH/4),camera.position.y-(V_HEIGHT/4));
//        batch.draw(arrowright,camera.position.x-(V_WIDTH/4)+(arrowright.getWidth()*2),camera.position.y-(V_HEIGHT/4));
//        batch.draw(arrowdown,camera.position.x-(V_WIDTH/4)+(arrowright.getWidth()),camera.position.y-(V_HEIGHT/4));
//        batch.draw(arrowup,camera.position.x-(V_WIDTH/4)+(arrowright.getWidth()),camera.position.y-(V_HEIGHT/4)+(arrowup.getHeight()));
        batch.draw(ogreTex,ogre.getPosition().x*PPM-(ogreTex.getWidth()/2),ogre.getPosition().y*PPM-(ogreTex.getHeight()/2));
        batch.end();
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
        map.dispose();
    }



}

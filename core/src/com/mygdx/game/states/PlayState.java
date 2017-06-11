package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

import static com.mygdx.game.utils.Constants.PPM;

public class PlayState extends GameState{

    private OrthogonalTiledMapRenderer tmr;
    private TiledMap map;

    private Box2DDebugRenderer b2dr;
    private World world;
    private Body player;

    private float inputUpdtX=0, inputUpdtY=0;

    private Texture tex;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        world= new World(new Vector2(0,0),false); //gravidade
		b2dr=new Box2DDebugRenderer();

		player=createBox(240,1328,20,20,false);
		//platform=createBox(0,0,64,32,true); //isstatic ultimo

		batch=new SpriteBatch();
		tex=new Texture("Images/hero_down1.png");


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
    public void inputUpdateY(float y) {
        inputUpdtY+=y;

    }
    public void inputUpdateX(float x) {
        inputUpdtX+=x;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        b2dr.render(world,camera.combined.scl(PPM));
        tmr.render();
        batch.begin();  //nao meter gamelogic aqui
        batch.draw(tex,player.getPosition().x*PPM-(tex.getWidth()/2),player.getPosition().y*PPM-(tex.getHeight()/3));
        batch.end();
    }
    public void handleInput(){}

    @Override
    public void dispose() {

        b2dr.dispose();
        world.dispose();
        batch.dispose();
        tmr.dispose();
        map.dispose();
    }



}

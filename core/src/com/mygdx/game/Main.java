package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.managers.GameStateManager;
import com.mygdx.game.utils.TiledObjectUtil;

import static com.mygdx.game.utils.Constants.*;

public class Main extends ApplicationAdapter {

	private Boolean DEBUG = false;

	private final float SCALE= 2.0f;

	private SpriteBatch batch;

	private GameStateManager gsm;

	private OrthographicCamera camera;

	@Override
	public void create () {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();


		camera= new OrthographicCamera();
		camera.setToOrtho(false,w/2/SCALE,h/2/SCALE);

		gsm=new GameStateManager(this);


	}
	@Override
	public void resize(int width, int height) {

		gsm.resize((int)(width/SCALE),(int)(height/SCALE));
		/*camera.setToOrtho(false,width/SCALE,height/SCALE);*/
	}

	@Override
	public void render () {
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();

	}

	public OrthographicCamera getCamera(){
		return camera;
	}
	public SpriteBatch getSpriteBatch(){
		return batch;
	}
	@Override
	public void dispose () {

		gsm.dispose();
		//batch.dispose();

	}
}

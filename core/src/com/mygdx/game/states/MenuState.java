package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Main;
import com.mygdx.game.managers.GameStateManager;
import com.mygdx.game.utils.Constants;
import com.sun.corba.se.impl.orbutil.closure.Constant;

public class MenuState extends GameState{
    private Texture background;
    private Texture playbutton;
    private Texture optionsbutton;
    private Texture logo;
    private SpriteBatch batch;


    //butoes
    private Stage stage;
    private Drawable drawable;
    private ImageButton playButton;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        background=new Texture("Images/landscape.png");
        playbutton=new Texture("Images/playbutton.png");
        optionsbutton=new Texture("Images/optionsbutton.png");
        logo=new Texture("Images/logo.png");
        batch = new SpriteBatch();


        drawable = new TextureRegionDrawable(new TextureRegion(playbutton));
        playButton = new ImageButton(drawable);
        stage=new Stage(new ScreenViewport());
        stage.addActor(playButton);
        playButton.setPosition(66 ,130);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void handleInput() {
        if(playButton.isPressed()){
            gsm.setState((GameStateManager.State.SPLASH));
            dispose();
        }
    }

    @Override
    public void resize(int w, int h) {
        super.resize(w, h);
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(background,0,0, Constants.V_WIDTH, Constants.V_HEIGHT);
        batch.draw(logo, 27,210);
        batch.draw(optionsbutton, 66,40);

        batch.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        playbutton.dispose();
        optionsbutton.dispose();
        logo.dispose();
    }
}

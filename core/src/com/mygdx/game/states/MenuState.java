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
import static com.mygdx.game.utils.Constants.*;

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
        playButton.setPosition((V_WIDTH/2)-(playButton.getWidth()/2) ,130);
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
       // batch.draw(background,0,0, 240, 240);
        batch.draw(logo, (V_WIDTH/2)-(logo.getWidth()/2),210);
        batch.draw(optionsbutton, (V_WIDTH/2)-(optionsbutton.getWidth()/2),40);

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

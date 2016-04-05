package com.qar.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.qar.webcam.WebCam;
import com.qar.webcam.WebCam.Resolation;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	WebCam cam ;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		cam = new WebCam(0);
	}
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		cam.camUpdate();
		
		cam.camDraw(batch,Gdx.graphics.getWidth()-400,Gdx.graphics.getHeight()-300,400,300);
		
		batch.begin();
		batch.draw(img,0,0);
		batch.end();
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		cam.camClose();
	}
}

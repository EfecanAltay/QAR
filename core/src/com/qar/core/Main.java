package com.qar.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.qar.algorithm.FaceDetection;
import com.qar.webcam.WebCam;
import com.qar.webcam.WebCam.Resolation;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	WebCam cam ;
	FaceDetection fd;
	
	int x ,y;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		cam = new WebCam(0);
		
		fd = new FaceDetection(cam.getPath());
	}
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		fd.DetectUpdate();
		
		cam.camUpdate();
		
		cam.camDraw(batch);
		
		if(fd.getFaces().length >0){
			x = fd.getFaces()[0].x;
			y =480-fd.getFaces()[0].y -fd.getFaces()[0].height;
		}
		batch.begin();
		batch.draw(img,x,y);
		//batch.draw(new Texture("DetectOutPut/Faces/image0.png"),0,0);
		batch.end();
		
		
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		cam.camClose();
	}
}

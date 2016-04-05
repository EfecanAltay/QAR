package com.qar.core;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	VideoCapture camera;
	Mat frame;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
	    camera = new VideoCapture(0);
	    camera.open(0); //Useless
	    if(!camera.isOpened()){
	        System.out.println("Camera Error");
	    }
	    else{
	        System.out.println("Camera OK?");
	    }

	    frame = new Mat();
	    
	    camera.read(frame);
	    Imgcodecs.imwrite("cam.png", frame);
	    //camera.grab();
	    //System.out.println("Frame Grabbed");
	    //camera.retrieve(frame);
	    //System.out.println("Frame Decoded");

	    camera.read(frame);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.read(frame);
		Imgcodecs.imwrite("cam.png", frame);
		img = new Texture(Gdx.files.internal("cam.png"));
		batch.begin();
		if(img != null)
		batch.draw(img, 0, 0);
		batch.end();
	}
}

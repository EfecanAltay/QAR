package com.qar.webcam;

import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WebCam {
	private VideoCapture webcam;
	private Mat camFrame;
	private String path = "WebCam/cam.png";
	private Texture camTexture ;
	
	public Vector2 position ;
	int width=800,height=600;
	Resolation resolation = Resolation.r640x360 ;
	
	Imgcodecs a;
	public enum Resolation{
		r640x360,
		r800x600,
		r1280x800,
		r1366x768
	}
	public String getPath(){
		return path;
	}
	
	public void setResolation(Resolation res){
		resolation = res ;
		switch (resolation) {
		case r640x360:
			width = 640 ;
			height = 360;
			break;
		case r800x600:
			width = 800 ;
			height = 600;
			break;
		case r1280x800:
			width = 1280 ;
			height = 800;
			break;
		case r1366x768:
			width = 1366 ;
			height = 768;
			break;
		default:
			break;
		}
	}
	
	public WebCam(int index) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		// TODO Auto-generated constructor stub
		webcam = new VideoCapture(index);
	
		if(!webcam.isOpened()){
	        System.out.println("Camera Error");
	    }
	    else{
	        System.out.println("Camera OK?");
	    }
		camFrame = new Mat();
		File file = new File("WebCam/");
		file.mkdir();
		webcam.read(camFrame);
	    Imgcodecs.imwrite(path, camFrame);
	    
	    position = new Vector2();
	    camTexture = new Texture(path);
	}
	public void camUpdate(){
		webcam.read(camFrame);
	    Imgcodecs.imwrite(path, camFrame);
	    camTexture = new Texture(path);
	}
	public void camDraw(SpriteBatch sb){
		sb.begin();
		sb.draw(camTexture,0,0);
		sb.end();
	}
	public void camDraw(SpriteBatch sb , int posx,int posy){
		sb.begin();
		sb.draw(camTexture,posx,posy,width,height);
		sb.end();
	}
	public void camDraw(SpriteBatch sb , int posx,int posy , int _width,int _height ){
		sb.begin();
		sb.draw(camTexture,posx,posy,_width,_height);
		sb.end();
	}
	public Texture getCamFrame(){
		
		return  camTexture;
	}
	public void Dispose(){
		camTexture.dispose();
	}
	public void camClose(){
		webcam.release();
		Dispose();
	}
}

package com.qar.algorithm;

import java.io.File;
import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;



public class FaceDetection {
	
	Mat image ;
	MatOfRect faces ;
	String path,outputPath = "DetectOutPut/Faces/" ;
	CascadeClassifier faceDetector;
	ShapeRenderer shr;
	
	Rect lastRect0,lastRect1;
	public FaceDetection(String path) {
		// TODO Auto-generated constructor stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		System.out.println("\nRunning FaceDetector");
		
		this.path = path; 
		File file = new File(outputPath);
		file.mkdirs();
		
		File currentDirFile = new File("");
		
		System.out.println(currentDirFile.getAbsoluteFile().getParentFile().getParentFile().getPath());
		
		faceDetector = new CascadeClassifier(currentDirFile.getAbsoluteFile().getParentFile().getParentFile().getPath() + "/core/res/haarcascades/haarcascade_frontalface_alt.xml");
		
		image = Imgcodecs.imread(path);
		faces = new MatOfRect();
		
		faceDetector.detectMultiScale(image, faces);
		if(faces.toArray().length > 0){
			lastRect0 = faces.toArray()[0];
			lastRect1 = faces.toArray()[0];
		}
		
		System.out.println("Taranan Yüz Sayýsý: " + faces.toArray().length);
	}
	public Rect[] getFaces(){
		
		return faces.toArray();
	}
	public void DetectUpdate(){
		image = Imgcodecs.imread(path);
		if(!image.empty()){
			faces = new MatOfRect();
			faceDetector.detectMultiScale(image, faces);
			if(faces.toArray().length > 0){
			DrawDebugRect();
			if(lastRect0 == null)
				{
					lastRect0 = faces.toArray()[0];
				}
				else{
					faces.toArray()[0] = new Rect((lastRect0.x + faces.toArray()[0].x)/2, (lastRect0.y + faces.toArray()[0].y)/2, (lastRect0.width + faces.toArray()[0].width)/2, (lastRect0.height + faces.toArray()[0].height)/2);
					if(lastRect1 == null)
					{
						lastRect1 = faces.toArray()[0];
					}else{
						faces.toArray()[0] = new Rect((lastRect1.x + faces.toArray()[0].x)/2, (lastRect1.y + faces.toArray()[0].y)/2, (lastRect1.width + faces.toArray()[0].width)/2, (lastRect1.height + faces.toArray()[0].height)/2);
					}
				}
			}
		}
	}
	private void DrawDebugRect(){
		for (Rect rect : faces.toArray()) {
			Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
	                    new Scalar(0, 255, 0));
			
		    Imgcodecs.imwrite(outputPath + "image0.png", image);
		    
		}	 
	}
	
}

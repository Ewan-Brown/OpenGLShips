package openglships.main;

import java.awt.Point;
import java.awt.geom.Point2D;

public class Shapes {
	
	static float[] rectangle = new float[8];
	static Point2D rectangleCenter;
	public static void init(){
		rectangle[0] = -0.25f;
		rectangle[1] = -0.25f;
		rectangle[2] = 0.25f;
		rectangle[3] = -0.25f;
		rectangle[4] = 0.25f;
		rectangle[5] = 0.25f;
		rectangle[6] = -0.25f;
		rectangle[7] = 0.25f;
		Point2D[] p = new Point2D[4];
		for(int i = 0; i < 4;i++){
			p[i] = new Point2D.Float(rectangle[i*2], rectangle[i*2 + 1]);
		}
		rectangleCenter = Data.getCenter(p);
	}
	
}

package openglships.main;

import java.awt.Point;
import java.awt.geom.Point2D;

public class Data {

	public static float getDistance(float x1, float x2, float y1, float y2){
		return (float)Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));

	}
	public static float getMod(float x, float y){
		return (float)Math.sqrt((x)*(x) + (y)*(y));

	}
	public static Point2D getCenter(Point2D[] points){
		double centreX = 0;
		double centreY = 0;
		double signedArea = 0.0;
		double x0 = 0.0; // Current vertex X
		double y0 = 0.0; // Current vertex Y
		double x1 = 0.0; // Next vertex X
		double y1 = 0.0; // Next vertex Y
		double a = 0.0;  // Partial signed area

		// For all points except last
		int i=0;
		for (i=0; i<points.length-1; ++i)
		{
			x0 = points[i].getX();
			y0 = points[i].getY();
			x1 = points[i+1].getX();
			y1 = points[i+1].getY();
			a = x0*y1 - x1*y0;
			signedArea += a;
			centreX += (x0 + x1)*a;
			centreY += (y0 + y1)*a;
		}

		// Do last vertex separately to avoid performing an expensive
		// modulus operation in each iteration.
		x0 = points[i].getX();
		y0 = points[i].getY();
		x1 = points[0].getX();
		y1 = points[0].getY();
		a = x0*y1 - x1*y0;
		signedArea += a;
		centreX += (x0 + x1)*a;
		centreY += (y0 + y1)*a;

		signedArea *= 0.5;
		centreX /= (6.0*signedArea);
		centreY /= (6.0*signedArea);
		return new Point2D.Double(centreX, centreY);

	}
	
}

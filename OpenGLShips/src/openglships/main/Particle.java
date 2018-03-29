package openglships.main;

public class Particle extends PhysicalObject{
	
	
	private Particle(float x, float y, float angle, float scale) {
		super(x, y, angle);
	}

	public static Particle createParticle(float x, float y, float angle, float scale){
		Particle p = new Particle(x,y,angle,scale){
			{
				xCenter = (float) Shapes.rectangleCenter.getX();
				yCenter = (float) Shapes.rectangleCenter.getY();
			}
			@Override
			public float[] getVertices() {
				return Shapes.rectangle;
			}
		};
		return p;
	}
	

}

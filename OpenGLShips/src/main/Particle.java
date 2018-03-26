package main;

public class Particle extends Movable{
	
	
	private Particle(float x, float y, float angle, float scale) {
		super(x, y, angle);
		this.scale = scale;
	}

	public static Particle createParticle(float x, float y, float angle, float scale){
		Particle p = new Particle(x,y,angle,scale){
			@Override
			public float[] getVertices() {
				return Shapes.rectangle;
			}
		};
		return p;
	}

}

package openglships.main;

import openglships.modules.Thruster;

public class Particle extends Movable{
	
	
	private Particle(float x, float y, float angle, float scale) {
		super(x, y, angle);
		this.scale = scale;
	}

	public static Particle createParticle(float x, float y, float angle, float scale){
		Particle p = new Particle(x,y,angle,scale){
			{
				xCenter = (float) Shapes.rectangleCenter.getX();
				yCenter = (float) Shapes.rectangleCenter.getY();
				rSquared = 0.25f*0.25f;
			}
			@Override
			public float[] getVertices() {
				return Shapes.rectangle;
			}
		};
		p.t = new Thruster(0.0f,0.0f,0, p.xCenter, p.yCenter);
		p.applyThrust();
		return p;
	}
	Thruster t;
	public void applyThrust(){
		float T = t.throttle;
		float x = (float)(T*Math.cos(Math.toRadians(angle)));
		float y = (float)(T*Math.sin(Math.toRadians(angle)));
		float alpha  = T * t.bMod * (float)Math.sin(theta)/ rSquared;
		System.out.println(t.bMod);
		turnSpeed += Math.toDegrees(alpha);
		xSpeed += x;
		ySpeed += y;
	}

}

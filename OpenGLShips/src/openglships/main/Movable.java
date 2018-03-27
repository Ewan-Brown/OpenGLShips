package openglships.main;

public class Movable extends Drawable{
	
	public Movable(float x, float y, float angle) {
		super(x, y, angle);
		// TODO Auto-generated constructor stub
	}
	float xCenter;
	float yCenter;
	float rSquared;
	float xSpeed = 0;
	float ySpeed = 0;
	float turnSpeed = 0;
	public void applySpeed(float xf, float yf){
		xSpeed += xf;
		ySpeed += yf;
	}
	public void applyTurnSpeed(float a){
		turnSpeed += a;
	}
	public void update(){
		x += xSpeed;
		y += ySpeed;
		angle += turnSpeed;
		xSpeed -= xSpeed / 100;
		ySpeed -= ySpeed / 100;
		turnSpeed -= turnSpeed / 100;
	}

}

package openglships.main;

import openglships.graphics.Drawable;

public class Movable implements Drawable{
	public Movable(float x, float y, float angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	public float angle = 0;
	public float x = 0;
	public float y = 0;
	float xCenter;
	float yCenter;
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
	}
	@Override
	public float getX() {
		return x;
	}
	@Override
	public float getY() {
		return y;
	}
	@Override
	public float getAngle() {
		return angle;
	}
	@Override
	public float getScale() {
		return scale;
	}
	@Override
	public float[] getVertices() {
		return null;
	}
	@Override
	public float[] getColors() {
		return null;
	}
	@Override
	public Drawable[] getSubDrawables() {
		return null;
	}

}

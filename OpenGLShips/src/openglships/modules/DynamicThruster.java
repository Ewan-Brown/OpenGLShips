package openglships.modules;

import org.joml.Vector2f;

import openglships.graphics.Drawable;
import openglships.main.BasicShip;

public class DynamicThruster implements Drawable{

	//Object-specific finals
	public float thrustMultiplier = 0.001f;
	public float baseAngle = 0;
	Vector2f position;
	
	//VTOL 
	public float currentAngle = 0;
	float targetAngle = 0;
	float turningSpeed = 0;
	
	BasicShip parent;
	
	public DynamicThruster(float thrusterX, float thrusterY, float baseAngle, BasicShip p){
		position = new Vector2f(thrusterX,thrusterY);
		this.baseAngle = baseAngle;
		this.parent = p;
	}
	public Vector2f getForce(){
		return new Vector2f(thrustMultiplier * (float)Math.cos(baseAngle),thrustMultiplier * (float)Math.sin(baseAngle));
	}
	//TODO Use Mass of ship (inverse relationship)
	public float getTurningAcceleration(){
		Vector2f force = getForce();
		return (float) Math.sin(position.angle(getForce())) * position.length() * force.length();
	}
	public Vector2f getAppliedForce(){
		return new Vector2f(thrustMultiplier * (float)Math.cos(currentAngle * parent.angle),thrustMultiplier * (float)Math.sin(currentAngle * parent.angle));
	}
	@Override
	public float getX() {
		return 0;
	}

	@Override
	public float getY() {
		return 0;
	}

	@Override
	public float getAngle() {
		return 0;
	}

	@Override
	public float getScale() {
		return 0;
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

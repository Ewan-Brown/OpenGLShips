package openglships.modules;

import org.joml.Math;
import org.joml.Vector2f;

import openglships.graphics.Drawable;
import openglships.main.BasicShip;
import openglships.main.Shapes;


//TODO add throttle accelleration so that the thrust doesn't go from on to off instantly, make like KSP engines where they take a second to slow down and turn on again. 
public class DynamicThruster implements Drawable{

	//Object-specific finals
	public float thrustMultiplier = 0.001f;
	public float baseAngle = 0;
	Vector2f position;
	public float rSquared;
	
	//VTOL 
	public float currentAngle = 0;
	float VTOLtargetAngle = 0;
	float turningSpeed = 1;
	float turningThreshold = 2;
	
	BasicShip parent;
	
	public DynamicThruster(float thrusterX, float thrusterY, float baseAngle, BasicShip p, float rSquared){
		position = new Vector2f(thrusterX,thrusterY);
		this.baseAngle = baseAngle; 
		this.parent = p;
		this.rSquared = rSquared;
	}
	public Vector2f getForce(){
		return new Vector2f(thrustMultiplier * (float)Math.cos(baseAngle),thrustMultiplier * (float)Math.sin(baseAngle));
	}
	//TODO Use Mass of ship (inverse relationship)
	public float getTurningAcceleration(){
		Vector2f force = getForce();
		return (float) Math.sin(position.angle(getForce())) * position.length() * force.length() / rSquared;
	}
	public Vector2f getAppliedForce(){
		return new Vector2f(thrustMultiplier * (float)Math.cos(currentAngle * parent.angle),thrustMultiplier * (float)Math.sin(currentAngle * parent.angle));
	}
	public void setTarget(float angle){
		this.VTOLtargetAngle = angle;
	}
	//XXX REMOVEME And use set target + PID
	public void setCurrentAngle(float angle){
		this.currentAngle = angle;
	}
	public void update(){
//		if(Math.abs(target - currentAngle))
	}
	@Override
	public float getX() {
		return this.position.x;
	}

	@Override
	public float getY() {
		return this.position.y;
	}

	@Override
	public float getAngle() {
		return this.baseAngle + currentAngle;
	}

	@Override
	public float getScale() {
		return 0.05f;
	}

	@Override
	public float[] getVertices() {
		return Shapes.rectangle;
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

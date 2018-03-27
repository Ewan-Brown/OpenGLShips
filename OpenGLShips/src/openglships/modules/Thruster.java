package openglships.modules;

import openglships.main.Data;

public class Thruster {

	public float x;
	public float y;
	public float throttle = 0.003f;
	public float angleRelative = 0;
	public float b;
	//TODO Center never gets updated. IF ship shapes change then it will have to get updates
	public Thruster(float x, float y, float angleRelative, float xC, float yC){
		this.x = x;
		this.y = y;
		b = Data.getDistance(x, xC, y, yC);
		this.angleRelative = angleRelative;
	}
	
}

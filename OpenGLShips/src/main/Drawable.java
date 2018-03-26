package main;

public abstract class Drawable {
	
	public Drawable(float x, float y, float angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	public float angle = 0;
	public float x = 0;
	public float y = 0;
	public float scale = 1;
	// Comes in tuplets (x,y)
	public float[] getVertices() {
		return null;
	}

	// Comes in quadrets (r,g,b,a)
	public float[] getColors() {
		return null;
	}
}

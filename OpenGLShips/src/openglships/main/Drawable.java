package openglships.main;

public interface Drawable {
	
	public Drawable[] getSubDraws();
	public float getX();
	public float getY();
	public float getAngle();
	public float getScale();
	public float scale = 1;
	// Comes in tuplets (x,y)
	public float[] getVertices();

	// Comes in quadrets (r,g,b,a)
	public float[] getColors();
}

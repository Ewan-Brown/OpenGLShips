package openglships.main;

public class Entity extends Movable{

	public int health = 0;
	public Entity(float x, float y, float angle, int health) {
		super(x, y, angle);
		this.health = health;
	}

}

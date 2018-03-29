package openglships.main;


//TODO Shoudn't this be an interface? No real implementation here, just the concept of life and death. 
//Would make destroyable modules in the same classification as ships = much cleaner and flexible :)
public class Entity extends PhysicalObject{

	
	public int health = 0;
	public Entity(float x, float y, float angle, int health) {
		super(x, y, angle);
		this.health = health;
	}

}

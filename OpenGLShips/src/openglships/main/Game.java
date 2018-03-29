package openglships.main;

import java.util.ArrayList;

public class Game {

	public static ArrayList<PhysicalObject> movables = new ArrayList<PhysicalObject>();
	public static ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	public static void main(String[] args) {
		Shapes.init();
		for(int i = 0; i < 5;i++){
			PhysicalObject m =BasicShip.createShip(
//					(float)Math.random() * 2 - 1,
//					(float)Math.random() * 2 - 1,
					i*2/5f - 0.8f,i*2/5f - 0.8f,
					0);
//					(float)Math.random() * 360);
			movables.add(m);
			drawables.add(m);

		}
		new Graphics().run();
	}
	public static void update(){
		for(int i = 0; i < movables.size();i++){
			PhysicalObject m = movables.get(i);
			m.update();
		}
	}
	

}
/* Some thanks
 * Daniel Howells - Torque
 * Ian Brown - Torque
 * Mr Wood - AI
 */

package openglships.main;

import java.util.ArrayList;

public class Game {

	public static ArrayList<Movable> movables = new ArrayList<Movable>();

	public static void main(String[] args) {
		Shapes.init();
		for(int i = 0; i < 10;i++){
			movables.add(BasicShip.createShip((float)Math.random() * 2 - 1,
					(float)Math.random() * 2 - 1,
					(float)Math.random() * 360,
					(float)Math.random()*0.45f + 0.05f));
		}
		new Graphics().run();
	}
	public static void update(){
		for(int i = 0; i < movables.size();i++){
			Movable m = movables.get(i);
			m.update();
		}
	}
	

}

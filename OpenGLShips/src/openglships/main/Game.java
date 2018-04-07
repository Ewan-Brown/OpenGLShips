package openglships.main;

import java.util.ArrayList;

import openglships.graphics.Graphics;

public class Game {

	public static ArrayList<Movable> movables = new ArrayList<Movable>();
	//TODO Make sure this is multithreaded
	public static void main(String[] args) {
		Shapes.init();
		for(int i = 0; i < 10;i++){
			movables.add(BasicShip.createShip(
					((float)Math.random() - 0.5f) * 4,
					((float)Math.random() - 0.5f) * 4,
					(float)Math.random(),
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

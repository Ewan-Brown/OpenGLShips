package openglships.main;

import java.util.ArrayList;

import openglships.graphics.Graphics;

public class Game {

	public static ArrayList<Movable> movables = new ArrayList<Movable>();
	//TODO Make sure this is multithreaded
	public static void main(String[] args) {
		Shapes.init();
		for(int i = 0; i < 10000;i++){
			movables.add(BasicShip.createShip(
					(float)Math.random()*2 - 1,
					(float)Math.random()*2 - 1,
					(float)Math.random()*360f,
					(float)Math.random()*0.45f + 0.05f));
		}
		new Graphics().run();
	}
	public static float targetX = 0;
	public static float targetY = 0;
	public static void update(){
		for(int i = 0; i < movables.size();i++){
			Movable m = movables.get(i);
			m.update();
		}
		float x = (float)Graphics.getCursorPos()[0] - Graphics.WIDTH / 2;
		float y = (float)Graphics.getCursorPos()[1] - Graphics.HEIGHT / 2;
		x /= Graphics.WIDTH/(2/Graphics.scale);
		y /= Graphics.HEIGHT/(2/Graphics.scale);
		targetX = x;
		targetY = y;
	}
	
	

}

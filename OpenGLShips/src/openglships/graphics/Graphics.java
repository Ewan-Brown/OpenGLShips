package openglships.graphics;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import openglships.main.Game;

public class Graphics {
	static private long window;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public Graphics(){

	}
	public static void render() {
		GL11.glLoadIdentity();
		//TODO Add Color usage 
		GL11.glColor3f(0.5f, 0.5f, 0.9f);
		GL11.glScaled(0.3, 0.3, 0.3);
		for(int i = 0; i < Game.movables.size();i++){
			Drawable d = Game.movables.get(i);
			GL11.glColor3f(0.5f, 0.5f, 0.9f);
			GL11.glPushMatrix(); 
			GL11.glTranslatef(d.getX(),d.getY(),0);
			GL11.glRotatef(d.getAngle(), 0.0f, 0.0f, 1f);
			GL11.glScalef(d.getScale(),d.getScale(),d.getScale());
			GL11.glBegin(GL11.GL_POLYGON);
			float[] v = d.getVertices();
			for(int j = 0; j < v.length / 2;j++){
				GL11.glVertex2f(v[j*2], v[j*2+1]);
			}
			Drawable[] dS = d.getSubDrawables();
			//TODO Draw modules
			GL11.glEnd();
			GL11.glPopMatrix();
			GL11.glColor3f(0.9f, 0.9f, 0.1f);
			if(dS != null){
				for(int j = 0; j < dS.length;j++){
					Drawable d2 = dS[j];
					GL11.glPushMatrix(); 
					GL11.glTranslatef(d.getX(), d.getY(), 0);
					GL11.glScalef(d2.getScale(), d2.getScale(), d2.getScale());
					GL11.glRotatef(d.getAngle(), 0, 0, 1f);
					GL11.glTranslatef(d2.getX(), d2.getY(), 0);
					GL11.glRotatef(d2.getAngle(), 0, 0, 1f);
					GL11.glBegin(GL11.GL_POLYGON);
					float[] vS = d2.getVertices();
					for(int k = 0; k < v.length / 2;k++){
						GL11.glVertex2f(vS[k*2], vS[k*2+1]);
					}
					GL11.glEnd();
					GL11.glPopMatrix();
				}
			}
		}
	}
	public void run() {
		init();
		loop();
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	private static void loop() {
		GL.createCapabilities();
		glClearColor(0, 0f, 0f, 0.0f);
		while (!glfwWindowShouldClose(window)) {
			Game.update();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			render();
			glfwSwapBuffers(window); // swap the color buffers
			glfwPollEvents();
		}
	}

	private static void init() {
		GLFWErrorCallback.createPrint(System.err).set();
		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); 
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); 
		window = glfwCreateWindow(WIDTH, HEIGHT, "Space Game in OpenGL!", NULL, NULL);
		if (window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1); 
			IntBuffer pHeight = stack.mallocInt(1);
			glfwGetWindowSize(window, pWidth, pHeight);
			glfwSetWindowPos(window, 0, 30);
		}
		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		glfwShowWindow(window);
	}

}

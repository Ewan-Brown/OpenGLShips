package openglships.main;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
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
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

public class Graphics {
	static private long window;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static float r = 0;

	public Graphics() {

	}
	/****************************************************************************
	XXX THINGS ARE ORIENTED TO THE EAST, 0 DEGREES IS EAST REMEMBER <b>this</b> PLEASE
	*****************************************************************************/
	public static void render() {
		// int VBO = GL15.glGenBuffers();
		// GL15.glBindBuffer(VBO, GL15.GL_ARRAY_BUFFER);
		// float[] f = new float[]{0,0,1,1};
		// GL15.glBufferData(GL15.GL_ARRAY_BUFFER, f, GL15.GL_STATIC_DRAW);
		GL11.glLoadIdentity();
		GL11.glColor3f(0.5f, 0.5f, 0.9f);
		r += 0.5;
		for (int mainDrawable = 0; mainDrawable < Game.drawables.size(); mainDrawable++) {
			Drawable d = Game.drawables.get(mainDrawable);
			GL11.glColor3f(0.5f, 0.5f, 0.9f);
			GL11.glPushMatrix();
			GL11.glTranslatef(d.getX(), d.getY(), 0);
			GL11.glRotatef(d.getAngle(), 0.0f, 0.0f, 1f);
			GL11.glScalef(d.getScale(), d.getScale(), d.getScale());
			GL11.glBegin(GL11.GL_POLYGON);
			float[] mainVerticeArray = d.getVertices();
			for (int mainVertice = 0; mainVertice < mainVerticeArray.length / 2; mainVertice++) {
				GL11.glVertex2f(mainVerticeArray[mainVertice * 2], mainVerticeArray[mainVertice * 2 + 1]);
			}
			GL11.glEnd();
			GL11.glPopMatrix();
			// TIME TO DRAW SUB BOYS :)
//			Drawable[] subDrawArray = d.getSubDraws();
//			GL11.glColor3f(1f, 1f, 0.0f);
//			if(subDrawArray != null){
//				for(int subDrawable = 0; subDrawable < subDrawArray.length;subDrawable++){
//					Drawable sD = subDrawArray[subDrawable];
//					GL11.glPushMatrix();
//					GL11.glTranslatef(d.getX(), d.getY(), 0);
//					GL11.glRotatef(d.getAngle(), 0.0f, 0.0f, 1f);
//					GL11.glTranslatef(-sD.getX(),-sD.getY(),0);
//					GL11.glScalef(sD.getScale(), sD.getScale(), sD.getScale());
//					GL11.glBegin(GL11.GL_POLYGON);
//					float[] subVerticeArray = sD.getVertices();
//					for (int subVertice = 0; subVertice < subVerticeArray.length / 2; subVertice++) {
//						GL11.glVertex2f(subVerticeArray[subVertice * 2], subVerticeArray[subVertice * 2 + 1]);
//					}
//					GL11.glEnd();
//					GL11.glPopMatrix();
//				}
//			}

		}
	}

	void run() {
		init();
		loop();
		// is closedbut this is messy
		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	private static void loop() {
		// bindings available for use.
		GL.createCapabilities();
		// Set the clear color
		glClearColor(0, 0f, 0f, 0.0f);

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while (!glfwWindowShouldClose(window)) {
			Game.update();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			render();
			glfwSwapBuffers(window); // swap the color buffers
			glfwPollEvents();
		}
	}

	private static void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are
		// already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden
		// after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be
		// resizable

		// Create the window
		window = glfwCreateWindow(WIDTH, HEIGHT, "Space Game in OpenGL!", NULL, NULL);
		if (window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback
		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);
			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			glfwSetWindowPos(window, 0, 30);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);
		// Make the window visible
		glfwShowWindow(window);
	}

}

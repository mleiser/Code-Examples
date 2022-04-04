import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 * @author Matthew Leiser
 * Made to have a cannon shot class to use with the shooting game
 */
public class Shot {
	//Instance variables below
	public static final double WIDTH = 8;
	public static final double HEIGHT = 8;
	boolean active = false;
	boolean visible = false;
	public static double x, y;
	public static double dx, dy;
	
	public void updateShot() {
		//Updates the position of the shot if active
		if (active) {
			updatePosition();
			//Removes the shot if a wall is reached
			if (x > ShootingGame.WIDTH) {
				suspend();
			}
		}
	}
	
	
	public void setPosition(double x2, double y2) {
		//Sets the position of the shot to a new location
		x = x2;
		y = y2;
	}
	
	public void setVelocity(double nx, double ny) {
		//Sets the velocity with which the shot travels
		dx = nx;
		dy = ny;
	}
	
	public void updatePosition() {
		//Updates the shot's position based on the velocity
		x += dx;
		y+= dy;
	}
	
	public boolean isActive() {
		//Checks if the shot is active
		return active;
	}
	
	public void resume() {
		//Sets active and visible to true
		active = true;
		visible = true;
	}
	
	public void suspend() {
		//Sets active and visible to true
		active = false;
		visible = false;
	}
	
	public void render(GraphicsContext gc) {
		//Renders the shot if visible is set to true
		if (visible) {
			gc.setFill(Color.BLACK);
			gc.fillRect(x , y, WIDTH, HEIGHT);
		}
	}
}

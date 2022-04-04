import java.awt.Rectangle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 * @author Matthew Leiser
 * Created as the target which the cannon shot is trying to hit for the shooting game
 */
public class Target {
	//Instance variables below
	boolean active = false;
	boolean visible = false;
	
	public static int x, y;
	public static int w, h;
	
	//Instantiates the target
	public Target() {
		w = 30;
		h = 30;
	}
	
	public void hit() {
		//Sets the target's active and visible state to false
		active = false;
		visible = false;
	}
	
	public boolean hitSuccess(Shot s) {
		//Checks if the target is intersecting with the shot
		Rectangle r = new Rectangle(x, y, w, h);
		Rectangle r2 = new Rectangle((int) s.x, (int) s.y, (int) s.WIDTH, (int) s.HEIGHT);
		if (r.intersects(r2)) {
			return true;
		}
		
		else {
			return false;
		}
	}
	
	public void place() {
		//Sets the target's active and visible attributes to true 
		active = true;
		visible = true;
	}
	
	public boolean isActive() {
		//Gives if the target is active or not
		return active;
	}
	
	public void setPosition(int x2, int y2) {
		//Sets the target position
		x = x2;
		y = y2;
	}
	
	public void render(GraphicsContext gc) {
		//Renders the target if visible is true
		if (visible) {
			gc.setFill(Color.RED);
			gc.fillRect(x, y, w, h);
		}
	}
}

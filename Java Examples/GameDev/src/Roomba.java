
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
/**
 * 
 * @author Matthew Leiser
 * The purpose of this class was to create a roomba that rotated and moved around to be used with the roomba game.
 */
public class Roomba {
	static int x = RoombaGame.WIDTH / 2;
	static int y = RoombaGame.HEIGHT / 2;
	static int w = 50;
	static int h = 50;
	static double dirAngle;
	private int speed;
	private double dx, dy;
	
	public Roomba() {
		//Initial angle and speed of the roomba
		dirAngle = 0;
		speed = 6;
	}
	
	public void move() {
		x += dx;
		y += dy;
		
		//Stops the roomba at the borders
		if (x < 0) {
			x = 0;
		}
		
		else {
			dx = speed * Math.cos(dirAngle);
			dy = speed * Math.sin(dirAngle);
		}
		
		if (x + w > RoombaGame.WIDTH) {
			x = RoombaGame.WIDTH - w;
		}
		
		else {
			dx = speed * Math.cos(dirAngle);
			dy = speed * Math.sin(dirAngle);
		}
		
		if (y < 0) {
			y = 0;
		}
		
		else {
			dx = speed * Math.cos(dirAngle);
			dy = speed * Math.sin(dirAngle);
		}
		
		if (y + h > RoombaGame.HEIGHT) {
			y = RoombaGame.HEIGHT - h;
		}
		
		else {
			dx = speed * Math.cos(dirAngle);
			dy = speed * Math.sin(dirAngle);
		}
	}
	
	public static void setDirAngle(double angle) {
		//Sets the angle the roomba moves at
		dirAngle += Math.toRadians(angle);
	}
	
	public void render(GraphicsContext gc) {
		//Creates the roomba sprite.
		gc.setFill(Color.BLACK);
		gc.fillOval(x, y, w, h);
		
		gc.setFill(Color.WHITE);
		gc.fillOval(x + 35, y + 20, w/4, h/4);
	}
}

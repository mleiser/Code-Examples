
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 * 
 * @author Matthew Leiser
 * The purpose of this was to create a class for dust bunnies to be used for the roomba game
 * Maybe something broke here but I can't find anything that would break the code
 */
public class DustBunny {
	private static Random rng = new Random();
	private static int x, y;
	private static int dx = 1;
	private static int dy = 1;
	private static int w = 15;
	private static int h = 15;
	
	public DustBunny() {
		//Creates a random trajectory for the dust bunnies
		if (rng.nextDouble() < 0.5) {
			dx = -dx;
		}
		
		if (rng.nextDouble() < 0.5) {
			dy = -dy;
		}
		
		//Places the dust bunnies randomly
		x = 5 + rng.nextInt(RoombaGame.WIDTH - w - 10);
		y = 5 + rng.nextInt(RoombaGame.HEIGHT - h - 10);
	}
	
	public void move() {
		x += dx;
		y += dy;
		
		//Wraps the dust bunnies around the window
		if (x < 0) {
			x = RoombaGame.WIDTH;
		}
		
		if (x > RoombaGame.WIDTH) {
			x = 0;
		}
		
		if (y < 0) {
			y = RoombaGame.HEIGHT;
		}
		
		if (y > RoombaGame.HEIGHT) {
			y = 0;
		}
	}
	
	public void render(GraphicsContext gc) {
		//Renders the bunnies
		gc.setFill(Color.GRAY);
		gc.fillOval(x, y, w, h);
	}
}

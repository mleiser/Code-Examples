import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 * @author Matthew Leiser
 * Created to have a cannon class to use with the shooting game to fire shots
 */
public class Cannon {
	//Instance variables below
	final double Y_HIGH = 5;
	final double Y_LOW = ShootingGame.HEIGHT;
	
	double y = Y_LOW;
	final double x = 0;
	
	Shot shot;
	
	//Instantiates the cannon with a shot
	public Cannon(Shot s) {
		shot = s;
	}
	
	public void setY(double y1) {
		//Sets the cannon's placement and limits it to the stage
		y = y1;
		
		if (y > Y_LOW) {
			y = Y_LOW;
		}
		
		else if (y < Y_HIGH) {
			y = Y_HIGH;
		}
	}
	
	void render(GraphicsContext gc) {
		//Renders the cannon
		gc.setFill(Color.NAVY);
		gc.fillRect(x, y, 30, 15);
	}
	
	public void fireShot() {
		//Fires a shot based on the position of cannon
		shot.setPosition(x, y);
		shot.setVelocity(20.0, 0.0);
		shot.resume();
	}
}

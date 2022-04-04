import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

/**
 * 
 * @author Matthew Leiser
 * Supposed to create a game that has a random target on the screen and a up and down moving 
 * cannon that shoots out a cube to hit the target
 *
 */
public class ShootingGame extends Application {
	//Instance variables below
	final String APPNAME = "Shooting Game";
	final int FPS = 30;
	static final int WIDTH = 750;
	static final int HEIGHT = 500;
	private static Random rng = new Random();
	
	int points = 0;
	
	Target target;
	Cannon cannon;
	Shot shot;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Sets up the stage 
		primaryStage.setTitle(APPNAME);
		
		Group root = new Group();
		Scene theScene = new Scene(root);
		primaryStage.setScene(theScene);
		
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		//Initializes the components and event handlers
		initialize();
		eventHandlers(theScene);
		
		//Sets up the animation loop
		KeyFrame kf = new KeyFrame(Duration.millis(1000 / FPS), 
				e -> {
					//updates the animation
					update();
					//renders the animation
					render(gc);
		});
		Timeline mainLoop = new Timeline(kf);
		mainLoop.setCycleCount(Animation.INDEFINITE);
		mainLoop.play();
		//Sets up the score 
		Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
		gc.setFont(theFont);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		
		//Shows the stage
		primaryStage.show();
		
	}
	
	public void initialize() {
		//Instantiates the components
		target = new Target();
		shot = new Shot();
		cannon = new Cannon(shot);
	}
	
	public void eventHandlers(Scene scene) {
		//Handles the mouse pressed action to fire a shot
		scene.setOnMousePressed(e -> {
			if (!shot.isActive()) {
				cannon.fireShot();
			}
		});
		
		//moves the cannon up and down.
		scene.setOnMouseMoved(e -> {
			cannon.setY(e.getY());
		});
	}
	
	public void update() {
		//Updates the shot's position
		shot.updateShot();
		
		//Checks if the shot successfully hit the target and adds a point if it did
		if (shot.isActive() && target.hitSuccess(shot)) {
			target.hit();
			shot.suspend();
			points++;
		}
		
		//Places the target if needed
		setTarget();
	}
	
	public void setTarget() {
		//If the target is not active, sets the target in a random place
		if (!target.isActive()) {
			target.setPosition(50 + rng.nextInt(600), rng.nextInt(450));
			target.place();
		}
	}
	public void render(GraphicsContext gc) {
		//Renders the background
		gc.setFill(Color.AZURE);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		
		gc.setFill(Color.BLACK);
		//Renders the score at the top of the screen
		String score = "Score: " + points;
		gc.fillText(score, 350, 30);
		gc.strokeText(score, 350, 30);
		
		//Renders the components
		cannon.render(gc);
		shot.render(gc);
		target.render(gc);
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}

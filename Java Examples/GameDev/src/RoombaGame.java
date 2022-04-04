

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
/**
 * 
 * @author Matthew Leiser
 * The program was created for assignment 3, but while experimenting with the dust bunnies something broke 
 * and I do not know how to fix it so finishing the program is pointless now.
 */
public class RoombaGame extends Application{
	private final int FPS = 30; // frames per second
	final static int WIDTH = 500;
    final static int HEIGHT = 400;
    private final String appName = "Roomba Game";
    private ArrayList<DustBunny> db = new ArrayList<DustBunny>();
    Roomba roomba;
    
    @Override
	public void start(Stage theStage) {
		theStage.setTitle(appName);

		Group root = new Group();
		Scene theScene = new Scene(root);
		theStage.setScene(theScene);

		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		initialize();
		setHandlers(theScene);
		
		KeyFrame kf = new KeyFrame(Duration.millis(1000 / FPS),
				e -> {
					//Updates  the stage
					update();
					//Renders the graphics
					render(gc);
				}
			);
		Timeline mainLoop = new Timeline(kf);
		mainLoop.setCycleCount(Animation.INDEFINITE);
		mainLoop.play();

		theStage.show();
	}
    public void setHandlers(Scene scene) {
    	//Rotates the roomba when left or right keys are pressed
    		scene.setOnKeyPressed(e -> {
    			switch(e.getCode()) {
    			
    			case A:
    			case LEFT:
    				Roomba.setDirAngle(-15);
    				break;
    			
    			case D:
    			case RIGHT:
    				Roomba.setDirAngle(15);;
    				break;
    				
    			default:
    				break;
    			}
    		});
    }
    
    public void initialize() {
    	//Creates the dust bunnies and roomba	
    		for (int i = 0; i <= 12; i ++) {
    			db.add(new DustBunny());
    		}
    		roomba = new Roomba();
    }
    
    public void update() {
    	//Updates and moves the dust bunnies and roomba
    		for (int i = 0; i < db.size(); i++) {
    			db.get(i).move();
    		}
    		roomba.move();
    }
    
    public void render(GraphicsContext gc) {
    	//Renders the window and sprites
    		gc.setFill(Color.AQUA);
    		gc.fillRect(0, 0, WIDTH, HEIGHT);
    		
    		for (int i = 0; i < db.size(); i++) {
    			db.get(i).render(gc);
    		}
    		roomba.render(gc);
    }
    
	public static void main(String[] args) {
		launch(args);
	}

}

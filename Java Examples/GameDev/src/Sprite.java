import javafx.scene.canvas.GraphicsContext;

public class Sprite {
	double x, y;
	double dx, dy;
	
	boolean active = false;
	boolean visible = false;
	
	void updatePosition() {
		x += dx;
		y += dy;
	}
	
	void setVelocity(double nx, double ny) {
		dx = nx;
		dy = ny;
	}
	
	void setPosition(double x2, double y2) {
		x = x2;
		y = y2;
	}
	
	boolean isCloserThan(Sprite s, double dist) {
		double dx = x - s.x;
		double dy = y - s.y;
		return dx * dx + dy * dy < dist * dist;
	}
	
	boolean isActive() {
		return active;
	}
	
	void resume() {
		active = true;
		visible = true;
	}
	
	void suspend() {
		active = false;
		visible = false;
	}
	
	void updateSprite() {}
	
	void render(GraphicsContext gc) {}
}

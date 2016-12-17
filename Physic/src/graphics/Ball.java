package graphics;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {

	private int x, y, radius;
	private double dx, dy, dt, gravity;

	public Ball(double... args) {
		this.x = (int) args[0];
		this.y = (int) args[1];
		this.gravity = args[2];
		this.dy = this.dx = 0;
		this.radius = 40;
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(this.x, this.y, radius, radius);
	}

	public void update() {

	}

}

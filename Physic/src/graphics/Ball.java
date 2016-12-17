package graphics;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {

	private int x, y, radius;
	private double dx, dy, dt, gravity, energyloss;

	public Ball(double... args) {
		this.x = (int) args[0];
		this.y = (int) args[1];
		this.gravity = args[2];
		this.dy = this.dx = 0;
		this.radius = 40;
		this.dt = Math.sqrt(2 * Window.H / Math.abs(gravity)) / 100;
		this.energyloss = 0.65;
		System.out.println(dt);
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(this.x, this.y, radius, radius);
	}

	public void update() {

		if (y > Window.H - radius - (radius / 2)) {
			y = Window.H - radius - (radius / 2);
			dy *= energyloss;
			dy = -dy;
		} else {
			// Velocity Formula
			dy += gravity * dt;
			// Position Formula
			y += dy * dt + (gravity * Math.pow(dt, 2) / 2);

		}
	}

}

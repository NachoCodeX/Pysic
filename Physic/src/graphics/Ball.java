package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {

	private int x, y, radius;
	private double dx, dy, dt, gravity, energyloss;

	public Ball(double... args) {
		this.x = (int) args[0];
		this.y = (int) args[1];
		this.gravity = args[2];
		this.dy = this.dx = 0;
		this.radius = (int) args[3];
		this.dt = (Math.sqrt(2 * Window.H / Math.abs(gravity)) / 100) + 0.1;
		this.energyloss = 0.65;
		// System.out.println(dt);

	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(this.x, this.y, radius, radius);
		g.setColor(Color.GREEN);
		g.drawRect(x + ((radius * 5) / 50), y + ((radius * 4) / 50), radius - ((radius * 10) / 50),
				radius - ((radius * 10) / 50));
	}

	public void update() {
		if (getRect().intersects(Window.floor)) {
			y = Window.floor.y - radius;
			// System.out.println(y);
			dy *= energyloss;
			dy = -dy;

		} else {
			// Velocity Formula
			dy += gravity * dt;
			// Position Formula
			y += dy * dt + (gravity * Math.pow(dt, 2) / 2);

		}

		if (x >= Window.W - Window.PW - radius) {
			x = Window.W - Window.PW - radius;
			dx = -dx;
		} else if (x <= 0) {
			dx *= -1;

		}

		x += dx;

	}

	public Rectangle getRect() {
		return new Rectangle(this.x, this.y, this.radius, this.radius);
	}

	public void push() {
		dx = -5 + (Math.random() * (10 + 5));

		// System.out.println(dx);
	}

	public void setEnergyLoss(double energyloss) {
		this.energyloss = energyloss;
	}
}

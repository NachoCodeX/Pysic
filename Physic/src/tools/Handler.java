package tools;

import java.awt.Graphics;
import java.util.ArrayList;

import graphics.Ball;

public class Handler {
	private ArrayList<Ball> balls = new ArrayList<Ball>();

	public Handler() {

	}

	public void render(Graphics g) {
		for (int i = 0; i < balls.size(); i++) {
			balls.get(i).render(g);
		}
	}

	public void update() {
		for (int i = 0; i < balls.size(); i++) {
			balls.get(i).update();
		}
	}

	public void add(Ball ball) {
		balls.add(ball);
	}

	public void clear() {
		balls.clear();
	}

}

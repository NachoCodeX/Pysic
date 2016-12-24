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

		collision();

	}

	public void collision() {
		for (int i = 0; i < balls.size(); i++) {
			Ball temp1 = balls.get(i);
			for (int j = 0; j < balls.size(); j++) {
				Ball temp2 = balls.get(j);
				if (temp1.getRect().intersects(temp2.getRect()) && i != j) {
					temp2.push();
					temp1.setEnergyLoss(Math.random() * (1));
				}

				if (temp1.getRect().x == temp2.getRect().x && temp1.getRect().y == temp2.getRect().y && i != j) {

				}
			}
		}
	}

	public void add(Ball ball) {
		balls.add(ball);
	}

	public void clear() {
		balls.clear();
	}

}

package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import tools.Handler;

public class Window extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static final int W = 900, H = 600, PW = 300;
	private JFrame win;
	private JPanel panel;
	private JSlider[] slider;
	private JButton[] btns;
	private JLabel[] label;
	private Thread thread;
	private volatile boolean isRun;
	private Handler handler;
	public static Rectangle floor;

	public Window() {
		handler = new Handler();
		isRun = true;
		this.setSize((W - PW), H);
		this.setBackground(Color.BLACK);
		setPanel();
		createJFrame();
	}

	private void setPanel() {
		floor = new Rectangle(0, 565, (W - PW), 5);

		label = new JLabel[2];
		label[0] = new JLabel("Gravity");
		label[0].setBounds((PW / 2) - 50, 0, 100, 25);
		label[1] = new JLabel("Size");
		label[1].setBounds((PW / 2) - 50, 0, 100, 125);

		btns = new JButton[2];
		btns[0] = new JButton("Add");
		btns[0].setBounds(25, 120, 100, 25);

		btns[1] = new JButton("Clear");
		btns[1].setBounds(125, 120, 100, 25);

		slider = new JSlider[2];
		slider[0] = new JSlider(-100, 100);
		slider[0].setBounds(20, 30, PW - 50, 20);
		slider[1] = new JSlider(10, 50);
		slider[1].setBounds(20, 80, PW - 50, 20);

		btns[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handler.add(new Ball(20 + (int) (Math.random() * (W - PW - 100)), 70,
						(double) slider[0].getValue() / 10, slider[1].getValue()));

			}
		});

		btns[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handler.clear();

			}
		});

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds((W - PW), 0, PW, H);
		for (int i = 0; i < btns.length; i++) {
			panel.add(btns[i]);
			panel.add(label[i]);
			panel.add(slider[i]);
		}
	}

	private void createJFrame() {
		win = new JFrame("Physic Balls");
		win.setSize(W, H);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setLocationRelativeTo(null);
		win.setResizable(false);
		win.add(panel);
		win.add(this);
		win.setVisible(true);
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, W, H);
		g.setColor(Color.GRAY);
		g.fillRect(0, 565, (W - PW), 5);
		handler.render(g);
		g.dispose();
		bs.show();
	}

	public void update() {
		handler.update();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		final int NS_SEG = 1000000000;
		final double APS = 60.0;

		final double NS_POR_APS = NS_SEG / APS;
		double tiempoTranscurrido = 0.0;

		double delta = 0.0;

		long verificarFPS = System.nanoTime();
		int aps = 0, fps = 0;

		while (isRun) {
			long now = System.nanoTime();

			tiempoTranscurrido = now - lastTime;
			lastTime = now;

			delta += tiempoTranscurrido / NS_POR_APS;

			if (delta >= 1) {
				delta--;
				// update
				update();
				aps++;
			}

			render();
			fps++;

			if (System.nanoTime() - verificarFPS >= NS_SEG) {
				verificarFPS = System.nanoTime();
				win.setTitle("FPS: " + fps + " || APS: " + aps);
				aps = 0;
				fps = 0;
			}

		}

	}

}

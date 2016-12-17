package graphics;

import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Window extends Canvas {
	private static final long serialVersionUID = 1L;

	private static final int W = 900, H = 600, PW = 300;
	private JFrame win;
	private JPanel panel;
	private JSlider slider;
	private JButton[] btns;
	private JLabel label;

	public Window() {
		this.setSize((W - PW), H);
		this.setBackground(Color.BLACK);
		setPanel();
		createJFrame();
	}

	private void setPanel() {
		label = new JLabel("Gravity");
		label.setBounds((PW / 2) - 50, 0, 100, 25);
		btns = new JButton[2];
		btns[0] = new JButton("Add");
		btns[0].setBounds(25, 60, 100, 25);

		btns[1] = new JButton("Clear");
		btns[1].setBounds(125, 60, 100, 25);

		slider = new JSlider();
		slider.setBounds(20, 30, PW - 50, 20);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds((W - PW), 0, PW, H);
		panel.add(slider);
		panel.add(label);
		panel.add(btns[0]);
		panel.add(btns[1]);
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

}

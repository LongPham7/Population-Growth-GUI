import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * This class serves as a view in the MVC architecture, creating GUI components
 * for a graph of exponential growth. The graph itself is, however, displayed on
 * a panel of the PanelExponentialGrowth class, which is nested in the frame of
 * this class. 
 * 
 * Additionally, this class calculates data points for exponential growth using
 * parameters specified by users, handing them in to the panel where a graph is
 * displayed. 
 */
public class ExponentialGrowth implements GrowthGraph {

	// Array of data points of the exponential growth
	private double[] data;
	private AppFrame app;
	private PanelExponentialGrowth panel;

	// Whether the animation mode is on
	private boolean isAnimate = false;

	// Interval of animation is 50 ms. 
	private final int interval = 50;
	private Timer timer = new Timer(interval, new animationListener());

	public ExponentialGrowth(AppFrame app) {
		this.app = app;
	}

	// Creates GUI components for the exponential growth graph.
	public void activate() {
		JFrame frame2 = new JFrame("Exponential Growth");
		panel = new PanelExponentialGrowth();
		JLabel labelInFrame2 = new JLabel("Exponential Growth Graph");
		frame2.getContentPane().add(BorderLayout.CENTER, panel);
		frame2.getContentPane().add(BorderLayout.NORTH, labelInFrame2);

		frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame2.setSize(700, 700);
		frame2.setVisible(false);

		frameDraw();
		frame2.setVisible(true);
	}

	// Activates/deactivates animation.
	public void setAnimate(boolean b) {
		isAnimate = b;
	}

	// Draws a graph of generation v.s. population of the exponential growth.
	private void frameDraw() {
		// Initial population
		int p = app.getField1();
		// Number of generations including the initial one
		int k = app.getField2() + 1;
		// Reproductive rate
		double r = app.getField3();

		// Upper bound of the population that can be properly displayed
		double bound = (Integer.MAX_VALUE) / r;

		// Whether the graph can be displayed without exceeding the frame size
		boolean canDisplay = true;

		data = new double[k];
		data[0] = p;// Initial population
		for (int i = 0; i < k - 1; i++) {
			data[i + 1] = r * data[i];
			if (data[i + 1] >= bound) {
				canDisplay = false;
				break;
			}
		}

		if (canDisplay == true) {
			panel.sample = data;
			if (isAnimate == false) {
				panel.count = data.length;
				panel.repaint();
			} else {
				frameAnimation();
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Some populations are larger than " + "the maximum integer allowed in Java.", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	// Animates the graph of the exponential growth.
	private void frameAnimation() {
		if (!timer.isRunning()) {
			timer.restart();
		}
	}

	// Action listener for animation
	class animationListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (panel.count < data.length) {
				panel.count++;
				panel.repaint();
			} else {
				timer.stop();
			}
		}
	}
}

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * This class serves as a view in the MVC architecture, creating GUI components
 * for a graph of logistic growth. The graph itself is, however, displayed on
 * a panel of the PanelLogisticGrowthA class, which is nested in the frame of
 * this class. 
 * 
 * Additionally, this class calculates data points for logistic growth using
 * parameters specified by users, handing them in to the panel where a graph is
 * displayed. 
 */
public class LogisticGrowthA implements GrowthGraph {

	// Array of data points of the logistic growth
	public double[] data;
	private AppFrame app;
	private PanelLogisticGrowthA panel;

	// Whether the animation mode is on
	private boolean isAnimate = false;

	// Interval of animation is 50 ms. 
	private final int interval = 50;
	private Timer timer = new Timer(interval, new animationListener());

	public LogisticGrowthA(AppFrame app) {
		this.app = app;
	}

	// Creates GUI components for the first graph of the logistic growth.
	public void activate() {
		JFrame frame3 = new JFrame("Logistic Growth A");
		panel = new PanelLogisticGrowthA();
		JLabel labelInFrame3 = new JLabel("Logistic Growth Graph");
		frame3.getContentPane().add(BorderLayout.CENTER, panel);
		frame3.getContentPane().add(BorderLayout.NORTH, labelInFrame3);

		frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame3.setSize(1350, 700);
		frame3.setVisible(false);

		frameDraw();
		frame3.setVisible(true);
	}

	// Activates/deactivates animation.
	public void setAnimate(boolean b) {
		isAnimate = b;
	}

	// Draws a graph of generation v.s. population of the logistic growth.
	private void frameDraw() {
		// Initial population
		int p = app.getField4();
		// Number of generations including the initial one
		int k = app.getField5() + 1;
		// Carrying capacity
		int cc = app.getField7();
		// Reproductive rate
		double r = app.getField6();

		data = new double[k];
		data[0] = p;

		for (int i = 0; i != k - 1; i++) {
			data[i + 1] = r * data[i] * (1 - data[i] / cc);
		}

		// The reproductive rate for logistic growth must be smaller than or
		// equal to 4.
		if (app.getField6() < 4) {
			panel.setData(data);
			if (isAnimate == false) {
				panel.count = data.length;
				panel.repaint();
			} else {
				frameAnimation();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Reproductive rate must be lower than 4.", "Error Message",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Animates the first graph of the logistic growth.
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

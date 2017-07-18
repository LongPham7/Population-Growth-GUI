import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * This class serves as a view in the MVC architecture, creating GUI components
 * for a second graph of exponential growth. The graph itself is, however,
 * displayed on a panel of the PanelLogisticGrowthB class, which is nested in
 * the frame of this class.
 * 
 * Additionally, this class calculates data points for exponential growth using
 * parameters specified by users, handing them in to the panel where a graph is
 * displayed.
 */
public class LogisticGrowthB implements GrowthGraph {

	// Array of data points of the logistic growth
	public double[] data;
	private AppFrame app;
	private PanelLogisticGrowthB panel;

	// Whether the animation mode is on
	private boolean isAnimate = false;

	// Interval of animation is 50 ms.
	private final int interval = 50;
	private Timer timer = new Timer(interval, new animate3bListener());

	public LogisticGrowthB(AppFrame app) {
		this.app = app;
	}

	// Creates GUI components for the second graph of logistic growth.
	public void activate() {
		JFrame frame3b = new JFrame("Logistic Growth B");
		panel = new PanelLogisticGrowthB();
		JLabel labelInFrame3b = new JLabel("Pairs of consecutve populations");
		frame3b.getContentPane().add(BorderLayout.CENTER, panel);
		frame3b.getContentPane().add(BorderLayout.NORTH, labelInFrame3b);

		frame3b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame3b.setSize(800, 700);
		frame3b.setVisible(false);

		frameDraw();
		frame3b.setVisible(true);
	}
	
	// Activates/deactivates animation. 
	public void setAnimate(boolean b) {
		isAnimate = b;
	}

	// Draws a graph that plots every pair of consecutive populations in
	// logistic growth.
	private void frameDraw() {
		// Initial population
		int p = app.getField4();
		// The number of generations including the initial one
		int k = app.getField5() + 1;
		// Carrying capacity
		int cc = app.getField7();
		// Reproductive rate
		double r = app.getField6();

		data = new double[k];
		data[0] = p;// Initial population

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
			JOptionPane.showMessageDialog(null, "Reproductive rate has to be lower than 4.", "Error Message",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Animates the second graph of the logistic growth.
	private void frameAnimation() {
		if (!timer.isRunning()) {
			timer.restart();
		}
	}

	// Action listener for animation
	class animate3bListener implements ActionListener {
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

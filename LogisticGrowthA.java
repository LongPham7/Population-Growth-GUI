import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class LogisticGrowthA {

	private AppFrame app;
	private PanelInFrame3 panelFrame3;
	private JLabel labelInFrame3;

	// Boolean to know if animation mode of the first graph of the logistic growth
	// is
	// on or off. If the animation mode is on, the boolean is true. Otherwise, it is
	// false.
	private boolean check = false;

	private Timer timer;// Timer to animate the first graph of the logistic growth

	public double[] data;// Array of data points of the logistic growth

	public LogisticGrowthA(AppFrame app) {
		this.app = app;
	}

	// This method adds widgets on the third JFrame which stays invisible until a
	// user
	// clicks a button to draw a graph of the logistic growth. This JFrame has a
	// label
	// and a graph of generation v.s. population in the logistic growth.
	public void activateLogisticGrowthA() {
		JFrame frame3 = new JFrame("Logistic Growth 1");
		// Class PanelInFrame3 is a subclass of PanelInFrame2.
		panelFrame3 = new PanelInFrame3();
		labelInFrame3 = new JLabel("Here is a graph of the discrete logistic growth.");
		frame3.getContentPane().add(BorderLayout.CENTER, panelFrame3);
		frame3.getContentPane().add(BorderLayout.NORTH, labelInFrame3);

		frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame3.setSize(1350, 700);
		frame3.setVisible(false);

		frame3Draw();
		frame3.setVisible(true);
	}
	
	public void setAnimate(boolean b) {
		check = b;
	}

	// This method draws a graph of generation v.s. population of the logistic
	// growth using
	// the constants submitted by the user.
	private void frame3Draw() {
		// Initial population
		int p = app.getField5();
		// The number of generations including the initial one
		int k = app.getField6() + 1;
		// Carrying capacity
		int cc = app.getField8();
		// Reproductive rate
		double r = app.getField7();

		data = new double[k];
		data[0] = p;// Initial population

		for (int i = 0; i < k - 1; i++) {
			data[i + 1] = r * data[i] * (1 - data[i] / cc);
		}

		if (app.getField7() < 4) {

			// Pass the array of data points of the logistic growth to the array "sample"
			// in PanelInFrame3
			panelFrame3.sample = data;

			/*
			 * If the animation mode is off, set the variable "count" to be the length of
			 * the array "data1", and draw the graph of the exponential growth. The variable
			 * "count" represents the number of data points to plot on the graph, so in this
			 * case, all data points are plotted.
			 */
			if (check == false) {
				panelFrame3.count = data.length;
				// Call paintComponent() in Class PanelInFrame3, which is a subclass of
				// PanelInFrame2.
				panelFrame3.repaint();
			}
			// Otherwise, animate the graph.
			else {
				frame3Animate();
			}
		}
		// If the reproductive rate in the logistic growth is larger than 4,
		// show an error message.
		else {
			JOptionPane.showMessageDialog(null, "Reproductive rate has to be lower than 4.", "Error Message",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// This method animates the first graph of the logistic growth.
	private void frame3Animate() {
		panelFrame3.count = 0;
		// If the timer does not exist yet, start the timer.
		if (timer == null) {
			timer = new Timer(50, new animate3Listener());
			timer.start();
		}
		// If the timer exists but does not running, restart the timer.
		else if (!timer.isRunning()) {
			timer.restart();
		}
	}

	// Action Listener of the timer for the animation of the first logistic growth
	// graph.
	class animate3Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// If the variable "count" is smaller than the length of the array of date
			// points,
			// increment the variable by 1 and repaint the panel.
			if (panelFrame3.count < data.length) {
				panelFrame3.count++;
				panelFrame3.repaint();
			}
			// If the variable "count" reaches the last element in the array, stop the
			// timer.
			else {
				timer.stop();
			}
		}
	}

}

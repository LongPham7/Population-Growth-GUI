import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
public class LogisticGrowthB extends LogisticGrowthA {

	public LogisticGrowthB(AppFrame app) {
		super(app);
	}

	@Override
	// Creates GUI components for the second graph of logistic growth.
	public void activate() {
		JFrame frame = new JFrame("Logistic Growth B");
		panel = new PanelLogisticGrowthB();
		JLabel labelInFrame = new JLabel("Pairs of consecutve populations");
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.getContentPane().add(BorderLayout.NORTH, labelInFrame);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800, 700);
		frame.setVisible(false);

		frameDraw();
		frame.setVisible(true);
	}
}

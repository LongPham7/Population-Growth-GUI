import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AppFrame {

	private ExponentialGrowth eg;
	private LogisticGrowthA lga;
	private LogisticGrowthB lgb;

	JFrame frame;

	JPanel panel1;
	JPanel panel2;
	JPanel panel3;

	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	JLabel label7;
	JLabel label8;
	JLabel label9;
	JLabel label10;
	JLabel label11;

	// Exponential growth
	JTextField field1;
	JTextField field2;
	JTextField field3;

	// Logistic growth
	JTextField field5;
	JTextField field6;
	JTextField field7;
	JTextField field8;

	JButton button1;
	JButton button2;
	JButton button3;

	JCheckBox box1;
	JCheckBox box2;
	JCheckBox box3;

	private GridBagConstraints c;

	// This method adds widgets on the first JFrame where a user can submit
	// constants of
	// of the exponential and logistic growth.
	public void activate() {
		eg = new ExponentialGrowth(this);
		lga = new LogisticGrowthA(this);
		lgb = new LogisticGrowthB(this);

		frame = new JFrame("Unpredictable Ouput");
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();

		label1 = new JLabel("The initial population: P=");
		label2 = new JLabel("The number of generations: k=");
		label3 = new JLabel("The reproductive rate: r=");
		label4 = new JLabel("Please enter each constant in the discrete exponential model.");
		label5 = new JLabel("The initial population: P=");
		label6 = new JLabel("The number of generations: k=");
		label7 = new JLabel("The reproductive rate: r=");
		label8 = new JLabel("The carrying capacity: CC=");
		label9 = new JLabel("Please enter each constant in the discrete logistic model.");
		label10 = new JLabel("Graph of generation and population");
		label11 = new JLabel("Graph of previous and later populations");
		field1 = new JTextField(6);
		field2 = new JTextField(6);
		field3 = new JTextField(6);
		field5 = new JTextField(6);
		field6 = new JTextField(6);
		field7 = new JTextField(6);
		field8 = new JTextField(6);
		button1 = new JButton("Draw Graph");
		button2 = new JButton("Draw Graph");
		button3 = new JButton("Draw Graph");
		box1 = new JCheckBox("Animation");
		box2 = new JCheckBox("Animation");
		box3 = new JCheckBox("Animation");

		frame.getContentPane().add(BorderLayout.NORTH, panel1);
		frame.getContentPane().add(BorderLayout.CENTER, panel2);
		frame.getContentPane().add(BorderLayout.SOUTH, panel3);

		panel1.setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		panel1Add(label4, 0, 0);
		panel1Add(label1, 0, 1);
		panel1Add(field1, 1, 1);
		panel1Add(label2, 0, 2);
		panel1Add(field2, 1, 2);
		panel1Add(label3, 0, 3);
		panel1Add(field3, 1, 3);
		panel1Add(box1, 0, 4);
		panel1Add(button1, 1, 4);

		panel2.setLayout(new GridBagLayout());
		panel2Add(label9, 0, 0);
		panel2Add(label5, 0, 1);
		panel2Add(field5, 1, 1);
		panel2Add(label6, 0, 2);
		panel2Add(field6, 1, 2);
		panel2Add(label6, 0, 3);
		panel2Add(field6, 1, 3);
		panel2Add(label7, 0, 4);
		panel2Add(field7, 1, 4);
		panel2Add(label8, 0, 5);
		panel2Add(field8, 1, 5);
		panel2Add(label10, 0, 6);
		panel2Add(box2, 0, 7);
		panel2Add(button2, 1, 7);
		panel2Add(label11, 0, 8);
		panel2Add(box3, 0, 9);
		panel2Add(button3, 1, 9);

		button1.addActionListener(new button1Listener());
		button2.addActionListener(new button2Listener());
		button3.addActionListener(new button3Listener());
		box1.addActionListener(new box1Listener());
		box2.addActionListener(new box2Listener());
		box3.addActionListener(new box3Listener());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}

	// Accessors

	public int getField1() {
		return Integer.parseInt(field1.getText());
	}

	public int getField2() {
		return Integer.parseInt(field2.getText());
	}

	public double getField3() {
		return Double.parseDouble(field3.getText());
	}

	public int getField5() {
		return Integer.parseInt(field5.getText());
	}

	public int getField6() {
		return Integer.parseInt(field6.getText());
	}

	public double getField7() {
		return Double.parseDouble(field7.getText());
	}

	public int getField8() {
		return Integer.parseInt(field8.getText());
	}

	// This method adds a component in a certain place of panel1.
	// The layout used here is GridBagLayout.
	// @param component to be added, x-coordinate in GridBagLayout, y-coordinate in
	// GridBagLayout
	private void panel1Add(Component o, int x, int y) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = x;
		c.gridy = y;
		panel1.add(o, c);
	}

	// This method adds a component in a certain place of panel2.
	// The layout used here is GridBagLayout.
	// @param component to be added, x-coordinate in GridBagLayout, y-coordinate in
	// GridBagLayout
	private void panel2Add(Component o, int x, int y) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = x;
		c.gridy = y;
		panel2.add(o, c);
	}

	// Action Listener of a button to draw the graph of the exponential growth
	class button1Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			eg.activateExponentialGrowth();
		}
	}

	// Action Listener of a button to draw the first graph of generation v.s.
	// population
	// of the logistic growth
	class button2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			lga.activateLogisticGrowthA();
		}
	}

	// Action Listener of a button to draw the graph of early population v.s. later
	// population of the logistic growth
	class button3Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			lgb.activateLogisticGrowthB();
		}
	}

	// Action Listener of a check box to set animation mode of the graph of the
	// exponential growth
	class box1Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// If the check box is selected, set the boolean "check1" to be true.
			// Otherwise, set the boolean to be false.
			if (box1.isSelected()) {
				eg.setAnimate(true);
			} else {
				eg.setAnimate(false);
			}
		}
	}

	// Action Listener of a check box to set animation mode of the first graph of
	// the logistic growth
	class box2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// If the check box is selected, set the boolean "check2" to be true.
			// Otherwise, set the boolean to be false.
			if (box2.isSelected()) {
				lga.setAnimate(true);
			} else {
				lga.setAnimate(false);
			}
		}
	}

	// Action Listener of a check box to set animation mode of the second graph of
	// the logistic growth
	class box3Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// If the check box is selected, set the boolean "check3" to be true.
			// Otherwise, set the boolean to be false.
			if (box3.isSelected()) {
				lgb.setAnimate(true);
			} else {
				lgb.setAnimate(false);
			}
		}
	}
}

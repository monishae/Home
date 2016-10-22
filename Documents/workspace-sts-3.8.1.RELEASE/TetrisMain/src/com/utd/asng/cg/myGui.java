package com.utd.asng.cg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

class myGui {

	private static void createAndShowGUI() {
		final String[] labels = { "Bill: ", "Last Top Up Date: ", "Same Network? " };
		int labelsLength = labels.length;
		final JTextField[] textField = new JTextField[labels.length];
		// Create and populate the panel.
		JPanel p = new JPanel(new SpringLayout());
//		for (int i = 0; i < labelsLength; i++) {
//			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
//			p.add(l);
//			textField[i] = new JTextField(10);
//			l.setLabelFor(textField[i]);
//			p.add(textField[i]);
//		}
		JButton button = new JButton("Submit");
		button.setBounds(200, 20, 10, 4);
		p.add(new JLabel());
		p.add(button);

		// Lay out the panel.

		
//		SpringUtilities.makeCompactGrid(p, labelsLength + 1, 2, // rows, cols
//				7, 7, // initX, initY
//				7, 7); // xPad, yPad

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Tetris();
					
			}
		});
		// Create and set up the window.
		JFrame frame = new JFrame("Tetris");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		p.setOpaque(true); // content panes must be opaque
	frame.setContentPane(p);
//
//		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

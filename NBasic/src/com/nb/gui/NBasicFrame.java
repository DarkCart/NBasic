package com.nb.gui;

import javax.swing.JFrame;

public class NBasicFrame {

	public static void createFrame(String title, int width, int height) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(title);
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}

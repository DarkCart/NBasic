package com.nb.gui;

import java.util.Vector;

import javax.swing.JFrame;

import com.nb.data.DataType;
import com.nb.data.Variable;
import com.nb.data.Vec2;

public class NBasicFrame extends Variable {

	public NBasicFrame(String title, Vec2 dimensions, DataType dataType) {
		super(title, dimensions, dataType);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(title);
		frame.setSize(dimensions.x, dimensions.y);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	public static void createFrame(String title, int width, int height) {
		
	}
}

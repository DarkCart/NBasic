package com.nb.methods;

import com.nb.core.Compiler;
import com.nb.data.DataType;
import com.nb.data.Vec2;
import com.nb.gui.NBasicFrame;

public class FrameMethods {

	public static void check(String[] arg) {
		// Creates frame with given parameters
		if (arg[0].equals("frame")) {
			Compiler.vars.add(new NBasicFrame(arg[1], arg[2], new Vec2(Integer
					.parseInt(arg[3]), Integer.parseInt(arg[4])),
					DataType.NULL));
		}
	}
	
}

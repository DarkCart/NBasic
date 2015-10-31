package com.nb.methods;

import com.nb.core.Compiler;
import com.nb.data.DataType;
import com.nb.data.Variable;

public class LoopMethods {

	public static void check(String[] arg) {
		if (arg[0].equals("for")) {
			String command = "";
			for(int l = 2; l < arg.length; l++) {
				command = command + arg[l]+":";
			}
			for (int j = 0; j < Integer.parseInt(arg[1]); j++) {
				Compiler.parse(command);
			}
		}
		if (arg[0].equals("while")) {
			String command = "";
			for(int l = 2; l < arg.length; l++) {
				command = command + arg[l]+":";
			}
			while (Boolean.parseBoolean(arg[1])) {
				Compiler.parse(command);
			}
		}
	}
	
}

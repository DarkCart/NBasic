package com.nb.methods;

import java.util.Scanner;

import com.nb.core.Compiler;
import com.nb.data.DataType;
import com.nb.data.Variable;
import com.nb.logging.Error;

public class VariableMethods {

	static Scanner input = new Scanner(System.in);

	public static void check(String[] arg) {
		// Set variable to another if they have same type
		if (arg[0].equals("set")) {
			for (Variable v : Compiler.vars) {
				if (v.getDataType() != DataType.CONST) {
					if (arg[1].equals(v.getName())) {
						for (Variable v1 : Compiler.vars) {
							if (arg[2].equals(v1.getName())) {
								if (v.getDataType() == v1.getDataType()) {
									v = v1;
								} else {
									new Error("VariableMismatchException", 1);
								}
							}
						}
					} else {
						new Error("Const cannot be changed", 1);
					}
				}
			}
		}

		// Saves input as a variable
		if (arg[0].equals("input")) {
			if (arg[1] != null) { // Checks the data type being input into
				String userInput = input.nextLine();
				switch (arg[1]) {
				case "num":
					Compiler.vars.add(new Variable(arg[2], userInput,
							DataType.NUM));
					break;
				case "string":
					Compiler.vars.add(new Variable(arg[2], userInput,
							DataType.STRING));
					break;
				}
			}
		}

		// Create variables
		if (arg[0].equals("string")) {
			if (arg[2].charAt(0) == '"'
					&& arg[2].charAt(arg[2].length() - 1) == '"') { // Checks if
																	// surrounded
																	// by quotes
				arg[2] = arg[2].substring(1, arg[2].length() - 1); // Strips
																	// away
																	// quotes
			} else { // Throws type mismatch error because there are no quotes
				new Error("Type Mismatch: " + arg[2]
						+ " needs to have quotes surrounding it.", 1);
			}
			Compiler.vars.add(new Variable(arg[1], arg[2], DataType.STRING));
		}
		if (arg[0].equals("double")) {
			Compiler.vars.add(new Variable(arg[1], Double.parseDouble(arg[2]),
					DataType.DOUBLE));
		}
		if (arg[0].equals("long")) {
			Compiler.vars.add(new Variable(arg[1], Long.parseLong(arg[2]),
					DataType.LONG));
		}
		if (arg[0].equals("num")) {
			Compiler.vars.add(new Variable(arg[1], arg[2], DataType.NUM));
		}
		if (arg[0].equals("const")) {
			Compiler.vars.add(new Variable(arg[1], arg[2], DataType.CONST));
		}
		if (arg[0].equals("bool") || arg[0].equals("boolean")) {
			Compiler.vars.add(new Variable(arg[1],
					Boolean.parseBoolean(arg[2]), DataType.BOOLEAN));
		}
		if (arg[0].equals("float")) {
			Compiler.vars.add(new Variable(arg[1], Float.parseFloat(arg[2]),
					DataType.FLOAT));
		}
	}

}

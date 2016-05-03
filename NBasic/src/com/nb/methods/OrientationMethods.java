package com.nb.methods;

import com.nb.core.Compiler;
import com.nb.data.DataType;
import com.nb.data.Variable;

public class OrientationMethods {

	public static void check(String[] arg) {
		// GOTO Function Syntax: goto: 1
		if (arg[0].equals("goto")) {
			int line = Integer.parseInt(arg[1]) + 1; // Parses the int
			Compiler.currentLine = line; // Sets the current line being scanned
											// to the line specified
		}

		// If Statements Syntax if: a > b:
		if (arg[0].equals("if")) {
			String[] statement = arg[1].split("==");
			statement[0] = statement[0].trim();
			statement[1] = statement[1].trim();
			for (Variable v : Compiler.vars) {
				if (statement[0].equals(v.getName())) {
					if (v.getDataType() == DataType.NUM) {
						int varVal = Integer.parseInt((String) v.getValue());
						int checkVal = Integer.parseInt(statement[1]);
						if (varVal == checkVal) {
							String command = "";
							for (int l = 2; l < arg.length; l++) {
								command = command + arg[l] + ":";
							}
							Compiler.parse(command);
						}
					}
				}
			}
		}

		// Exits NBasic program
		if (arg[0].equals("exit")) {
			System.exit(0);
		}

		// Puts program's thread to sleep for a given amount of milliseconds
		if (arg[0].equals("pause") || arg[0].equals("delay")) {
			try {
				Thread.sleep(Integer.parseInt(arg[1]));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

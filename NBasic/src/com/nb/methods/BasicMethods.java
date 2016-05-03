package com.nb.methods;

import com.nb.core.Compiler;
import com.nb.data.Function;
import com.nb.data.Variable;
import com.nb.logging.Error;

public class BasicMethods {

	public static void check(String[] arg) {
		// Print any type
		if (arg[0].equals("print")) {
			String line = arg[1].trim(); // Creates var of line and trims away whitespace
			if(line.startsWith("\"")) { // Checks if it is a raw string or variable name
				line = line.substring(1, line.length()-1); // Strips away quotes
				System.out.println(line);
			} else { // Obviously a variable because there aren't quotes
				for (Variable v : Compiler.vars) { // Cycle through all variables know.
					if (arg[1].equals(v.getName())) { // If it finds a match, print value.
						System.out.println(v.getValue());
						return;
					}
				}
				// If no match is found for the variable, yell at programmer because he has a nullpointer
				new Error("NullPointerException: " + line + " doesn't exist.", 1);
			}
		} else {
			for (Function f: Compiler.functions) {
				if (f.getName().equals(arg[0])) {
					Compiler.parse(f.getAction());
					return;
				}
			}
		}
	}
	
}
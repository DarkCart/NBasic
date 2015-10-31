package com.nb.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.nb.data.Variable;
import com.nb.methods.BasicMethods;
import com.nb.methods.FileMethods;
import com.nb.methods.FrameMethods;
import com.nb.methods.LoopMethods;
import com.nb.methods.MathMethods;
import com.nb.methods.OrientationMethods;
import com.nb.methods.VariableMethods;

public class Compiler {

	public static String textFile;
	public Scanner fileScanner;
	public String line;
	String userInput = "";
	String writeLine = "";
	public static int currentLine = 0;

	public static ArrayList<Variable> vars = new ArrayList<Variable>();

	public static void main(String[] args) {
		textFile = args[0];
		new Compiler();
	}

	public Compiler() {
		load();
	}

	public void load() {
		try {
			File file = new File(textFile);
			Scanner scanner = new Scanner(file);
			StringBuilder sb = new StringBuilder();			
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if(line != "") {
					sb.append(line + "\n");
				}
				
			}

			line = sb.toString();
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] lines = line.split("\n");
		for(currentLine = 0; currentLine < lines.length; currentLine++) {
			parse(lines[currentLine]);
		}
	}
	
	/*public String checkForEmptyLine(String s) {
		if(s == null) {
			s = " ";
			return s;
		} else {
			return s;
		}
	}*/

	public static void parse(String data) {
			if(!data.startsWith("//") && !data.startsWith("#")) {
				String[] arg = data.split(":");
				String longString = "";
				for(int ii = 0; ii < arg.length; ii++) {
					longString = longString.concat(arg[ii]);
					arg[ii] = arg[ii].trim(); // Trims off all whitespace so that people don't need to put spaces
				}
				//Parses out semi-colons
				if(longString.length() != 0) {
					String a = arg[arg.length-1];
					if(a.charAt(a.length()-1) == ';') { // Check if the last arg contains a semi-colon
						arg[arg.length-1] = a.replace(";", ""); // Removes semi-colon
					}
				}
				
				//////////////////// Call Actual Methods ////////////////////
				BasicMethods.check(arg);
				OrientationMethods.check(arg);
				MathMethods.check(arg);
				VariableMethods.check(arg);
				FileMethods.check(arg);
				FrameMethods.check(arg);
				LoopMethods.check(arg);
			}
	}
}
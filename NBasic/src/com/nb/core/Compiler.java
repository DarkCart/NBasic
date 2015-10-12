package com.nb.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.nb.data.DataType;
import com.nb.data.Variable;
import com.nb.gui.NBasicFrame;

public class Compiler {

	public static String textFile;
	public Scanner fileScanner;
	public String line;
	String userInput = "";
	String writeLine = "";

	ArrayList<Variable> vars = new ArrayList<Variable>();

	public static void main(String[] args) {
		textFile = args[0];
		new Compiler();
	}

	public Compiler() {
		load();
	}

	public void load() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(textFile));
			StringBuilder sb = new StringBuilder();
			line = br.readLine();

			while (line != null) {
				sb.append(line + "\n");
				line = br.readLine();
			}

			line = sb.toString();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		parse(line);
	}

	public void parse(String data) {

		String[] lines = data.split("\n");

		for (int i = 0; i < lines.length; i++) {

			String[] arg = lines[i].split(": ");
			
			
			//Print any type
			if (arg[0].equals("print")) {
				System.out.println(arg[1]);
			}
			
			//Print variable value
			if (arg[0].equals("printv")) {
				for (Variable v : vars) {
					if (arg[1].equals(v.getName())) {
						System.out.println(v.getValue());
					}
				}
			}
			
			//Add variables of type NUM
			if (arg[0].equals("add")) {
				for (Variable v : vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt((String) v.getValue())
								+ Integer.parseInt(arg[2]));
						break;
					}
					if (arg[2].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt(arg[1])
								+ Integer.parseInt((String) v.getValue()));
						break;
					}
				}
			}
			
			//Subtract variables of type NUM
			if (arg[0].equals("sub")) {
				for (Variable v : vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt((String) v.getValue())
								- Integer.parseInt(arg[2]));
						break;
					}
					if (arg[2].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt(arg[1])
								- Integer.parseInt((String) v.getValue()));
						break;
					}
				}
			}
			
			//Multiply variables of type NUM
			if (arg[0].equals("mul")) {
				for (Variable v : vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt((String) v.getValue())
								* Integer.parseInt(arg[2]));
						break;
					}
					if (arg[2].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt(arg[1])
								* Integer.parseInt((String) v.getValue()));
						break;
					}
				}
			}
			
			//Set variable to another if they have same type
			if (arg[0].equals("set")) {
				for (Variable v: vars) {
					if (arg[1].equals(v.getName())) {
						for (Variable v1: vars) {
							if (arg[2].equals(v1.getName())) {
								if (v.getDataType() == v1.getDataType()) {
									v = v1;
								} else {
									new Error("VariableMismatchException");
								}
							}
						}
					}
				}
			}
			
			/**
			 * print: Testing setting var to another var
				string: name: bob
				string: friend: tom
				set: name: friend
				printv: name
				printv: friend
				#name should be same as friend
			 */
			
			//Divide variables of type NUM
			if (arg[0].equals("div")) {
				for (Variable v : vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt((String) v.getValue())
								/ Integer.parseInt(arg[2]));
						break;
					}
					if (arg[2].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt(arg[1])
								/ Integer.parseInt((String) v.getValue()));
						break;
					}
				}
			}
			
			//Applies and exponent to a variable
			if (arg[0].equals("exp")) {
				for (Variable v : vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt((String) v.getValue())
								^ Integer.parseInt(arg[2]));
						break;
					}
					if (arg[2].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt(arg[1])
								^ Integer.parseInt((String) v.getValue()));
						break;
					}
				}
			}
			
			//Saves input as a variable
			if (arg[0].equals("input")) {
				userInput = new Scanner(System.in).nextLine();
				if (arg[1] != null) {
					vars.add(new Variable(arg[1], userInput, DataType.STRING));
				}
			}
			
			//Exits NBasic program
			if (arg[0].equals("exit")) {
				System.exit(0);
			}
			
			
			if (arg[0].equals("write")) {
				try {
					PrintWriter pr = new PrintWriter(arg[1]);
					if (arg[2].equals("input")) {
						pr.write(userInput);
					} else {
						pr.write(arg[2]);
					}
					pr.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			
			//Puts program's thread to sleep for a given amount of milliseconds
			if (arg[0].equals("pause") || arg[0].equals("delay")) {
				try {
					Thread.sleep(Integer.parseInt(arg[1]));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//Applies sine to a variable
			if (arg[0].equals("sin")) {
				for (Variable v : vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Math.sin(Integer.parseInt((String) v
								.getValue())));
						break;
					}
				}
			}
			
			//Applies cosine to a variable
			if (arg[0].equals("cos")) {
				for (Variable v : vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Math.cos(Integer.parseInt((String) v
								.getValue())));
						break;
					}
				}
			}
			
			//Prints out a random number
			if (arg[0].equals("rand")) {
				System.out.println(Math.random());
			}
			
			//Rounds variables of type NUM up
			if (arg[0].equals("ceil")) {
				for (Variable v : vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Math.ceil(Integer.parseInt((String) v
								.getValue())));
						break;
					}
				}
			}
			
			//Rounds variables of type NUM down
			if (arg[0].equals("floor")) {
				for (Variable v : vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Math.floor(Integer.parseInt((String) v
								.getValue())));
						break;
					}
				}
			}
			
			//Applies square root to variables of type NUM
			if (arg[0].equals("sqr")) {
				for (Variable v : vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Math.sqrt(Integer.parseInt((String) v
								.getValue())));
						break;
					}
				}
			}
			
			//Applies tangent to variables of type NUM
			if (arg[0].equals("tan")) {
				for (Variable v : vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Math.tan(Integer.parseInt((String) v
								.getValue())));
						break;
					}
				}
			}
			
			//Creates frame with given parameters
			if (arg[0].equals("frame")) {
				NBasicFrame.createFrame(arg[1], Integer.parseInt(arg[2]),
						Integer.parseInt(arg[3]));
			}
			
			//Create variables
			if (arg[0].equals("string")) {
				vars.add(new Variable(arg[1], arg[2], DataType.STRING));
			}
			if (arg[0].equals("double")) {
				vars.add(new Variable(arg[1], Double.parseDouble(arg[2]),
						DataType.DOUBLE));
			}
			if (arg[0].equals("long")) {
				vars.add(new Variable(arg[1], Long.parseLong(arg[2]),
						DataType.LONG));
			}
			if (arg[0].equals("num")) {
				vars.add(new Variable(arg[1], arg[2], DataType.NUM));
			}
			
			char[] comments = arg[0].toCharArray();
			if (Character.toString(comments[0]).equals("#")) {
				continue;
			}
		}
	}
}

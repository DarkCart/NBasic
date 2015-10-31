package com.nb.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.nb.data.DataType;
import com.nb.data.Variable;
import com.nb.data.Vec2;
import com.nb.gui.NBasicFrame;
import com.nb.logging.Error;

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
		parse(line);
	}
	
	/*public String checkForEmptyLine(String s) {
		if(s == null) {
			s = " ";
			return s;
		} else {
			return s;
		}
	}*/

	public void parse(String data) {

		String[] lines = data.split("\n");

		for (int i = 0; i < lines.length; i++) {
			if(!lines[i].startsWith("//") && !lines[i].startsWith("#")) {
				String[] arg = lines[i].split(":");
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

				// Print any type
				if (arg[0].equals("print")) {
					String line = arg[1].trim(); // Creates var of line and trims away whitespace
					if(line.startsWith("\"")) { // Checks if it is a raw string or variable name
						line = line.substring(1, line.length()-1); // Strips away quotes
						System.out.println(line);
					} else { // Obviously a variable because there aren't quotes
						for (Variable v : vars) { // Cycle through all variables know.
							if (arg[1].equals(v.getName())) { // If it finds a match, print value.
								System.out.println(v.getValue());
								return;
							}
						}
						// If no match is found for the variable, yell at programmer because he has a nullpointer
						new Error("NullPointerException: " + line + " doesn't exist.", 1);
					}
				}
				
				// GOTO Function Syntax: goto: 1
				/*if(arg[0].equals("goto")) {
					int line = Integer.parseInt(arg[1]); // Parses the int
					i = 0; // Sets the current line being scanned to the line specified
				}*/
				
				// If Statements Syntax if: a > b: 
				/*if(arg[0].equals("if")) {
					String[] statement = arg[1].split("==");
					statement[0] = statement[0].trim();
					statement[1] = statement[1].trim();
					for (Variable v : vars) {
						if (statement[0].equals(v.getName())) {
							if (v.getDataType() == DataType.NUM) {
								int varVal = (int)v.getValue();
								int checkVal = Integer.parseInt(statement[1]);
								if(varVal == checkVal) {
									System.out.println("yay");
								} else {
									System.out.println("no");
								}
							}
						}
					}
				}*/
				
				// Add variables of type NUM
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
						} else {
							System.out.println(Integer.parseInt(arg[1])
									+ Integer.parseInt(arg[2]));
							break;
						}
					}
				}

				// Subtract variables of type NUM
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
						} else {
							System.out.println(Integer.parseInt(arg[1])
									- Integer.parseInt(arg[2]));
							break;
						}
					}
				}

				// Multiply variables of type NUM
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
						} else {
							System.out.println(Integer.parseInt(arg[1])
									* Integer.parseInt(arg[2]));
							break;
						}
					}
				}

				// Set variable to another if they have same type
				if (arg[0].equals("set")) {
					for (Variable v : vars) {
						if (arg[1].equals(v.getName())) {
							for (Variable v1 : vars) {
								if (arg[2].equals(v1.getName())) {
									if (v.getDataType() == v1.getDataType()) {
										v = v1;
									} else {
										new Error("VariableMismatchException", 1);
									}
								}
							}
						}
					}
				}

				/**
				 * print: Testing setting var to another var string: name: bob
				 * string: friend: tom set: name: friend printv: name printv: friend
				 * #name should be same as friend
				 */

				// Divide variables of type NUM
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
						} else {
							System.out.println(Integer.parseInt(arg[1])
									/ Integer.parseInt(arg[2]));
							break;
						}
					}
				}

				// Applies and exponent to a variable
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
						} else {
							System.out.println(Integer.parseInt(arg[1])
									^ Integer.parseInt(arg[2]));
							break;
						}
					}
				}

				// Saves input as a variable
				if (arg[0].equals("input")) {
					userInput = new Scanner(System.in).nextLine();
					if (arg[1] != null) {
						vars.add(new Variable(arg[1], userInput, DataType.STRING));
					}
				}

				// Exits NBasic program
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

				// Applies sine to a variable
				if (arg[0].equals("sin")) {
					for (Variable v : vars) {
						if (arg[1].equals(v.getName())
								&& v.getDataType() == DataType.NUM) {
							v.setValue(Math.sin(Integer.parseInt((String) v
									.getValue())));
							break;
						} else {
							System.out.println(Math.sin(Integer.parseInt(arg[1])));
							break;
						}
					}
				}

				// Applies cosine to a variable
				if (arg[0].equals("cos")) {
					for (Variable v : vars) {
						if (arg[1].equals(v.getName())
								&& v.getDataType() == DataType.NUM) {
							v.setValue(Math.cos(Integer.parseInt((String) v
									.getValue())));
							break;
						} else {
							System.out.println(Math.cos(Integer.parseInt(arg[1])));
							break;
						}
					}
				}

				// Prints out a random number
				if (arg[0].equals("rand")) {
					System.out.println(Math.random());
				}

				// Rounds variables of type NUM up
				if (arg[0].equals("ceil")) {
					for (Variable v : vars) {
						if (arg[1].equals(v.getName())
								&& v.getDataType() == DataType.NUM) {
							v.setValue(Math.ceil(Integer.parseInt((String) v
									.getValue())));
							break;
						} else {
							System.out.println(Math.ceil(Integer.parseInt(arg[1])));
							break;
						}
					}
				}

				// Rounds variables of type NUM down
				if (arg[0].equals("floor")) {
					for (Variable v : vars) {
						if (arg[1].equals(v.getName())
								&& v.getDataType() == DataType.NUM) {
							v.setValue(Math.floor(Integer.parseInt((String) v
									.getValue())));
							break;
						} else {
							System.out
									.println(Math.floor(Integer.parseInt(arg[1])));
							break;
						}
					}
				}

				// Applies square root to variables of type NUM
				if (arg[0].equals("sqr")) {
					for (Variable v : vars) {
						if (arg[1].equals(v.getName())
								&& v.getDataType() == DataType.NUM) {
							v.setValue(Math.sqrt(Integer.parseInt((String) v
									.getValue())));
							break;
						} else {
							System.out.println(Math.sqrt(Integer.parseInt(arg[1])));
							break;
						}
					}
				}

				// Applies tangent to variables of type NUM
				if (arg[0].equals("tan")) {
					for (Variable v : vars) {
						if (arg[1].equals(v.getName())
								&& v.getDataType() == DataType.NUM) {
							v.setValue(Math.tan(Integer.parseInt((String) v
									.getValue())));
							break;
						} else {
							System.out.println(Math.tan(Integer.parseInt(arg[1])));
							break;
						}
					}
				}

				// Creates frame with given parameters
				if (arg[0].equals("frame")) {
					vars.add(new NBasicFrame(arg[1], arg[2], new Vec2(Integer
							.parseInt(arg[3]), Integer.parseInt(arg[4])),
							DataType.NBFRAME));
				}

				// Create variables
				if (arg[0].equals("string")) {
					if(arg[2].charAt(0) == '"' && arg[2].charAt(arg[2].length()-1) == '"') { // Checks if surrounded by quotes
						arg[2] = arg[2].substring(1, arg[2].length()-1); // Strips away quotes
					} else { // Throws type mismatch error because there are no quotes
						new Error("Type Mismatch: " + arg[2] + " needs to have quotes surrounding it.", 1);
					}
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
				if (arg[0].equals("for")) {
					for (int j = 0; j < Integer.parseInt(arg[1]); j++) {
						if (arg[2].equals("print")) {
							System.out.println(arg[3]);
						}
						if (arg[2].equals("add")) {
							for (Variable v : vars) {
								if (arg[1].equals(v.getName())
										&& v.getDataType() == DataType.NUM) {
									v.setValue(Integer.parseInt((String) v.getValue())
											+ Integer.parseInt(arg[4]));
									break;
								}
								if (arg[2].equals(v.getName())
										&& v.getDataType() == DataType.NUM) {
									v.setValue(Integer.parseInt(arg[3])
											+ Integer.parseInt((String) v.getValue()));
									break;
								} else {
									System.out.println(Integer.parseInt(arg[3])
											+ Integer.parseInt(arg[4]));
									break;
								}
							}
						}
						if (arg[2].equals("sub")) {
							for (Variable v : vars) {
								if (arg[1].equals(v.getName())
										&& v.getDataType() == DataType.NUM) {
									v.setValue(Integer.parseInt((String) v.getValue())
											- Integer.parseInt(arg[4]));
									break;
								}
								if (arg[2].equals(v.getName())
										&& v.getDataType() == DataType.NUM) {
									v.setValue(Integer.parseInt(arg[3])
											- Integer.parseInt((String) v.getValue()));
									break;
								} else {
									System.out.println(Integer.parseInt(arg[3])
											- Integer.parseInt(arg[4]));
									break;
								}
							}
						}
						if (arg[2].equals("mul")) {
							for (Variable v : vars) {
								if (arg[1].equals(v.getName())
										&& v.getDataType() == DataType.NUM) {
									v.setValue(Integer.parseInt((String) v.getValue())
											* Integer.parseInt(arg[4]));
									break;
								}
								if (arg[2].equals(v.getName())
										&& v.getDataType() == DataType.NUM) {
									v.setValue(Integer.parseInt(arg[3])
											* Integer.parseInt((String) v.getValue()));
									break;
								} else {
									System.out.println(Integer.parseInt(arg[3])
											* Integer.parseInt(arg[4]));
									break;
								}
							}
						}
						if (arg[2].equals("div")) {
							for (Variable v : vars) {
								if (arg[1].equals(v.getName())
										&& v.getDataType() == DataType.NUM) {
									v.setValue(Integer.parseInt((String) v.getValue())
											/ Integer.parseInt(arg[4]));
									break;
								}
								if (arg[2].equals(v.getName())
										&& v.getDataType() == DataType.NUM) {
									v.setValue(Integer.parseInt(arg[3])
											/ Integer.parseInt((String) v.getValue()));
									break;
								} else {
									System.out.println(Integer.parseInt(arg[3])
											/ Integer.parseInt(arg[4]));
									break;
								}
							}
						}
					}
				}
				if (arg[0].equals("while")) {
					while (Boolean.parseBoolean(arg[1])) {
						if (arg[2].equals("print")) {
							System.out.println(arg[3]);
						}
						if (arg[2].equals("add")) {
							for (Variable v : vars) {
								if (arg[1].equals(v.getName())
										&& v.getDataType() == DataType.NUM) {
									v.setValue(Integer.parseInt((String) v.getValue())
											+ Integer.parseInt(arg[4]));
									break;
								}
								if (arg[2].equals(v.getName())
										&& v.getDataType() == DataType.NUM) {
									v.setValue(Integer.parseInt(arg[3])
											+ Integer.parseInt((String) v.getValue()));
									break;
								} else {
									System.out.println(Integer.parseInt(arg[3])
											+ Integer.parseInt(arg[4]));
									break;
								}
							}
						}
						if (arg[2].equals("sub")) {
							for (Variable v : vars) {
								if (arg[1].equals(v.getName())
										&& v.getDataType() == DataType.NUM) {
									v.setValue(Integer.parseInt((String) v.getValue())
											- Integer.parseInt(arg[4]));
									break;
								}
								if (arg[2].equals(v.getName())
										&& v.getDataType() == DataType.NUM) {
									v.setValue(Integer.parseInt(arg[3])
											- Integer.parseInt((String) v.getValue()));
									break;
								} else {
									System.out.println(Integer.parseInt(arg[3])
											- Integer.parseInt(arg[4]));
									break;
								}
							}
						}
						if (arg[2].equals("mul")) {
							for (Variable v : vars) {
								if (arg[1].equals(v.getName())
										&& v.getDataType() == DataType.NUM) {
									v.setValue(Integer.parseInt((String) v.getValue())
											* Integer.parseInt(arg[4]));
									break;
								}
								if (arg[2].equals(v.getName())
										&& v.getDataType() == DataType.NUM) {
									v.setValue(Integer.parseInt(arg[3])
											* Integer.parseInt((String) v.getValue()));
									break;
								} else {
									System.out.println(Integer.parseInt(arg[3])
											* Integer.parseInt(arg[4]));
									break;
								}
							}
						}
						if (arg[2].equals("div")) {
							for (Variable v : vars) {
								if (arg[1].equals(v.getName())
										&& v.getDataType() == DataType.NUM) {
									v.setValue(Integer.parseInt((String) v.getValue())
											/ Integer.parseInt(arg[4]));
									break;
								}
								if (arg[2].equals(v.getName())
										&& v.getDataType() == DataType.NUM) {
									v.setValue(Integer.parseInt(arg[3])
											/ Integer.parseInt((String) v.getValue()));
									break;
								} else {
									System.out.println(Integer.parseInt(arg[3])
											/ Integer.parseInt(arg[4]));
									break;
								}
							}
						}
					}
				}
				
				if (arg[0].equals("adv")) {
					System.out.println(Integer.parseInt(arg[1]) + Integer.parseInt(arg[2]) / 2);
				}
			}
		}
	}
}
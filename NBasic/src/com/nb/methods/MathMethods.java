package com.nb.methods;

import com.nb.core.Compiler;
import com.nb.data.DataType;
import com.nb.data.Variable;

public class MathMethods {

	public static void check(String[] arg) {
		// Add variables of type NUM
		if (arg[0].equals("add")) {
			for (Variable v : Compiler.vars) {
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
			for (Variable v : Compiler.vars) {
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
			for (Variable v : Compiler.vars) {
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

		// Divide variables of type NUM
		if (arg[0].equals("div")) {
			for (Variable v : Compiler.vars) {
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
			for (Variable v : Compiler.vars) {
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

		// Applies sine to a variable
		if (arg[0].equals("sin")) {
			for (Variable v : Compiler.vars) {
				if (arg[1].equals(v.getName())
						&& v.getDataType() == DataType.NUM) {
					v.setValue(Math.sin(Integer.parseInt((String) v.getValue())));
					break;
				} else {
					System.out.println(Math.sin(Integer.parseInt(arg[1])));
					break;
				}
			}
		}

		// Applies cosine to a variable
		if (arg[0].equals("cos")) {
			for (Variable v : Compiler.vars) {
				if (arg[1].equals(v.getName())
						&& v.getDataType() == DataType.NUM) {
					v.setValue(Math.cos(Integer.parseInt((String) v.getValue())));
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
			for (Variable v : Compiler.vars) {
				if (arg[1].equals(v.getName())
						&& v.getDataType() == DataType.NUM) {
					v.setValue(Math.ceil(Integer.parseInt((String) v.getValue())));
					break;
				} else {
					System.out.println(Math.ceil(Integer.parseInt(arg[1])));
					break;
				}
			}
		}

		// Rounds variables of type NUM down
		if (arg[0].equals("floor")) {
			for (Variable v : Compiler.vars) {
				if (arg[1].equals(v.getName())
						&& v.getDataType() == DataType.NUM) {
					v.setValue(Math.floor(Integer.parseInt((String) v
							.getValue())));
					break;
				} else {
					System.out.println(Math.floor(Integer.parseInt(arg[1])));
					break;
				}
			}
		}

		// Applies square root to variables of type NUM
		if (arg[0].equals("sqr")) {
			for (Variable v : Compiler.vars) {
				if (arg[1].equals(v.getName())
						&& v.getDataType() == DataType.NUM) {
					v.setValue(Math.sqrt(Integer.parseInt((String) v.getValue())));
					break;
				} else {
					System.out.println(Math.sqrt(Integer.parseInt(arg[1])));
					break;
				}
			}
		}

		// Applies tangent to variables of type NUM
		if (arg[0].equals("tan")) {
			for (Variable v : Compiler.vars) {
				if (arg[1].equals(v.getName())
						&& v.getDataType() == DataType.NUM) {
					v.setValue(Math.tan(Integer.parseInt((String) v.getValue())));
					break;
				} else {
					System.out.println(Math.tan(Integer.parseInt(arg[1])));
					break;
				}
			}
		}
		// Applies Absolute value to NUM
		if (arg[0].equals("abs")) {
			for (Variable v : Compiler.vars) {
				if (arg[1].equals(v.getName())
						&& v.getDataType() == DataType.NUM) {
					v.setValue(Math.abs(Integer.parseInt((String) v.getValue())));
					break;
				} else {
					System.out.println(Math.abs(Integer.parseInt(arg[1])));
					break;
				}
			}
		}
	}

}

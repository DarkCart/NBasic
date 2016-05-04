package com.nb.methods;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.nb.core.Compiler;
import com.nb.logging.Error;

public class FileMethods {

	public static void check(String[] arg) {
		if (arg[0].equals("write")) {
			Scanner input = new Scanner(System.in);
			try {
				PrintWriter pr = new PrintWriter(arg[1]);
				if (arg[2].equals("input")) {
					pr.write(input.nextLine());
				} else {
					pr.write(arg[2]);
				}
				pr.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			input.close();
		}
		if (arg[0].equals("include")) {
			if (Compiler.currentLine != 0) {
				new Error("Includes must come at the beginning", 1);
			} else {
				Compiler.parse(Compiler.load(arg[1]));
			}
		}
	}

}

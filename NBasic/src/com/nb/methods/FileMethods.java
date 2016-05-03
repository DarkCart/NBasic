package com.nb.methods;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

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
	}

}

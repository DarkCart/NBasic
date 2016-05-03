package com.nb.methods;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.nb.core.Compiler;
import com.nb.data.Function;
import com.nb.data.Variable;

public class MiscMethods {

	public static void check(String[] arg) {
		if (arg[0].equals("typeof")) {
			for (Variable v : Compiler.vars) {
				if (arg[1].equals(v.getName())) {
					System.out.println(v.getDataType());
				}
			}
		}
		if (arg[0].equals("date")) {
			System.out.println(new Date());
		}
		if (arg[0].equals("time")) {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println(sdf.format(cal.getTime()));
		}
		if (arg[0].equals("function")) {
			String command = "";
			for(int l = 2; l < arg.length; l++) {
				command = command + arg[l]+":";
			}
			Compiler.functions.add(new Function(arg[1], command));
		}
		if (arg[0].equals("break")) {
			Compiler.currentLine += 1;
		}
	}
}

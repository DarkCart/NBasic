package com.nb.methods;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.nb.core.Compiler;
import com.nb.data.Variable;
import com.nb.logging.Error;

public class MiscMethods {

	public static void check(String[] arg) {
		if (arg[0].equals("typeof")) {
			for (Variable v : Compiler.vars) {
				if (arg[1].equals(v.getName())) {
					System.out.println(v.getDataType());
				} else {
					new Error("Variable not found", 1);
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
	}
}

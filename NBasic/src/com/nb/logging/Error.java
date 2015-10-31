package com.nb.logging;

import com.nb.core.Compiler;

public class Error {
		
	//example would be "badThing = new PoopyException("you didn't poop right", 1)"
	public Error(String exception, int status) {
		System.out.println("Error: " + exception + "!" + " On line " + (Compiler.currentLine+1));
		
		if(status >=1)
			System.exit(status);
	}
	
}
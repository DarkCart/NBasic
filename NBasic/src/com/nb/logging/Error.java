package com.nb.logging;

public class Error {
		
	//example would be "badThing = new PoopyException("you didn't poop right", 1)"
	public Error(String exception, int status) {
		System.out.println("Error: " + exception + "!");
		
		if(status >=1)
			System.exit(status);
	}
	
}
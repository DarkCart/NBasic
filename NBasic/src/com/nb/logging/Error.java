package com.nb.logging;

import java.util.Random;

import com.nb.core.Compiler;

public class Error {
	
	String[] curses = {"^#*$!", "Shucks!", "Oops!", "Whoops!", "Fiddlesticks!"};
		
	//example would be "badThing = new PoopyException("you didn't poop right", 1)"
	public Error(String exception, int status) {
		String curse = curses[new Random().nextInt(curses.length - 1)];
		System.out.println(curse + " Error: " + exception + "!" + " On line " + (Compiler.currentLine+1));
		
		if(status >=1)
			System.exit(status);
	}
	
}
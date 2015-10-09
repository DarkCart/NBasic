import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFrame;

public class Compiler {

	public static String textFile;
	public Scanner fileScanner;
	public String line;
	String userInput = "";
	String writeLine = "";

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
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			
			line = sb.toString();
			br.close();
		} catch (Exception e) { e.printStackTrace(); }
		parse(line);
	}

	public void parse(String data) {
		
		String[] lines = data.split("\n");
		
		for (int i = 0; i < lines.length; i++) {
			
			String[] arg = lines[i].split(": ");
			
			if (lines[i].contains("print")) {
				if (arg[1].equals("input")) {
					System.out.println(userInput);
				} else {
					System.out.println(arg[1]);
				}
			}
			
			if (lines[i].contains("add")) {
				System.out.println(Integer.parseInt(arg[1])
						+ Integer.parseInt(arg[2]));
			}
			
			if (lines[i].contains("sub")) {
				System.out.println(Integer.parseInt(arg[1])
						- Integer.parseInt(arg[2]));
			}
			
			if (lines[i].contains("mul")) {
				System.out.println(Integer.parseInt(arg[1])
						* Integer.parseInt(arg[2]));
			}
			
			if (lines[i].contains("div")) {
				System.out.println(Integer.parseInt(arg[1])
						/ Integer.parseInt(arg[2]));
			}
			
			if (lines[i].contains("exp")) {
				System.out.println(Integer.parseInt(arg[1])
						^ Integer.parseInt(arg[2]));
			}
			
			if (lines[i].contains("input")) {
				userInput = new Scanner(System.in).nextLine();
			}
			
			if (lines[i].equals("exit")) {
				System.exit(0);
			}
			
			if (lines[i].contains("write")) {
				try {
					PrintWriter pr = new PrintWriter(arg[1]);
					if (arg[2].equals("input")) {
						pr.write(userInput);
					} else {
						pr.write(arg[2]);
					}
					pr.close();
				} catch (FileNotFoundException e) {e.printStackTrace();}
			}
			
			if (lines[i].contains("pause") || lines[i].contains("delay")) {
				try {
					Thread.sleep(Integer.parseInt(arg[1]));
				} catch (NumberFormatException e) {e.printStackTrace();} catch (InterruptedException e) {e.printStackTrace();}
			}
			
			if (lines[i].contains("sin")) {
				System.out.println(Math.sin(Integer.parseInt(arg[1])));
			}
			
			if (lines[i].contains("cos")) {
				System.out.println(Math.cos(Integer.parseInt(arg[1])));
			}
			
			if (lines[i].contains("rand")) {
				System.out.println(Math.random());
			}
			
			if (lines[i].contains("ceil")) {
				System.out.println(Math.ceil(Integer.parseInt(arg[1])));
			}
			
			if (lines[i].contains("floor")) {
				System.out.println(Math.floor(Integer.parseInt(arg[1])));
			}
			
			if (lines[i].contains("sqr")) {
				System.out.println(Math.sqrt(Integer.parseInt(arg[1])));
			}
			
			if (lines[i].contains("tan")) {
				System.out.println(Math.tan(Integer.parseInt(arg[1])));
			}
			
			if (lines[i].contains("frame")) {
				int width = Integer.parseInt(arg[2]);
				int height = Integer.parseInt(arg[3]);
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle(arg[1]);
				frame.setSize(width, height);
				frame.setResizable(false);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
			
		}
	}
}

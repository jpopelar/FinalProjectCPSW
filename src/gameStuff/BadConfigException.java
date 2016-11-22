package gameStuff;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class BadConfigException extends Exception{

	public BadConfigException() {
		super("The file is not in the correct format.");
	}
	
	
	public BadConfigException(String message){
		super(message);
		PrintWriter out;
		try {
			out = new PrintWriter("logfile.txt");
			out.println(message);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find output log file");
					}
		
	}
	

}
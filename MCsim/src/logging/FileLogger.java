package logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class FileLogger extends Logger{

	BufferedWriter out;
	
	public FileLogger(URGENCY GIVEN_URGENCY) throws FileNotFoundException {
		
		super(GIVEN_URGENCY);
		
		File f = new File("log.txt");
		if(!f.exists()) { 
		   throw new FileNotFoundException();
		}
		
	}
	
	protected void print(String message) {
		
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(new File("log.txt"),true));
			out.println(message );
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		
	}
	

}

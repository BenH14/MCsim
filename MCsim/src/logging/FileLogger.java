package logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FileLogger extends Logger{

	private File f;

	public FileLogger(URGENCY GIVEN_URGENCY) throws FileNotFoundException {

		super(GIVEN_URGENCY);

		f = new File("log.txt");
		if(!f.exists()) {
				throw new FileNotFoundException();
		}

	}

	protected void print(String message) {

		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(f,true));
			out.println(message );
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


	}


}

package logging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Logger {

	public enum URGENCY {
		STATUS,
		DEBUG,
		ERROR,
		FATAL,
		UNKOWN
	}

	protected URGENCY LOG_URGENCY;

	public Logger() {
		LOG_URGENCY = URGENCY.UNKOWN;
	}

	public Logger(URGENCY GIVEN_URGENCY) {
		LOG_URGENCY = GIVEN_URGENCY;
	}

	public void write(String message) {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		switch(LOG_URGENCY) {
		case DEBUG: message = "<#>   - " + sdf.format(cal.getTime()) + " : " + message;
		break;
		case ERROR: message = "<!>   - " + sdf.format(cal.getTime()) + " : " + message;
		break;
		case FATAL: message = "<!!!> - " + sdf.format(cal.getTime()) + " : " + message;
		break;
		case STATUS:message = "<OK>  - " + sdf.format(cal.getTime()) + " : " + message;
		break;
		case UNKOWN:message = "<?>   - " + sdf.format(cal.getTime()) + " : " + message;
		break;
		}

		print(message);
	}

	public void startup() {

		File f = new File("log.txt");

		try {
			
			f.createNewFile();

			PrintWriter out = new PrintWriter(new FileOutputStream(f,false));

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

			out.println("New Launch at " + sdf.format(cal.getTime()));

			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected abstract void print(String message);



}

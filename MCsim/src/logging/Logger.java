package logging;

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
	
	protected abstract void print(String message);
	
	
	
}

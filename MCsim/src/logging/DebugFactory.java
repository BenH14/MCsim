package logging;

import java.io.FileNotFoundException;

public class DebugFactory {

	public static Logger getDebug(Logger.URGENCY GIVEN_URGENCY) {
		try {
			return new FileLogger(GIVEN_URGENCY);
		} catch (FileNotFoundException ex) {
			return new ConsoleLogger(GIVEN_URGENCY);
		}
	}
	
}

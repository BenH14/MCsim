package main;

import logging.DebugFactory;
import logging.Logger;

public class Launch {

	public static void main(String[] args) {
		
		DebugFactory.getDebug(Logger.URGENCY.STATUS).startup();
		
		SuperController sc = new SuperController();
		
		DebugFactory.getDebug(Logger.URGENCY.STATUS).write("Starting Main Controller Loop");
		
		sc.loop();
		
		DebugFactory.getDebug(Logger.URGENCY.FATAL).write("Main Controller Loop has ended, program ending");

	}

}

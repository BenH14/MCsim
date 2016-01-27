package logging;

public class ConsoleLogger extends Logger{

	public ConsoleLogger(URGENCY GIVEN_URGENCY) {
		super(GIVEN_URGENCY);
	}
	
	protected void print(String message) {
		System.out.println(message);	
	}

}

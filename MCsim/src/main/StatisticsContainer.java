package main;

public class StatisticsContainer {

	//Time
	public int gameTime; //In ticks
	public double gameTimeSecs; //In seconds
	
	public StatisticsContainer() {

		gameTime = 0;
		gameTimeSecs = 0;
		
	}
	
	public void tick() {
		
		//Update Time
		gameTime++;
		gameTimeSecs = gameTimeSecs / 60;
		
		
	}
	
}

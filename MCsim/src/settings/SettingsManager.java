package settings;

import java.awt.AWTError;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import logging.DebugFactory;
import logging.Logger;

public class SettingsManager {	

	//Tells us if the program has been run before
	static boolean firstTime;
	//Tells us if the splashscreen should be shown
	static boolean splashScreen;

	//Video rendering settings
	static boolean TextAA;
	static boolean AA;
	static boolean Dithering;
	static boolean HighQuality;
	static boolean AlphaInterpolation;
	static boolean HighQualityColour;
	static boolean Undecorated;
	static int textLCDContrast;

	static float volume;

	//Variables to store the state of each setting
	static int res[];
	static int keyBinding[];

	//Properties file path
	static String filePath;

	//Initiate the SettingsManager, as it is fully static and therefore doesn't have a constructor
	public static void init() {

		filePath = "config.properties";
		res = new int[2];
		keyBinding = new int[8];

		loadSettings();

		if(firstTime == true) {
			DebugFactory.getDebug(Logger.URGENCY.STATUS).write("First Time Detected, using default settings");
			restoreDefaultSettings();
			loadSettings();
		}

	}

	//Load the settings
	public static void loadSettings() {

		DebugFactory.getDebug(Logger.URGENCY.STATUS).write("Trying to load settings File...");

		Properties prop = new Properties();

		InputStream in = null;

		try {

			in = new FileLoader().getInStream(filePath);

			//LOAD FILE
			prop.load(in);

		} catch (Exception ex) {
			DebugFactory.getDebug(Logger.URGENCY.ERROR).write("Error in loading properties file - " + ex.toString());
			DebugFactory.getDebug(Logger.URGENCY.STATUS).write("Assuming no properties file exists, restoring default settings");
			restoreDefaultSettings();
		} finally { if (in != null) {try {in.close();} catch (IOException ignored) {}}}


		//GET VALUES
		firstTime = Boolean.parseBoolean(prop.getProperty("First Time"));
		splashScreen = Boolean.parseBoolean(prop.getProperty("Enable Splashscreen"));

		res[0] = Integer.parseInt(prop.getProperty("Resoloution (X)"));
		res[1] = Integer.parseInt(prop.getProperty("Resoloution (Y)"));

		setKeyCode("left", Integer.parseInt(prop.getProperty("Key Binding : Left")));
		setKeyCode("right", Integer.parseInt(prop.getProperty("Key Binding : Right")));
		setKeyCode("up", Integer.parseInt(prop.getProperty("Key Binding : Up")));
		setKeyCode("down", Integer.parseInt(prop.getProperty("Key Binding : Down")));
		setKeyCode("pause", Integer.parseInt(prop.getProperty("Key Binding : Pause")));

		TextAA = Boolean.parseBoolean(prop.getProperty("Text Antialiasing"));
		AA = Boolean.parseBoolean(prop.getProperty("Antialiasing"));
		Dithering = Boolean.parseBoolean(prop.getProperty("Dithering"));
		HighQuality = Boolean.parseBoolean(prop.getProperty("High Quality Rendering"));
		AlphaInterpolation = Boolean.parseBoolean(prop.getProperty("Alpha Interpolation"));
		HighQualityColour = Boolean.parseBoolean(prop.getProperty("High Quality Colour"));
		Undecorated = Boolean.parseBoolean(prop.getProperty("Undecorated"));
		textLCDContrast = Integer.parseInt(prop.getProperty("Text LCD Contrast"));

		volume = Float.parseFloat(prop.getProperty("Volume"));

		DebugFactory.getDebug(Logger.URGENCY.STATUS).write("... Settings File loaded");

	}

	//Save the settings
	public static void saveSettings() {

		DebugFactory.getDebug(Logger.URGENCY.STATUS).write("Trying to save settings File...");

		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileLoader().getOutStream(filePath);

			//SET VALUES
			prop.setProperty("First Time", Boolean.toString(firstTime));
			prop.setProperty("Enable Splashscreen", Boolean.toString(splashScreen));
			//Resoloution
			prop.setProperty("Resoloution (X)", Integer.toString(res[0]));
			prop.setProperty("Resoloution (Y)", Integer.toString(res[1]));
			//Key Bindings
			prop.setProperty("Key Binding : Left", Integer.toString(getKeyCode("left")));
			prop.setProperty("Key Binding : Right", Integer.toString(getKeyCode("right")));
			prop.setProperty("Key Binding : Up", Integer.toString(getKeyCode("up")));
			prop.setProperty("Key Binding : Down", Integer.toString(getKeyCode("down")));
			prop.setProperty("Key Binding : Pause", Integer.toString(getKeyCode("pause")));
			//Video Settings
			prop.setProperty("Text Antialiasing", Boolean.toString(TextAA));
			prop.setProperty("Antialiasing", Boolean.toString(AA));
			prop.setProperty("Dithering", Boolean.toString(Dithering));
			prop.setProperty("High Quality Rendering", Boolean.toString(HighQuality));
			prop.setProperty("Alpha Interpolation", Boolean.toString(AlphaInterpolation));
			prop.setProperty("High Quality Colour", Boolean.toString(HighQualityColour));
			prop.setProperty("Undecorated", Boolean.toString(Undecorated));
			prop.setProperty("Text LCD Contrast", Integer.toString(textLCDContrast));

			prop.setProperty("Volume", Float.toString(volume));

			//SAVE FILE
			prop.store(output, null);

		} catch (IOException | URISyntaxException io) {
			DebugFactory.getDebug(Logger.URGENCY.ERROR).write("Error in saving properties file - " + io.toString());
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException io){
					io.printStackTrace();
				}
			}
		}

		DebugFactory.getDebug(Logger.URGENCY.STATUS).write("... Settings File saved");

	}

	//Restore default settings
	public static void restoreDefaultSettings() {

		firstTime = false;
		splashScreen = true;

		//Get the default size of the primary monitor, not using toolkit as that messes up multi-monitor setups
		try {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		res[0] = gd.getDisplayMode().getWidth();
		System.out.println(res[0]);
		res[1] = gd.getDisplayMode().getHeight();
		System.out.println(res[1]);
		} catch (AWTError fuckxorg) {
			DebugFactory.getDebug(Logger.URGENCY.ERROR).write("Cannot connect to xorg server - " + fuckxorg.toString());
			System.out.println("IMPORTANT - We cannot seem to connect to the Graphics Device, please set your resoloution manually in the config.properties file");
			res[0] = 1366;
			res[1] = 768;
		}

		//Default Bindings are WAD for Jump/Left/Right respectively, spacebar is primary attack, ctrl for secondary attack, placeblock as q, pause as esc
		setKeyCode("left", 37);
		setKeyCode("right", 39);
		setKeyCode("up", 38);
		setKeyCode("down", 40);
		setKeyCode("pause", 0);

		TextAA = true;
		AA = true;
		Dithering = true;
		HighQuality = true;
		AlphaInterpolation = true;
		HighQualityColour = true;
		Undecorated = true;
		textLCDContrast = 100; 

		volume = 0.0f;

		saveSettings();

	}


	//===========================================================================================================================================
	//>>>>>>>>>> GET AND SET FUNCTIONS | GET AND SET FUNCTIONS | GET AND SET FUNCTIONS | GET AND SET FUNCTIONS | GET AND SET FUNCTIONS <<<<<<<<<<
	//===========================================================================================================================================

	//========================
	//RESOLOUTION
	//========================

	public static int getResX() {return res[0];}
	public static void setResX(int givenResX) {res[0] = givenResX;}
	public static int getResY() {return res[1];}
	public static void setResY(int givenResY) {res[1] = givenResY;}

	//========================
	//VIDEO SETTINGS
	//========================

	public static Graphics2D setRenderingHints(Graphics2D g2d) {

		if(AA == true) g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(TextAA == true) g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		if(HighQuality == true) g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		if(Dithering == true) g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		if(AlphaInterpolation == true) g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		if(HighQualityColour == true) g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

		g2d.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, textLCDContrast);

		return g2d;
	}
	
	public static boolean getUndecorated() {return Undecorated;}
	public static void setUndecorated(boolean given) {Undecorated = given;}

	//========================
	//OTHER SETTINGS
	//========================

	public static boolean getSplashScreenEnabled() {return splashScreen;}
	public static void setSplashScreenEnabled(boolean given) {splashScreen = given;}

	public static float getVolume() {return volume;}
	public static void setVolume(float givenVolume){volume = givenVolume;}



	//========================
	//KEY BINDINGS
	//========================
	//======Possible actions: 
	//		left
	//		right
	//		up
	//		down
	//		pause
	//=======================
	public static int[] getKeyCode() {
		return keyBinding;
	}

	public static int getKeyCode(String action) {

		int result = -1;

		switch(action) {
		case "left": result = keyBinding[0];
		break;
		case "right": result = keyBinding[1]; 
		break;
		case "up": result = keyBinding[2]; 
		break;
		case "down": result = keyBinding[3];
		break;
		case "pause": result = keyBinding[6];
		break;			
		};		

		if(result == -1) {
			DebugFactory.getDebug(Logger.URGENCY.FATAL).write("Improper call for getKeyCode in SettingsManager");
			System.exit(0);
		}

		return result;

	}
	public static void setKeyCode(String action, int keyCode) {

		switch(action) {
		case "left": keyBinding[0] = keyCode;
		break;
		case "right": keyBinding[1] = keyCode; 
		break;
		case "up": keyBinding[2] = keyCode; 
		break;
		case "down": keyBinding[3] = keyCode;
		break;
		case "pause": keyBinding[6] = keyCode;
		break;
		};	

	}

}

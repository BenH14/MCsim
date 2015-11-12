package settings;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

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
	static int textLCDContrast;
	
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
			System.out.println("FIRST TIME SETUP");
			restoreDefaultSettings();
		}
		
	}

	//Load the settings
	public static void loadSettings() {

		System.out.println("Trying to load settings File...");

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(filePath);

			//LOAD FILE
			prop.load(input);
			
			//GET VALUES
			firstTime = Boolean.parseBoolean(prop.getProperty("First Time"));
			splashScreen = Boolean.parseBoolean(prop.getProperty("Enable Splashscreen"));
			
			res[0] = Integer.parseInt(prop.getProperty("Resoloution (X)"));
			res[1] = Integer.parseInt(prop.getProperty("Resoloution (Y)"));
			
			setKeyCode("left", Integer.parseInt(prop.getProperty("Key Binding : Left")));
			setKeyCode("right", Integer.parseInt(prop.getProperty("Key Binding : Right")));
			setKeyCode("jump", Integer.parseInt(prop.getProperty("Key Binding : Jump")));
			setKeyCode("pattack", Integer.parseInt(prop.getProperty("Key Binding : Primary Attack")));
			setKeyCode("sattack", Integer.parseInt(prop.getProperty("Key Binding : Secondary Attack")));
			setKeyCode("placeblock", Integer.parseInt(prop.getProperty("Key Binding : Place Block")));
			setKeyCode("pause", Integer.parseInt(prop.getProperty("Key Binding : Pause")));
			setKeyCode("openinv", Integer.parseInt(prop.getProperty("Key Binding : Open Inventory")));
			
			TextAA = Boolean.parseBoolean(prop.getProperty("Text Antialiasing"));
			AA = Boolean.parseBoolean(prop.getProperty("Antialiasing"));
			Dithering = Boolean.parseBoolean(prop.getProperty("Dithering"));
			HighQuality = Boolean.parseBoolean(prop.getProperty("High Quality Rendering"));
			AlphaInterpolation = Boolean.parseBoolean(prop.getProperty("Alpha Interpolation"));
			HighQualityColour = Boolean.parseBoolean(prop.getProperty("High Quality Colour"));
			textLCDContrast = Integer.parseInt(prop.getProperty("Text LCD Contrast"));

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException io){
					io.printStackTrace();
				}
			}
		}

		System.out.println("... Settings File saved");

	}

	//Save the settings
	public static void saveSettings() {

		System.out.println("Trying to save settings File...");

		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream(filePath);

			//SET VALUES
			prop.setProperty("First Time", Boolean.toString(firstTime));
			prop.setProperty("Enable Splashscreen", Boolean.toString(splashScreen));
			//Resoloution
			prop.setProperty("Resoloution (X)", Integer.toString(res[0]));
			prop.setProperty("Resoloution (Y)", Integer.toString(res[1]));
			//Key Bindings
			prop.setProperty("Key Binding : Left", Integer.toString(getKeyCode("left")));
			prop.setProperty("Key Binding : Right", Integer.toString(getKeyCode("right")));
			prop.setProperty("Key Binding : Jump", Integer.toString(getKeyCode("jump")));
			prop.setProperty("Key Binding : Primary Attack", Integer.toString(getKeyCode("pattack")));
			prop.setProperty("Key Binding : Secondary Attack", Integer.toString(getKeyCode("sattack")));
			prop.setProperty("Key Binding : Place Block", Integer.toString(getKeyCode("placeblock")));
			prop.setProperty("Key Binding : Pause", Integer.toString(getKeyCode("pause")));
			prop.setProperty("Key Binding : Open Inventory", Integer.toString(getKeyCode("openinv")));
			//Video Settings
			prop.setProperty("Text Antialiasing", Boolean.toString(TextAA));
			prop.setProperty("Antialiasing", Boolean.toString(AA));
			prop.setProperty("Dithering", Boolean.toString(Dithering));
			prop.setProperty("High Quality Rendering", Boolean.toString(HighQuality));
			prop.setProperty("Alpha Interpolation", Boolean.toString(AlphaInterpolation));
			prop.setProperty("High Quality Colour", Boolean.toString(HighQualityColour));
			prop.setProperty("Text LCD Contrast", Integer.toString(textLCDContrast));
			
			//SAVE FILE
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException io){
					io.printStackTrace();
				}
			}
		}

		System.out.println("... Settings File saved");

	}

	//Restore default settings
	public static void restoreDefaultSettings() {

		firstTime = false;
		splashScreen = true;
		
		//Get the default size of the primary monitor, not using toolkit as that messes up multi-monitor setups
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		res[0] = gd.getDisplayMode().getWidth();
		res[1] = gd.getDisplayMode().getHeight();

		//Default Bindings are WAD for Jump/Left/Right respectively, spacebar is primary attack, ctrl for secondary attack, placeblock as q, pause as esc
		setKeyCode("left", 65);
		setKeyCode("right", 68);
		setKeyCode("jump", 87);
		setKeyCode("openinv", 69);
		setKeyCode("pattack", 32);
		setKeyCode("sattack", 17);
		setKeyCode("placeblock", 81);
		setKeyCode("pause", 27);
		
		TextAA = true;
		AA = true;
		Dithering = true;
		HighQuality = true;
		AlphaInterpolation = true;
		HighQualityColour = true;
		textLCDContrast = 100; 
		
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
	
	//========================
	//OTHER SETTINGS
	//========================
	
	public static boolean getSplashScreenEnabled() {return splashScreen;}
	public static void setSplashScreenEnabled(boolean given) {splashScreen = given;}
	
	
	
	//========================
	//KEY BINDINGS
	//========================
	//======Possible actions: 
	//		left
	//		right
	//		jump
	//		pattack
	//		sattack
	//		placeblock
	//		pause
	//		openinv
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
		case "jump": result = keyBinding[2]; 
		break;
		case "pattack": result = keyBinding[3];
		break;
		case "sattack": result = keyBinding[4]; 
		break;
		case "placeblock": result = keyBinding[5]; 
		break;
		case "pause": result = keyBinding[6];
		break;
		case "openinv": result = keyBinding[7];
		break;			
		};		

		if(result == -1) {
			System.out.println("Improper call for getKeyCode in SettingsManager");
			System.out.println("Exiting...");
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
		case "jump": keyBinding[2] = keyCode; 
		break;
		case "pattack": keyBinding[3] = keyCode;
		break;
		case "sattack": keyBinding[4] = keyCode; 
		break;
		case "placeblock": keyBinding[5] = keyCode; 
		break;
		case "pause": keyBinding[6] = keyCode;
		break;
		case "openinv": keyBinding[7] = keyCode;
		break;			
		};	

	}

}

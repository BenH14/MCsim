package settings;

import java.io.InputStream;

//This just gets around the fact that settingsmanager is static so i can load the file
public class FileLoader {

	public InputStream getStream(String path) {
		return getClass().getClassLoader().getResourceAsStream(path);
	}
	
}

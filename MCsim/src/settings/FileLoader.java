package settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;

//This just gets around the fact that settingsmanager is static so i can load the file
public class FileLoader {

	public InputStream getInStream(String path) {
		return getClass().getClassLoader().getResourceAsStream(path);
	}
	
	public OutputStream getOutStream(String path) throws FileNotFoundException, URISyntaxException {
		URL resourceUrl = getClass().getClassLoader().getResource(path);
		File file = new File(resourceUrl.toURI());
		return new FileOutputStream(file);
	}
	
}

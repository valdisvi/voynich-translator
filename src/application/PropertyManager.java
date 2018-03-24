package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

public class PropertyManager {
	private String fileName;
	private LinkedProperties properties;
	private InputStream input;
	private Transliteration rules;

	public PropertyManager(String transliterationName, String fileName) throws IOException {

		properties = new LinkedProperties();
		input = new FileInputStream(new File(fileName));
		this.fileName = fileName;
		rules = new Transliteration(transliterationName);
		properties.load(new InputStreamReader(input, "UTF8"));
		readFromFile();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public LinkedProperties getProperties() {
		return properties;
	}

	public void setProperties(LinkedProperties properties) {
		this.properties = properties;
	}

	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}

	public Transliteration getRules() {
		return rules;
	}

	public void setRules(Transliteration rules) {
		this.rules = rules;
	}

	public void readFromFile() throws IOException {

		Enumeration<Object> enuKeys = this.properties.keys();

		while (enuKeys.hasMoreElements()) {

			String key = (String) enuKeys.nextElement();
			String value = this.properties.getProperty(key);
			rules.addElement(key, value);
		}

		LinkedHashMap<String, String> list = rules.getList();

		for (Map.Entry<String, String> entry : list.entrySet()) {

			String key = entry.getKey();
			String value = entry.getValue();
		}
	}
}

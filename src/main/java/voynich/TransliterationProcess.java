package voynich;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TransliterationProcess {

	private String transliterationFilePath;
	private String transliterationFileName;
	private LinkedHashMap<String, String> rulesList;

	public TransliterationProcess(String path, String fileName) throws IOException {

		this.transliterationFilePath = path;
		this.transliterationFileName = fileName;
		this.rulesList = this.getRules();
	}

	public LinkedHashMap<String, String> getRules() throws IOException {

		PropertyManager pManager = new PropertyManager(transliterationFileName, transliterationFilePath);
		Transliteration rules = pManager.getRules();
		return rules.getList();
	}

	public String transliterate(String inputText) throws IOException, InterruptedException {

		for (Map.Entry<String, String> entry : rulesList.entrySet()) {

			String k = entry.getKey();
			String v = entry.getValue();
			if (k.equals("*"))
				k = "\\*";
			else if (k.equals("\\"))
				k = "\\\\";
			else if (k.equals("?"))
				k = "\\?";
			else if (k.equals("!"))
				k = "\\!";
			else if (k.equals("%"))
				k = "\\%";
			else if (k.equals(".")) {
				k = "\\.";
				v = " ";
			} else if (k.equals("^"))
				k = "\\^";

			inputText = inputText.replaceAll(k, v);
		}
		return inputText;

	}

	public boolean contains(String tmpString) throws IOException {

		for (Map.Entry<String, String> entry : rulesList.entrySet()) {
			if (entry.getKey().startsWith(tmpString)) {
				return true;
			}
		}
		return false;
	}

	public boolean equals(String tmpString) throws IOException {

		for (Map.Entry<String, String> entry : rulesList.entrySet()) {
			if (entry.getKey().equals(tmpString)) {
				return true;
			}
		}
		return false;
	}
}

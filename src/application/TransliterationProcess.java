package application;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TransliterationProcess {
	
	private String transliterationFilePath;
	private String transliterationFileName;
	private int index;
	private LinkedHashMap<String, String> rulesList;

	public TransliterationProcess(String path, String fileName) throws IOException {
		
		this.transliterationFilePath = path;
		this.transliterationFileName = fileName;
		this.index = 0;
		this.rulesList = this.getRules();
	}
	public LinkedHashMap<String, String> getRules() throws IOException {
		
		PropertyManager pManager = new PropertyManager(transliterationFileName, transliterationFilePath);
		Transliteration rules = pManager.getRules();
		return rules.getList();
	}	
	
	
	public String transliterate(String inputText) throws IOException, InterruptedException{
		
		
		for (Map.Entry<String,String> entry : rulesList.entrySet())
		 { 
			
			 String k = entry.getKey();
			 String v = entry.getValue();
			 if (k.equals("*")) k = "\\*";
			 else if (k.equals("\\")) k = "\\\\";
			 else if (k.equals("?")) k = "\\?";
			 else if (k.equals("!")) k = "\\!";
			 else if (k.equals("%")) k = "\\%";
			else if (k.equals(".")) { k="\\.";v=" ";}
			
			 else if (k.equals("^")) k = "\\^";
			 
			//System.out.println(inputText);
			
			 inputText = inputText.replaceAll(k,v);
		 }
		 return inputText;
		
	}
	
	
	
	public boolean contains(String tmpString) throws IOException{
			
		for(Map.Entry<String,String> entry : rulesList.entrySet()){
			if(entry.getKey().startsWith(tmpString)){
				return true;
			}
		}
		return false;
	}
	
	public boolean equals(String tmpString) throws IOException{
		
		for(Map.Entry<String,String> entry : rulesList.entrySet()){
			if(entry.getKey().equals(tmpString)){
				return true;
			}
		}
		return false;
	}

	public String getPath(){
		return Controller.path2;
	}
	
	public String getName(){
		// TODO get name from path
		String path = "Currier";
		return path;
	}
}

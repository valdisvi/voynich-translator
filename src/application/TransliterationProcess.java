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
			 inputText = inputText.replaceAll(entry.getKey(), entry.getValue());
		 }
		 return inputText;
		
		/*char[] array = inputText.toCharArray();
		return getTransliterationString(array);
	}
	
	public LinkedHashMap<String, String> getRules() throws IOException {
		
		PropertyManager pManager = new PropertyManager(transliterationFileName, transliterationFilePath);
		Transliteration rules = pManager.getRules();
		return rules.getList();
	}
	
	public String getTransliterationString(char[] array) throws IOException, InterruptedException{
 //for(int i=0;i<array.length;i++){System.out.println(array[i]+" ");}
		String result = "";
		StringBuilder finalElement = new StringBuilder();
		String tmpString = "";
		boolean b = false;
		while(index < array.length){
			System.out.println("While : ");
			String tmpString2 = tmpString + String.valueOf(array[index]);
			
			if(contains(tmpString2)){
				
				tmpString += String.valueOf(array[index]);
				index++;
				b =true;
				System.out.println("IF : ");
				TimeUnit.SECONDS.sleep(2);
			}
			else if(equals(tmpString)) {
				for (Map.Entry<String,String> entry : rulesList.entrySet()){
    				
        			if(tmpString.equals(entry.getKey())){
        				finalElement.append(entry.getValue());
        			}
        		}
				tmpString = "";
				b=false;
				System.out.println("Else if : ");
				TimeUnit.SECONDS.sleep(2);					
			}
			else if(!(contains(tmpString2)&&b==true))
					{
				       int len = tmpString.length();
				       
					}
			else {
				char[] tmpArray = tmpString.toCharArray();
				int tmpIndex = this.index;
				this.index = 0;
				finalElement.append(getTransliterationString(tmpArray));	
				this.index = tmpIndex;
				System.out.println("Else : ");
				System.out.println("Index : "+index);
				TimeUnit.SECONDS.sleep(2);
				
			}
			
			if(index == array.length){
				if(rulesList.containsKey(tmpString)){
					for (Map.Entry<String,String> entry : rulesList.entrySet()){
	        			if(tmpString.equals(entry.getKey())){
	        				finalElement.append(entry.getValue());
	        			}
	        		}
					result = finalElement.toString();
				}
				else {
					//kusok
					char[] arrayOfExclude = tmpString.toCharArray();
					for(int i=0; i<arrayOfExclude.length; i++){
						for (Map.Entry<String,String> entry : rulesList.entrySet()){
							if(String.valueOf(arrayOfExclude[i]).equals(entry.getKey())){
								finalElement.append(entry.getValue());
							}
						}
					}
					result = finalElement.toString();
					System.out.println("		HARDEST DONE");
				}
			}
			System.out.println("while end :" + index);
		}
		return result;*/
		
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

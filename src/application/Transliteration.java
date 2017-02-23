package application;

import java.util.LinkedHashMap;

public class Transliteration {
	
	private LinkedHashMap<String, String> list; //list for rules
	private String name;
	
	public Transliteration(String name)	{

		list = new LinkedHashMap<String, String>();
		this.name = name;
	}

	public String getName() { return name; }
	public void setList (LinkedHashMap<String, String> list) { this.list = list; }
	public void setName(String name) { this.name = name; }
	public LinkedHashMap<String, String> getList(){ return list; }
	public void addElement(String key,String value)
	{
		list.put(key, value);
	}

	@Override
	public String toString(){
		return getName();
	}
}

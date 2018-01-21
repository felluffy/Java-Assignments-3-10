package GladLibs;

import edu.duke.*;
import java.util.*;

public class GladLib 
{
//	private ArrayList<String> adjectiveList;
//	private ArrayList<String> nounList;
//	private ArrayList<String> colorList;
//	private ArrayList<String> countryList;
//	private ArrayList<String> nameList;
//	private ArrayList<String> animalList;
//	private ArrayList<String> timeList;
	
	private ArrayList<String> consideredLists;
	private HashMap<String, ArrayList<String>> myMap;	//type list
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "E:/eclipse/workspace/Assignments 3-10/resources/data";
	
	public GladLib()
	{
		consideredLists = new ArrayList<String>();
		myMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		//initializeFromSource(dataSourceURL);
		myRandom = new Random();
	}
	
	public GladLib(String source)
	{
		consideredLists = new ArrayList<String>();
		myMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) 
	{
//		adjectiveList= readIt(source+"/adjective.txt");	
//		nounList = readIt(source+"/noun.txt");
//		colorList = readIt(source+"/color.txt");
//		countryList = readIt(source+"/country.txt");
//		nameList = readIt(source+"/name.txt");		
//		animalList = readIt(source+"/animal.txt");
//		timeList = readIt(source+"/timeframe.txt");		
		String[] categories = {"adjective", "noun", "color", "country", "name", "animal", "timeframe"};
		for(String cat: categories)
		{
			ArrayList<String> lst = readIt(source + "/" + cat + ".txt");
			myMap.put(cat, lst);
		}
	}
	
	private String randomFrom(ArrayList<String> source)
	{
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) 
	{
//		if (label.equals("country")) {
//			return randomFrom(countryList);
//		}
//		if (label.equals("color")){
//			return randomFrom(colorList);
//		}
//		if (label.equals("noun")){
//			return randomFrom(nounList);
//		}
//		if (label.equals("name")){
//			return randomFrom(nameList);
//		}
//		if (label.equals("adjective")){
//			return randomFrom(adjectiveList);
//		}
//		if (label.equals("animal")){
//			return randomFrom(animalList);
//		}
//		if (label.equals("timeframe")){
//			return randomFrom(timeList);
//		}
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		else if(myMap.containsKey(label))
		{
			if(!consideredLists.contains(label))
			consideredLists.add(label);
			return randomFrom(myMap.get(label));
		}
		else
			return "**UNKNOWN**";
	}
	
	private String processWord(String w)
	{
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String type = w.substring(first+1, last);
		type = type.trim();
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
//		String sub = getSubstitute(w.substring(first+1,last));
		String sub = getSubstitute(type);
//		if(!consideredLists.contains(type))
//			consideredLists.add(type);
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth)
	{
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source)
	{
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source)
	{
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	private int totalWordsInMap()
	{
		int total = 0;
		for(String s: myMap.keySet())
		{
			total += myMap.get(s).size();
		}
		return total;
	}
	
	private int totalWordsConsidered()
	{
		int total = 0;
		for(String s: this.consideredLists)
		{
			total += myMap.get(s).size();
			System.out.println(s);
		}
//		System.out.println();
//		for(String s: myMap.keySet())
//			System.out.println(s);
		return total;
	}
	
	public void makeStory()
	{
	    System.out.println("\n");
		String story = fromTemplate("E:/eclipse/workspace/Assignments 3-10/resources/data/madtemplate.txt");
		printOut(story, 60);
		System.out.println("\nTotal words: " + totalWordsInMap());
		System.out.println("\nTotal words considered is: " + totalWordsConsidered());
	}
	
	public static void main(String[] args)
	{
		GladLib gl = new GladLib();
		gl.makeStory();
	}
	
}

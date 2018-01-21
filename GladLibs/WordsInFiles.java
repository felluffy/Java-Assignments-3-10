package GladLibs;

import edu.duke.*;
import java.io.*;
import java.util.*;

public class WordsInFiles 
{
	public WordsInFiles()
	{
		map = new HashMap<String, ArrayList<String>>();
	}
	
	private void addWordsFromFIle(File f) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = null;
		while((line = br.readLine()) != null)
		{
			String[] words = line.split("\\s+");
			for(String word: words)
			{
				//System.out.println(word);
				if(!map.containsKey(word))
					map.put(word, new ArrayList<String>());
				if(!map.get(word).contains(f.getName()))
				{
					map.get(word).add(f.getName());
				}
			}
		}
		br.close();
	}
	
	private void buildWordMap()
	{
		map.clear();
		DirectoryResource dr = new DirectoryResource();
		for(File f: dr.selectedFiles())
		{
			try 
			{
				addWordsFromFIle(f);
			}
			catch(IOException e)
			{
				System.err.println("IO error");
				continue;
			}
		}
	}
	
	//returns the max number of files any word is present in the map
	private int maxNumber()
	{
		int max = -1;
		for(String str: map.keySet())
		{
			if(map.get(str).size() > max)
				max = map.get(str).size();
		}
		return max;
	}
	
	//returns an array list of words that has exactly number number of files present for each word in the map
	private ArrayList<String> wordsInNumFiles(int number)
	{
		ArrayList<String> wordList = new ArrayList<String>();
		for(String str: map.keySet())
		{
			if(map.get(str).size() == number)
			{
				wordList.add(str);
			}
		}
		return wordList;
	}
	
	private void printFilesIn(String word)
	{
		for(String str: map.keySet())
		{
			if(str.equals(word))
			{
				for(String files: map.get(str))
				{
					System.out.println(files);
				}
				return;
			}
		}
	}
	
	public void tester()
	{
		this.buildWordMap();
//		for(String str: map.keySet())
//		{
//			System.out.println(str);
//			for(String files: map.get(str))
//			{
//				System.out.println(files);
//			}
//		}
		int max = this.maxNumber();
		System.out.println(max);
		ArrayList<String> list = this.wordsInNumFiles(max);
		for(String str: list)
		{
			System.out.println(str);
			this.printFilesIn(str);
		}
	}
	
	public static void main(String[] args)
	{
		WordsInFiles wif = new WordsInFiles();
		wif.tester();
	}
	
	private HashMap<String, ArrayList<String>> map;
}

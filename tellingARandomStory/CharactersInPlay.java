package tellingARandomStory;

import java.util.ArrayList;
import edu.duke.FileResource;


public class CharactersInPlay 
{
	CharactersInPlay()
	{
		names = new ArrayList<String>();
		counts = new ArrayList<Integer>();
	}
	
	public void update(String person)
	{
		int index = names.indexOf(person);
		if(index != -1)
		{
			int val = counts.get(index);
			counts.set(index, val + 1);
		}
		else
		{
			names.add(person);
			counts.add(1);
		}
	}
	
	public void findAllCharacters()
	{
		names.clear();
		counts.clear();
		FileResource fr = new FileResource();
		for(String lines: fr.lines())
		{
//			if(lines.contains("."))
//			{
//				int index = lines.indexOf('.');
//			}
			int index = lines.indexOf('.');
			if(index != -1)
			{
				String possibleName = new String(lines.substring(0, index));
				update(possibleName);
			}
		}
	}
	
	public void tester()
	{
		findAllCharacters();
		System.out.println("All chracters are");
		for(int i = 0; i != names.size(); i++)
		{
			System.out.println(names.get(i) + "\t" + counts.get(i));
		}
		
	}
	
	public static void main(String[] args) 
	{
		CharactersInPlay cip = new CharactersInPlay();
		cip.tester();
	}
	
	private ArrayList<String> names;
	private ArrayList<Integer> counts;
}

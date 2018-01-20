package tellingARandomStory;

import java.util.ArrayList;
import edu.duke.*;

public class WordFrequencies 
{
	public WordFrequencies()
	{
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
	}

	public void findUnique()
	{
		myWords.clear();
		myFreqs.clear();
		FileResource fr = new FileResource();
		for(String word: fr.words())
		{
			word = word.toLowerCase();
			int index = myWords.indexOf(word);
			if(index == -1)
			{
				myWords.add(word);
				myFreqs.add(1);
			}
			else
			{
				int val = myFreqs.get(index);
				myFreqs.set(index, val + 1);
			}
		}
	}
	
	public int findIndexOfMax()
	{
		int max = -1;
		int index = 0;
		int i = 0;
		for(int val: myFreqs)
		{
			if(val > max)
			{
				max = val;
				index = i;
			}
			i++;
		}
		return index;
	}
	
	public void tester()
	{
		findUnique();
		System.out.println("Count\tWord");
		for(int i = 0; i != myWords.size(); i++)
		{
			System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
		}
		
		int maxInd = findIndexOfMax();
		System.out.println("The word that occurs most often and its count are: " + myWords.get(maxInd) + " " + myFreqs.get(maxInd));
		
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		WordFrequencies wf = new WordFrequencies();
		wf.tester();

	}
	private ArrayList<String> myWords;
	private ArrayList<Integer> myFreqs;
}

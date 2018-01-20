package GladLibs;

import java.util.HashMap;
import edu.duke.FileResource;

public class codonCount 
{
	public codonCount()
	{
		dnaCount = new HashMap<String, Integer>();
	}
	
	//builds a unique map from start for a dna sequence
	private void buildCodonMap(int start, String DNA)
	{
		DNA = DNA.toUpperCase();
		//dnaCount.clear();
		for(int i = start; i + 3 <= DNA.length(); i += 3)
		{
			String strand = DNA.substring(i, i + 3);
			//System.out.println(strand + " check ");
			if(!dnaCount.containsKey(strand))
			{
				dnaCount.put(strand, 1);
			}
			else
				dnaCount.put(strand, dnaCount.get(strand) + 1);
		}
	}
	
	//gets the most common codon in the hashmap
	private String getMostCommonCodon()
	{
		String strand = "";
		if(dnaCount.isEmpty() == true)
			return strand;
		int max = -1;
		for(String w: dnaCount.keySet())
		{
			if(dnaCount.get(w) >= max)
			{
				max = dnaCount.get(w);
				strand = w;
			}
		}
		return strand;
	}
	
	//prints in a range
	private void printCodonCounts(int start, int end)
	{
		for(String str: dnaCount.keySet())
		{
			int val = dnaCount.get(str);
			if(val >= start && val <= end)
			{
				System.out.println(str + "\t" + val);
			}
		}
	}
	
	
	public void tester()
	{
		FileResource fr = new FileResource();
		int start = 1;
		int end = 5;
		for(int i = 0; i != 3; i++)
		{
			dnaCount.clear();
			//System.out.println(dnaCount.keySet().size());
			for(String strands: fr.lines())
			{
				System.out.println(strands);
				strands = strands.trim();
				buildCodonMap(i, strands);
				
			}
			String common = getMostCommonCodon();
			System.out.println("Reading frame starting with " + i + " results in " + dnaCount.keySet().size() +
					" unique codons" + "\n\tand most common codon is " + common + " with count " + dnaCount.get(common));
			System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
			printCodonCounts(start, end);
			System.out.println();
		}
		
	}
	
	public static void main(String[] arg)
	{
		codonCount cc = new codonCount();
		cc.tester();
	}
	
	private HashMap<String, Integer> dnaCount;
}

package unknownlanguage;

import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker 
{
    public String sliceString(String message, int whichSlice, int totalSlices) 
    {
    	String ret = new String();
    	for ( int i = whichSlice; i < message.length() ; i+= totalSlices) 
    	{
    		ret += message.charAt(i);
    	}
        return ret;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) 
    {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength ; i++) 
        {
        	String str = sliceString(encrypted, i, klength);
        	int dekey = cc.getKey(str);
        	key[i] = dekey;
        }
        return key;
    }

    public void breakVigenere () 
    {
        //FileResource fr = new FileResource("E:/eclipse/workspace/Assignments 3-10/resources/athens_keyflute.txt");
    	FileResource fr = new FileResource();
        String messageEncrypted = fr.asString();
        int [] key = tryKeyLength(messageEncrypted, 5, 'e');
        VigenereCipher vc = new VigenereCipher(key);
    	String decrypted = vc.decrypt(messageEncrypted);
    	System.out.println(decrypted);
    }
    
    public HashSet<String> readDictionary(FileResource fr)
    {
    	HashSet<String> vocab = new HashSet<String>();
    	for (String line: fr.lines()) 
    	{
    	   vocab.add(line.toLowerCase());
    	}
    	return vocab;
    }
    
    public int countWords(String message, HashSet<String> dictionary)
    {
    	int count = 0;
    	for(String words: message.split("\\W+"))
    	{
    		if(dictionary.contains(words.toLowerCase()))
    			count++;
    	}
    	return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary)
    {
    	//char mostCommon = 'e';
    	char mostCommon = mostCommonCharIn(dictionary);
    	VigenereCipher vc;
    	int[] wordCount = new int[100];
    	for (int i = 0; i < 100; i++) {
            int[] key = tryKeyLength(encrypted, i+1, mostCommon);
            vc = new VigenereCipher(key);
            String result = vc.decrypt(encrypted);
            wordCount[i] = countWords(result, dictionary);
    	}
    	int max = 0, ind = 0;
    	for (int i = 0; i != 100; i++) {
            if (wordCount[i] > max) {
                max = wordCount[i];
                ind = i;
            }
    	}
    	int[] key = tryKeyLength(encrypted, ind + 1, mostCommon);
        System.out.print("Predicted keys are ");
        for (int i = 0; i != key.length; i++) 
            System.out.print(key[i] + " ");
        System.out.println("\nThe key length is " + key.length);
        vc = new VigenereCipher(key);
        return vc.decrypt(encrypted);
    }
    
    public void breakVigenere2 () {
    	FileResource fr = new FileResource("resources/athens_keyflute.txt");
    	String message = fr.asString();
    	FileResource fr2 = new FileResource("resources/dictionaries/English");
    	HashSet<String> dictionary = readDictionary(fr2);
    	String decrypt = breakForLanguage(message, dictionary);
    	System.out.println(decrypt);
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary)
    {
    	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    	for(char ch = 'a'; ch <= 'z'; ch++)
    		map.put(ch, 0);
    	
    	//build the chracter map
    	for(String words: dictionary)
    	{
    		for(char let: map.keySet())
    		{
    			for(String chars: words.split(""))
    			{
    				if(let == chars.charAt(0))
    					map.put(let, map.get(let) + 1);
    			}
    		}
    	}
    	
    	//find the character with max occurrence
    	int max = 0;
    	char ret = '\0';
    	for(char c: map.keySet())
    	{
    		if(map.get(c) > max)
    		{
    			ret = c;
    			max = map.get(c);
    		}
    	}
    	return ret;
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages)
    {
    	ArrayList<String> decryptedMessages = new ArrayList<String>(); 
    	String probableDecrypted = "";
    	String decrypted;
    	String language = null;
    	int wc = 0;
    	for(String lang: languages.keySet())
    	{
    		//decryptedMessages.add(breakForLanguage(encrypted, languages.get(lang)));
    		decrypted = breakForLanguage(encrypted, languages.get(lang));
    		int count = countWords(decrypted, languages.get(lang));
    		if(count > wc)
    		{
    			wc = count;
    			language = lang;
    			probableDecrypted = decrypted;
    		}
    	}
    	System.out.println("probable lanuage: " + language);
    	return probableDecrypted;
    }
    
    public void breakVigenere3() 
    {
    	HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
    	FileResource fr = new FileResource();
        String message = fr.asString();
        HashSet<String> result = new HashSet<String>();
    	DirectoryResource dr = new DirectoryResource();
    	for (File d: dr.selectedFiles()) 
    	{
    		FileResource langs = new FileResource(d.toString());
    		result.clear();
    		result = readDictionary(langs);
            languages.put(d.getName(), result);
    	}
    	String dec = breakForAllLangs(message, languages);
    	System.out.println("Probable decrypted message: \n" + dec);
    }
}

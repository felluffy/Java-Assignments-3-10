package unknownkeylength;

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
    	char mostCommon = 'e';
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
}

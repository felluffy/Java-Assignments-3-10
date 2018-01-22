package knownkeylength;

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
}

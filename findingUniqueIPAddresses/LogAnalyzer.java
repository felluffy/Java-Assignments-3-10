
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package findingUniqueIPAddresses;

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() 
     {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) 
     {
         // complete method
    	 FileResource fr;
    	 try
    	 {
    		 fr = new FileResource(filename);
    	 }
    	 catch(Exception e)
    	 {
    		 System.err.println("Couldn't find specific file from relative path");
    		 fr = new FileResource();
    	 }
    	 for(String lines: fr.lines())
    	 {
    		 records.add(WebLogParser.parseEntry(lines));
    	 }
     }
        
     public void printAll() 
     {
         for (LogEntry le : records) 
         {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs()
     {
    	 ArrayList<String> uniqueIPs = new ArrayList<String>();
    	 for(LogEntry le: records)
    	 {
    		 if(!uniqueIPs.contains(le.getIpAddress()))
    		 {
    			 uniqueIPs.add(le.getIpAddress());
    		 }
    	 }
    	 return uniqueIPs.size();
     }
     
}

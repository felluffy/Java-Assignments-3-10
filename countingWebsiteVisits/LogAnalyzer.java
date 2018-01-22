
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package countingWebsiteVisits;

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() 
     {
         records = new ArrayList<LogEntry>();
     }
     
     //Reads from a file to records
     public void readFile(String filename) 
     {
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
     
     //prints all the records
     public void printAll() 
     {
         for (LogEntry le : records) 
         {
             System.out.println(le);
         }
     }
     
     //finds the total number of unique ips
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
     
     //prints all records that have higher than num stauts code
     public void printAllHigherThanNum(int num)
     {
    	 for(LogEntry le: records)
    	 {
    		 if(le.getStatusCode() > num)
    		 {
    			 System.out.println(le.toString());
    		 }
    	 }
     }
     
     //creates a list that contains unique ips that visited someday
     public ArrayList<String> uniqueIPVisitsOnDay(String someday)
     {
    	 ArrayList<String> uniqueIPsDay = new ArrayList<String>();
    	 for(LogEntry le: records)
    	 {
    		 if(le.getAccessTime().toString().contains(someday))
    		 {
    			 uniqueIPsDay.add(le.getIpAddress());
    		 }
    	 }
    	 return uniqueIPsDay;
     }
     
     
     //finds the total number of ips that has their status codes in the range between high and low
     public int countUniqueIPsInRange(int low, int high)
     {
    	 ArrayList<String> uniqueIPs = new ArrayList<String>();
    	 //int sz = 0;
    	 for(LogEntry le: records)
    	 {
    		 if(!uniqueIPs.contains(le.getIpAddress()) && le.getStatusCode() >= low && le.getStatusCode() <= high)
    		 {
    			 uniqueIPs.add(le.getIpAddress());
    			 //System.out.println(uniqueIPs.get(sz++));
    			 //sz++;
    		 }
    	 }
    	 //return sz;
    	 return uniqueIPs.size();
     }
     
     
     //creates a map from ip address to the number of visits from that ip 
     public HashMap<String, Integer> countVisitsPerIP()
     {
    	 HashMap<String, Integer> counts = new HashMap<String, Integer>();
    	 for(LogEntry le: records)
    	 {
    		 String ip = le.getIpAddress();
    		 if(!counts.containsKey(ip))
    		 {
    			 counts.put(ip, 1);
    		 }
    		 else
    			 counts.put(ip, counts.get(ip) + 1);
    	 }
    	 return counts;
     }
     
     //returns the max number of visits to a website by a single ip address
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts)
     {
    	 int max = 0;
    	 for(String ip: counts.keySet())
    	 {
    		 if(counts.get(ip) > max)
    			 max = counts.get(ip);
    	 }
    	 return max;
     }
     
     //creats a list of ip address that all have the maximum number of visits to a website
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts)
     {
    	 int max = mostNumberVisitsByIP(counts);
    	 ArrayList<String> ips = new ArrayList<String>();
    	 for(String ip: counts.keySet())
    	 {
    		 if(counts.get(ip).equals(max))
    			 ips.add(ip);
    	 }
    	 return ips;
     }
     
     
     //creates a map from date to ip all addresses that visited in those days  
     public HashMap<String, ArrayList<String>> iPsForDays()
     {
    	 HashMap<String, ArrayList<String>> iPPerDays = new HashMap<String, ArrayList<String>>();
    	 for(LogEntry le: records)
    	 {
    		 String date = le.getAccessTime().toString();
    		 String day = date.substring(4, 10);
    		 day = day.trim();
//    		 iPPerDays.put(day, iPPerDays.get(day).add(le.getIpAddress()));
    		 if(!iPPerDays.containsKey(day))
    		 {
    			 iPPerDays.put(day, new ArrayList<String>());
    		 }
    		 iPPerDays.get(day).add(le.getIpAddress());
    		 //System.out.println(date);
    	 }
    	 return iPPerDays;
     }
     
     //returns the day with max number of visits
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPPerDays)
     {
    	 int max = 0;
    	 String ret = null;
    	 for(String day: iPPerDays.keySet())
    	 {
    		 if(iPPerDays.get(day).size() > max)
    		 {
    			 max = iPPerDays.get(day).size();
    			 ret = day;
    		 }
    	 } 
    	 return ret;
     }
     
   //creates a map from ip address to the number of visits from that ip  on a single day
     public HashMap<String, Integer> countVisitsPerIP(ArrayList<String> iPEntries)
     {
    	 HashMap<String, Integer> counts = new HashMap<String, Integer>();
    	 for(String ip: iPEntries)
    	 {
    		 if(!counts.containsKey(ip))
    		 {
    			 counts.put(ip, 1);
    		 }
    		 else
    			 counts.put(ip, counts.get(ip) + 1);
    	 }
    	 return counts;
     }
     
     
     //returns a list of address that had most access on date 
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPPerDays, String date)
     {
    	 HashMap<String, Integer> iPCountsDay = countVisitsPerIP(iPPerDays.get(date));
    	 ArrayList<String> al = iPsMostVisits(iPCountsDay);
    	 return al;
     }
}

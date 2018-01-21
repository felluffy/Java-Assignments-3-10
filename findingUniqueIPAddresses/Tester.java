
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package findingUniqueIPAddresses;

import java.util.*;

public class Tester
{
    public void testLogEntry() 
    {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() 
    {
    	LogAnalyzer la = new LogAnalyzer();
    	//String fl = "E:/eclipse/workspace/Assignments 3-10/src/readingLogFiles/";
    	la.readFile("resources/short-test_log");
    	la.printAll();
    }
    
    public void testUniqueIP()
    {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("resources/short-test_log");
    	System.out.println("Number of unique ips: " + la.countUniqueIPs());
    }
    
    public void testHigherThanStatus()
    {
    	int high = 300;
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("resources/short-test_log");
    	System.out.println("Entries that have status codes higher than " + high + " are: ");
    	la.printAllHigherThanNum(high);
    }
    
    public void testUniqueIPVisitsOnDay(String day)
    {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("resources/weblog-short_log");
    	System.out.println("Number of unique visits on " + day + " is " + la.uniqueIPVisitsOnDay(day).size());
    }
    
    public void testCountUniqueIPsInRange()
    {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("resources/short-test_log");
    	System.out.println(la.countUniqueIPsInRange(200, 299));
    }
    
    public static void main(String[] args)
    {
    	Tester t = new Tester();
    	//t.testLogAnalyzer();
    	//t.testUniqueIP();
    	//t.testHigherThanStatus();
    	//t.testUniqueIPVisitsOnDay("Sep 14");
    	//t.testUniqueIPVisitsOnDay("Sep 30");
    	t.testCountUniqueIPsInRange();
    	
    }
}

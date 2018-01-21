
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
package readingLogFiles;

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
    
    public static void main(String[] args)
    {
    	Tester t = new Tester();
    	t.testLogAnalyzer();
    }
}

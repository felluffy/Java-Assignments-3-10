package unknownkeylength;

public class Tester 
{
	public static void main(String[] args)
	{
		String input = "Coal-black is better than another hue,\r\n" + 
				"In that it scorns to bear another hue;\r\n" + 
				"For all the water in the ocean\r\n" + 
				"Can never turn the swan's black legs to white,\r\n" + 
				"Although she lave them hourly in the flood.";
		CaesarCipher cc = new CaesarCipher(20);
		System.out.println(cc.encrypt(input));
		CaesarCracker ccc = new CaesarCracker();
		System.out.println(ccc.decrypt(cc.encrypt(input)));
	}
}

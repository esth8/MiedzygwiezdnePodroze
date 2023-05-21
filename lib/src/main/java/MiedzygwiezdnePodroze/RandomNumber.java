package MiedzygwiezdnePodroze;

import java.util.Random;

public class RandomNumber {
	public static int generateRandom (int n)
	{
		Random rand = new Random(); 
		
		int number = rand.nextInt(n);
		
		return number;
	}
}
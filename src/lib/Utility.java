package lib;

import java.util.Random;

import logic.Chicken;
import logic.Fish;
import logic.GrillableFood;
import logic.Pork;

public class Utility {
	
	public static GrillableFood randomFood(){
		Random r = new Random(System.nanoTime());
		int random = (int) r.nextInt(12000 - 0) + 0;
		
		if (random <= 4000 ){
			return new Pork();
		}
		else if (random > 4000 && random <= 8000 ){
			return new Fish();
		}
		else return new Chicken();
	}
	
	public static int random(int start, int end){
		return (int) (Math.random() * (end - start) + start);
	}
	

}

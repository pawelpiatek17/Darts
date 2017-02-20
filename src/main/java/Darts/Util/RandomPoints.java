package Darts.Util;

import java.util.Random;

public final class RandomPoints {
	private RandomPoints(){};
	
	public static Pair generate() {
		Random random = new Random();
		int randomInt = random.nextInt(13) +1;
		if(randomInt > 0 && randomInt <= 5) {
			return new Pair(false,random.nextInt(20) + 1);
		}
		else if (randomInt > 5 && randomInt <= 7 ) {
			return new Pair(true,(random.nextInt(20) + 1) * 2);
		}
		else if (randomInt == 8) {
			return new Pair(false,(random.nextInt(20) + 1) * 3);
		}
		else if (randomInt == 9) {
			return new Pair(false,25);
		}
		else if (randomInt == 10) {
			return new Pair(true,50);
		}
		else {
			return new Pair(false,0);
		}
	}
}

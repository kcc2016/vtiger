package genericUtility;

import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public String generateSystemDateAndTime() {
		Date date = new Date();
		return date.toString().replace(' ', '_').replace(':', '_');
	}

	public int generateRandomNumber(int bound) {
		Random rand = new Random();
		return rand.nextInt(bound);
	}
}

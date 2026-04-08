package testpassword;
import com.password.generator.PasswordGenerator;

public class TestPassword {
	public static void main(String[] args) {
		
		System.out.println(PasswordGenerator.generateOTP());
		
		int count = 0;
		int bad = 0;
		int good = 0;
		
		
		while(count < 10000) {
			String p = PasswordGenerator.generatePassword();
			System.out.println(p);
			if(PasswordGenerator.validatePassword(p))
				good++;
			else
				bad++;
			count++;
				
		}
		System.out.printf(
				"Number of runs: %d%n" +
				"Ratio of good vs bad is: %d:%d%n" +
				"Error percentage: %.2f%s"
				,count , good, bad,(((double)bad/count) * 100),"%");
		
	}
}

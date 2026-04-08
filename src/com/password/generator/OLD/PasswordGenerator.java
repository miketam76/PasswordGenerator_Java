package com.password.generator.OLD;

import java.util.Random;

/**
 * This utility class contains methods which generates passwords and OTPs (One Time password) and validates
 * user passwords for minimum requirements.<br>
 * The margin of error of generating a unique password with all the minimum requirements is about 8% based 
 * on a simulation of a 100000 runs.<br>
 * This will be improved in a future release.
 * @author Mike Tam
 * @version 1.0
 * @since
 * 1.0 - First release<br>
 */
public class PasswordGenerator {
	/**
	 * Max random value for OTP
	 */
	private final static int RANDOM_OTP = 10;
	/**
	 * Max OTP length
	 */
	private final static int MAX_OTP_LENGTH = 6;
	/**
	 * Minimum password length
	 */
	private final static int MIN_PASSWORD_LENGTH = 8;
	/**
	 * Maximum password length
	 */
	private final static int MAX_PASSWORD_LENGTH = 14;
	/**
	 * Instance of Random object to generate random integer values
	 */
	private static Random rnd = new Random();
	
	/**
	 * Default Constructor - set to private to prevent instantiation  
	 */
	private PasswordGenerator() { }
	
	/**
	 * Utility method which returns a generated OTP (One time password) with digits ranging from 0 to 
	 * 9.&nbsp;OTP length is determined by the value in MAX_OTP_LENGTH.
	 * @return String representation of OTP.
	 */
	public static String generateOTP() {
		String OTP = "";
		
		for(int i=0; i < MAX_OTP_LENGTH; i++) {
			OTP = OTP + rnd.nextInt(RANDOM_OTP);
		}
		return OTP;
	}
	
	/**
	 * Utility method which returns a generated password of random alphanumeric, numeric, and special
	 * characters.&nbsp;Password length is determined by value in MAX_PASSWORD_LENGTH.
	 * @return String representation of generated password
	 */
	public static String generatePassword() {
		// Contains string values for upper case letters
		String valuesUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		// Contains string values for lower case letters
		String valuesLower = "abcdefghijklmnopqrstuvwxyz";
		// Contains string values for number values
		String valuesNumber = "0123456789";
		// Contains string values for special characters
		String valuesSpecialChar = "!@#%$^&_.-";
		
		String generatedPassword = "";
		
		// Generate password
		for(int i=0; i < MAX_PASSWORD_LENGTH; i++) {
			// Choose a random value from the 4 String objects
			switch(rnd.nextInt(4)) {
				// Randomly choose upper case letter
				case 0:
					generatedPassword = generatedPassword + valuesUpper.charAt(rnd.nextInt(valuesUpper.length()));
					break;
				// Randomly choose lower case letter
				case 1:
					generatedPassword = generatedPassword + valuesLower.charAt(rnd.nextInt(valuesLower.length()));
					break;
				// Randomly choose a number
				case 2:
					generatedPassword = generatedPassword + valuesNumber.charAt(rnd.nextInt(valuesNumber.length()));
					break;
				// Randomly choose a special character
				case 3:
					generatedPassword = generatedPassword + valuesSpecialChar.charAt(rnd.nextInt(valuesSpecialChar.length()));
					break;
				default:
			}
		}
		return generatedPassword;
	}
	
	/**
	 * Utility method which returns a boolean value (true/false) if user inputed password meets the
	 * minimum requirements of 8 characters and maximum of 14 characters.&nbsp;Password should also 
	 * contain the following: 1 lower case letter, 1 upper case letter, 1 numeric value, and 1 special 
	 * character.<br>
	 * @param pass String representation of user password.
	 * @return boolean value if password meets (true) or fails (false) minimum requirements.
	 */
	public static boolean validatePassword(String pass) throws InvalidPasswordRequirementsException {
		boolean hasLowerCaseLetter = false;
		boolean hasUpperCaseLetter = false;
		boolean hasNumber = false;
		boolean hasSpecialChar = false;
		// Password length is between 8 to 14 characters
		if( (pass.length() >= MIN_PASSWORD_LENGTH) && (pass.length() <= MAX_PASSWORD_LENGTH) ) { 
			for(int i=0; i < pass.length(); i++) {
				// Checks for upper case letters
				if((pass.charAt(i) >= 'A') && (pass.charAt(i) <= 'Z')) {
					hasUpperCaseLetter = true;
				}
				// Checks for lower case letters
				if((pass.charAt(i) >= 'a') && (pass.charAt(i) <= 'z')) {
					hasLowerCaseLetter = true;
				}
				// Checks for number values
				if((pass.charAt(i) >= '0') && (pass.charAt(i) <= '9')) {
					hasNumber = true;
				}
				// Checks for any special characters listed below (note: not all special characters can be used for a password)
				switch(pass.charAt(i)) {
					case '!':
					case '@':
					case '#':
					case '%':
					case '$':
					case '^':
					case '&':
					case '_':
					case '-':
					case '.':
						hasSpecialChar = true;
						break;
					default:
				}
			}
			// If it meets the requirements, its a valid password
			if(hasLowerCaseLetter && hasUpperCaseLetter && hasNumber && hasSpecialChar)
				return true;
			else
				// It does not meet requirements
				return false;
		}
		else
			// It does not meet length requirements
			return false;
	}
	
	/**
	 * Exception type class which inherits attributes from IllegalArgumentExeception
	 * @author Mike Tam
	 */
	public static class InvalidPasswordRequirementsException extends IllegalArgumentException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public InvalidPasswordRequirementsException(String msg) {
			super(msg);
		}
	}
}

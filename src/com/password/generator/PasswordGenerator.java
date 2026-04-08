package com.password.generator;

import java.security.SecureRandom;

/**
 * This static class contains methods which generates passwords and OTPs (One Time password) and validates
 * user passwords for minimum requirements.
 * @author Mike Tam
 * @version 2.0
 * @since
 * 1.0 - First release
 * 2.0 - Updated to use SecureRandom for stronger randomness and improved password generation logic to ensure all requirements are met.
 */
public enum PasswordGenerator {
	INSTANCE;
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
	// Use SecureRandom for stronger randomness suitable for passwords/OTPs
	private static SecureRandom rnd = new SecureRandom();
	
	/**
	 * Default Constructor - set to private to prevent instantiation  
	 */
	private PasswordGenerator( ) { } 
	
	/**
	 * Utility method which returns a generated OTP (One time password) with digits ranging from 0 to 
	 * 9.&nbsp;OTP length is determined by the value in MAX_OTP_LENGTH.
	 * @return String representation of OTP.
	 */
	public static String generateOTP() {
		StringBuilder sb = new StringBuilder(MAX_OTP_LENGTH);
		for (int i = 0; i < MAX_OTP_LENGTH; i++) {
			sb.append(rnd.nextInt(RANDOM_OTP));
		}
		return sb.toString();
	}
	
	/**
	 * Utility method which returns a generated password of random alphanumeric, numeric, and special
	 * characters.&nbsp;Password length is determined by value in MAX_PASSWORD_LENGTH.
	 * @return String representation of generated password
	 */
	/**
	 * Generate a random password of length MAX_PASSWORD_LENGTH. The generated password
	 * is guaranteed to contain at least one upper-case letter, one lower-case letter,
	 * one digit and one special character (from the allowed set).
	 * @return generated password string
	 */
	public static String generatePassword() {
		final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String lower = "abcdefghijklmnopqrstuvwxyz";
		final String digits = "0123456789";
		final String special = "!@#%$^&_.-";

		int length = MAX_PASSWORD_LENGTH;

		// Build a char array and ensure at least one of each required category
		char[] pwd = new char[length];
		int pos = 0;

		// Guarantee one from each category
		pwd[pos++] = upper.charAt(rnd.nextInt(upper.length()));
		pwd[pos++] = lower.charAt(rnd.nextInt(lower.length()));
		pwd[pos++] = digits.charAt(rnd.nextInt(digits.length()));
		pwd[pos++] = special.charAt(rnd.nextInt(special.length()));

		String all = upper + lower + digits + special;

		// Fill remaining positions with random chars from all categories
		for (; pos < length; pos++) {
			pwd[pos] = all.charAt(rnd.nextInt(all.length()));
		}

		// Shuffle the array to avoid predictable placement
		for (int i = pwd.length - 1; i > 0; i--) {
			int j = rnd.nextInt(i + 1);
			char tmp = pwd[i];
			pwd[i] = pwd[j];
			pwd[j] = tmp;
		}

		return new String(pwd);
	}
	
	/**
	 * Utility method which returns a boolean value (true/false) if user inputed password meets the
	 * minimum requirements of 8 characters and maximum of 14 characters.&nbsp;Password should also 
	 * contain the following: 1 lower case letter, 1 upper case letter, 1 numeric value, and 1 special character.
	 * @param pass String representation of user password.
	 * @return boolean value if password meets (true) or fails (false) minimum requirements.
	 */
	public static boolean validatePassword(String pass) {
		if (pass == null) {
			return false;
		}

		boolean hasLowerCaseLetter = false;
		boolean hasUpperCaseLetter = false;
		boolean hasNumber = false;
		boolean hasSpecialChar = false;

		// Password length is between MIN_PASSWORD_LENGTH to MAX_PASSWORD_LENGTH
		if (pass.length() < MIN_PASSWORD_LENGTH || pass.length() > MAX_PASSWORD_LENGTH) {
			return false;
		}

		final String special = "!@#%$^&_.-";

		for (int i = 0; i < pass.length(); i++) {
			char c = pass.charAt(i);
			if (Character.isUpperCase(c)) {
				hasUpperCaseLetter = true;
			} else if (Character.isLowerCase(c)) {
				hasLowerCaseLetter = true;
			} else if (Character.isDigit(c)) {
				hasNumber = true;
			} else if (special.indexOf(c) >= 0) {
				hasSpecialChar = true;
			}
		}

		return hasLowerCaseLetter && hasUpperCaseLetter && hasNumber && hasSpecialChar;
	}
}

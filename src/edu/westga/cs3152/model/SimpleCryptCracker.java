package edu.westga.cs3152.model;

import java.util.HashMap;
import java.util.regex.Pattern;

import edu.westga.cs3152.datatier.PasswordHashData;
import edu.westga.cs3152.datatier.WorstPasswordsHashData;
import edu.westga.cs3152.hashing.SimpleCrypt;

public class SimpleCryptCracker {
	/**
	 * A demo of password checking using MD5 without salt
	 * 
	 * @param args - not used
	 */
	public static void main(String[] args) {
		PasswordHashData pwData = new PasswordHashData("passwordData.csv");
		WorstPasswordsHashData worstData = new WorstPasswordsHashData("500-worst-passwords.txt");
		SimpleCrypt crypt = new SimpleCrypt();
		HashMap<String, String> output = new HashMap<String, String>();

		for (int idx = 0; idx < pwData.size() - 1; idx++) {
			System.out.println();
			System.out.println("Guessing a password for user " + pwData.getUsername(idx));
			String passwordHash = pwData.getHash(idx);
			boolean found = false;
			
			for (String passwordGuess : worstData.getHashes()) {
				if (crypt.checkPassword(passwordGuess, passwordHash)) {
					output.put(pwData.getUsername(idx), passwordGuess);
					System.out.println("The password for " + pwData.getUsername(idx) + " is " + passwordGuess + ".");
					found = true;
					break;
				} 
			}
			
			if (found) {
				continue;
			}
			
		}
		
		System.out.println(output.size() + " have been cracked.");
	}
	
	
	private String[] starterPasswords() {
		String[] starterPasswords;
		for (int uppercaseLetter = 0; uppercaseLetter < 26; uppercaseLetter++) {
			for (int lowercaseLetter = 0; lowercaseLetter < 26; lowercaseLetter++) {
				for (int numbers = 0; numbers < 10000; numbers++) {
					StringBuilder builder = new StringBuilder();
					starterPasswords += builder.
				}
			}
		}
		return starterPasswords;
	}
}

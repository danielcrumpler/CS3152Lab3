package edu.westga.cs3152.datatier;

import java.util.ArrayList;

/**
 * The Class PasswordHashData
 * 
 * @author Daniel Crumpler
 * @version Fall 2020
 */
public class StarterPasswordsData {
	private ArrayList<String> passwords;

	/**
	 * Instantiates a StarterPasswordsData object.
	 * 
	 * @pre none
	 * @post none
	 */
	public StarterPasswordsData() {
		this.passwords = new ArrayList<String>();
		for (int uppercaseLetter = 0; uppercaseLetter < 26; uppercaseLetter++) {
			for (int lowercaseLetter = 0; lowercaseLetter < 26; lowercaseLetter++) {
				for (int number1 = 0; number1 < 10; number1++) {
					for (int number2 = 0; number2 < 10; number2++) {
						for (int number3 = 0; number3 < 10; number3++) {
							for (int number4 = 0; number4 < 10; number4++) {
								String[] upperCase = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
								String[] lowerCase = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
								String currPassword = upperCase[uppercaseLetter] + lowerCase[lowercaseLetter] + number1
										+ number2 + number3 + number4;
								this.passwords.add(currPassword);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Gets the ArrayList of the generated passwords
	 * 
	 * @pre none
	 * @post none
	 * @return passwords of type ArrayList<String>
	 */
	public ArrayList<String> getPasswords() {
		return this.passwords;
	}
}
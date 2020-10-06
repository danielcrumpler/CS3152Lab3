package edu.westga.cs3152.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import edu.westga.cs3152.datatier.PasswordHashData;
import edu.westga.cs3152.datatier.StarterPasswordsData;
import edu.westga.cs3152.datatier.WorstPasswordsHashData;
import edu.westga.cs3152.hashing.SimpleCrypt;

/**
 * The Class SimpleCryptCracker
 * 
 * @author Daniel Crumpler
 * @version Fall 2020
 */
public class SimpleCryptCracker {
	/**
	 * My implementation of password checking using MD5 without salt
	 * 
	 * @param args - not used
	 */
	public static void main(String[] args) {
		PasswordHashData pwData = new PasswordHashData("passwordData.csv");
		WorstPasswordsHashData worstData = new WorstPasswordsHashData("500-worst-passwords.txt");
		SimpleCrypt crypt = new SimpleCrypt();
		HashMap<String, String> output = new HashMap<String, String>();
		StarterPasswordsData starterData = new StarterPasswordsData();

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
				if (crypt.checkPassword(passwordGuess.replace("e", "3"), passwordHash)) {
					output.put(pwData.getUsername(idx), passwordGuess.replace("e", "3"));
					System.out.println("The password for " + pwData.getUsername(idx) + " is "
							+ passwordGuess.replace("e", "3") + ".");
					found = true;
					break;
				}
				if (crypt.checkPassword(passwordGuess.replace("i", "1"), passwordHash)) {
					output.put(pwData.getUsername(idx), passwordGuess.replace("i", "1"));
					System.out.println("The password for " + pwData.getUsername(idx) + " is "
							+ passwordGuess.replace("i", "1") + ".");
					found = true;
					break;
				}
				if (crypt.checkPassword(passwordGuess.replace("o", "0"), passwordHash)) {
					output.put(pwData.getUsername(idx), passwordGuess.replace("o", "0"));
					System.out.println("The password for " + pwData.getUsername(idx) + " is "
							+ passwordGuess.replace("o", "0") + ".");
					found = true;
					break;
				}
				if (crypt.checkPassword(passwordGuess.replace("s", "5"), passwordHash)) {
					output.put(pwData.getUsername(idx), passwordGuess.replace("s", "5"));
					System.out.println("The password for " + pwData.getUsername(idx) + " is "
							+ passwordGuess.replace("s", "5") + ".");
					found = true;
					break;
				}

				if (crypt.checkPassword(passwordGuess.replace("e", "3").replace("i", "1"), passwordHash)) {
					output.put(pwData.getUsername(idx), passwordGuess.replace("e", "3").replace("i", "1"));
					System.out.println("The password for " + pwData.getUsername(idx) + " is "
							+ passwordGuess.replace("e", "3").replace("i", "1") + ".");
					found = true;
					break;
				}

				if (crypt.checkPassword(passwordGuess.replace("e", "3").replace("o", "0"), passwordHash)) {
					output.put(pwData.getUsername(idx), passwordGuess.replace("e", "3").replace("o", "0"));
					System.out.println("The password for " + pwData.getUsername(idx) + " is "
							+ passwordGuess.replace("e", "3").replace("o", "0") + ".");
					found = true;
					break;
				}

				if (crypt.checkPassword(passwordGuess.replace("e", "3").replace("s", "5"), passwordHash)) {
					output.put(pwData.getUsername(idx), passwordGuess.replace("e", "3").replace("s", "5"));
					System.out.println("The password for " + pwData.getUsername(idx) + " is "
							+ passwordGuess.replace("e", "3").replace("s", "5") + ".");
					found = true;
					break;
				}

				if (crypt.checkPassword(passwordGuess.replace("i", "1").replace("o", "0"), passwordHash)) {
					output.put(pwData.getUsername(idx), passwordGuess.replace("i", "1").replace("o", "0"));
					System.out.println("The password for " + pwData.getUsername(idx) + " is "
							+ passwordGuess.replace("e", "3").replace("s", "5") + ".");
					found = true;
					break;
				}

				if (crypt.checkPassword(passwordGuess.replace("i", "1").replace("s", "5"), passwordHash)) {
					output.put(pwData.getUsername(idx), passwordGuess.replace("i", "1").replace("s", "5"));
					System.out.println("The password for " + pwData.getUsername(idx) + " is "
							+ passwordGuess.replace("e", "3").replace("s", "5") + ".");
					found = true;
					break;
				}

				if (crypt.checkPassword(passwordGuess.replace("o", "0").replace("s", "5"), passwordHash)) {
					output.put(pwData.getUsername(idx), passwordGuess.replace("o", "0").replace("s", "5"));
					System.out.println("The password for " + pwData.getUsername(idx) + " is "
							+ passwordGuess.replace("e", "3").replace("s", "5") + ".");
					found = true;
					break;
				}

				if (crypt.checkPassword(
						passwordGuess.replace("e", "3").replace("i", "1").replace("o", "0").replace("s", "5"),
						passwordHash)) {
					output.put(pwData.getUsername(idx),
							passwordGuess.replace("e", "3").replace("i", "1").replace("o", "0").replace("s", "5"));
					System.out.println("The password for " + pwData.getUsername(idx) + " is "
							+ passwordGuess.replace("e", "3").replace("i", "1").replace("o", "0").replace("s", "5")
							+ ".");
					found = true;
					break;
				}
			}

			if (found) {
				continue;
			}

			for (String passwordGuess : starterData.getPasswords()) {
				if (crypt.checkPassword(passwordGuess, passwordHash)) {
					output.put(pwData.getUsername(idx), passwordGuess);
					System.out.println("The password for " + pwData.getUsername(idx) + " is " + passwordGuess + ".");
					found = true;
					break;
				}
			}

			if (!found) {
				System.out.println("The password for " + pwData.getUsername(idx) + " was not found.");
			}
		}

		System.out.println();
		System.out.println(output.size() + " passwords have been cracked.");

		String summary = "username,password" + System.lineSeparator();
		summary += formatString(pwData, output, summary);
		outputToFile(summary);
	}

	private static String formatString(PasswordHashData pwData, HashMap<String, String> output, String summary) {
		for (int i = 0; i < pwData.getUsernames().length; i++) {
			summary += pwData.getUsername(i) + "," + output.get(pwData.getUsername(i)) + System.lineSeparator();
		}
		return summary;
	}

	private static void outputToFile(String summary) {
		try {
			FileOutputStream outputStream = new FileOutputStream("output.csv");
			byte[] strToBytes = summary.getBytes();
			outputStream.write(strToBytes);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

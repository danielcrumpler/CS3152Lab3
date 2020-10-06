package edu.westga.cs3152.datatier;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Class PasswordHashData
 * 
 * @author CS3152
 * @version Fall 2020
 */
public class WorstPasswordsHashData {
	private File passwordHashFile;
	private String[] passwordHash;

	/**
	 * Instantiates a PasswordHashData object. The usernames and password hashes are
	 * loaded from the specified file.
	 * 
	 * @pre filename != null and !filename.isEmpty()
	 * @post size() = number of usernames with given password hashes and getUser(i)
	 *       is the ith username and getHash(i) is the password hash of the ith
	 *       username for i = 0, 1, 2, ..., size() - 1
	 * @param filename the name of the file from which the data is loaded
	 */
	public WorstPasswordsHashData(String filename) {
		if (filename == null) {
			throw new IllegalArgumentException("filename cannot be null");
		}
		if (filename.isEmpty()) {
			throw new IllegalArgumentException("filename cannot be empty");
		}
		this.passwordHashFile = new File(filename);
		this.loadFile();
	}


	/**
	 * Gets the password hash of the password with the specified index
	 * 
	 * @pre index >= 0 and index < size()
	 * @post none
	 * @param index - index of the password
	 * @return the username of the ith user
	 * @throws ArrayIndexOutOfBoundsException if passed in index is less than 0 or
	 *                                        greater than or equal to size()
	 */
	public String getHash(int index) {
		return this.passwordHash[index];
	}
	
	/**
	 * Gets the password hash of the password with the specified index
	 * 
	 * @pre none
	 * @post none
	 * @return an array with the password hashes
	 */
	public String[] getHashes() {
		return this.passwordHash;
	}
	
	/**
	 * Gets the number of usernames with passwords
	 * 
	 * @pre none
	 * @post none
	 * @return the number of usernames
	 */
	public int size() {
		return this.passwordHash.length;
	}
	
	private boolean loadFile() {
		try {
			this.allocateMemoryForPasswordData();
			this.loadPasswordData();
		} catch (IOException exc) {
			this.logFileError(exc);
			return false;
		}
		return true;
	}

	private void allocateMemoryForPasswordData() throws IOException {
		int lineCount = 0;
		try (Scanner input = new Scanner(this.passwordHashFile)) {
			while (input.hasNextLine()) {
				input.nextLine();
				lineCount++;
			}
		}
		this.passwordHash = new String[lineCount];
	}

	private void loadPasswordData() throws IOException {
		int index = 0;
		try (Scanner input = new Scanner(this.passwordHashFile)) {
			while (input.hasNextLine()) {
				String line = input.nextLine();
				this.passwordHash[index] = line;
				index++;
			}
		}
	}

	private void logFileError(IOException exc) {
		System.err.println("Could not read password hashes from " + this.passwordHashFile);
		System.err.println(exc.getMessage());
	}
}
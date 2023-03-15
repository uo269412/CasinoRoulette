package logic;

import java.io.*;
import java.util.*;

public abstract class FileUtil {

	public static void loadUsersFile(List<Player> playersList) {
		String line;
		String[] playersData = null;

		try {
			BufferedReader file = new BufferedReader(new FileReader("files/players.dat"));
			while (file.ready()) {
				line = file.readLine();
				playersData = line.split("@");
				playersList.add(
						new Player(playersData[0], playersData[1], playersData[2], playersData[3], Double.parseDouble(playersData[4])));
			}
			file.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}
	}

	public static void saveToUsersFile(List<Player> playersList) {
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter("files/players.dat"));
			for (Player player : playersList) {
				file.write(player.toString());
				file.newLine();
			}
			file.close();
		}

		catch (FileNotFoundException fnfe) {
			System.out.println("The file could not be saved.");
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}
	}

	public static void saveNewPlayer(Player player) {
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter("files/players.dat"));
			file.append(player.toString());
			file.newLine();
			file.close();
		}

		catch (FileNotFoundException fnfe) {
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}
	}

	public static void loadDrinksFile(List<Drink> drinksList) {
		String line;
		String[] drinksData = null;
		try {
			BufferedReader file = new BufferedReader(new FileReader("files/drinks.dat"));
			while (file.ready()) {
				line = file.readLine();
				drinksData = line.split("@");
				drinksList.add(new Drink(drinksData[0], drinksData[1], Integer.parseInt(drinksData[2]),
						Double.parseDouble(drinksData[3])));
			}
			file.close();
		} catch (FileNotFoundException fnfe) {
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}
	}
}

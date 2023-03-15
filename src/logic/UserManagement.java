package logic;

import java.util.ArrayList;

import logic.exceptions.AlreadyRegisteredException;
import logic.exceptions.PasswordsDontMatchException;
import logic.exceptions.UserNotFoundException;

public class UserManagement {

	private ArrayList<Player> players = new ArrayList<Player>();

	private Player currentPlayer = null;

	public UserManagement() {
		updatePlayerList();
	}

	public Player getPlayer() {
		return currentPlayer;
	}

	public void setPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	private void updatePlayerList() {
		FileUtil.loadUsersFile(players);
	}

	public void register(String dni, String name, String username, String password, String password2)
			throws AlreadyRegisteredException, PasswordsDontMatchException {
		checkPassword(password, password2);
		if (searchForUser(username)) {
			throw new AlreadyRegisteredException();
		}
		Player newPlayer = new Player(dni, name, username, password, 100);
		players.add(newPlayer);
		setPlayer(newPlayer);
		FileUtil.saveNewPlayer(newPlayer);

	}

	public void signIn(String username, String password, String password2)
			throws PasswordsDontMatchException, UserNotFoundException {
		checkPassword(password, password2);
		if (!searchForUser(username)) {
			throw new UserNotFoundException();
		}
		for (Player currentPlayer : players) {
			if (currentPlayer.getUsername().equals(username)) {
				setPlayer(currentPlayer);
			}
		}
	}

	public void logOff(Player player) {
		for (Chip chip : player.getChips()) {
			player.addBalance(chip.getValue());
		}
		ArrayList<Player> updatedPlayerList = new ArrayList<Player>();
		for (Player registeredPlayer : players) {
			if (registeredPlayer.getUsername().equals(player.getUsername())) {
				updatedPlayerList.add(player);
			} else {
				updatedPlayerList.add(registeredPlayer);
			}
		}
		this.players = updatedPlayerList;
		FileUtil.saveToUsersFile(players);

	}

	private boolean searchForUser(String username) {
		for (Player currentPlayer : players) {
			if (currentPlayer.getUsername().equals(username)) {
				return true;
			}
		}
		return false;

	}

	private void checkPassword(String password, String password2) throws PasswordsDontMatchException {
		if (!password.equals(password2)) {
			throw new PasswordsDontMatchException();
		}
	}

}

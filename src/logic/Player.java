package logic;

import java.util.ArrayList;

public class Player {

	private String username;
	private String name;
	private String password;
	private String dni;
	private double balance;
	private String bankAccount;
	private ArrayList<Chip> chips;

	public Player(String username, String password, String dni, double balance, String bankAccount,
			ArrayList<Chip> chips) {
		this.username = username;
		this.password = password;
		this.dni = dni;
		this.balance = balance;
		this.bankAccount = bankAccount;
		this.chips = chips;
	}

	public Player(String dni, String name, String username, String password, double balance) {
		this.dni = dni;
		this.name = name;
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.chips = new ArrayList<Chip>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public ArrayList<Chip> getChips() {
		return chips;
	}

	public String getName() {
		return name;
	}

	public void setChips(ArrayList<Chip> chips) {
		this.chips = chips;
	}

	public void addBalance(double quantity) {
		this.balance += quantity;
	}

	public void addChip(Chip chip) {
		chips.add(chip);
	}

	public void removeChip(Chip chip) {
		chips.remove(chip);
	}

	public void removeChip(int valueOfChip) {
		boolean alreadyRemoved = false;
		ArrayList<Chip> newChipList = new ArrayList<Chip>();
		for (Chip chip : getChips()) {
			if (chip.getValue() == valueOfChip && !alreadyRemoved) {
				alreadyRemoved = true;
			} else {
				newChipList.add(chip);
			}
		}
		setChips(newChipList);
	}

	public int getChipsByValue(int i) {
		int counter = 0;
		for (Chip chip : chips) {
			if (chip.getValue() == i) {
				counter++;
			}
		}
		return counter;
	}

	public int getChipsTotalValue() {
		int value = 0;
		for (Chip chip : chips) {
			value += chip.getValue();
		}
		return value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getDni());
		builder.append("@");
		builder.append(getName());
		builder.append("@");
		builder.append(getUsername());
		builder.append("@");
		builder.append(getPassword());
		builder.append("@");
		builder.append(getBalance());
		return builder.toString();
	}
}

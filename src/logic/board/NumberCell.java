package logic.board;

import java.util.ArrayList;

import logic.Chip;

public class NumberCell extends Cell {

	private boolean isRed;
	private boolean isOdd;
	private boolean isFail;
	private int dozen;
	private int column;
	private int number;

	public NumberCell(String type, String name, int number) {
		super(type, name);
		this.number = number;
		computeNumber(number);
	}

	private void computeNumber(int number) {
		if (number % 2 == 0) {
			this.isOdd = false; // it's even
			this.isRed = false; // it's black
		} else {
			this.isOdd = true;
			this.isRed = true;
		}
		if (number <= 18) {
			this.isFail = true;
		}
		else {
			this.isFail = false;
		}
		if (number <= 12) {
			this.dozen = 1;
		} else if (number > 12) {
			this.dozen = 2;
		} else if (number > 24) {
			this.dozen = 3;
		}

		boolean numberFound = false;
		int counter = 1;
		while (counter <= 36 && !numberFound) {
			if (counter == number) {
				this.column = 1;
			}
			counter += 3;
		}

		while (counter <= 2 && !numberFound) {
			if (counter == number) {
				this.column = 2;
			}
			counter += 3;
		}

		while (counter <= 3 && !numberFound) {
			if (counter == number) {
				this.column = 3;
			}
			counter += 3;
		}
	}

	@Override
	public ArrayList<Chip> getChipsToWin(Chip chip) {
		ArrayList<Chip> price = new ArrayList<Chip>();
		for (int i = 0; i <= 35; i++) {
			price.add(new Chip(chip.getValue()));
		}
		return price;

	}

	public boolean isRed() {
		return isRed;
	}

	public boolean isOdd() {
		return isOdd;
	}

	public boolean isFail() {
		return isFail;
	}

	public int getDozen() {
		return dozen;
	}

	public int getColumn() {
		return column;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	
}

package logic.board;

import java.util.ArrayList;

import logic.Chip;

public class OddEvenCell extends Cell {

	private boolean isOdd;

	public OddEvenCell(String type, String name) {
		super(type, name);
	}

	public OddEvenCell(String type, String name, boolean isOdd) {
		super(type, name);
		this.isOdd = isOdd;
	}

	public boolean isOdd() {
		return isOdd;
	}

	@Override
	public
	ArrayList<Chip> getChipsToWin(Chip chip) {
		ArrayList<Chip> price = new ArrayList<Chip>();
		price.add(new Chip(chip.getValue()));
		price.add(new Chip(chip.getValue()));
		return price;
	}

}

package logic.board;

import java.util.ArrayList;

import logic.Chip;

public class DozenCell extends Cell {

	private int dozen;

	public DozenCell(String type, String name) {
		super(type, name);
	}

	public DozenCell(String type, String name, int dozen) {
		super(type, name);
		this.dozen = dozen;
	}

	@Override
	public
	ArrayList<Chip> getChipsToWin(Chip chip) {
		ArrayList<Chip> price = new ArrayList<Chip>();
		price.add(new Chip(chip.getValue()));
		price.add(new Chip(chip.getValue()));
		price.add(new Chip(chip.getValue()));
		return price;
	}

	public int getDozen() {
		return dozen;
	}

}

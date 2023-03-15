package logic.board;

import java.util.ArrayList;

import logic.Chip;

public class ColorCell extends Cell {

	private boolean isRed;

	public ColorCell(String type, String name) {
		super(type, name);
	}

	public ColorCell(String type, String name, boolean isRed) {
		super(type, name);
		this.isRed = isRed;
	}

	public boolean isRed() {
		return isRed;
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

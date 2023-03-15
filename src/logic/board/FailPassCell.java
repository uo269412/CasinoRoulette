package logic.board;

import java.util.ArrayList;

import logic.Chip;

public class FailPassCell extends Cell {

	private boolean isFail;

	public FailPassCell(String type, String name) {
		super(type, name);
	}

	public FailPassCell(String type, String name, boolean isFail) {
		super(type, name);
		this.isFail = isFail;
	}

	public boolean isFail() {
		return isFail;
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

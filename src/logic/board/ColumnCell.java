package logic.board;

import java.util.ArrayList;

import logic.Chip;

public class ColumnCell extends Cell {

	private int column;

	public ColumnCell(String type, String name) {
		super(type, name);
	}

	public ColumnCell(String type, String name, int column) {
		super(type, name);
		this.column = column;
	}

	@Override
	public ArrayList<Chip> getChipsToWin(Chip chip) {
		ArrayList<Chip> price = new ArrayList<Chip>();
		price.add(new Chip(chip.getValue()));
		price.add(new Chip(chip.getValue()));
		price.add(new Chip(chip.getValue()));
		return price;
	}

	public int getColumn() {
		return column;
	}

}

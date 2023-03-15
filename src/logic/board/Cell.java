package logic.board;

import java.util.ArrayList;

import logic.Chip;

public abstract class Cell {

	private String type;
	private String name;

	public Cell(String type, String name) {
		this.setType(type);
		this.setName(name);
	}

	public abstract ArrayList<Chip> getChipsToWin(Chip chip);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

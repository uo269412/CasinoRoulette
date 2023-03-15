package logic;

import logic.board.Cell;

public class Bet {
	private Chip chip;
	private Cell cell;
	private boolean isWinner;

	public Bet(Cell cell, Chip chip) {
		super();
		this.chip = chip;
		this.cell = cell;
	}

	public Chip getChip() {
		return chip;
	}

	public Cell getCell() {
		return cell;
	}

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cell: " + cell.getName());
		builder.append(" - ");
		builder.append("Chip: " + chip.getValue());
		return builder.toString();
	}

}

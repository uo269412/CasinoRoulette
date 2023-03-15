package logic.board;

import java.util.ArrayList;

public class Board {
	ArrayList<Cell> cells = new ArrayList<Cell>();

	public Board() {
		loadBoard();
	}

	public ArrayList<Cell> getCellsBoard() {
		return this.cells;
	}

	public void loadBoard() {

		for (int i = 0; i <= 36; i++) {
			cells.add(new NumberCell(Integer.toString(i), "Number " + i, i));
		}

		cells.add(new FailPassCell("1-18", "Fail", true));
		cells.add(new FailPassCell("19-36", "Pass", false));

		cells.add(new ColorCell("Red", "Red", true));
		cells.add(new ColorCell("Black", "Black", false));

		cells.add(new OddEvenCell("Odd", "Odd", true));
		cells.add(new OddEvenCell("Even", "Even", false));

		cells.add(new DozenCell("1st dozen", "1st Dozen", 1));
		cells.add(new DozenCell("2nd dozen", "2nd Dozen", 2));
		cells.add(new DozenCell("3rd dozen", "3rd Dozen", 3));

		cells.add(new ColumnCell("C1", "1st Column", 1));
		cells.add(new ColumnCell("C2", "2nd Column", 2));
		cells.add(new ColumnCell("C3", "3rd Column", 3));

	}
}

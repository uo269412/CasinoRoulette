package logic;

import java.util.ArrayList;
import java.util.Random;

import logic.board.Board;
import logic.board.Cell;
import logic.board.ColorCell;
import logic.board.ColumnCell;
import logic.board.DozenCell;
import logic.board.FailPassCell;
import logic.board.NumberCell;
import logic.board.OddEvenCell;

public class Game {

	private Board board;
	private ArrayList<Bet> bets;
	private ArrayList<Chip> prize;
	
	

	public Game() {
		this.board = new Board();
		this.bets = new ArrayList<Bet>();
		this.prize = new ArrayList<Chip>(); 
	}

	public NumberCell getWinnerCell() {
		Random random = new Random();
		return (NumberCell) board.getCellsBoard().get(random.nextInt(37));
	}

	public ArrayList<Bet> getBets() {
		return bets;
	}

	public Board getBoard() {
		return board;
	}

	private ArrayList<Chip> getPrize() {
		return this.prize;
	}

	public void addBet(Cell cell, Chip chip) {
		bets.add(new Bet(cell, chip));
	}

	private void addBetToPrize(Bet bet) {
		bet.setWinner(true);
		prize.addAll(bet.getCell().getChipsToWin(bet.getChip()));
	}

	public void resetBets(Player player) {
		for (Bet bet : bets) {
			player.addChip(bet.getChip());
		}
		this.bets = new ArrayList<Bet>();
	}

	// Error looking

	public Cell searchInBoard(String type) {
		for (Cell cell : getBoard().getCellsBoard()) {
			if (type.equals(cell.getType())) {
				return cell;
			}
		}
		return null;
	}

	public boolean alreadyBetCell(String cellType) {
		for (Bet bet : getBets()) {
			if (cellType.equals(bet.getCell().getType())) {
				return true;
			}
		}
		return false;
	}

	// End of game
	
	public void redeemChips(Player player) {
		player.getChips().addAll(getPrize());
	}

	public int calcTotalPrize() {
		int totalPrize = 0;
		for (Chip chip : prize) {
			totalPrize += chip.getValue();
		}
		return totalPrize;
	}

	public String getWinnerBetsString() {
		StringBuilder builder = new StringBuilder();
		for (Bet bet : bets) {
			if (bet.isWinner()) {
				builder.append(bet.toString());
				builder.append(System.lineSeparator());
			}
		}
		return builder.toString();
	}

	public void computeEndOfGame(NumberCell winner) {
		for (Bet bet : bets) {
			if (bet.getCell() instanceof NumberCell) {
				if (winner.getNumber() == ((NumberCell) bet.getCell()).getNumber()) {
					addBetToPrize(bet);
				}
			} else {
				if (winner.getNumber() == 0) {
					computeWinnerZero(bet);
				} else {
					computeWinnerNotNumber(winner, bet);
				}
			}
		}
	}

	private void computeWinnerNotNumber(NumberCell winner, Bet bet) {
		if (bet.getCell() instanceof ColorCell) {
			if (winner.isRed() == ((ColorCell) bet.getCell()).isRed()) {
				addBetToPrize(bet);
			}
		} else if (bet.getCell() instanceof ColumnCell) {
			if (winner.getColumn() == ((ColumnCell) bet.getCell()).getColumn()) {
				addBetToPrize(bet);
			}
		} else if (bet.getCell() instanceof DozenCell) {
			if (winner.getDozen() == ((DozenCell) bet.getCell()).getDozen()) {
				addBetToPrize(bet);
			}
		} else if (bet.getCell() instanceof FailPassCell) {
			if (winner.isFail() == ((FailPassCell) bet.getCell()).isFail()) {
				addBetToPrize(bet);
			}
		} else if (bet.getCell() instanceof OddEvenCell) {
			if (winner.isOdd() == ((OddEvenCell) bet.getCell()).isOdd()) {
				addBetToPrize(bet);
			}
		}
	}

	private void computeWinnerZero(Bet bet) {
		if (bet.getChip().getValue() == 5) {
			prize.add(new Chip(5));
		} else if (bet.getChip().getValue() == 10) {
			prize.add(new Chip(10));
			prize.add(new Chip(5));
		} else if (bet.getChip().getValue() == 20) {
			prize.add(new Chip(20));
			prize.add(new Chip(10));
		} else if (bet.getChip().getValue() == 50) {
			prize.add(new Chip(50));
			prize.add(new Chip(20));
			prize.add(new Chip(5));
		} else if (bet.getChip().getValue() == 100) {
			prize.add(new Chip(100));
			prize.add(new Chip(50));
		}
	}

}

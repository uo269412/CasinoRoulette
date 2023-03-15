package logic;

import java.util.ArrayList;

public class Shop {

	private ArrayList<Drink> drinks = new ArrayList<Drink>();
	private Order order;

	public Shop() {
		this.order = new Order();
	}

	// BALANCE

	public void addMoney(double d, Player player) {
		player.setBalance(player.getBalance() + d);
	}

	// CHIPS

	public int calcTotalPriceChips(int number5Chip, int number10Chip, int number20Chip, int number50Chip,
			int number100Chip) {
		return number5Chip * 5 + number10Chip * 10 + number20Chip * 20 + number50Chip * 50 + number100Chip * 100;

	}

	public void addChips(int valueOfChip, int unitsOfChip, Player player) {
		for (int i = 0; i < unitsOfChip; i++) {
			player.addChip(new Chip(valueOfChip));
		}
	}

	public void returnChips(int valueOfChip, int unitsOfChip, Player player) {
		int numberOfReturnedChips = 0;
		ArrayList<Chip> newChipList = new ArrayList<Chip>();
		for (Chip chip : player.getChips()) {
			if (chip.getValue() == valueOfChip && numberOfReturnedChips != unitsOfChip) {
				numberOfReturnedChips++;
			} else {
				newChipList.add(chip);
			}
		}
		player.setChips(newChipList);
	}

	// DRINKS

	private void loadDrinks() {
		FileUtil.loadDrinksFile(drinks);
	}

	private void loadDrinksByType(boolean alcholic) {
		loadDrinks();
		ArrayList<Drink> drinksFiltered = new ArrayList<Drink>();
		for (Drink drink : drinks) {
			if (drink.isAlcoholic() == alcholic) {
				drinksFiltered.add(drink);
			}
		}
		this.drinks = drinksFiltered;
	}

	public Drink[] getDrinks() {
		loadDrinks();
		Drink[] drinkArray = drinks.toArray(new Drink[drinks.size()]);
		return drinkArray;
	}

	public Drink[] getDrinksByType(boolean alcoholic) {
		loadDrinksByType(alcoholic);
		Drink[] drinkArray = drinks.toArray(new Drink[drinks.size()]);
		return drinkArray;
	}

	public void addDrinkToOrder(Drink drink) {
		order.addToOrder(drink);
	}

	public void removeDrinkFromOrder(Drink drink) {
		order.removeFromOrder(drink);
	}

	public void confirmDrinkOrder() {
		this.order = new Order();
		resetDrinkOrder();
	}

	public void resetDrinkOrder() {
		order = new Order();
	}

	public ArrayList<Drink> getDrinkOrder() {
		return order.getDrinkOrder();
	}

	public double getPriceOrder() {
		double price = 0.0;
		for (Drink drink : order.getDrinkOrder()) {
			price += drink.getPrice();
		}
		return price;
	}
}

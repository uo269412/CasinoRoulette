package logic;

import java.util.ArrayList;

public class Order {

	ArrayList<Drink> drinkOrder;

	public Order() {
		drinkOrder = new ArrayList<Drink>();
	}

	public ArrayList<Drink> getDrinkOrder() {
		return drinkOrder;
	}

	public void addToOrder(Drink drink) {
		drinkOrder.add(drink);
	}

	public void removeFromOrder(Drink drink) {
		drinkOrder.remove(drink);
	}
}

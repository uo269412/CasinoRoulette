package logic;

public class Drink {

	private String code;
	private String name;
	private boolean alcoholic;
	private double price;

	private boolean warm;
	private boolean ice;

	public Drink(String code, String name, int alcoholic, double price) {
		this.code = code;
		this.name = name;
		if (alcoholic == 1) {
			this.alcoholic = true;
		} else {
			this.alcoholic = false;
		}
		this.price = price;
	}

	public Drink(Drink drink, boolean warm, boolean ice) {
		this.name = drink.getName();
		this.price = drink.getPrice();
		this.warm = warm;
		this.ice = ice;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public boolean isAlcoholic() {
		return alcoholic;
	}

	public double getPrice() {
		return price;
	}

	public boolean isWarm() {
		return warm;
	}

	public boolean isIce() {
		return ice;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAlcoholic(boolean alcoholic) {
		this.alcoholic = alcoholic;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setWarm(boolean warm) {
		this.warm = warm;
	}

	public void setIce(boolean ice) {
		this.ice = ice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getName() + " - ");
		builder.append(getPrice() + " €");
		if (isWarm()) {
			builder.append(" - WARM");
		}
		if (isIce()) {
			builder.append(" - ICE");
		}
		return builder.toString();
	}

}

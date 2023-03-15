package logic;

public class Chip {

	private int value;

	public Chip(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Chip [value=" + value + "]";
	}

}

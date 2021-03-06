import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class MusicalInstrument implements InstrumentFunc<MusicalInstrument> {
	private Number price;
	private String brand;

	public MusicalInstrument(String brand, Number price) {
		setBrand(brand);
		setPrice(price);
	}

	public MusicalInstrument(Scanner scanner) {
		Number price = 0;
		String brand;

		try {
			if (scanner.hasNextInt()) {
				price = scanner.nextInt();
			} else {
				price = scanner.nextDouble();
			}
		} catch (InputMismatchException ex) {
			throw new InputMismatchException("Price not found!");
		}
		setPrice(price);
		scanner.nextLine();
		brand = scanner.nextLine();
		setBrand(brand);
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Number getPrice() {
		return price;
	}

	public void setPrice(Number price) {
		if (price.doubleValue() > 0)
			this.price = price;
		else
			throw new InputMismatchException("Price must be a positive number!");

	}

	protected boolean isValidType(String[] typeArr, String material) {
		for (int i = 0; i < typeArr.length; i++) {
			if (material.equals(typeArr[i])) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof MusicalInstrument))
			return false;

		MusicalInstrument otherInstrument = (MusicalInstrument) o;

		return getPrice().doubleValue() == otherInstrument.getPrice().doubleValue()
				&& getBrand().equals(otherInstrument.getBrand());
	}

	@Override
	public int compareTo(MusicalInstrument other) {
		int brandComapare = this.getBrand().compareToIgnoreCase(other.getBrand());
		if (brandComapare == 0) {
			return (int) (this.getPrice().doubleValue() - other.getPrice().doubleValue());
		}
		return brandComapare;
	}

	@Override
	public String toString() {
		if (price instanceof Double) {
			return String.format("%-8s %-9s| Price: %7.2f,", getBrand(), getClass().getCanonicalName(), getPrice());
		} else
			return String.format("%-8s %-9s| Price: %7d,", getBrand(), getClass().getCanonicalName(), getPrice());
	}
}

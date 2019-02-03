package ro.utcluj.model.library;

public class Book {

	/**
	 * The title, author and year fields form a natural identifier (Primary Key)
	 */
	private final String title;
	private final String author;
	private final String genre;

	private final int year;
	private int quantity;
	private final double price;

	public Book(String title, String author, String genre, int year, int quantity, double price) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.year = year;
		this.quantity = quantity;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getGenre() {
		return genre;
	}

	public int getYear() {
		return year;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", genre="
				+ genre + ", year=" + year + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return year == other.year;
	}
}

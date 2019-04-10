package ro.utcluj.view.model.library;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Book {

    /**
     * The title, author and year fields form a natural identifier (Primary Key)
     */
    private final String title;
    private final String author;
    private final String genre;
    private final int    year;

    /**
     * Debatable if these are properties of the book or not.
     * In a real life application, these properties would be stored in a Book decorator
     * This way, you can reuse the book entity for a different library instance with different stock and price
     */
    private IntegerProperty quantity;
    private double          price;

    public Book(String title, String author, String genre, int year, int quantity, double price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.quantity = new SimpleIntegerProperty(quantity);
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
        return quantity.getValue();
    }

    public void setQuantity(int quantity) {
        this.quantity.setValue(quantity);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
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

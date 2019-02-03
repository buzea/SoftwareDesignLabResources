package ro.utcluj.model.report;

import ro.utcluj.model.library.Book;
import ro.utcluj.model.library.Library;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class TextReport implements Report {

    private final Library library;

    public TextReport() {
        library = Library.getInstance();
    }

    @Override
    public boolean generateReport() {
        File file = new File("Report.txt");
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (Book b : library.getBooks()) {
                if (b.getQuantity() == 0) {
                    String formatStr = "%-20s %-20s %-15s%n";
                    bw.write(String.format(formatStr, b.getTitle(), b.getAuthor(), b.getYear()));
                }
            }
            bw.close();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }
}

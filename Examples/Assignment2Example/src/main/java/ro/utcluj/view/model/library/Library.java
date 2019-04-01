package ro.utcluj.view.model.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class Library {

    private static final File                 xmlFile = new File("src/main/resources/Books.xml");
    private static       Library              instance;
    private              ObservableList<Book> books; // because of this field from javafx.collections, this is actually a viewmodel

    private Library() {

        books = FXCollections.observableArrayList();
        readBooksFromFile();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    private void readBooksFromFile() {
        try {
            NodeList nList = readBooksNodeList();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Book book = convertNodeToBook((Element) nNode);
                    books.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private NodeList readBooksNodeList() throws ParserConfigurationException, SAXException, IOException {
        Document doc = buildDocument();
        return doc.getElementsByTagName("Book");
    }

    private Document buildDocument() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return doc;
    }

    private Book convertNodeToBook(Element nNode) {
        Element eElement = nNode;
        String title = getContent(eElement, "title");
        String author = getContent(eElement, "author");
        int year = Integer.parseInt(getContent(eElement, "year"));
        String genre = getContent(eElement, "genre");
        int quantity = Integer.parseInt(getContent(eElement, "quantity"));
        double price = Double.parseDouble(getContent(eElement, "price"));
        return new Book(title, author, genre, year, quantity, price);
    }

    private String getContent(Element eElement, String title) {
        return eElement.getElementsByTagName(title).item(0).getTextContent();
    }

    private void persist() {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
            doc.getDocumentElement().normalize();

            Element rootElement = doc.getDocumentElement();
            NodeList nList = doc.getElementsByTagName("Book");
            int temp;
            for (temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    Book book = books.get(temp);
                    eElement.getElementsByTagName("title").item(0).setTextContent(book.getTitle());
                    eElement.getElementsByTagName("author").item(0).setTextContent(book.getAuthor());
                    eElement.getElementsByTagName("year").item(0).setTextContent(book.getYear() + "");
                    eElement.getElementsByTagName("genre").item(0).setTextContent(book.getGenre());
                    eElement.getElementsByTagName("quantity").item(0).setTextContent(book.getQuantity() + "");
                    eElement.getElementsByTagName("price").item(0).setTextContent(book.getPrice() + "");
                }
            }

            if (books.size() > nList.getLength()) {
                for (; temp < books.size(); temp++) {
                    Book book = books.get(temp);

                    Element bookElement = doc.createElement("Book");
                    rootElement.appendChild(bookElement);

                    Element title = doc.createElement("title");
                    title.appendChild(doc.createTextNode(book.getTitle()));
                    bookElement.appendChild(title);

                    Element auth = doc.createElement("author");
                    auth.appendChild(doc.createTextNode(book.getAuthor()));
                    bookElement.appendChild(auth);

                    Element year = doc.createElement("year");
                    year.appendChild(doc.createTextNode(book.getYear() + ""));
                    bookElement.appendChild(year);

                    Element genre = doc.createElement("genre");
                    genre.appendChild(doc.createTextNode(book.getGenre()));
                    bookElement.appendChild(genre);

                    Element quantity = doc.createElement("quantity");
                    quantity.appendChild(doc.createTextNode(book.getQuantity() + ""));
                    bookElement.appendChild(quantity);

                    Element price = doc.createElement("price");
                    price.appendChild(doc.createTextNode(book.getPrice() + ""));
                    bookElement.appendChild(price);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Library [books=" + books + "]";
    }

    public ObservableList<Book> getBooks() {
        return books;
    }

    public boolean sell(Book book, int amount) {
        for (Book i : books) {
            if (i.equals(book)) {
                int stock = i.getQuantity();
                if (stock >= amount) {
                    i.setQuantity(stock - amount);
                    persist();
                    return true;
                }
                break;
            }
        }

        return false;
    }

    public void addBook(Book book) {
        books.add(book);
        persistBook(book);
    }

    private void persistBook(Book book) {
        try {
            Document doc = buildDocument();
            NodeList nList = doc.getElementsByTagName("Book");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Book book2 = convertNodeToBook((Element) nNode);
                    if (book2.equals(book)) {
                        return;
                    }
                }
            }

            Element rootElement = doc.getDocumentElement();

            Element bookElement = doc.createElement("Book");
            rootElement.appendChild(bookElement);

            Element title = doc.createElement("title");
            title.appendChild(doc.createTextNode(book.getTitle()));
            bookElement.appendChild(title);

            Element auth = doc.createElement("author");
            auth.appendChild(doc.createTextNode(book.getAuthor()));
            bookElement.appendChild(auth);

            Element year = doc.createElement("year");
            year.appendChild(doc.createTextNode(book.getYear() + ""));
            bookElement.appendChild(year);

            Element genre = doc.createElement("genre");
            genre.appendChild(doc.createTextNode(book.getGenre()));
            bookElement.appendChild(genre);

            Element quantity = doc.createElement("quantity");
            quantity.appendChild(doc.createTextNode(book.getQuantity() + ""));
            bookElement.appendChild(quantity);

            Element price = doc.createElement("price");
            price.appendChild(doc.createTextNode(book.getPrice() + ""));
            bookElement.appendChild(price);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteBook(Book book) {
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Book");
            Element rootElement = doc.getDocumentElement();

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String title = getContent(eElement, "title");
                    String author = getContent(eElement, "author");
                    int year = Integer.parseInt(getContent(eElement, "year"));
                    if (title.equalsIgnoreCase(book.getTitle()) && author.equalsIgnoreCase(book.getAuthor()) && year == book.getYear()) {
                        rootElement.removeChild(nNode);
                    }
                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Book findBook(String title, String author, int year) {
        Book e = null;
        for (Book book : books) {
            if (title.equalsIgnoreCase(book.getTitle()) && author.equalsIgnoreCase(book.getAuthor()) && year == book.getYear()) {
                e = book;
                break;
            }
        }

        return e;
    }
}

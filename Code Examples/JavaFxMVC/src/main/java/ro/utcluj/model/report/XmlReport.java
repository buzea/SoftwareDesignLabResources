package ro.utcluj.model.report;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ro.utcluj.model.library.Book;
import ro.utcluj.model.library.Library;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XmlReport implements Report {

	private final Library library;

	public XmlReport() {
		library = Library.getInstance();
	}

	@Override
	public boolean generateReport() {

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("BooksOutOfStock");
			doc.appendChild(rootElement);
			for (Book book : library.getBooks()) {
				if (book.getQuantity() == 0) {
					rootElement.appendChild(createElement(book, doc));
				}
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("Report.xml"));

			transformer.transform(source, result);


			return true;
		} catch (ParserConfigurationException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	private Element createElement(Book book, Document doc) {
		Element bookElement = doc.createElement("Book");
		//rootElement.appendChild(bookElement);

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

		return bookElement;

	}

}

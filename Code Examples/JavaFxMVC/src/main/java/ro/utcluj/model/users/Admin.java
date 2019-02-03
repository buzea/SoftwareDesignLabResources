package ro.utcluj.model.users;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ro.utcluj.model.report.Report;
import ro.utcluj.model.report.ReportFactory;
import ro.utcluj.model.report.ReportFactory.ReportType;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password, User.Type.ADMIN);
    }

    // create
    public boolean createUser(String username, String password, int type) {

        try {

            File fXmlFile = new File("Users.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            Element rootElement = doc.getDocumentElement();
            NodeList nList = doc.getElementsByTagName("User");
            // check for unique username
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String nodeUsername = eElement.getElementsByTagName("Username").item(0).getTextContent();

                    if (username.equals(nodeUsername))
                        return false;
                }
            }

            Element staff = doc.createElement("User");
            rootElement.appendChild(staff);

            // set attribute to staff element
            if (type == 1)
                staff.setAttribute("type", "admin");
            else
                staff.setAttribute("type", "employee");

            Element usernameNode = doc.createElement("Username");
            usernameNode.appendChild(doc.createTextNode(username));
            staff.appendChild(usernameNode);

            Element passwordNode = doc.createElement("Password");
            passwordNode.appendChild(doc.createTextNode(password));
            staff.appendChild(passwordNode);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(fXmlFile);
            transformer.transform(source, result);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // read
    public Object[][] getUserMatrix() {
        Object[][] result = null;
        List<User> list = new LinkedList<>();
        try {
            File fXmlFile = new File("Users.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            NodeList nList = doc.getElementsByTagName("User");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String type = eElement.getAttribute("type");
                    if (type.equalsIgnoreCase("employee")) {
                        String nodeUsername = eElement.getElementsByTagName("Username").item(0).getTextContent();
                        String nodePassword = eElement.getElementsByTagName("Password").item(0).getTextContent();
                        Employee employee = new Employee(nodeUsername, nodePassword);
                        list.add(employee);
                    } else {
                        String nodeUsername = eElement.getElementsByTagName("Username").item(0).getTextContent();
                        Admin admin = new Admin(nodeUsername, "*SECRET*");
                        list.add(admin);
                    }
                }
            }
            result = new Object[list.size()][3];
            for (int i = 0; i < list.size(); i++) {
                User e = list.get(i);
                result[i][0] = e.getUsername();
                result[i][1] = e.getPassword();
                result[i][2] = e.getType();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // update
    public boolean updatePassword(String username, String newPassword) {
        if (newPassword.equals(""))
            return false;

        try {
            File fXmlFile = new File("Users.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //Element rootElement = doc.getDocumentElement();
            NodeList nList = doc.getElementsByTagName("User");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String type = eElement.getAttribute("type");
                    String nodeUsername = eElement.getElementsByTagName("Username").item(0).getTextContent();
                    //String nodePassword = eElement.getElementsByTagName("Password").item(0).getTextContent();
                    if (username.equals(nodeUsername)) {
                        if (type.equalsIgnoreCase("employee")) {
                            eElement.getElementsByTagName("Password").item(0).setTextContent(newPassword);
                        } else {
                            //can't change the admin password
                            return false;
                        }
                    }
                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(fXmlFile);
            transformer.transform(source, result);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // delete
    public boolean deleteUser(String username, String password) {
        try {
            File fXmlFile = new File("Users.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            Element rootElement = doc.getDocumentElement();
            NodeList nList = doc.getElementsByTagName("User");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String type = eElement.getAttribute("type");
                    String nodeUsername = eElement.getElementsByTagName("Username").item(0).getTextContent();
                    String nodePassword = eElement.getElementsByTagName("Password").item(0).getTextContent();
                    if (username.equals(nodeUsername)) {
                        if (type.equalsIgnoreCase("employee")) {
                            rootElement.removeChild(nNode);
                        } else {
                            if (password.equals(nodePassword)) {
                                rootElement.removeChild(nNode);
                            }
                        }
                    }
                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(fXmlFile);
            transformer.transform(source, result);

            return true;
        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public boolean generateReport(int type) {
        ReportType reportType = type == 1 ? ReportType.XML : ReportType.TEXT;
        Report report = ReportFactory.createReport(reportType);

        return Objects.requireNonNull(report).generateReport();
    }

    public boolean openReport(int type) {
        String fileName;
        if (type == 0)
            fileName = "Report.txt";
        else
            fileName = "Report.xml";
        File file = new File(fileName);
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(file);
                // Desktop.getDesktop().edit(file);
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }
}

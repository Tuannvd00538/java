/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hixml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Computer
 */
public class HiXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            File inputFile = new File("./src/resource/students.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nodeListStudents = doc.getElementsByTagName("student");
            for (int i = 0; i < nodeListStudents.getLength(); i++) {
                Node studentNode = nodeListStudents.item(i);
                if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println("----------------------------");
                    Element eElement = (Element) studentNode;
                    System.out.println("Student ID: " + eElement.getAttribute("id"));
                    System.out.println("RollNumber: " + eElement.getElementsByTagName("rollNumber").item(0).getTextContent());
                    System.out.println("Name: " + eElement.getElementsByTagName("name").item(0).getTextContent());
                }
            }

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            System.err.println(e.getMessage());
        }
    }

}

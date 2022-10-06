package Model;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static truc.HelloApplication.planet;

public class Model {

    Path path;

    public static String current_Planet;
    public static int current_Gravity;

    public final String KAMINO;
    public final int KAMINO_GRAVITY;
    public final String ENDOR;
    public final int ENDOR_GRAVITY;

    public Model(String path) throws IOException, SAXException {
        this.path = Path.of(path + "universe.xml");
        KAMINO = this.getKAMINO();
        KAMINO_GRAVITY = this.getKAMINO_GRAVITY();
        ENDOR = this.getENDOR();
        ENDOR_GRAVITY = this.getENDOR_GRAVITY();

        this.current_Planet = this.current_Planet();
        this.current_Gravity = this.current_Gravity();
    }

    public int current_Gravity() {
        if (planet) {
            return this.ENDOR_GRAVITY;
        } else {
            return this.KAMINO_GRAVITY;
        }
    }

    public String current_Planet() {
        if (planet) {
            return this.ENDOR;
        } else {
            return this.KAMINO;
        }
    }

    public String getKAMINO() throws SAXException, IOException {
        try {
            File file = new File(this.path.toString());
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            String Kamino = document.getElementsByTagName("planet1").item(0).getTextContent();
            return Kamino;
        } catch (ParserConfigurationException ex) {
            System.err.println("An exception has ocurred while reading from the XML file : " + ex.getCause());
            return null;
        }
    }

    public int getKAMINO_GRAVITY() throws SAXException, IOException {
        try {
            File file = new File(this.path.toString());
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            int KaminoGravity = Integer.parseInt(document.getElementsByTagName("gravity1").item(0).getTextContent());
            return KaminoGravity;
        } catch (ParserConfigurationException ex) {
            System.err.println("An exception has ocurred while reading from the XML file : " + ex.getCause());
            return 0;
        }
    }

    public String getENDOR() throws SAXException, IOException {
        try {
            File file = new File(this.path.toString());
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            String Endor = document.getElementsByTagName("planet2").item(0).getTextContent();
            return Endor;
        } catch (ParserConfigurationException ex) {
            System.err.println("An exception has ocurred while reading from the XML file : " + ex.getCause());
            return null;
        }
    }
    public int getENDOR_GRAVITY() throws SAXException, IOException {
        try {
            File file = new File(this.path.toString());
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            int EndorGravity = Integer.parseInt(document.getElementsByTagName("gravity2").item(0).getTextContent());
            return EndorGravity;
        } catch (ParserConfigurationException ex) {
            System.err.println("An exception has ocurred while reading from the XML file : " + ex.getCause());
            return 0;
        }
    }
}

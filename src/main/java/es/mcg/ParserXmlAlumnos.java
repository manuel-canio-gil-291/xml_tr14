package es.mcg;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ParserXmlAlumnos {
    public static void main(String[] args) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try 
        {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File("alumnos.xml"));
            Element rootElement = document.getDocumentElement();
            NodeList nodeListAlumno = rootElement.getElementsByTagName("alumno");
            for(int i = 0; i < nodeListAlumno.getLength(); i++)
            {
                Element nodeAlumno = (Element) nodeListAlumno.item(i);

                String nombre = nodeAlumno.getAttributes().getNamedItem("nombre").getTextContent();
                Integer edad = Integer.valueOf(nodeAlumno.getAttributes().getNamedItem("edad").getTextContent());
                Double calificacion = Double.valueOf(nodeAlumno.getElementsByTagName("calificacion").item(0).getTextContent());
                Boolean unidadesPendientes = Boolean.valueOf(nodeAlumno.getElementsByTagName("unidadesPendientes").item(0).getTextContent());
                
            }
            

        } 
        catch (Exception e) 
        {
            // TODO: handle exception
        }
    }
}

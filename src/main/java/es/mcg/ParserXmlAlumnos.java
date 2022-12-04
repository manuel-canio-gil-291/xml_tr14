package es.mcg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParserXmlAlumnos {
    public static class Alumno 
    {
        private String nombre;
        private Integer edad;
        private Double calificacion;
        private Boolean unidadesPendientes;
        public Alumno(String nombre, Integer edad, Double calificacion, Boolean unidadesPendientes) {
            this.nombre = nombre;
            this.edad = edad;
            this.calificacion = calificacion;
            this.unidadesPendientes = unidadesPendientes;
        }
        public String getNombre() {
            return nombre;
        }
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        public Integer getEdad() {
            return edad;
        }
        public void setEdad(Integer edad) {
            this.edad = edad;
        }
        public Double getCalificacion() {
            return calificacion;
        }
        public void setCalificacion(Double calificacion) {
            this.calificacion = calificacion;
        }
        public Boolean getUnidadesPendientes() {
            return unidadesPendientes;
        }
        public void setUnidadesPendientes(Boolean unidadesPendientes) {
            this.unidadesPendientes = unidadesPendientes;
        }
        @Override
        public String toString() {
            return "Alumno [nombre=" + nombre + ", edad=" + edad + ", calificacion=" + calificacion
                    + ", unidadesPendientes=" + unidadesPendientes + "]";
        }
    }
    public static void main(String[] args) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        File file = null;
        List<Alumno> alumnosinUnidades = null;
        try 
        {
            file = new File("salida-alumnos.txt");
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File("alumnos.xml"));
            Element rootElement = document.getDocumentElement();
            NodeList nodeListAlumno = rootElement.getElementsByTagName("alumno");
            alumnosinUnidades = new ArrayList<Alumno>();
            for(int i = 0; i < nodeListAlumno.getLength(); i++)
            {
                Element nodeAlumno = (Element) nodeListAlumno.item(i);

                String nombre = nodeAlumno.getAttributes().getNamedItem("nombre").getTextContent();
                Integer edad = Integer.valueOf(nodeAlumno.getAttributes().getNamedItem("edad").getTextContent());
                Double calificacion = Double.valueOf(nodeAlumno.getElementsByTagName("calificacion").item(0).getTextContent());
                Boolean unidadesPendientes = Boolean.valueOf(nodeAlumno.getElementsByTagName("unidadesPendientes").item(0).getTextContent());
                
            }
            

        } 
        catch (ParserConfigurationException | SAXException | IOException xmlException) 
        {
            xmlException.printStackTrace();
        }
    }
}

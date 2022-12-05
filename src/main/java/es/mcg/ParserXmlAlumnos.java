package es.mcg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        List<Alumno> alumnosinUnidades = null;
        Alumno alumno = null;
        Double calificacionM = 0.0, calificacionP = 0.0, media = 0.0;
        String nombreA = "", nombreB = "";
        try 
        {
            file = new File("salida-alumnos.txt");
            fileWriter = new FileWriter(file);
            printWriter = new PrintWriter(fileWriter);
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
                media += calificacion;
                if(calificacionM == 0.0 && calificacionP == 0.0)
                {
                    calificacionM = calificacion;
                    nombreA = nombre;
                    calificacionP = calificacion;
                    nombreB = nombre;
                }
                else if(calificacion > calificacionM)
                {
                    calificacionM = calificacion;
                    nombreA = nombre;
                }
                if(calificacion < calificacionP)
                {
                    calificacionP = calificacion;
                    nombreB = nombre;
                }
                Boolean unidadesPendientes = Boolean.valueOf(nodeAlumno.getElementsByTagName("unidadesPendientes").item(0).getTextContent());
                alumno = new Alumno(nombre, edad, calificacion, unidadesPendientes);
                if(unidadesPendientes == false)
                {
                    alumnosinUnidades.add(alumno);
                }
            }
            printWriter.println("Listado de alumnos sin unidades pendientes:");
            printWriter.println(alumnosinUnidades);
            printWriter.println("Alumno con mejor calificacion: "+nombreA);
            printWriter.println("Alumno con peor calificacion: "+nombreB);
            printWriter.println("Nota media de clase: "+(media/nodeListAlumno.getLength()));
            printWriter.flush();
        } 
        catch (ParserConfigurationException | SAXException | IOException xmlException) 
        {
            xmlException.printStackTrace();
        }
    }
}

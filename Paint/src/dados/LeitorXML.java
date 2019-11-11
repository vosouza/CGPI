/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dados;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import figuras.Poligono;
import javafx.scene.paint.Color;
import primitivos.CirculoGr;
import primitivos.PontoGr;
import primitivos.QuadradoGr;
import primitivos.RetaGr;

/**
 *
 * @author bruno.chagas
 */
public class LeitorXML {
	
	private ListaPrimitivos lista;
	
	
	public LeitorXML(ListaPrimitivos lista){
		this.lista = lista;
	}
	
	public ListaPrimitivos getListaPrimitivos(){
		return this.lista;
	}
    /**
     * @param fileAsString 
     * @param args the command line arguments
     */
    public void passar(String fileAsString) {
        try {
        	
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        System.out.print(fileAsString);
	       // fileAsString = "file:///" + fileAsString;
	        Document doc = builder.parse(fileAsString);
	      
	        NodeList figura = doc.getElementsByTagName("Figura");
            Node primitivos = figura.item(0);//entra dentro do no figura
            NodeList listaPrimitivos = primitivos.getChildNodes();//pega todos os filhos do no figura;
            
            int qtdNodes = listaPrimitivos.getLength();
            for(int i=0 ; i<qtdNodes; i++) {
            	Node primitivo = listaPrimitivos.item(i);
            	
            	if(primitivo.getNodeName().equals("Reta")) {
            		NodeList reta = primitivo.getChildNodes();
            		Element ponto = (Element) reta.item(0);
            		
            		NodeList coordenadas = ponto.getChildNodes();
            		Node xstring = coordenadas.item(0);
            		Node ystring = coordenadas.item(1);
            
            		double x = Double.parseDouble(xstring.getTextContent())*500;
            		double y = Double.parseDouble(ystring.getTextContent())*500;
            				System.out.println(x+"xxx"+y);
            		ponto = (Element) reta.item(1);
            		coordenadas = ponto.getChildNodes();
            		Node x2string = coordenadas.item(0);
            		Node y2string = coordenadas.item(1);
            	
            		double x2 = Double.parseDouble(x2string.getTextContent())*500;
            		double y2 = Double.parseDouble(y2string.getTextContent())*500;
            		System.out.println(x2+"yyy"+y2);
            		lista.inserir(new RetaGr( (int)x,  (int)y,  (int)x2,  (int)y2, Color.BLACK, "",  1));

            	}else if(primitivo.getNodeName().equals("Circulo")) {
            		NodeList circulo = primitivo.getChildNodes();
            		Element ponto = (Element) circulo.item(0);
            		
            		NodeList coordenadas = ponto.getChildNodes();
            		Node xstring = coordenadas.item(0);
            		Node ystring = coordenadas.item(1);
            		
            		double x = Double.parseDouble(xstring.getTextContent())*500;
            		double y = Double.parseDouble(ystring.getTextContent())*500;
            		
            		ponto = (Element) circulo.item(1);
            		double raio = Double.parseDouble(ponto.getTextContent())*500;
            		
            		lista.inserir(new CirculoGr((int)x, (int)y,(int)raio, Color.BLACK, "", 1));
            	}else if(primitivo.getNodeName().equals("Retangulo")) {
            		NodeList quadrado = primitivo.getChildNodes();
            		Element ponto = (Element) quadrado.item(0);
            		
            		NodeList coordenadas = ponto.getChildNodes();
            		Node xstring = coordenadas.item(0);
            		Node ystring = coordenadas.item(1);
            		
            		double x = Double.parseDouble(xstring.getTextContent())*500;
            		double y = Double.parseDouble(ystring.getTextContent())*500;
            		
            		ponto = (Element) quadrado.item(1);
            		coordenadas = ponto.getChildNodes();
            		Node x2string = coordenadas.item(0);
            		Node y2string = coordenadas.item(1);
            	
            		double x2 = Double.parseDouble(x2string.getTextContent())*500;
            		double y2 = Double.parseDouble(y2string.getTextContent())*500;
            		
            		lista.inserir(new QuadradoGr((int)x,  (int)y,  (int)x2,  (int)y2, Color.BLACK, "",  1));
            	}else if(primitivo.getNodeName().equals("Poligono")) {
            		Poligono figuraPoligono =  new Poligono();
            		
            		NodeList poligono = primitivo.getChildNodes();
            		int tam = poligono.getLength();
            		for(int cont =0 ; cont <tam;cont ++) {
            			Element ponto = (Element) poligono.item(cont );
            			NodeList coordenadas = ponto.getChildNodes();
                		Node xstring = coordenadas.item(0);
                		Node ystring = coordenadas.item(1);
                		
                		double x = Double.parseDouble(xstring.getTextContent())*500;
                		double y = Double.parseDouble(ystring.getTextContent())*500;
                		figuraPoligono.addPonto(new PontoGr((int)x, (int)y));
            		}
            		figuraPoligono.setCor(Color.BLACK);
            		figuraPoligono.setTamanho(1);
            		lista.inserir(figuraPoligono);
            		System.out.println("Poligono");
            	}
            }
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(LeitorXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(LeitorXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeitorXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

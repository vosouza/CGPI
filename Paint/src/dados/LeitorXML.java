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

/**
 *
 * @author bruno.chagas
 */
public class LeitorXML {

    /**
     * @param args the command line arguments
     */
    public void passar() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            Document doc = builder.parse("C:\\Users\\cpudv\\Desktop\\Trabalhos\\Paint\\src\\interfacesFXML\\exemplo.xml");
            
            NodeList listaPrimitivos = doc.getElementsByTagName("Figura");
            
            int tamanhoLista = listaPrimitivos.getLength();
            for (int i = 0; i < tamanhoLista; i++) {//Figura

                Node primitivo = listaPrimitivos.item(i);

                if(primitivo.getNodeType() == Node.ELEMENT_NODE){

                    Element elementoPrimitivo = (Element) primitivo;
                    NodeList listaNosPrimitivos = elementoPrimitivo.getChildNodes();
                    int tamanhoListaPrimitivos = listaNosPrimitivos.getLength();

                    for (int j = 0; j < tamanhoListaPrimitivos; j++) {//Reta Circulo Quadrado

                        Node noPrimitivo = listaNosPrimitivos.item(j);

                        if(noPrimitivo.getNodeType() == Node.ELEMENT_NODE){

                            Element primitivoFilho = (Element) noPrimitivo;
                            NodeList propriedadesPrimitivos = primitivoFilho.getChildNodes();
                            int prop =  propriedadesPrimitivos.getLength();
                            
                            if(primitivoFilho.getTagName().equals("Poligono")) {
                            	 System.out.println("Poligono");
                            }else {
                            	for (int x = 0; x < prop; x++) {
                            		 System.out.println("Raiz");
                            		 
                            		 Node parametros = propriedadesPrimitivos.item(x);
	                               	 if(primitivoFilho.getTagName().equals("Reta")) {
	                               		 Element priProp = (Element) parametros;
	                               		 NodeList paramPri = priProp.getChildNodes();
	                               		 Node p1 = paramPri.item(0);
	                               		 System.out.println("p1 : " + p1.getTextContent());
	                               		 Node p2 = paramPri.item(1);
	                               		System.out.println("p2 : " + p2.getTextContent());
	                               	 }else if(primitivoFilho.getTagName().equals("Circulo")) {
	                               		 
	                               	 }
                            		
                            	} 
                            }

                            switch(primitivoFilho.getTagName()){
                                case "Reta":

                                    System.out.println("Reta=" + primitivoFilho.getTextContent());
                                    break;
                                    
                                case "Circulo":

                                    System.out.println("Circulo=" + primitivoFilho.getTextContent());
                                    break;
                                    
                                case "Retangulo":
                                		System.out.println("Retangulo" + primitivoFilho.getTextContent());
                                    break;
                            }
                        }
                    }
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

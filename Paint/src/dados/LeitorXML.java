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

import javafx.scene.paint.Color;
import primitivos.PontoGr;

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
     * @param args the command line arguments
     */
    public void passar() {
        try {
        	boolean completo = false;
        	Color cor ;
        	PontoGr p1 ;
        	PontoGr p2 ;
        	Double Raio;
        	
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            Document doc = builder.parse("C:\\Users\\306526\\Desktop\\CGPI\\Paint\\src\\interfacesFXML\\exemplo.xml");
            
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
                            		 System.out.println(primitivoFilho.getTagName());//reta
                            		 
                            		 Node parametros = propriedadesPrimitivos.item(x);
	                               	 if(primitivoFilho.getTagName().equals("Reta")) {
	                               		 
	                               		 //entra na tag de reta
	                               		 Element priProp = (Element) parametros; 	System.out.println(priProp.getTagName());//Ponto
	                               		 NodeList paramPri = priProp.getChildNodes();
	                               		 
	                               		 if( priProp.getTagName().equals("Ponto")){
		                               		 Node x1 = paramPri.item(0);
		                               		 Element x1Conteudo = (Element ) x1;
		                               		 System.out.print("X : " + x1Conteudo.getTextContent());//X
		                               		 
		                               		 
		                               		 Node y1 = paramPri.item(1);
		                               		 Element y1Conteudo = (Element ) y1;
		                               		 System.out.print(" Y : " + y1Conteudo.getTextContent());//X
		                               		 if(completo ==false){
		                               			 Double xx = Double.parseDouble(x1Conteudo.getTextContent()) * 500;
		                               			 Double yy = Double.parseDouble(y1Conteudo.getTextContent()) * 500;
		                               			 p1 = new PontoGr((int)Math.round(xx),(int)Math.round(yy));
		                               		 }
	                               		 }else if(priProp.getTagName().equals("Cor")){
	                               			 Node R = paramPri.item(0);
		                               		 Element rValor = (Element ) R;
		                               		 System.out.print("R : " + rValor.getTextContent());//X
		                               		 
		                               		 
		                               		 Node G = paramPri.item(1);
		                               		 Element gValor = (Element ) G;
		                               		 System.out.print(" G : " + gValor.getTextContent());//X
		                               		 
		                               		Node B = paramPri.item(1);
		                               		Element bValor = (Element ) B;
		                               		System.out.println(" B : " + bValor.getTextContent());//X
		                               		if(completo ==false){
		                               			 cor = new Color(Double.parseDouble(rValor.getTextContent()),
		                               					 		 Double.parseDouble(gValor.getTextContent()),
		                               					 		 Double.parseDouble(bValor.getTextContent()),
		                               					 		 0);
		                               		 }
	                               		 }
	                               		 
	                               	 }else if(primitivoFilho.getTagName().equals("Circulo")) {
	                               		 Element priProp = (Element) parametros; 	System.out.println(priProp.getTagName());//Ponto
	                               		 NodeList paramPri = priProp.getChildNodes();
	                               		 
	                               		if( priProp.getTagName().equals("Ponto")){
		                               		 Node x1 = paramPri.item(0);
		                               		 Element x1Conteudo = (Element ) x1;
		                               		 System.out.print("X : " + x1Conteudo.getTextContent());//X
		                               		 
		                               		 
		                               		 Node y1 = paramPri.item(1);
		                               		 Element y1Conteudo = (Element ) y1;
		                               		 System.out.println(" Y : " + y1Conteudo.getTextContent());//X
		                               		 
	                               		 }else if(priProp.getTagName().equals("Raio")){
	                               			 
	                               			System.out.print(" X " + priProp.getTextContent());
	                               			
	                               		 }else if(priProp.getTagName().equals("Cor")){
	                               			 Node R = paramPri.item(0);
		                               		 Element rValor = (Element ) R;
		                               		 System.out.print("R : " + rValor.getTextContent());//X
		                               		 
		                               		 
		                               		 Node G = paramPri.item(1);
		                               		 Element gValor = (Element ) G;
		                               		 System.out.print(" G : " + gValor.getTextContent());//X
		                               		 
		                               		 Node B = paramPri.item(1);
		                               		 Element bValor = (Element ) B;
		                               		 System.out.println(" B : " + bValor.getTextContent());//X
	                               		 }
	                               	 }
	                               //	 if()
                            		
                            	} 
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

<<<<<<< HEAD
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
    	System.out.println("NOME=");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            Document doc = builder.parse("C:/Users/306526/Desktop/Paint/src/interfacesFXML/exemplo.xml");
            
            NodeList listaDePessoas = doc.getElementsByTagName("Figura");
            
            int tamanhoLista = listaDePessoas.getLength();
            
            for (int i = 0; i < tamanhoLista; i++) {
                
                //pego cada item (pessoa) como um nó (node)
                Node noPessoa = listaDePessoas.item(i);
                
                //verifica se o noPessoa é do tipo element (e não do tipo texto etc)
                if(noPessoa.getNodeType() == Node.ELEMENT_NODE){
                    
                    //caso seja um element, converto o no Pessoa em Element pessoa
                    Element elementoPessoa = (Element) noPessoa;
                    
                    //já posso pegar o atributo do element
                    String id = elementoPessoa.getAttribute("id");
                    
                    //imprimindo o id
                    System.out.println("ID = " + id);      
                    
                    //recupero os nos filhos do elemento pessoa (nome, idade e peso)
                    NodeList listaDeFilhosDaPessoa = elementoPessoa.getChildNodes();
                    
                    //pego o tamanho da lista de filhos do elemento pessoa
                    int tamanhoListaFilhos = listaDeFilhosDaPessoa.getLength();
                            
                    //varredura na lista de filhos do elemento pessoa
                    for (int j = 0; j < tamanhoListaFilhos; j++) {
                        
                        //crio um no com o cada tag filho dentro do no pessoa (tag nome, idade e peso)
                        Node noFilho = listaDeFilhosDaPessoa.item(j);
                        
                        //verifico se são tipo element
                        if(noFilho.getNodeType() == Node.ELEMENT_NODE){
                            
                            //converto o no filho em element filho
                            Element elementoFilho = (Element) noFilho;
                            
                            //verifico em qual filho estamos pela tag
                            switch(elementoFilho.getTagName()){
                                case "nome":
                                    //imprimo o nome
                                    System.out.println("NOME=" + elementoFilho.getTextContent());
                                    break;
                                    
                                case "idade":
                                    //imprimo a idade
                                    System.out.println("IDADE=" + elementoFilho.getTextContent());
                                    break;
                                    
                                case "peso":
                                    //imprimo o peso
                                    System.out.println("PESO=" + elementoFilho.getTextContent());
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
    
=======
package dados;

import java.io.IOException;

import javax.naming.directory.Attributes;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//Utilizado a api SAX
public class LeitorXML extends DefaultHandler {

	private ListaPrimitivos historico;
	private String tagAtual;
    private String siglaAtual;
    
	public LeitorXML(ListaPrimitivos historico) {
		super();
		this.historico = historico;
	}
	
	public void fazerParsing(String pathArq) {
		 
        // Passo 1: cria instância da classe SAXParser, através da factory
        // SAXParserFactory
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser;
 
        try {
            saxParser = factory.newSAXParser();
 
            // Passo 2: comanda o início do parsing
            saxParser.parse(pathArq, this); // o "this" indica que a própria
                                // classe "DevmediaSAX" atuará como
                                // gerenciadora de eventos SAX.
 
            // Passo 3: tratamento de exceções.
        } catch (ParserConfigurationException | SAXException | IOException e) {
            StringBuffer msg = new StringBuffer();
            msg.append("Erro:\n");
            msg.append(e.getMessage() + "\n");
            msg.append(e.toString());
            System.out.println(msg);
        }
    }
	
	public void startDocument() {
        System.out.println("\nIniciando o Parsing...\n");
    }
	
	public void endDocument() {
        System.out.println("\nFim do Parsing...");
    }
	
	public void startElement(String uri, String localName, String qName,Attributes atts) {
 
        // recupera o nome da tag atual
        tagAtual = qName;
 
        // se a tag for "<pais>", recupera o valor do atributo "sigla"
        if (qName.compareTo("Reta") == 0) {
            siglaAtual = atts.toString();
        }
    }
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
 
        tagAtual = "";
    }
 
    /**
     * evento characters do SAX. É onde podemos recuperar as informações texto
     * contidas no documento XML (textos contidos entre tags). Neste exemplo,
     * recuperamos os nomes dos países, a população e a moeda
     * 
     */
    public void characters(char[] ch, int start, int length)throws SAXException {
 
        String texto = new String(ch, start, length);
 
        // ------------------------------------------------------------
        // --- TRATAMENTO DAS INFORMAÇÕES DE ACORDO COM A TAG ATUAL ---
        // ------------------------------------------------------------
 
        if (tagAtual.compareTo("Reta") == 0) {
 
            System.out.print(texto + " - SIGLA: " + siglaAtual);
        }
 
        if (tagAtual.compareTo("moeda") == 0) {
 
            System.out.print(" - MOEDA: " + texto);
        }
 
        if (tagAtual.compareTo("populacao") == 0) {
 
            System.out.println(" - POPULACAO: " + texto);
        }
    }
>>>>>>> master
}

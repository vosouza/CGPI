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
}

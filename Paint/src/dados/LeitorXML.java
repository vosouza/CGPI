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
                
                //pego cada item (pessoa) como um n� (node)
                Node noPessoa = listaDePessoas.item(i);
                
                //verifica se o noPessoa � do tipo element (e n�o do tipo texto etc)
                if(noPessoa.getNodeType() == Node.ELEMENT_NODE){
                    
                    //caso seja um element, converto o no Pessoa em Element pessoa
                    Element elementoPessoa = (Element) noPessoa;
                    
                    //j� posso pegar o atributo do element
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
                        
                        //verifico se s�o tipo element
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
    
}

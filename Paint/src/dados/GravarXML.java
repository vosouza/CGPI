package dados;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import figuras.Fractal;
import figuras.Mandala;
import figuras.Poligono;
import primitivos.CirculoGr;
import primitivos.QuadradoGr;
import primitivos.RetaGr;

public class GravarXML {
	
	private ListaPrimitivos lista;

	public GravarXML(ListaPrimitivos lista){
		this.lista = lista;
	}
	
	public void Gravar() {
        try {
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document documentoXML = builder.newDocument();
			
			Element figura = documentoXML.createElement("Figura");
			documentoXML.appendChild(figura);

			Object obj;
			if(lista.vazia() == false) {
				for(int i = 0 ; i < lista.getQtd() ; i++) {
					obj = lista.buscar(i);
					if(obj instanceof RetaGr) {
						System.out.print("xxx");
						Element reta = documentoXML.createElement("Reta");
						Element p1 = documentoXML.createElement("Ponto");
						
						Element x = documentoXML.createElement("x");
						double numx = (((RetaGr)obj).getP1().getX())/500;
						x.appendChild(documentoXML.createTextNode(Double.toString(numx)));
						
						Element y = documentoXML.createElement("y");
						double numy = (((RetaGr)obj).getP1().getX())/500;
						y.appendChild(documentoXML.createTextNode(Double.toString(numy)));
						
						p1.appendChild(x);
						p1.appendChild(y);
						reta.appendChild(p1);
						
						Element p2 = documentoXML.createElement("Ponto");
						
						Element x1 = documentoXML.createElement("x");
						double numx1 = (((RetaGr)obj).getP2().getX())/500;
						x1.appendChild(documentoXML.createTextNode(Double.toString(numx1)));
						
						Element y1 = documentoXML.createElement("y");
						double numy1 = (((RetaGr)obj).getP2().getX())/500;
						y1.appendChild(documentoXML.createTextNode(Double.toString(numy1)));
						
						p2.appendChild(x1);
						p2.appendChild(y1);
						reta.appendChild(p2);
						
						figura.appendChild(reta);
					}else if(obj instanceof CirculoGr) {
						Element circulo = documentoXML.createElement("Circulo");
						Element p = documentoXML.createElement("Ponto");
						
						Element x = documentoXML.createElement("x");
						Double xnum = ((CirculoGr) obj).getCentro().getX()/500;
						x.appendChild(documentoXML.createTextNode(Double.toString(xnum)));
						
						Element y = documentoXML.createElement("y");
						Double ynum = ((CirculoGr) obj).getCentro().getX()/500;
						y.appendChild(documentoXML.createTextNode(Double.toString(ynum)));
						
						Element raio = documentoXML.createElement("Raio");
						Double r = ((CirculoGr) obj).getRaio()/500;
						raio.appendChild(documentoXML.createTextNode(Double.toString(r)));
						
						p.appendChild(x);
						p.appendChild(y);
						circulo.appendChild(p);
						circulo.appendChild(raio);
						figura.appendChild(circulo);
						
					}else if(obj instanceof Fractal) {
						
					}else if(obj instanceof Mandala) {
						
					}else if(obj instanceof QuadradoGr) {
					
					}if(obj instanceof Poligono) {
						
					}
				}
			}
			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();
			DOMSource fonteDOC = new DOMSource(documentoXML);
			StreamResult docfinal = new StreamResult(new File("C:\\Users\\cpudv\\Desktop\\Trabalhos\\CGPI\\Paint\\src\\interfacesFXML\\pessoa.xml"));
			trans.transform(fonteDOC, docfinal);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

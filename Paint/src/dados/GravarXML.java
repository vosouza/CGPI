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
import javafx.scene.paint.Color;
import primitivos.CirculoGr;
import primitivos.PontoGr;
import primitivos.QuadradoGr;
import primitivos.RetaGr;

public class GravarXML {
	
	private ListaPrimitivos lista;

	public GravarXML(ListaPrimitivos lista){
		this.lista = lista;
	}
	
	public void Gravar(String fileAsString) {
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
						Element reta = documentoXML.createElement("Reta");
						Element p1 = documentoXML.createElement("Ponto");
						
						Element x = documentoXML.createElement("x");
						double numx = (((RetaGr)obj).getP1().getX())/500;
						//System.out.println(numx);
						x.appendChild(documentoXML.createTextNode(Double.toString(numx)));
						
						Element y = documentoXML.createElement("y");
						double numy = (((RetaGr)obj).getP1().getY())/500;
						y.appendChild(documentoXML.createTextNode(Double.toString(numy)));
						//System.out.println(numy);
						p1.appendChild(x);
						p1.appendChild(y);
						reta.appendChild(p1);
						
						Element p2 = documentoXML.createElement("Ponto");
						
						Element x1 = documentoXML.createElement("x");
						double numx1 = (((RetaGr)obj).getP2().getX())/500;
						x1.appendChild(documentoXML.createTextNode(Double.toString(numx1)));
						//System.out.println(numx1);
						Element y1 = documentoXML.createElement("y");
						double numy1 = (((RetaGr)obj).getP2().getY())/500;
						y1.appendChild(documentoXML.createTextNode(Double.toString(numy1)));
						//System.out.println(numy1);
						p2.appendChild(x1);
						p2.appendChild(y1);
						reta.appendChild(p2);
						
						Element cor = documentoXML.createElement("Cor");
						Color info =  ((RetaGr) obj).getCor();
						Element r1 = documentoXML.createElement("R");
						r1.appendChild(documentoXML.createTextNode(Integer.toString((int)(info.getRed()*255))));
						Element g = documentoXML.createElement("G");
						g.appendChild(documentoXML.createTextNode(Integer.toString((int)(info.getGreen()*255))));
						Element b = documentoXML.createElement("B");
						b.appendChild(documentoXML.createTextNode(Integer.toString((int)(info.getBlue()*255))));
						cor.appendChild(r1);
						cor.appendChild(g);
						cor.appendChild(b);
						reta.appendChild(cor);
						
						figura.appendChild(reta);
					}else if(obj instanceof CirculoGr) {
						Element circulo = documentoXML.createElement("Circulo");
						Element p = documentoXML.createElement("Ponto");
						
						Element x = documentoXML.createElement("x");
						Double xnum = ((CirculoGr) obj).getCentro().getX()/500;
						x.appendChild(documentoXML.createTextNode(Double.toString(xnum)));
						
						Element y = documentoXML.createElement("y");
						Double ynum = ((CirculoGr) obj).getCentro().getY()/500;
						y.appendChild(documentoXML.createTextNode(Double.toString(ynum)));
						
						Element raio = documentoXML.createElement("Raio");
						Double r = ((CirculoGr) obj).getRaio()/500;
						raio.appendChild(documentoXML.createTextNode(Double.toString(r)));
						
						p.appendChild(x);
						p.appendChild(y);
						circulo.appendChild(p);
						circulo.appendChild(raio);
						
						Element cor = documentoXML.createElement("Cor");
						Color info =  ((CirculoGr) obj).getCor();
						Element r1 = documentoXML.createElement("R");
						r1.appendChild(documentoXML.createTextNode(Integer.toString((int)(info.getRed()*255))));
						Element g = documentoXML.createElement("G");
						g.appendChild(documentoXML.createTextNode(Integer.toString((int)(info.getGreen()*255))));
						Element b = documentoXML.createElement("B");
						b.appendChild(documentoXML.createTextNode(Integer.toString((int)(info.getBlue()*255))));
						cor.appendChild(r1);
						cor.appendChild(g);
						cor.appendChild(b);
						circulo.appendChild(cor);
						
						figura.appendChild(circulo);
						
					}else if(obj instanceof Fractal) {
						
					}else if(obj instanceof Mandala) {
						
					}else if(obj instanceof QuadradoGr) {
						System.out.print("xxx");
						Element retangulo = documentoXML.createElement("Retangulo");
						Element p1 = documentoXML.createElement("Ponto");
						
						Element x = documentoXML.createElement("x");
						double numx = (((QuadradoGr)obj).getP1().getX())/500;
						x.appendChild(documentoXML.createTextNode(Double.toString(numx)));
						
						Element y = documentoXML.createElement("y");
						double numy = (((QuadradoGr)obj).getP1().getY())/500;
						y.appendChild(documentoXML.createTextNode(Double.toString(numy)));
						
						p1.appendChild(x);
						p1.appendChild(y);
						retangulo.appendChild(p1);
						
						Element p2 = documentoXML.createElement("Ponto");
						
						Element x1 = documentoXML.createElement("x");
						double numx1 = (((QuadradoGr)obj).getP2().getX())/500;
						x1.appendChild(documentoXML.createTextNode(Double.toString(numx1)));
						
						Element y1 = documentoXML.createElement("y");
						double numy1 = (((QuadradoGr)obj).getP2().getX())/500;
						y1.appendChild(documentoXML.createTextNode(Double.toString(numy1)));
						
						p2.appendChild(x1);
						p2.appendChild(y1);
						retangulo.appendChild(p2);
						
						Element cor = documentoXML.createElement("Cor");
						Color info =  ((QuadradoGr) obj).getCor();
						Element r = documentoXML.createElement("R");
						r.appendChild(documentoXML.createTextNode(Integer.toString((int)(info.getRed()*255))));
						Element g = documentoXML.createElement("G");
						g.appendChild(documentoXML.createTextNode(Integer.toString((int)(info.getGreen()*255))));
						Element b = documentoXML.createElement("B");
						b.appendChild(documentoXML.createTextNode(Integer.toString((int)(info.getBlue()*255))));
						cor.appendChild(r);
						cor.appendChild(g);
						cor.appendChild(b);
						retangulo.appendChild(cor);
						
						figura.appendChild(retangulo);
					}if(obj instanceof Poligono) {
						Element poligono = documentoXML.createElement("Poligono");
						
						int tamanho = ((Poligono) obj).getQTD();
						System.out.println(tamanho);
						for(int j=0;j<tamanho;j++) {
							Element p1 = documentoXML.createElement("Ponto");
							Element x = documentoXML.createElement("x");
							
							PontoGr pol1 = ((Poligono) obj).getPonto(j);
							double numx = (pol1.getX())/500;
							x.appendChild(documentoXML.createTextNode(Double.toString(numx)));
							
							Element y = documentoXML.createElement("y");
							double numy = (pol1.getY())/500;
							y.appendChild(documentoXML.createTextNode(Double.toString(numy)));
							
							p1.appendChild(x);
							p1.appendChild(y);
							poligono.appendChild(p1);
						}
						
						Element cor = documentoXML.createElement("Cor");
						Color info =  ((Poligono) obj).getCor();
						Element r = documentoXML.createElement("R");
						r.appendChild(documentoXML.createTextNode(Integer.toString((int)(info.getRed()*255))));
						Element g = documentoXML.createElement("G");
						g.appendChild(documentoXML.createTextNode(Integer.toString((int)(info.getGreen()*255))));
						Element b = documentoXML.createElement("B");
						b.appendChild(documentoXML.createTextNode(Integer.toString((int)(info.getBlue()*255))));
						cor.appendChild(r);
						cor.appendChild(g);
						cor.appendChild(b);
						poligono.appendChild(cor)
;						figura.appendChild(poligono);
					}
				}
			}
			
			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();
			DOMSource fonteDOC = new DOMSource(documentoXML);
			StreamResult docfinal = new StreamResult(new File(fileAsString));
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

package figuras;

import dados.ListaPrimitivos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import primitivos.AlgoritmosRetas;
import primitivos.PontoGr;
import primitivos.RetaGr;

public class Poligono {
	private ListaPrimitivos pontos; // pilha que armazena todos os pontos do poligono
	private Color cor; // cor de todas as linhas
	private int tamanho;
	
	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public Poligono() {
		pontos = new ListaPrimitivos();
	}
	
	public void setCor(Color c) {
		cor = c;
	}
	
	public Color getCor(Color c) {
		return cor;
	}
	
	public void addPonto(PontoGr ponto) {
		pontos.inserir(ponto);
	}
	
	public void removerUltimo() {
		pontos.remover(pontos.getQtd()-1);
	}
	
	public PontoGr getLastPonto() {
		return (PontoGr) pontos.buscar(pontos.getQtd()-1);
	}
	public void desenharPoligono(Canvas canvas, GraphicsContext gc,double mult) {
		PontoGr p1 = null, p2 = null;		
		for(int cont = 0; cont< pontos.getQtd() ; cont++) {
			if(cont%2 == 0) {
				p1 = (PontoGr) pontos.buscar(cont);
			}else {
				p2 = (PontoGr) pontos.buscar(cont);
			}
			if(p1 != null && p2 != null) {
				RetaGr.desenhar(gc,(int)p1.getX(),(int)p1.getY(),(int)p2.getX(),(int)p2.getY(),"", cor,  tamanho, AlgoritmosRetas.STROKELINE);
			}
		}
	}
	
	public void fecharFigura(GraphicsContext gc,double mult) {
		if(pontos.getQtd()-1 >= 2) {
			PontoGr p1 = (PontoGr) pontos.buscar(0);
			PontoGr p2 = (PontoGr) pontos.buscar(pontos.getQtd()-1);
			RetaGr.desenhar(gc,(int)p1.getX(),(int)p1.getY(),(int)p2.getX(),(int)p2.getY(),"", cor,  tamanho, AlgoritmosRetas.STROKELINE);
		}
	}
}

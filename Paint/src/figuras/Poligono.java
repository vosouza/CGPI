package figuras;

import dados.PilhaPrimitivos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import primitivos.AlgoritmosRetas;
import primitivos.PontoGr;
import primitivos.RetaGr;

public class Poligono {
	private PilhaPrimitivos pontos; // pilha que armazena todos os pontos do poligono
	private Color cor; // cor de todas as linhas
	private int tamanho;
	
	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public Poligono() {
		pontos = new PilhaPrimitivos();
	}
	
	public void setCor(Color c) {
		cor = c;
	}
	
	public Color getCor(Color c) {
		return cor;
	}
	
	public void addPonto(PontoGr ponto) {
		pontos.push(ponto);
	}
	
	public void removerUltimo() {
		pontos.pop();
	}
	
	public void desenharPoligono(Canvas canvas, GraphicsContext gc,double mult) {
		int contador = 1;
		PontoGr p1 = null, p2 = null;
		PilhaPrimitivos aux = new PilhaPrimitivos();
		
		while(pontos.isEmpty() ==  false) {
			aux.push(pontos.top());
			pontos.pop();
		}
		while(aux.isEmpty() ==  false) {
			if(contador%2 == 0) {
				p2 = (PontoGr) aux.top();
				RetaGr.desenhar(gc,p1.getX(),p1.getY(),p2.getX(),p2.getY(),"", cor,  tamanho, AlgoritmosRetas.STROKELINE);
			}else {
				p1 = (PontoGr) aux.top();
			}
			aux.pop();
		}
	}
}

package application;

import javax.swing.JOptionPane;

import dados.ListaPrimitivos;
import figuras.Fractal;
import figuras.Mandala;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import primitivos.AlgoritmosCirculos;
import primitivos.AlgoritmosRetas;
import primitivos.CirculoGr;
import primitivos.PontoGr;
import primitivos.RetaGr;

public class CanvasAction {
	
	private boolean primeiraVez;//variavel para marcar os cliques na tela
	
	private int x1, y1;
	
	private Color cor;
	private int tamanho;
	
	private Canvas canvas;
	private GraphicsContext gc;
	
	//Parametros necessarios para desenhar no canvas
	public CanvasAction(Canvas cv) {
		this.canvas = cv;
		this.gc = cv.getGraphicsContext2D();
		this.primeiraVez = true;
		cor = Color.BLACK;
	}
	
	public void cancelarClick() {
		primeiraVez = true;
	}
	
	public Color getCor() {
		return cor;
	}


	public void setCor(Color cor) {
		this.cor = cor;
	}


	public int getTamanho() {
		return tamanho;
	}


	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	//Guarda (x,y) do primeiro ponto e de acordo com o modo passado
	//faz o respectivo desenho de acordo com o botão selecionado na janela principal
	public void action(int mode, int x, int y, ListaPrimitivos lista) {
		
		switch(mode){
			case 1:
				if(primeiraVez) {
					x1 = x;
					y1 = y;
					primeiraVez = false;
				}else {
					RetaGr.desenhar(gc, x1, y1, x, y, "", cor,  tamanho, AlgoritmosRetas.STROKELINE);
					lista.inserir(new RetaGr( x1, y1, x, y, cor, "",  tamanho));
					primeiraVez = true;
				}
			break;
			case 2:
				if(primeiraVez) {
					x1 = x;
					y1 = y;
					primeiraVez = false;
				}else {
					double raio = new PontoGr (x1, y1).distance(x, y);
					CirculoGr.desenhar(gc, x1, y1, raio, cor, "", tamanho, AlgoritmosCirculos.STROKELINE);
					lista.inserir(new CirculoGr(x1, y1, raio, cor, "", tamanho));
					primeiraVez = true;
				}
			break;
			case 3:
				try {
					int qtd = Integer.parseInt(JOptionPane.showInputDialog("Digite q quantidade de triangulos"));
					new Fractal(canvas, gc).trianguloRecursivo(qtd);
					lista.inserir(new Fractal(canvas, gc, qtd));
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Digite um valor valido ");
				}
			break;
			case 4:
				new Mandala(canvas, gc,1);
			break;
		}
		
	}
	
	//desenha na convas sem guardar na lista
	public void desenhoTemporario(int mode,int x, int y,ListaPrimitivos lista){
		if(primeiraVez == false) {
			limparTela();
			if(mode == 1) {
				RetaGr.desenhar(gc, x1, y1, x, y, "", cor,  tamanho, AlgoritmosRetas.STROKELINE);
			}else if(mode == 2) {
				double raio = new PontoGr (x1, y1).distance(x, y);
				CirculoGr.desenhar(gc, x1, y1, raio, cor, "", tamanho, AlgoritmosCirculos.STROKELINE);
			}
			loadHistorico(lista);
		}
	}
	
	public void limparTela() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());		
	}
	
	//Desenha os primitivos guardados na lista
	public void loadHistorico(ListaPrimitivos h) {
		Object obj;
		for(int i = h.getQtd() - 1 ; i >= 0; i--) {
			obj = h.buscar(i);
			if(obj instanceof RetaGr) {
				((RetaGr) obj).desenharReta(gc, AlgoritmosRetas.STROKELINE);
			}else if(obj instanceof CirculoGr) {
				((CirculoGr) obj).desenharCirculo(gc, AlgoritmosCirculos.STROKELINE);
			}else if(obj instanceof Fractal) {
				
			}else if(obj instanceof Mandala) {
				new Mandala(canvas, gc,1);
			}
		}
	}
	
	//Desenha os primitivos guardados na lista multiplicando as coordenadas por um numero
	public void loadHistorico(ListaPrimitivos h, Double mult) {
		Object obj;
		for(int i = h.getQtd() - 1 ; i >= 0; i--) {
			obj = h.buscar(i);
			if(obj instanceof RetaGr) {
				Double x =((RetaGr) obj).getP1().getX()*mult,
					y = ((RetaGr) obj).getP1().getY()*mult,
					x2 = ((RetaGr) obj).getP2().getX()*mult,
					y2 =((RetaGr) obj).getP2().getY()*mult;
				RetaGr.desenhar(gc,x,y,x2,y2,"", ((RetaGr) obj).getCor(),  ((RetaGr) obj).getEsp(), AlgoritmosRetas.STROKELINE);
			}else if(obj instanceof CirculoGr) {
				Double x = ((CirculoGr) obj).getCentro().getX() * mult,
				y = ((CirculoGr) obj).getCentro().getY() * mult;
				CirculoGr.desenhar(gc, x, y, ((CirculoGr) obj).getRaio()*mult, ((CirculoGr) obj).getCor(), "", ((CirculoGr) obj).getEsp(), AlgoritmosCirculos.STROKELINE);
			}else if(obj instanceof Fractal) {
				
			}else if(obj instanceof Mandala) {
				new Mandala(canvas, gc,0.2);
			}
		}
	}
}

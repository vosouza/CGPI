package application;

import javax.swing.JOptionPane;

import dados.ListaPrimitivos;
import figuras.Fractal;
import figuras.Mandala;
import figuras.Poligono;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import primitivos.AlgoritmosCirculos;
import primitivos.AlgoritmosRetas;
import primitivos.CirculoGr;
import primitivos.PontoGr;
import primitivos.QuadradoGr;
import primitivos.RetaGr;

public class CanvasAction {
	
	private boolean primeiraVez;//variavel para marcar os cliques na tela
	
	private int x1, y1;
	
	private boolean doisCliques = false;
	
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
			case 1: // trata do clique da reta
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
			
			case 2: // trata do clique do circulo
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
			
			case 3://botao quadrado
				if(primeiraVez) {
					x1 = x;
					y1 = y;
					primeiraVez = false;
				}else {
					new QuadradoGr(x1,y1,x,y,cor,tamanho).desenharQuadrado(x1, y1, x, y, gc);
					lista.inserir(new QuadradoGr(x1,y1,x,y,cor,tamanho));
					primeiraVez = true;
				}
			break;
			
			case 4://Poligono
				Poligono figura = null;
				if(doisCliques == false) {
					lista.inserir(new Poligono());
					figura = (Poligono)lista.buscar(lista.getQtd()-1);
					figura.setCor(cor);
					figura.setTamanho(tamanho);
					figura.fecharFigura(gc, 1);
					doubleClique();
				}else {
					figura = (Poligono)lista.buscar(lista.getQtd()-1);
				}
				
				if(primeiraVez) {
					x1 = x;
					y1 = y;
					figura.addPonto(new PontoGr(x1,y1));
					primeiraVez = false;
				}else {
					figura.addPonto(new PontoGr(x,y));
					figura.desenharPoligono(canvas, gc, 1);
				}
			break;
			
			case 5: // trata do clique do fractal
				try {
					int qtd = Integer.parseInt(JOptionPane.showInputDialog("Digite q quantidade de triangulos"));
					new Fractal(canvas, gc).trianguloRecursivo(qtd);
					lista.inserir(new Fractal(canvas, gc, qtd));
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Digite um valor valido ");
				}
			break;
			
			case 6: // trata do clique da mandala
				new Mandala(canvas, gc,1);
				lista.inserir(new Mandala(canvas, gc,1));
				
			break;
		}
		
	}
	
	//desenha no canvas sem guardar na lista
	public void desenhoTemporario(int mode,int x, int y,ListaPrimitivos lista){
		if(primeiraVez == false) {
			limparTela();
			loadHistorico(lista);
			if(mode == 1) {
				RetaGr.desenhar(gc, x1, y1, x, y, "", cor,  tamanho, AlgoritmosRetas.STROKELINE);
			}else if(mode == 2) {
				double raio = new PontoGr (x1, y1).distance(x, y);
				CirculoGr.desenhar(gc, x1, y1, raio, cor, "", tamanho, AlgoritmosCirculos.STROKELINE);
			}else if(mode == 3) {
				new QuadradoGr(x1,y1,x,y,cor,tamanho).desenharQuadrado(x1, y1, x, y, gc);
			}else if(mode == 4) {
				Poligono poli = (Poligono) lista.buscar(lista.getQtd()-1);
				PontoGr p = poli.getLastPonto();
				RetaGr.desenhar(gc,(int) p.getX(),(int) p.getY(), x, y, "", cor,  tamanho, AlgoritmosRetas.STROKELINE);
			}	
		}
	}
	
	public void limparTela() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());		
	}
	
	//Desenha os primitivos guardados na lista
	public void loadHistorico(ListaPrimitivos h) {
		Object obj;
		if(h.vazia() == false) {
			for(int i = 0 ; i < h.getQtd() ; i++) {
				obj = h.buscar(i);
				if(obj instanceof RetaGr) {
					((RetaGr) obj).desenharReta(gc, AlgoritmosRetas.STROKELINE);
				}else if(obj instanceof CirculoGr) {
					((CirculoGr) obj).desenharCirculo(gc, AlgoritmosCirculos.STROKELINE);
				}else if(obj instanceof Fractal) {
					
				}else if(obj instanceof Mandala) {
					new Mandala(canvas, gc,1);
				}else if(obj instanceof QuadradoGr) {
					Double x =((QuadradoGr) obj).getP1().getX(),
							y = ((QuadradoGr) obj).getP1().getY(),
							x2 = ((QuadradoGr) obj).getP2().getX(),
							y2 =((QuadradoGr) obj).getP2().getY();
					QuadradoGr.desenharQuadrado(x.intValue(),y.intValue(),x2.intValue(), y2.intValue(), ((QuadradoGr) obj).getCor(), ((QuadradoGr) obj).getEsp(), gc);
				}if(obj instanceof Poligono) {
					((Poligono) obj).desenharPoligono(canvas, gc, 1);
				}
			}
		}
	}
	
	//Desenha os primitivos guardados na lista multiplicando as coordenadas por um numero
	public void loadHistorico(ListaPrimitivos h, Double mult) {
		Object obj;
		if(h.vazia() == false) {
			for(int i = 0 ; i < h.getQtd() ; i++) {
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
				}else if(obj instanceof QuadradoGr) {
					Double x =((QuadradoGr) obj).getP1().getX()*mult,
							y = ((QuadradoGr) obj).getP1().getY()*mult,
							x2 = ((QuadradoGr) obj).getP2().getX()*mult,
							y2 =((QuadradoGr) obj).getP2().getY()*mult;
					QuadradoGr.desenharQuadrado(x.intValue(),y.intValue(),x2.intValue(), y2.intValue(), ((QuadradoGr) obj).getCor(), ((QuadradoGr) obj).getEsp(), gc);
				}else if(obj instanceof Poligono) {
					((Poligono) obj).desenharPoligono(canvas, gc, mult);
				}
			}
		}
	}
	
	public void doubleClique() {
		doisCliques = doisCliques ? false : true;
	}
}

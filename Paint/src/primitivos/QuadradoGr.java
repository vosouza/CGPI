package primitivos;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class QuadradoGr extends Reta{
	Color corQuadrado;
	String nomeQuadrado;
	int espessura;

	public QuadradoGr(int x1, int y1, int x2, int y2, Color corReta, String nomeReta, int espessura){
		super (x1, y1, x2, y2);
		setCorReta(corReta);
		setNomeQuadrado(nomeReta);
		setEspessura(espessura);
	}    

public QuadradoGr(int x1, int y1, int x2, int y2, Color corReta, int espessura){
		super (x1, y1, x2, y2);
		setCorReta(corReta);
		setNomeQuadrado("");
		setEspessura(espessura);
	}   

	public QuadradoGr(int x1, int y1, int x2, int y2, int espessura){
		super (x1, y1, x2, y2);
		setCorReta(Color.BLACK);
		setNomeQuadrado("");
		setEspessura(espessura);
	}   

	public QuadradoGr(PontoGr p1, PontoGr p2, int espessura){
		super(p1, p2);
		setCorReta(Color.BLACK);
		setNomeQuadrado("");
		setEspessura(espessura);
	}    

	public QuadradoGr(PontoGr p1, PontoGr p2, Color corReta, int espessura){
		super(p1, p2);
		setCorReta(corReta);
		setNomeQuadrado("");
		setEspessura(espessura);
	}    

	public QuadradoGr(PontoGr p1, PontoGr p2, Color corReta, String nomeReta, int espessura){
		super(p1, p2);
		setCorReta(corReta);
		setNomeQuadrado(nomeReta);
		setEspessura(espessura);
	}    

	public void setCorReta(Color corReta) {
		this.corQuadrado = corReta;
	}

	public void setNomeQuadrado(String nomeReta) {
		this.nomeQuadrado = nomeReta;
	}

	public Color getCor() {
		return corQuadrado;
	}

	public String getStr() {
		return nomeQuadrado;
	}

	public int getEsp() {
		return espessura;
	}

	
	public void setEspessura(int espessura) {
		this.espessura = espessura;
	}
	
	public void desenharQuadrado(int x1, int y1, int x2, int y2, GraphicsContext gc){		 
		 
		 //A para C//
		 RetaGr.desenhar(gc, x1, y1, x2, y1, "", corQuadrado,  espessura, AlgoritmosRetas.MIDPOINT);
		 //A para D//
		 RetaGr.desenhar(gc, x1, y1, x1, y2, "", corQuadrado,  espessura, AlgoritmosRetas.MIDPOINT);
		 //B para C//
		 RetaGr.desenhar(gc, x2, y2, x2, y1, "", corQuadrado,  espessura, AlgoritmosRetas.MIDPOINT);
		 //B para D//
		 RetaGr.desenhar(gc, x2, y2, x1, y2, "", corQuadrado,  espessura, AlgoritmosRetas.MIDPOINT);		
		 
		 	
	}

	public static void desenharQuadrado(int x1, int y1, int x2, int y2,Color corQuadrado,int espessura, GraphicsContext gc){		 
		 
		 //A para C//
		 RetaGr.desenhar(gc, x1, y1, x2, y1, "", corQuadrado,  espessura, AlgoritmosRetas.MIDPOINT);
		 //A para D//
		 RetaGr.desenhar(gc, x1, y1, x1, y2, "", corQuadrado,  espessura, AlgoritmosRetas.MIDPOINT);
		 //B para C//
		 RetaGr.desenhar(gc, x2, y2, x2, y1, "", corQuadrado,  espessura, AlgoritmosRetas.MIDPOINT);
		 //B para D//
		 RetaGr.desenhar(gc, x2, y2, x1, y2, "", corQuadrado,  espessura, AlgoritmosRetas.MIDPOINT);		
		 
		 	
	}
	
}
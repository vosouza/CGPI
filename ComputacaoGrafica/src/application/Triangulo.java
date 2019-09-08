package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import primitivos.AlgoritmosRetas;
import primitivos.PontoGr;
import primitivos.RetaGr;

public class Triangulo {
	
	Canvas canvas;
	Stage palco;
	GraphicsContext gc;
	
	Triangulo(Canvas canvas, Stage palco, GraphicsContext gc){
		this.canvas = canvas;
		this.palco = palco;
		this.gc = gc;
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	public void trianguloRecursivo(int cont) {
		PontoGr p1, p2, p3;
		p1 = new PontoGr(250,0);
		p2 = new PontoGr(0,500);
		p3 = new PontoGr(500,500);
		
		desenharTriangulo((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY(), (int) p3.getX(), (int) p3.getY());
		if(cont == 1) {
			//fazer triangulo
			desenharTriangulo(pontoMedioX(p1,p2), pontoMedioY(p1,p2), pontoMedioX(p1,p3), pontoMedioY(p1,p3),pontoMedioX(p2,p3), pontoMedioY(p2,p3));
		}else if(cont > 1) {
			tri(p1,p2,p3, cont);
			
		}
		//triangulo cima
		//triangulo esq
		//triangulo dir
	}
	private void tri(PontoGr p1, PontoGr p2, PontoGr p3,int cont) {
		if(cont > 1) {
			tri(new PontoGr(pontoMedioX(p1,p2),pontoMedioY(p1,p2)), p2, new PontoGr(pontoMedioX(p2,p3),pontoMedioY(p2,p3)), cont - 1);
			tri(p1, new PontoGr(pontoMedioX(p1,p2),pontoMedioY(p1,p2)), new PontoGr(pontoMedioX(p1,p3),pontoMedioY(p1,p3)), cont - 1);
			tri(new PontoGr(pontoMedioX(p1,p3),pontoMedioY(p1,p3)), p3, new PontoGr(pontoMedioX(p2,p3),pontoMedioY(p2,p3)), cont - 1);
		}else {
			desenharTriangulo((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY(), (int) p3.getX(), (int) p3.getY());
		}
	}
	public int pontoMedioX(PontoGr p1, PontoGr p2) {
		return (int )(p1.getX() + p2.getX())/2;
	}
	public int pontoMedioY(PontoGr p1, PontoGr p2) {
		return (int )(p1.getY() + p2.getY())/2;
	}
	public void desenharTriangulo(int x1,int y1,int x2,int y2, int x3, int y3) {
		RetaGr.desenhar(gc, x1, y1, x2, y2, "", Color.RED, 1, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, x1, y1, x3, y3, "", Color.RED, 1, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, x2, y2, x3, y3, "", Color.RED, 1, AlgoritmosRetas.MIDPOINT);
	}
}

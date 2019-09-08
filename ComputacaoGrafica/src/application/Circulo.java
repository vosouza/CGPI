package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import primitivos.AlgoritmosCirculos;
import primitivos.AlgoritmosRetas;
import primitivos.CirculoGr;
import primitivos.RetaGr;

public class Circulo {
	Circulo(Canvas canvas, Stage palco, GraphicsContext gc){
		CirculoGr.desenhar(gc, 250, 250, 125, Color.GREEN, "", 2, AlgoritmosCirculos.STROKELINE);
		CirculoGr.desenhar(gc, 375, 250, 125, Color.GREEN, "", 2, AlgoritmosCirculos.STROKELINE);
		CirculoGr.desenhar(gc, 125, 250, 125, Color.GREEN, "", 2, AlgoritmosCirculos.STROKELINE);
		CirculoGr.desenhar(gc, 188, 141, 125, Color.GREEN, "", 2, AlgoritmosCirculos.STROKELINE);
		CirculoGr.desenhar(gc, 311, 141, 125, Color.GREEN, "", 2, AlgoritmosCirculos.STROKELINE);
		CirculoGr.desenhar(gc, 313, 357, 125, Color.GREEN, "", 2, AlgoritmosCirculos.STROKELINE);
		CirculoGr.desenhar(gc, 186, 357, 125, Color.GREEN, "", 2, AlgoritmosCirculos.STROKELINE);
		RetaGr.desenhar(gc, 250, 250, 250, 32, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250, 250, 436, 139, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250, 250, 438, 356, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250, 250, 250, 465, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250, 250, 61, 356, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250, 250, 63, 139, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250, 32, 436, 139, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 436, 139, 438, 356, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 438, 356, 250, 465, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250, 465, 61, 356, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 61, 356, 63, 139, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 63, 139, 250, 32, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		
		RetaGr.desenhar(gc, 438, 356, 61, 356, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 438, 356, 250, 32, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 61, 356, 250, 32, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		
		RetaGr.desenhar(gc, 436, 139, 63, 139, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 436, 139, 250, 465, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 63, 139, 250, 465, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		
		RetaGr.desenhar(gc, 250, 250, 311, 141, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250, 250, 375, 248, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250, 250, 313, 357, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250, 250, 186, 357, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250, 250, 125, 248, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250, 250, 188, 141, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
	}
}

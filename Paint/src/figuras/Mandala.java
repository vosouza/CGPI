package figuras;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import primitivos.AlgoritmosCirculos;
import primitivos.AlgoritmosRetas;
import primitivos.CirculoGr;
import primitivos.RetaGr;

public class Mandala {
	public Mandala(Canvas canvas, GraphicsContext gc,double mult){
		CirculoGr.desenhar(gc, 250*mult, 250*mult, 125*mult, Color.GREEN, "", 2, AlgoritmosCirculos.STROKELINE);
		CirculoGr.desenhar(gc, 375*mult, 250*mult, 125*mult, Color.GREEN, "", 2, AlgoritmosCirculos.STROKELINE);
		CirculoGr.desenhar(gc, 125*mult, 250*mult, 125*mult, Color.GREEN, "", 2, AlgoritmosCirculos.STROKELINE);
		CirculoGr.desenhar(gc, 188*mult, 141*mult, 125*mult, Color.GREEN, "", 2, AlgoritmosCirculos.STROKELINE);
		CirculoGr.desenhar(gc, 311*mult, 141*mult, 125*mult, Color.GREEN, "", 2, AlgoritmosCirculos.STROKELINE);
		CirculoGr.desenhar(gc, 313*mult, 357*mult, 125*mult, Color.GREEN, "", 2, AlgoritmosCirculos.STROKELINE);
		CirculoGr.desenhar(gc, 186*mult, 357*mult, 125*mult, Color.GREEN, "", 2, AlgoritmosCirculos.STROKELINE);
		RetaGr.desenhar(gc, 250*mult, 250*mult, 250*mult, 32*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250*mult, 250*mult, 436*mult, 139*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250*mult, 250*mult, 438*mult, 356*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250*mult, 250*mult, 250*mult, 465*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250*mult, 250*mult, 61*mult, 356*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250*mult, 250*mult, 63*mult, 139*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250*mult, 32*mult, 436*mult, 139*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 436*mult, 139*mult, 438*mult, 356*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 438*mult, 356*mult, 250*mult, 465*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250*mult, 465*mult, 61*mult, 356*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 61*mult, 356*mult, 63*mult, 139*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 63*mult, 139*mult, 250*mult, 32*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		
		RetaGr.desenhar(gc, 438*mult, 356*mult, 61*mult, 356*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 438*mult, 356*mult, 250*mult, 32*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 61*mult, 356*mult, 250*mult, 32*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		
		RetaGr.desenhar(gc, 436*mult, 139*mult, 63*mult, 139*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 436*mult, 139*mult, 250*mult, 465*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 63*mult, 139*mult, 250*mult, 465*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		
		RetaGr.desenhar(gc, 250*mult, 250*mult, 311*mult, 141*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250*mult, 250*mult, 375*mult, 248*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250*mult, 250*mult, 313*mult, 357*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250*mult, 250*mult, 186*mult, 357*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250*mult, 250*mult, 125*mult, 248*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
		RetaGr.desenhar(gc, 250*mult, 250*mult, 188*mult, 141*mult, "", Color.RED, 2, AlgoritmosRetas.MIDPOINT);
	}
}

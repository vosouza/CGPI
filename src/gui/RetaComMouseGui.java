package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import primitivos.PontoGr;

public class RetaComMouseGui {
	int indicePonto=1;
	int estado=0;
	int x1=0;
	int y1=0;
	int x2=0;
	int y2=0;
	
	public RetaComMouseGui(Stage palco) {
		// define titulo da janela
		palco.setTitle("Desenhar Reta");
		palco.setResizable(false);
		
		// define largura e altura da janela
		palco.setWidth(505);
		palco.setHeight(505);

		// Painel para os componentes
		BorderPane pane = new BorderPane();

		// componente para desenho
		Canvas canvas = new Canvas(500, 500);
		// componente para desenhar graficos
		
		GraphicsContext gc;
		gc = canvas.getGraphicsContext2D();

		canvas.setOnMouseMoved(event -> {
			palco.setTitle("Desenha reta (Pressione botao do Mouse):"+" (" + (int)event.getX() + ", " + (int)event.getY() + ")");
		});
		// trata mousePressed
		canvas.setOnMousePressed(event -> {
			switch(estado) {
				case 0:
					if (event.getButton() == MouseButton.PRIMARY) {
						x1 = (int)event.getX();
						y1 = (int)event.getY();
						desenharPonto(gc, x1, y1, 4, "A"+indicePonto+" ( "+ x1 + ","+ y1 +" )", Color.BLUE);
						estado++;
					}
				break;
				case 1:
					if (event.getButton() == MouseButton.PRIMARY) {
						x2 = (int)event.getX();
						y2 = (int)event.getY();
						desenharPonto(gc, x2, y2, 4, "B"+indicePonto+" ( "+ x2 + ","+ y2 +" )", Color.BLUE);
						estado++;indicePonto++;
						desenharReta(gc,x1,y1,x2,y2);
					}					
					estado=0;
				break;
			}
		});

			// atributos do painel
			pane.setBackground(new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, Insets.EMPTY)));
			pane.setCenter(canvas); // posiciona o componente de desenho
			
			// cria e insere cena
			Scene scene = new Scene(pane);
			palco.setScene(scene);
			palco.show();	
		}
	
		private void desenharReta(GraphicsContext g,int x1, int y1, int x2, int y2) {
			PontoGr p;
			double m=0, x=0, y=0, fim=0;
			
			m = (double)(y2-y1)/(x2-x1);
			
			p = new PontoGr(x1,y1);
			p.desenharPonto(g);
			
			if(x2 < x1) {
				y=y2;
				x=x2;
				fim = x1;
				//m = Math.abs(m);
			}else if(x1 == x2) {
				fim=0;
				if(y1>y2) m=-1; else  m=1;
				y=y1; x=x1;
				while(y < y2) {
					y+=m;
					p = new PontoGr((int)x,(int)y);
					p.desenharPonto(g);
				}
			}else {
				y=y1;
				x=x1;
				fim = x2;
			}
			
			System.out.println(m);
			while(x < fim) {
				x++;
				y+=m;
				p = new PontoGr((int)x,(int)y);
				p.desenharPonto(g);
			}
		}

		/**
		 * Desenha um ponto grafico 
		 * 
		 * @param g contexto grafico
		 * @param x posicao x
		 * @param y posicao y
		 * @param diametro diametro do ponto
		 * @param nome nome do ponto
		 * @param cor cor do ponto
		 */
		public void desenharPonto(GraphicsContext g, int x, int y, int diametro, String nome, Color cor) {
			PontoGr p; 
	
			// Cria um ponto
			p = new PontoGr(x, y, cor, nome, diametro);
	
			// Desenha o ponto
			p.desenharPonto(g);
		}
	
}

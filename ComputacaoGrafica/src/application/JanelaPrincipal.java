package application;

import javax.swing.JOptionPane;

import figuras.Mandala;
import figuras.Fractal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import primitivos.AlgoritmosCirculos;
import primitivos.AlgoritmosRetas;
import primitivos.CirculoGr;
import primitivos.PontoGr;
import primitivos.RetaGr;

public class JanelaPrincipal {
	
	boolean primeiraVez;
	private int mode = 0;
	private int x1, x2, y1, y2;
	
	Canvas canvas;
	GraphicsContext gc;
	Stage palco;
	BorderPane bp;
	
	private Label mensagem;
	private Label mensagem1;
	
	private Button botaoReta;
	private Button botaoCirculo;
	private Button botaolimpar;
	private Button botaoTriangulo;
	private Button botaoComum;
	
	JanelaPrincipal(Stage palco) {
		this.palco = palco;
		BorderPane bp = new BorderPane();
		this.bp = bp;
		
		definirCanvas(); //define canvas e graphicContext
		criarBotao();// define os botoes e funcioanlidades
		definirFuncao();//Define as Reta, circulo e os desenhos
		
		
		HBox barraInferior = new HBox();
		mensagem = new Label();
		mensagem1 = new Label();
		barraInferior.getChildren().add(mensagem);
		barraInferior.getChildren().add(mensagem1);
		bp.setBottom(barraInferior);
		
		Scene scene = new Scene(bp);
		palco.setTitle("Paint 1.0");
		palco.setResizable(false);
		palco.setScene(scene);
		palco.show();
	}
	
	
	//Cria o Canvas
	private void definirCanvas() {	
		canvas = new Canvas(500, 500);
		gc = canvas.getGraphicsContext2D();
		
		
		StackPane sp = new StackPane();
		sp.getChildren().add(canvas);
		sp.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		bp.setCenter(sp);
	}



	private void criarBotao() {
		botaoReta = new Button();
		botaoReta.setText("Desenhar Reta");
		botaoReta.setPrefWidth(125);
		botaoReta.setOnAction(new EventHandler<ActionEvent>() {
	 
        	@Override
        	public void handle(ActionEvent event) {
        		mode = 1;
        	}
        });
		
		botaoCirculo = new Button();
		botaoCirculo.setText("Desenhar Circulo");
		botaoCirculo.setPrefWidth(125);
		botaoCirculo.setOnAction(new EventHandler<ActionEvent>() {
			 
        	@Override
        	public void handle(ActionEvent event) {
        		mode = 2;
        	}
        });
		
		botaolimpar = new Button();
		botaolimpar.setText("Limpar");
		botaolimpar.setPrefWidth(125);
		botaolimpar.setOnAction(new EventHandler<ActionEvent>() {
			 
        	@Override
        	public void handle(ActionEvent event) {
        		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        	}
        });
		
		botaoTriangulo = new Button();
		botaoTriangulo.setText("Triangulos");
		botaoTriangulo.setPrefWidth(125);
		botaoTriangulo.setOnAction(new EventHandler<ActionEvent>() {
			 
        	@Override
        	public void handle(ActionEvent event) {
        		mensagem1.setText("Clique em algum lugar do Canvas");
        		palco.setTitle("Clique em qualquer lugar do Canvas");
        		mode = 4;
        	}
        });
		
		botaoComum = new Button();
		botaoComum.setText("Desenho Comum");
		botaoComum.setPrefWidth(125);
		botaoComum.setOnAction(new EventHandler<ActionEvent>() {
			 
        	@Override
        	public void handle(ActionEvent event) {
        		mensagem1.setText("Clique em algum lugar do Canvas");
        		mode = 5;
        	}
        });
		
		VBox menu = new VBox();
		menu.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, CornerRadii.EMPTY, Insets.EMPTY)));
		menu.getChildren().addAll(botaoReta,botaoCirculo,botaolimpar,botaoTriangulo,botaoComum);
		bp.setLeft(menu);
	}
	
	public void definirFuncao() {
		primeiraVez = true;
		
		canvas.setOnMouseMoved(event->{
			mensagem.setText(" (" + (int)event.getX() + ", " + (int)event.getY() + ")");
		});
		
		
		canvas.setOnMouseClicked(event->{
			
			switch(mode) {
			case 1:
				//reta
				if (primeiraVez == true) {
						x1 = (int)event.getX();
						y1 = (int)event.getY();
						new PontoGr(x1, y1, Color.BLUE, "", 6).desenharPonto(gc);
						primeiraVez = false;
					} else {
						x2 = (int)event.getX();
						y2 = (int)event.getY();
						RetaGr.desenhar(gc, x1, y1, x2, y2, "", Color.RED,  2, AlgoritmosRetas.MIDPOINT);					
						new PontoGr(x2, y2, Color.BLUE, "", 6).desenharPonto(gc);
						primeiraVez = true;
					}
				break;
			case 2: //circulo
					if (primeiraVez == true) {
						x1 = (int)event.getX();
						y1 = (int)event.getY();
						new PontoGr(x1, y1, Color.BLUE, "", 6).desenharPonto(gc);
						primeiraVez = false;
					} else {
						x2 = (int)event.getX();
						y2 = (int)event.getY();
						double raio = new PontoGr (x1, y1).distance(x2, y2);
						CirculoGr.desenhar(gc, x1, y1, raio, Color.GREEN, "", 2, AlgoritmosCirculos.STROKELINE);					
						new PontoGr(x2, y2, Color.BLUE, "", 6).desenharPonto(gc);
						primeiraVez = true;
					}
				break;
			case 4: // triangulo recursivo
				mensagem1.setText("");
				try {
					int x = Integer.parseInt(JOptionPane.showInputDialog("Digite q quantidade de triangulos"));
					if(x<10){
						new Fractal(canvas,palco, gc).trianguloRecursivo(x);
					}else{
						JOptionPane.showMessageDialog(null, "Valor muito grande, tente um valor menor!");
					}
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Digite um valor valido");
				}
				
				break;
			case 5: //mandala
					new Mandala(canvas,palco, gc);
					mensagem1.setText("");
				break;
			}
		});
	}
}

package application;

import java.net.URL;
import java.util.ResourceBundle;

import dados.LeitorXML;
import dados.ListaPrimitivos;
import dados.PilhaPrimitivos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MainController implements Initializable {
	
	private PilhaPrimitivos desfazer; // pilha com todos os primitivos que foram retirador da lista
	private ListaPrimitivos historico; //lista com todos os primitivos
	
	private int mode; // identifica qual botão foi precionado
	
	private CanvasAction desenharNoCanvas;// classe que contem todos os desenhos para serem colocados no canvas
	private CanvasAction desenharNoMiniMapa;
	@FXML private Canvas viewPortCanvas; // canvas que foi pocionado por fxml
	@FXML private Canvas miniMapa;
	
	@FXML private Button btnReta;
	@FXML private Button btnCirculo;
	@FXML private ColorPicker colorPicker;
	@FXML private Slider sliderTamanho;
	
	@FXML private void mouseClickCanvas(MouseEvent event) {
		pegarTamanho();
		if(event.getButton() == MouseButton.PRIMARY) {
			desenharNoCanvas.action(mode, (int)event.getX(), (int)event.getY(), historico);
		}else if(event.getButton() == MouseButton.SECONDARY) {
			desenharNoCanvas.cancelarClick();
			desenharNoCanvas.limparTela();
			desenharNoCanvas.loadHistorico(historico);
		}
		desenharNoMiniMapa.loadHistorico(historico, 0.2);
	}
	
	@FXML private void pegarCor(ActionEvent event) {
		desenharNoCanvas.setCor(colorPicker.getValue());
	}
	
	private void pegarTamanho() {
		desenharNoCanvas.setTamanho((int)sliderTamanho.getValue());
	}
	
	@FXML private void botaoReta(ActionEvent event) {
		mode = 1;
	}
	
	@FXML private void botaoCirculo(ActionEvent event) {
		mode = 2;
	}
	
	@FXML private void botaoQuadrado(ActionEvent event) {
		mode = 3;
	}
	
	@FXML private void botaoPoligono(ActionEvent event) {
		mode = 4;
	}
	
	@FXML private void botaoLimpar(ActionEvent event) {
		int i = historico.getQtd() -1 ;
		while(historico.vazia() == false) {
			desfazer.push(historico.buscar(i));
			historico.remover(i);
			i--;
		}
		desenharNoCanvas.limparTela();
		desenharNoMiniMapa.limparTela();
		desenharNoCanvas.loadHistorico(historico);
		desenharNoMiniMapa.loadHistorico(historico,0.2);
	}
	
	@FXML private void botaoDesfazer(ActionEvent event) {
		try {
			if((historico.vazia() == true) && (desfazer.isEmpty() == false)) {
				while(desfazer.isEmpty() == false) {
					historico.inserir(desfazer.top());
					desfazer.pop();
				}
			}else {
				if(historico.vazia() == false) { 
					desfazer.push(historico.buscar(historico.getQtd()-1));
					historico.remover(historico.getQtd()-1);
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		desenharNoCanvas.limparTela();
		desenharNoMiniMapa.limparTela();
		desenharNoCanvas.loadHistorico(historico);
		desenharNoMiniMapa.loadHistorico(historico,0.2);
	}
	
	@FXML private void botaoMandala(ActionEvent event) {
		mode = 7;
	}
	
	@FXML private void botaoFractal(ActionEvent event) {
		mode = 8;
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		historico =  new ListaPrimitivos();
		desfazer = new PilhaPrimitivos();
		desenharNoCanvas = new CanvasAction(viewPortCanvas);
		desenharNoMiniMapa = new CanvasAction(miniMapa);
		
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent event) { 
				   desenharNoCanvas.desenhoTemporario(mode, (int)event.getX(), (int)event.getY(), historico);  
			   } 
		};   
		viewPortCanvas.addEventFilter(MouseEvent.MOUSE_MOVED, eventHandler);
		
		LeitorXML arq = new LeitorXML();
		arq.passar();
		mode = 0;
	}

}

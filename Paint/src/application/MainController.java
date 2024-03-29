package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import dados.GravarXML;
import dados.LeitorXML;
import dados.ListaPrimitivos;
import dados.PilhaPrimitivos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class MainController implements Initializable {
	
	private PilhaPrimitivos desfazer; // pilha com todos os primitivos que foram retirador da lista
	private ListaPrimitivos historico; //lista com todos os primitivos
	
	private int mode; // identifica qual bot�o foi precionado
	
	private CanvasAction desenharNoCanvas;// classe que contem todos os desenhos para serem colocados no canvas
	private CanvasAction desenharNoMiniMapa;
	@FXML private Canvas viewPortCanvas; // canvas que foi pocionado por fxml
	@FXML private Canvas miniMapa;
	
	@FXML private ColorPicker colorPicker;
	@FXML private Slider sliderTamanho;
	
	@FXML private void mouseClickCanvas(MouseEvent event) {
		pegarTamanho();
		if(event.getButton() == MouseButton.PRIMARY) {
			desenharNoCanvas.action(mode, (int)event.getX(), (int)event.getY(), historico);
			if(event.getClickCount() == 2){
                desenharNoCanvas.doubleClique();
            }
		}else if(event.getButton() == MouseButton.SECONDARY) {
			desenharNoCanvas.cancelarClick();
			desenharNoCanvas.doubleClique();
		}
		atualizarTela();
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
		//historico.inserir(new Poligono());
	}
	
	@FXML private void botaoMandala(ActionEvent event) {
		mode = 5;
	}
	
	@FXML private void botaoFractal(ActionEvent event) {
		mode = 6;
	}
	
	@FXML private void botaoLimpar(ActionEvent event) {
		limparHistorico();
		atualizarTela();
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
		
		atualizarTela();
	}
	
	@FXML private void abrirArquivo(ActionEvent event) {
		LeitorXML arq = new LeitorXML(historico);
		FileChooser chooser = new FileChooser();
        Window stage = null;
		File file = chooser.showOpenDialog(stage);
        if (file != null) {
        	limparHistorico();
            String fileAsString = file.toString();
            arq.passar(fileAsString);
    		atualizarTela();
        }
	}
	@FXML private void salvarArquivo(ActionEvent event) {
		GravarXML gravador = new GravarXML(historico);
        Window stage = null;
        
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files","*.xml");
        chooser.getExtensionFilters().add(extFilter);
		File file = chooser.showSaveDialog(stage);
		
        if (file != null) {
            String fileAsString = file.toString();
            gravador.Gravar(fileAsString);
        }
    }
		
	//limpa a tela e redesenha conforme as figuras salvas na lista
	private void atualizarTela() {
		desenharNoCanvas.limparTela();
		desenharNoMiniMapa.limparTela();
		desenharNoCanvas.loadHistorico(historico);
		desenharNoMiniMapa.loadHistorico(historico,0.2);//0.2 � a razao da do canvas pelos miniMapa
	}
	
	//Limpa o historico
	public void limparHistorico() {
		int i = historico.getQtd() -1 ;
		while(historico.vazia() == false) {
			desfazer.push(historico.buscar(i));
			historico.remover(i);
			i--;
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Inicializacao das variaveis
		historico =  new ListaPrimitivos();
		desfazer = new PilhaPrimitivos();
		desenharNoCanvas = new CanvasAction(viewPortCanvas);
		desenharNoMiniMapa = new CanvasAction(miniMapa);
		
		//Evento para dasenho temporario
		//chama desenharTemporario que n�o grava na lista e desenha sempre que o mouse se mover
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
			   @Override 
			   public void handle(MouseEvent event) { 
				   desenharNoCanvas.desenhoTemporario(mode, (int)event.getX(), (int)event.getY(), historico);  
			   } 
		};   
		viewPortCanvas.addEventFilter(MouseEvent.MOUSE_MOVED, eventHandler);

		mode = 0;
	}

}

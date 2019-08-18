package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MenuGUI {
	Button btnReta;
	Button btnPonto; 
	public MenuGUI(Stage palco) {
		btnPonto = new Button();
		btnPonto.setText("Desenhar Ponto");
    	
		btnPonto.setOnAction(new EventHandler<ActionEvent>() {
	 
        	@Override
        	public void handle(ActionEvent event) {
        		new PontoComMouseGui(palco);
        	}
        });
	    
		btnReta = new Button();
		btnReta.setText("Desenhar Reta");
        
		btnReta.setOnAction(new EventHandler<ActionEvent>() {
			 
        	@Override
        	public void handle(ActionEvent event) {
        		new RetaComMouseGui(palco);
        	}
        });
        
        FlowPane root = new FlowPane(Orientation.VERTICAL);
        root.setColumnHalignment(HPos.LEFT); // align labels on left
        root.setPrefWrapLength(150); 
        
        root.getChildren().add(btnPonto);
        root.getChildren().add(btnReta);
        
        Scene scene = new Scene(root,200,350);
		palco.setScene(scene);
		palco.show();	
	}
}

package application;
	
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import entidades.Tabuleiro;

public class Main extends Application {
	
	Tabuleiro tabuleiro = new Tabuleiro();
	
	private static final int tamCasa = 40;
	private static final int comprimento = (tamCasa * Tabuleiro.COLUNA);
	private static final int altura = (tamCasa * Tabuleiro.LINHA);
	
	private Scene scene;
	
	private Parent createContent() {
		Pane root = new Pane();
		
		root.setPrefSize(comprimento, altura);
		
		tabuleiro.iniciaCampo();
		tabuleiro.definePosicaoDasBombas();
		tabuleiro.defineNumerosDeBombasAoLado();
		
		poeCasasNoPane(root, tabuleiro);
		poeNumerosNoPane(tabuleiro);
		poeNadaNosZeros(tabuleiro);		
		
		return root;
	}
	
	private void poeCasasNoPane(Pane root, Tabuleiro tabuleiro) {
		for(int i = 0; i < Tabuleiro.LINHA; i++) {
			for(int j = 0; j < Tabuleiro.COLUNA; j++) {
				root.getChildren().add(tabuleiro.campo[i][j]);
			}
		}
	}
	
	private void poeNumerosNoPane(Tabuleiro tabuleiro)	{
		for(int i = 0; i < Tabuleiro.LINHA; i++) {
			for(int j = 0; j < Tabuleiro.COLUNA; j++) {
				if(tabuleiro.campo[i][j].getTipoCasa() == 0) {
					tabuleiro.campo[i][j].text.setText(
							String.valueOf(tabuleiro.campo[i][j].getBombasAoLado()));
				}
			}	
		}
	}

	private void poeNadaNosZeros(Tabuleiro tabuleiro) {
		for (int i = 0; i < Tabuleiro.LINHA; i++) {
			for (int j = 0; j < Tabuleiro.COLUNA; j++) {
				if (tabuleiro.campo[i][j].getBombasAoLado() == 0) {
					tabuleiro.campo[i][j].text.setText("");
				}
			}
		}
	}

	@Override
	public void start(Stage stage) {
		try {
			scene = new Scene(createContent());
			stage.setScene(scene);
			stage.show();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {					
		launch(args);	
	}	


}

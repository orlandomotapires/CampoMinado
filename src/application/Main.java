package application;
	
import entidades.Tabuleiro;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Parent creatContent() {
		Pane root = new Pane();
		root.setPrefSize(Tabuleiro.LINHA, Tabuleiro.COLUNA);
		
		return root;
	}
	
	@Override
	public void start(Stage stage) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/gui/View.fxml"));	
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Tabuleiro tabuleiro = new Tabuleiro();
		tabuleiro.iniciaCampo();
		tabuleiro.definePosicaoDasBombas();
		tabuleiro.defineNumerosDeBombasAoLado();
		
		Button campoGrafico[][] = new Button[Tabuleiro.LINHA][Tabuleiro.COLUNA];
		
		launch(args);
		
	}	


}

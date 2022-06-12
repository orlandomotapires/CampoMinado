package application;
	
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	
	Tabuleiro tabuleiro = new Tabuleiro();
	
	private Scene scene;
	
	public int perdeu;
	
	private static final int tamCasa = 40;
	private static final int comprimento = (tamCasa * Tabuleiro.COLUNA);
	private static final int altura = (tamCasa * Tabuleiro.LINHA);
	
	public Parent createContent() throws InterruptedException {
		Pane root = new Pane();
		perdeu = 0;
		
		root.setPrefSize(comprimento, altura);
		
		tabuleiro.iniciaCampo();
		tabuleiro.definePosicaoDasBombas();
		tabuleiro.defineNumerosDeBombasAoLado();
		
		tabuleiro.mostraTabuleiro();
		
		poeCasasNoPane(root, tabuleiro);
		poeNumerosNoPane(tabuleiro);
		poeNadaNosZeros(tabuleiro);		
		
		return root;
	}
	
	public List<Casa> pegaAoLado(Casa casa){
		List <Casa> listaDeVizinhos = new ArrayList<>();
		
		//DIREITA
		if(casa.x != 14){
			listaDeVizinhos.add(tabuleiro.campo[casa.x + 1][casa.y]);
		}
		
		//ESQUERDA
		if(casa.x != 0){
			listaDeVizinhos.add(tabuleiro.campo[casa.x - 1][casa.y]);
		}	
		
		// CIMA
		if(casa.y != 14){
			listaDeVizinhos.add(tabuleiro.campo[casa.x][casa.y + 1]);
		}
		
		//BAIXO
		if(casa.y != 0){
			listaDeVizinhos.add(tabuleiro.campo[casa.x][casa.y - 1]);	
		}
		
		//DIREITA CIMA
		if(casa.x != 14 && casa.y != 14){
			//System.out.println("Casa adicionada: x: " + (casa.x + 1) + " Y: " + (casa.y + 1));
			listaDeVizinhos.add(tabuleiro.campo[casa.x + 1][casa.y + 1]);
		
		}
		
		//DIREITA BAIXO
		if(casa.x  !=  14 && casa.y != 0){
			//System.out.println("Casa adicionada: x: " + (casa.x + 1) + " Y: " + (casa.y - 1));
			listaDeVizinhos.add(tabuleiro.campo[casa.x + 1][casa.y - 1]);
			

		}	
		
		//ESQUERDA CIMA
		if(casa.x != 0 && casa.y != 14){
			//System.out.println("Casa adicionada: x: " + (casa.x - 1) + " Y: " + (casa.y + 1));
			listaDeVizinhos.add(tabuleiro.campo[casa.x - 1][casa.y + 1]);
		
		}
		
		//ESQUERDA BAIXO
		if(casa.x  != 0  && casa.y != 0 ){
			//System.out.println("Casa adicionada: x: " + (casa.x - 1) + " Y: " + (casa.y - 1));
			listaDeVizinhos.add(tabuleiro.campo[casa.x - 1][casa.y - 1]);
		}
					
		return listaDeVizinhos;
	}
	
	public class Casa extends StackPane{
			private int x, y;
		    private int tipoCasa;
		    private int bombasAoLado;
		    private ImageView img;
		    
		    private boolean aberto = false;
		    
		    private Rectangle border = new Rectangle(40, 40);
		    public Text text = new Text();
		    
		    public Casa(int x, int y){
				this.x = x;
				this.y = y;
				this.bombasAoLado = 0;
				this.tipoCasa = 0;
				border.setStroke(Color.BLACK);
				border.setFill(Color.GRAY);
				
				text.setFill(Color.WHITE);
				
				text.setFont(Font.font(20));
				 
				text.setVisible(false);
					
				getChildren().addAll(border, text);
				
				setTranslateX(x * 40);
				setTranslateY(y * 40);	
	
				setOnMouseClicked(e -> open());
											
			}		    		 

			public void open(){								
				
				if(perdeu == 1) {			
					try {
						scene.setRoot(createContent());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return;
				}else if(perdeu == -1) {
					try {
						scene.setRoot(createContent());
					} catch (InterruptedException e) {				
						e.printStackTrace();
					}
				}
				
				
				if(aberto) {
					return;
				}																	
				
				if(tipoCasa == 1) {
					System.out.println("Voce perdeu!");
					System.out.println("Reiniciando...");	
					perdeu = 1;
					mostraTodasBombas(tabuleiro, perdeu);
					return;
					
				}
		
				aberto = true;
				text.setVisible(true);
				border.setFill(Color.ORANGE);
				text.setFill(Color.BLACK);
				
				List <Casa> listaDeVizinhos = new ArrayList<>();
				if(this.getBombasAoLado() == 0){
					listaDeVizinhos = pegaAoLado(this);	
					for(Casa casaAtual : listaDeVizinhos){					
						if(casaAtual.tipoCasa != 1){
							casaAtual.open();
						}
					}
				}
				
				if(checaSeVenceu(tabuleiro)){
					System.out.println("VOCE VENCEU!!!!!!");
					perdeu = -1;
					mostraTodasBombas(tabuleiro, perdeu);			
					return;
				}
				
				
			}	 						

			public void setImage(Image img) {
				this.img = new ImageView(img);
			}
			
			public int getTipoCasa() {
				return tipoCasa;
			}
			
			public void setTipoCasa(int tipo) {
				tipoCasa = tipo;
			}
			
			public int getBombasAoLado() {
				return bombasAoLado;
			}
			
			public void incrementaBomba() {
				bombasAoLado++;
			}

		}

	public class Tabuleiro {
		public final static int LINHA = 15;
		public final static int COLUNA = 15;
		private final static int QNTBOMBA = 21;
		public Casa[][] campo;

		public Tabuleiro() {
			campo = new Casa[LINHA][COLUNA];
		}

		public void iniciaCampo() {
			for (int i = 0; i < LINHA; i++) {
				for (int j = 0; j < COLUNA; j++) {
					campo[i][j] = new Casa(i, j);
				}
			}
		}

		public void definePosicaoDasBombas() { 
			Image img = new Image("bombaLaranja.png");
			
			for (int i = 0; i < QNTBOMBA; i++) {
				double rand1 = Math.random();
				double rand2 = Math.random();
				int lin = (int) (rand1 * LINHA);
				int coluna = (int) (rand2  * COLUNA);
				
				if (campo[lin][coluna].getTipoCasa() == 1) {
					i--;	
				}else {
					campo[lin][coluna].setTipoCasa(1);
					campo[lin][coluna].text.setText("X");
					//campo[lin][coluna].setImage(img);
				}
			}
		}

		public void defineNumerosDeBombasAoLado(){ // Funcao para definir os contadores das casas que tem bombas ao lado.
			for (int i = 0; i < LINHA; i++) {
				for (int j = 0; j < COLUNA; j++) {
					if (campo[i][j].getTipoCasa() == 1) {
						incrementaNumeroDeBombasAoLado(i, j);
					}
				}
			}		
		}

		private void incrementaNumeroDeBombasAoLado(int ini, int fim){ // Funcao para Incrementar os contadores das casas ao lado ao													// encontrar uma bomba.
			for (int i = ini - 1; i <= ini + 1; i++) {
				for (int j = fim - 1; j <= fim + 1; j++) {
					if (i != -1 && j != -1 && i != LINHA && j != COLUNA) {
						campo[i][j].incrementaBomba();
					}
				}
			}
		}

		public void mostraTabuleiro() {
			System.out.println("=-=-=-=-= Tabuleiro =-=-=-=-=");
			for(int i = 0; i < LINHA; i++) {
				for(int j = 0; j < COLUNA; j++) {
					if(campo[i][j].getTipoCasa() == 1){
						System.out.print("X ");
					}else{
						System.out.print(campo[i][j].getBombasAoLado() + " ");
					}	
				}
				System.out.println();
			}
		}
		
		public void setRootGetChildren(Pane root, Casa casa) {
			root.getChildren().add(casa);
		}
	}
	
	private boolean checaSeVenceu(Tabuleiro tabuleiro) {
		for(int i = 0; i < Tabuleiro.LINHA; i++) {
			for(int j = 0; j < Tabuleiro.COLUNA; j++) {
				if(tabuleiro.campo[i][j].getTipoCasa() == 0 && tabuleiro.campo[i][j].text.isVisible() == false) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private void mostraTodasBombas(Tabuleiro tabuleiro, int perdeu) {
		
		for(int i = 0; i < Tabuleiro.LINHA; i++) {
			for(int j = 0; j < Tabuleiro.COLUNA; j++) {
				if(tabuleiro.campo[i][j].getTipoCasa() == 1 && tabuleiro.campo[i][j].text.isVisible() == false) {
					tabuleiro.campo[i][j].text.setVisible(true);
					tabuleiro.campo[i][j].text.setFill(Color.BLACK);
					if(perdeu == 1)
						tabuleiro.campo[i][j].border.setFill(Color.RED);
					if(perdeu == -1) 
						tabuleiro.campo[i][j].border.setFill(Color.GREEN);
				}
			}
		}
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

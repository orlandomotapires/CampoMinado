package entidades;

import javafx.scene.layout.Pane;

public class Tabuleiro {
	public final static int LINHA = 15;
	public final static int COLUNA = 15;
	private final static int QNTBOMBA = 40;
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

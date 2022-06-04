package entidades;

// Classe destinada a definir as caracteristicas e e processos realizados para o tabuleiro do jogo 

public class Tabuleiro {
	public final static int LINHA = 15;
	public final static int COLUNA = 15;
	private final static int QNTBOMBA = 40;
	private Casa[][] campo;

	public Tabuleiro() {
		campo = new Casa[LINHA][COLUNA];
	}

	public void iniciaCampo() {
		for (int i = 0; i < LINHA; i++) {
			for (int j = 0; j < COLUNA; j++) {
				campo[i][j] = new Casa();
			}
		}
	}

	public void definePosicaoDasBombas() { // Funcao para definir as casas que terao bombas e embaralha-las.
		for (int i = 0; i < QNTBOMBA; i++) {
			double rand1 = Math.random();
			double rand2 = Math.random();

			int lin = (int) rand1 * LINHA;
			int coluna = (int) rand2 * COLUNA;

			if (campo[lin][coluna].getTipoCasa() == 1) {
				i--;
			} else {
				campo[lin][coluna].setTipoCasa(1);
			}
		}
	}

	public void defineNumerosDeBombasAoLado() { // Funcao para definir os contadores das casas que tem bombas ao lado.
		for (int i = 0; i < LINHA; i++) {
			for (int j = 0; j < COLUNA; j++) {
				if (campo[i][j].getTipoCasa() == 1) {
					incrementaNumeroDeBombasAoLado(i, j);
				}
			}
		}
	}

	private void incrementaNumeroDeBombasAoLado(int ini, int fim) { // Funcao para Incrementar os contadores das casas ao lado ao													// encontrar uma bomba.
		for (int i = ini - 1; i <= ini + 1; i++) {
			for (int j = fim - 1; j <= fim + 1; j++) {
				if (i != -1 && j != -1 && i != 18 && j != 14) {
					campo[i][j].incrementaBomba();
				}
			}
		}
	}

}

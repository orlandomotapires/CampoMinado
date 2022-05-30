package entidades;
     
import java.util.Random;

// Classe destinada a definir as caracteristicas e e processos realizados para o tabuleiro do jogo 

public class Tabuleiro {
	private int linha;
	private int coluna;
	private int qntBomba;
	private Casa[][] campo;   
	private Random gerador;
	
	public Tabuleiro(int linha, int coluna, int qntBomba){		
		this.linha = linha;
        this.coluna = coluna;
        this.qntBomba = qntBomba;
        campo = new Casa [linha][coluna];
        gerador = new Random();
	}
	
	public void iniciaTabuleiro(){ //Funcao para criar o tabuleiro.
		defineBombas();
		defineContadores();
	}
	
	public void iniciaCampo(){
		for(int i = 0; i < linha; i++) {
			for(int j = 0; j < coluna; j++) {
				campo[i][j] = new Casa();
			}
		}
	}
	
	public void defineBombas(){ // Funcao para definir as casas que terao bombas e embaralha-las.
		for(int i = 0; i < qntBomba; i ++) {
			double rand1 = Math.random();
			double rand2 = Math.random();
			
			double lin = rand1 * 7;
			double coluna = rand2 * 7;
			
			if(campo[(int) lin][(int) coluna].getTipoCasa() == 1) {
				i--;
			}else {
				campo[(int) lin][(int) coluna] = new Bomba();
			}
		}
	}

    public void incrementaBombasAoLado(int ini, int fim){ // Funcao para Incrementar os contadores das casas ao lado ao encontrar uma bomba.
		for(int i = ini-1; i <= ini+1; i++){ 
			for(int j = fim-1; j <= fim+1; j++){
				if(i != -1 && j != -1 && i != 18 && j != 14){
						campo[i][j].incrementaBomba();	  
				}
			}	
		}
    }

	public void defineContadores(){ // Funcao para definir os contadores das casas que tem bombas ao lado.
		for(int i = 0; i < this.linha; i++){
			for(int j = 0; j < this.coluna; j++){		
				if(campo[i][j].getTipoCasa() > 1){		
					incrementaBombasAoLado(i, j);
				}	
			}
		}
	}	
	
}

package entidades;

public class Casa {
    private int tipoCasa = 0;
    private int bombasAoLado;
	
	public Casa(){
		this.bombasAoLado = 0;
	}

	//Getters e Setters:
	public int getTipoCasa() {
		return tipoCasa;
	}
	
	public void setTipoCasa(int tipo) {
		tipoCasa = tipo;
	}
	
	public void incrementaBomba() {
		bombasAoLado++;
	}

}

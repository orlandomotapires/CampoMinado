package entidades;

public class Casa {
    final private int tipoCasa = -1;
    private int bombasAoLado;
	
	public Casa() {
		super();
		this.bombasAoLado = 0;
	}

	//Getters e Setters:
	public int getTipoCasa() {
		return tipoCasa;
	}
	
	public void incrementaBomba() {
		bombasAoLado++;
	}

}

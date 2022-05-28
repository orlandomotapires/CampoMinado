package entidades;

public abstract class Casa {
	int bombasAoLado;
    private int tipoCasa; 

	public Casa(){
		bombasAoLado = 0;
		tipoCasa = 0;	
	}	
	
	//Getters e Setters:
	public int getTipoCasa() {
		return tipoCasa;
	}

	public void setTipoCasa(int tipoCasa){
		this.tipoCasa = tipoCasa;
	}	

}

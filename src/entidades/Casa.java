package entidades;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Casa extends StackPane{
		private int x, y;
	    private int tipoCasa;
	    private int bombasAoLado;
	    
	    private boolean aberto = false;
	    
	    private Rectangle border = new Rectangle(40, 40);
	    public Text text = new Text();
	    
	    public Casa(int x, int y){
			this.x = x;
			this.y = y;
			this.bombasAoLado = 0;
			this.tipoCasa = 0;
			
			border.setStroke(Color.DARKORANGE);
			border.setFill(Color.BLACK);
			
			text.setFill(Color.WHITE);
			
			text.setFont(Font.font(20));
			
			text.setVisible(false);
				
			getChildren().addAll(border, text);
			
			setTranslateX(x * 40);
			setTranslateY(y * 40);
			
			setOnMouseClicked(e -> open());		
		}

		public void open() {
			if(aberto) {
				return;
			}
			
			if(tipoCasa == 1) {
				System.out.println("Voce perdeu!");
				System.out.println("Reiniciando...");
				return;
			}
			
			aberto = true;
			text.setVisible(true);
			border.setFill(Color.ORANGE);
			text.setFill(Color.BLACK);		
			
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
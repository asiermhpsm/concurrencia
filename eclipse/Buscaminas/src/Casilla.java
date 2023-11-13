package Juego;

public class Casillas{
	private boolean bomba;
	private int bombasAlrededor;
	private boolean abierto;

	public Casillas() {
		this.bomba = false;
		this.abierto = false;
	}
	
	public void asignarBomba() {
		this.bomba = true;
	}
	
	public void abrir() {
		this.abierto = true;
	}
	
	public boolean getBomba() {
		return this.bomba;
	}
	public void setBombasAlrededor(int n) {
		this.bombasAlrededor = n;
	}
	public int getBombasAlrededor() {
		return this.bombasAlrededor;
	}
}

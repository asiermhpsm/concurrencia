package Juego;


public class Mapa {
	private Casilla[][] mapa;
	private int numeroBombasEnMapa;
	public static final double PORCENTAJEBOMBAS = 0.18;
	
	//Constructor 1
	public Mapa (int filas, int  columnas, int numeroBombasEnMapa) {
		mapa = new Casilla[filas][columnas];
		for(int i = 0; i < filas; i++) {
			for(int j = 0; i < columnas; j++) {
				mapa[i][j] = new Casilla();
			}
		}
		this.numeroBombasEnMapa = numeroBombasEnMapa;
		creacionBombas();
		calcucalBombasAlrededor();
	}

	
	//Constructor 2
	public Mapa (int filas, int  columnas) {
		mapa = new Casilla[filas][columnas];
		for(int i = 0; i < filas; i++) {
			for(int j = 0; i < columnas; j++) {
				mapa[i][j] = new Casilla();
			}
		}
		this.numeroBombasEnMapa = (int) (filas * columnas * PORCENTAJEBOMBAS);
		creacionBombas();
		calcucalBombasAlrededor();
	}
	
	//Asigna las bombas
	private void creacionBombas() {
		for(int k = 1; k <= numeroBombasEnMapa; k++ ) {
			int i =(int)(Math. random()*mapa.length-1);
			int j= (int) Math. random()*mapa[0].length-1;
			if(mapa[i][j].getBomba())
				k--;
			else
				mapa[i][j].asignarBomba();
		}
	}
	
	//Calcula las bombas de alrededor
	public void calcucalBombasAlrededor() {
		for(int i=0; i<mapa.length; i++) {
			for(int j=0; j< mapa[0].length; j++) {
				mapa[i][j].setBombasAlrededor(sumarBombasAlrededor(i,j));
				
			}
		}
		
	}
	
	//Abre las casillas adyacentes vacías
	public void abrirCasilla(int i, int j) {
		mapa[i][j].abrir();
		for(int k=i-1;k<i+1;k+=2) {
			if(mapa[k][j].getBombasAlrededor()==0  &&  casillaAlrededorAbierta(k,j)) {
				abrirCasilla(k,j);
			}
		}
		if(mapa[i][j-1].getBombasAlrededor()==0  &&  casillaAlrededorAbierta(i,j-1)) {
			abrirCasilla(i,j-1);
		}
		if(mapa[i][j+1].getBombasAlrededor()==0  &&  casillaAlrededorAbierta(i,j+1)) {
			abrirCasilla(i,j+1);
		}
	}
	
	public void mostrarMapa() {
		for(int k=0;k<mapa[0].length;k++) {
			numeroALetras(k);
		}
		System.out.println();
		for(int i=0; i<mapa.length; i++) {
			System.out.print(i+1);
			for(int j=0; j<mapa.length;j++) {
				if(!mapa[i][j].getAbierto()) {
					System.out.print("X");
				}
				else {
					System.out.print(mapa[i][j].getBombasAlrededor());;
				}
			}
			System.out.println();
		}
	}
	
	public void play(int i, int j) throws Exception{
		if(mapa[i][j].getBomba()) {
			throw new Exception();
		}
		abrirCasilla(i,j);
		mostrarMapa();
	}
	
	
	
	//FUNCIONES AUXILIARES
	
	private int booleanToInteger(boolean a) {
		return (a)?1:0;
	}

	private int sumarBombasAlrededor(int i, int j) {
		int num = 0;
		for(int k=i-1;k<=i+1;k+=2) {
			for(int l=j-1;l<=j+1;l++) {
				try {
					num += booleanToInteger(mapa[k][j].getBomba());
				}catch(ArrayIndexOutOfBoundsException e) {}
			}
		}
		try {
			num += booleanToInteger(mapa[i][j-1].getBomba());
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			num += booleanToInteger(mapa[i][j+1].getBomba());
		}catch(ArrayIndexOutOfBoundsException e) {}
		
		return num;
	}
	
	private boolean casillaAlrededorAbierta(int i, int j) {
		boolean res = false;
		for(int k=i-1;k<=i+1 && !res;k+=2) {
			res = mapa[k][j].getAbierto();
		}
		if(!res) {
			res = mapa[i][j-1].getAbierto();
		}
		else if(!res) {
			res = mapa[i][j+1].getAbierto();
		}
		return res;
	}
	
	private char numeroALetras(int num) {
		return (char)(num+65);
	}
	
	
}

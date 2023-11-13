import java.util.Scanner;
import Juego.Mapa;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Filas: ");
		int filas = s.nextInt();
		System.out.println("Columnas: ");
		int columnas = s.nextInt();
		int opcion;
		Mapa mapa = null;
		do {
			System.out.println("MENU\n1-Elegir numero de bomas\n2-Generación automática de bombas");
			opcion = s.nextInt();
		}while(opcion==1 || opcion==0);
		if(opcion==1) {
			boolean valido = true;
			do {
				System.out.println("Numero de bombas: ");
				int bombas = s.nextInt();
				if(bombas>filas*columnas) {
					valido=false;
				}else {
					mapa = new Mapa(filas,columnas,bombas);
				}
			}while(valido);
		}
		else {
			mapa = new Mapa(filas,columnas);
		}
		
		
		boolean perdido= false;
		do {
			mapa.mostrarMapa();
			System.out.println("Fila: ");
			int fila =  s.nextInt();
			System.out.println("Columna: ");
			char columna =  s.next().trim().toUpperCase().charAt(0);
			try {
				mapa.play(fila, letrasANumeros(columna));
			}catch(Exception e) {
				perdido = true;
			}
		}while(!perdido);
	}
	
	
	private static int letrasANumeros(char letra) {
		return (int)(letra-65);
	}

}

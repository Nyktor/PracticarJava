package main;

import java.util.Scanner;

public class Main {
	
	static final char FICHA_VACIA = ' ';
	static final char FICHA_J1 = 'X';
	static final char FICHA_J2 = 'O';

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		char[][] tablero = new char[3][3];
		int fila, columna, jugadorActual;
		char opcion = ' ';
		boolean jugarOtraVez = false;
		
		jugadorActual = 2;
		fila = -1;
		columna = -1;
		
		
		// Se jugara mientras quieran seguir jugando
		do {

			jugadorActual = 2;
			inicializarTablero(tablero);
			imprimirTablero(tablero);
			
			// La partida continuara mientras no este lleno el
			// tablero y mientras no haya ganador
			do {
			
				jugadorActual = (jugadorActual==1 ? 2 : 1);
					

				// Comprobar que ponga la ficha correctamente
				do {
					
					// Comprobar que la fila es correcta
					do {
						
						System.out.print("\nJugador "+jugadorActual+", selecciona la FILA (1-3)\n>> ");
						fila = sc.nextInt();
						fila--;
						
						if(fila < 0 || fila > 2) {
							System.out.println("\nERROR: Escribe una fila correcta.\n");
						}
						
					}while(fila < 0 || fila > 2);
					
					// Comprobar que la columna es correcta
					do {
						
						System.out.print("\nJugador "+jugadorActual+", selecciona la COLUMNA (1-3)\n>> ");
						columna = sc.nextInt();
						columna--;
						
						if(columna < 0 || columna > 2) {
							System.out.println("\nERROR: Escribe una columna correcta.\n");
						}
						
					}while(columna < 0 || columna > 2);
					
					
					// Comprobar si la posicion esta ocupada
					if(estaOcupada(tablero, fila, columna)) {
						System.out.println("\nERROR: esa posicion esta ocupada.\n");
					}
				
					
				}while(estaOcupada(tablero, fila, columna));
				
				
				// Aqui asumimos que la fila y la columna son correctas
				char fichaInsertar = (jugadorActual == 1 ? FICHA_J1 : FICHA_J2);
				// E insertamos la ficha
				tablero = insertarFicha(tablero, fila, columna, fichaInsertar);

				imprimirTablero(tablero);
			
				
			}while(!estaLleno(tablero) && (hayGanador(tablero) == FICHA_VACIA));
			
			
			// Si llegamos aqui significa que o el tablero esta lleno o hay un ganador
			if(hayGanador(tablero) == FICHA_J1) {
				
				System.out.println("\nEl ganador es el Jugador 1!\n");
			
			}else if(hayGanador(tablero) == FICHA_J2) {
				System.out.println("\nEl ganador es el Jugador 2!\n");
				
			}else {
				System.out.println("\nHa habido un empate! :(\n");
			}
			
			
			// Preguntar si quiere jugar otra vez
			do {
				
				System.out.print("\nQuieres jugar otra vez? (s/n)\n>> ");
				opcion = Character.toLowerCase(sc.next().charAt(0));
				
				if(opcion != 's' && opcion != 'n') {
					System.out.println("\nERROR: Opcion incorrecta.\n");
				}
				
			}while(opcion != 's' && opcion != 'n');
			
			jugarOtraVez = (opcion == 's');
			
			
		}while(jugarOtraVez);
		
		sc.close();
	}
	
	// Se llama a esta funcion al principio de todo
	// y por cada nuevo juego que se empiece
	static char[][] inicializarTablero(char[][] tablero) {
		
		char[][] tableroActualizado = tablero;
		
		for(int i = 0; i < tablero.length; i++) {
			for(int j = 0; j < tablero[0].length; j++) {
				tableroActualizado[i][j] = FICHA_VACIA;
			}
		}
		
		return tableroActualizado;
	}
	
	// Funcion que imprime el tablero por pantalla
	static void imprimirTablero(char[][] tablero) {
		System.out.println();
		for(int i = 0; i < tablero.length; i++) {
			
			if(i > 0) {
				System.out.print("\n-----------\n");
			}

			for(int j = 0; j < tablero[0].length; j++) {
				System.out.print(" "+tablero[i][j]+" ");
				
				if(j < 2) {
					System.out.print("|");
				}
				
			}
		}
		System.out.println();
	}
	
	// Determina si una posicion del array tablero esta ocupada
	static boolean estaOcupada(char[][] tablero, int fila, int columna) {
		boolean ocupada;
		
		if(tablero[fila][columna] == FICHA_VACIA) {
			ocupada = false;
		}else {
			ocupada = true;
		}
		
		return ocupada;
		
	}
	
	// Inserta la ficha deseada en las coordenadas indicadas
	static char[][] insertarFicha(char[][] tablero, int fila, int columna, char ficha){
		
		char[][] tableroActualizado = tablero;
		
		tableroActualizado[fila][columna] = ficha;
		
		return tableroActualizado;
	}
	
	// Devuelve FALSE si hay algun hueco encontrado o TRUE si no hay ninguno
	static boolean estaLleno(char[][] tablero) {
		boolean huecoEncontrado = false;
		
		for(int i = 0; i < tablero.length; i++) {
			for(int j = 0; j < tablero[0].length; j++) {
				if(tablero[i][j] == FICHA_VACIA) {
					huecoEncontrado = true;
				}
			}
		}
		
		return !huecoEncontrado;
	}
	
	// Comprueba las verticales, horizontales y diagonales para
	// determinar si hay un ganador. Si lo hay, devuelve la ficha
	// del que haya ganado. Si no, devuelve la ficha vacia
	static char hayGanador(char[][] tablero) {
		
		char ganador = FICHA_VACIA;
		int i = 0;
		
		// Comprobara dentro de los limites y mientras no encuentre ganador
		while(i < tablero.length && ganador != FICHA_VACIA){
			
			// Comprueba las verticales
			if(tablero[0][i] == tablero[1][i] && tablero[0][i] == tablero[2][i] && tablero[0][i] != FICHA_VACIA){ 	
				
				ganador = tablero[0][i];
				
			// Comprueba las horizontales
			}else if(tablero[i][0] == tablero[i][1] && tablero[i][0] == tablero[i][2] && tablero[i][0] != FICHA_VACIA){ 	
				
				ganador = tablero[i][0];
				
			}
			
			i++;
		}
		
		// Si todavia no hay ganador, comprobar las diagonales
		if(ganador == FICHA_VACIA) {
			if((tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2] && tablero[1][1] != FICHA_VACIA) ||
				(tablero[2][0] == tablero[1][1] && tablero[2][0] == tablero[0][2] && tablero[1][1] != FICHA_VACIA)){
				
				ganador = tablero[1][1];
				
		    }
		}
		
		return ganador;
	}

}

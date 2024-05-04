package main;

import java.util.Scanner;

public class Main {
	
	static final byte OPCION_SALIDA = 6;
	static Scanner sc = new Scanner(System.in);
	
	static String[][] cofre = new String[9][2];

	
	public static void main(String[] args) {

		String[][] inventario = new String[9][2]; //{{Objeto, cantidad}}
		byte opcion = 0;
		
		inventario = inicializarInventario(inventario);
		inicializarCofre();
		
		// Menu principal
		do {
			
			printMenu();
			
			opcion = sc.nextByte();
			
			switch(opcion) {
				
				// Imprimir inventario
				case 1->{
					verInventario(inventario);
				}
				
				// Add un objeto
				case 2->{
					String objeto = "";
					
					System.out.print("\nQue objeto quieres añadir?\n>> ");
					objeto = sc.next();
					
					addObjeto(inventario, objeto);
				}
				
				// Dropear un objeto
				case 3->{
					String objeto = "";
					
					System.out.print("\nQue objeto quieres dropear?\n>> ");
					objeto = sc.next();
					
					dropObjeto(inventario, objeto);
				}
				
				// Ver cofre
				case 4->{

					verCofre();
				}
				
				// Meter item en el cofre
				case 5->{

					String objeto = "";
					
					System.out.print("\nQue objeto quieres meter en el cofre?\n>> ");
					objeto = sc.next();
					
					inventario = guardarItem(inventario, objeto);
				}
				
				default ->{
					tirarError("Opcion incorrecta.");
				}
				
			}
			
		}while(opcion != OPCION_SALIDA);
		
	}
	
	// Inicializa el inventario, con los objetos vacíos y de cantidad 0
	static String[][] inicializarInventario(String[][] inventario) {
		for(int i = 0; i < inventario.length; i++) {
			inventario[i][0] = "";
			inventario[i][1] = "0";
		}
		
		return inventario;
	}
	
	// 1. Imprimir inventario
	static void verInventario(String[][] inventario) {
		System.out.println("\nInventario: ");
		for(int i = 0; i < inventario.length; i++) {
			if(inventario[i][0].equals("")) {
				System.out.print("[]");
			}else {
				System.out.print("["+inventario[i][0]+": "+inventario[i][1]+"]");
			}
		}
		System.out.println();
	}
	
	// 2. Añadir objeto
	static String[][] addObjeto(String[][] inventario, String objeto){
		// Primero de todo mirar si ya tenemos un objeto del mismo nombre
		if(buscarObjeto(inventario, objeto) != -1) {
			// Si ya existe, obtener en que posicion esta
			int posicion = buscarObjeto(inventario, objeto);
			// Sumarle 1 a la cantidad
			int cantidad = Integer.valueOf(inventario[posicion][1])+1;
			// Actualizar el inventario
			inventario[posicion][1] = cantidad+"";
		// Si no existe en el inventario
		}else {
			// Primero de todo mirar que el inventario no este lleno
			if(estaLleno(inventario)) {
				System.out.println("\nERROR: el inventario esta lleno.\n");
			// Si no esta lleno...
			}else {
				// ... pone el objeto nuevo en la primera posicion vacia y de cantidad 1
				int posicion = obtenerPrimeraPosicionVacia(inventario);
				inventario[posicion][0] = objeto;
				inventario[posicion][1] = "1";

			}
		}
		
		verInventario(inventario);
		
		return inventario;
	}
	
	// 3. Dropear objeto
	static String[][] dropObjeto(String[][] inventario, String objeto){
		// Comprueba si el inventario esta vacio
		if(estaVacio(inventario)) {
			System.out.println("\nERROR: El inventario esta vacio");
		// Si hay objetos mirar si tenemos ese objeto en el inventario
		}else {
			// Si no esta, que salte error
			if(buscarObjeto(inventario, objeto) == -1) {
				System.out.println("\nERROR: No tienes ese objeto\n");
			}else {
				// Si esta ese objeto, primero encontrar la posicion
				int posicion = buscarObjeto(inventario, objeto);
				// Luego mirar la cantidad q hay de ese objeto
				int cantidad = Integer.valueOf(inventario[posicion][1]);
				
				// Si hay mas de 1 objeto
				if(cantidad > 1) {
					// Simplemente reducir la cantidad en 1 y ponerle ese valor
					cantidad--;
					inventario[posicion][1] = cantidad+"";
				// Si solo queda 1 objeto...
				}else {
					//Borrar el nombre
					inventario[posicion][0] = "";
					// Y poner la cantidad a 0
					inventario[posicion][1] = "0";
				}
			}
		}
		
		verInventario(inventario);
		
		return inventario;
	}
	
	// Funcion inicializar cofre
	static void inicializarCofre() {
		// Por cada una de las filas
		for(int i = 0; i < cofre.length; i++) {
			// Por cada una de las posiciones de item
			cofre[i][0] = "";
			cofre[i][1] = "0";
		}
	}
	
	// 4. Ver contenidos del cofre
	static void verCofre() {
		System.out.println("\nCofre: ");
		for(int i = 0; i < cofre.length; i++) {
			// Salto de linea por cada fila
			if(cofre[i][0].equals("")) {
				System.out.print("[]");
			}else {
				System.out.print("["+cofre[i][0]+": "+cofre[i][1]+"]");
			}
		}
		System.out.println();
	}
	
	// 5. Meter item del inventario dentro del cofre
	static String[][] guardarItem(String[][] inventario, String objeto) {
		// Primero ver si tiene realmente ese objeto
		if(buscarObjeto(inventario, objeto) == -1) {
			System.out.println("\nERROR: No tienes ese objeto\n");
		}else {
			// Si lo tiene, mirar si ya hay un objeto igual en el cofre
			if(buscarObjeto(cofre, objeto) != -1) {
				// Si ya hay uno, pillar la posicion
				int posicion = buscarObjeto(cofre, objeto);
				// ... aumentar la cantidad en 1 ...
				int cantidad = Integer.valueOf(cofre[posicion][1])+1;
				// .. y actualizarlo en el cofre
				cofre[posicion][1] = cantidad+"";
				
				// Finalmente, quitarle el objeto al jugador
				dropObjeto(inventario, objeto);
				
			// Si no hay ningun objeto del mismo tipo en el cofre...
			}else {
				// Mirar si el cofre esta lleno
				if(estaLleno(cofre)) {
					tirarError("El cofre esta lleno.");
				}else {;
					// Si no, meterle el item en la ultima posicion vacia
					int posicionInventario = buscarObjeto(inventario, objeto);
					int posicionVaciaCofre = obtenerPrimeraPosicionVacia(cofre);
					
					cofre[posicionVaciaCofre][0] = inventario[posicionInventario][0];
					cofre[posicionVaciaCofre][1] = "1";
					
					// Finalmente, quitarle el objeto al jugador
					dropObjeto(inventario, objeto);
					
				}
			}
			
			verCofre();
		}
		
		return inventario;
	}
	

/***********************************************************FUNCIONES AUXILIARES************************************************************/
	// Devuelve la posicion de un objeto, si existe. Si no, devuelve -1
	static int buscarObjeto(String[][] inventario, String objeto) {
		int i = 0;
		boolean encontrado = false;
		
		while(!encontrado && i < inventario.length) {
			if(inventario[i][0].equals(objeto)) encontrado = true;
			else i++;
		}
		
		if(encontrado) {
			return i;
		}else {
			return -1;
		}
	}
	
	// Devuelve la primera posicion vacia del array. Si esta lleno, devuelve -1
	static int obtenerPrimeraPosicionVacia(String[][] inventario) {
		int i = 0;
		boolean encontrada = false;
		
		while(!encontrada && i < inventario.length) {
			if(inventario[i][0].equals("")) encontrada = true;
			else i++;
		}
		
		if(encontrada) {
			return i;
		}else {
			return -1;
		}
	}
	
	// Determina si el inventario esta lleno
	static boolean estaLleno(String[][] inventario) {
		int i = 0;
		boolean huecoEncontrado = false;
		
		while(!huecoEncontrado && i < inventario.length) {
			if(inventario[i][0].equals("")) huecoEncontrado = true;
			else i++;
		}
		
		return !huecoEncontrado;
	}
	
	// Determina si el inventario esta vacio
	static boolean estaVacio(String[][] inventario) {
		int i = 0;
		boolean objetoEncontrado = false;
		
		while(!objetoEncontrado && i < inventario.length) {
			if(!inventario[i][0].equals("")) objetoEncontrado = true;
			else i++;
		}
		
		return !objetoEncontrado;
	}
	
	static void printMenu() {
		System.out.print("\nElige que hacer:\n"
					    + "1. Ver el inventario\n"
					    + "2. Añadir objeto\n"
					    + "3. Dropear objeto\n"
					    + "4. Ver cofre\n"
					    + "5. Meter item en el cofre\n"
					    + "6. Salir\n\n"
					    + ">> ");
	}
	
	static void tirarError(String error) {
		System.out.println("\nERROR: "+error+"\n");
	}

}

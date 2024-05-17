# Tres en raya / Tic Tac Toe
En este programa simularás un tres en raya en Java usando un array **local** de **chars** bidimensional 3x3.

## Base
El programa, a lo largo de su funcionamiento: 
1. Imprimirá por pantalla el tablero.
2. Preguntará al jugador **que le toque** en qué columna y en qué fila querrá colocar su ficha, la cual será un **char** ('X' para el jugador 1 y 'O' para el jugador 2).
3. Al llenarse el tablero o cuando alguien haga 3 en raya, la partida terminará con un ganador o con un empate.
  3.1. Si hay un ganador, se mostrará quién de los dos ha ganado.
  3.2. Si es un empate, se mostrará como tal.  
4. Se preguntará si se quiere volver a jugar.
  4.1. En caso afirmativo, el tablero se vaciará y la partida empezará de nuevo.
  4.2. En caso negativo, el programa finalizará del todo. 

En todo momento comprobarás que el jugador introduce **datos correctos** (_es decir que no te meterá la ficha en la fila -2 ni en la 47_), además de las comprobaciones lógicas del propio juego (_que no intente poner una ficha donde ya hay una, que no se pueda seguir jugando si hay ganador o el tablero esta lleno..._). Además, controlarás el turno: cuando un jugador termine su turno, le tocará al otro, y así hasta acabar.

Necesitarás tres funciones **principales** (imprimirTablero, insertarFicha y hayGanador) y tres funciones **auxiliares** (inicializarTablero, estaOcupada, estaLleno).

## Funciones principales
- **imprimirTablero()**
  - **Recibe:** el tablero (_array bidimensional char 3x3_).
  - **Devuelve:** nada.
  - **Hace:** imprime el tablero por pantalla. Tendrás que ser creativo a la hora de asegurarte de que se imprima tal y como lo dibujaríamos en papel.
            _Pista: usa dos bucles anindados con condicionales dentro._
- **insertarFicha()**
  - **Recibe:** el tablero, número de fila, número de columna, y una ficha ('X' u 'O').
  - **Devuelve:** el array actualizado.
  - **Hace:** inserta la ficha (**char**) correspondiente en la columna y fila indicadas. **_Se asume que esa posición está vacía_**
- **hayGanador()**
  - **Recibe:** el tablero.
  - **Devuelve:** el tipo de ficha ganadora ('X' u 'O') si hay ganador, o una ficha vacía (' ') si todavía no lo hay.
  - **Hace:** comprueba todas las filas, las columnas y las dos diagonales buscando que haya tres carácteres ('X' u 'O') consecutivos. Si los hay, devolverá el **char** correspondiente. Si no, devolverá un espacio (' ').

## Funciones auxiliares
- **inicializarTablero()**
  - **Recibe:** el tablero.
  - **Devuelve:** el tablero inicializado.
  - **Hace:** llena todo el tablero con chars de espacio (' ').
- **estaOcupada()**
  - **Recibe:** el tablero, una fila y una columna.
  - **Devuelve:** un boolean.
  - **Hace:** comprueba si la posición correspondiente a la fila y la columna ya contiene una ficha (_que no sea vacía_). Si contiene alguna, devolverá TRUE. Si no, FALSE.
- **estaLleno()**
  - **Recibe:** el tablero.
  - **Devuelve:** un boolean.
  - **Hace:** comprueba si el tablero se ha llenado. Si quedase algun espacio libre, devolvería FALSE. Si no encontrase ninguno, devolvería TRUE.



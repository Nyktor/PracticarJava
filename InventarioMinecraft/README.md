
# Simulador del inventario del Minecraft
Vas a intentar simular un inventario del Minecraft usando un array bidimensional de Strings **local** de 9x2 (9 posiciones, cada una con la posicion 0 \[objeto\] y la posicion 1 \[cantidad\].
## Base
En la función **main** crea un menú principal con tres opciones:
- Ver inventario
- Añadir item. Preguntará al jugador qué ítem quiere meter en el inventario.
- Cerrar

Necesitarás tres funciones **principales** (inicializarInventario(), verInventario() y addObjeto()) y tres funciones **auxiliares**.
### Funciones principales
* **inicializarInventario():**
  * **Recibe:** el inventario (array bidimensional String)
  * **Devuelve:** el inventario inicializado.
  * **Hace:** inicializa el inventario, poniendo el objeto en "" y la cantidad en "0". Se llama al principio del programa.
* **verInventario()**
  * **Recibe:** el inventario
  * **Devuelve:** nada
  * **Hace:** imprime el inventario por pantalla. Formato \[Objeto:cantidad\]\[Objeto2:cantidad2\]\[\]\[\]
* **addObjeto()**
  * **Recibe:** el inventario y el objeto (String) que se quiere añadir
  * **Devuelve:** el inventario actualizado (si se ha podido añadir el objeto)
  * **Hace:** añade el objeto pasado al inventario:
    * Si ya existía, se suma 1 a la cantidad
    * Si no existía, comprueba si el inventario está lleno:
      * Si lo está, hace saltar un error diciendo que ta lleno
      * Si no lo está, mete el objeto en la primera posicion vacia
### Funciones auxiliares
* **buscarObjeto()**
  * **Recibe:** el inventario y el objeto (String) que se quiere encontrar
  * **Devuelve:** la posición (*índice*) del ítem en el array inventario, **o -1 si no lo encuentra**
  * **Hace:** un bucle que te recorre todo el array en busca de ese objeto
* **obtenerPrimeraPosicionVacia()**
  * **Recibe:** el inventario
  * **Devuelve:** la posición (*índice*) de la primera posicion vacía del inventario, **o -1 si no lo encuentra**
  * **Hace:** un bucle que recorre todo el array en busca de la primera posición vacía
* **estaLleno()**
  * **Recibe:** el inventario
  * **Devuelve:** un boolean
  * **Hace:** comprueba si existe algun hueco vacio en el inventario
* **estaVacio()**
  * **Recibe:** el inventario
  * **Devuelve:** un boolean
  * **Hace:** comprueba si existe algun item en el inventario

## Extra 1: dropear objetos
Vamos a añadir una tercera opcion al menu: dropear un objeto del inventario. Para ello solo hará falta una nueva función:
* **dropearItem()**
 * **Recibe:** el inventario y el objeto que quiere dropear
 * **Devuelve:** el inventario actualizado sin el objeto
 * **Hace:** quita el objeto pasado del inventario:
   * Si el inventario está vacío, saltará un error
   * Si no, buscará el objeto pasado a lo largo del inventario
     * Si no lo encuentra, mostrará un error por pantalla
     * Si lo encuentra, actuará en función del número de ítems que hay
       * Si es mayor a 1, simplemente disminuirá el número en 1
       * Si es 1, quitará completamente el ítem del inventario
*Nota: podemos hacer que el array sea ordenado o no. En Minecraft no son ordenados, ya que puedes tener un ítem en la posición 2 sin tener nada en la 0 ni en la 1.*  

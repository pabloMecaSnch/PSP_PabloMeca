package entregable2;

public class Persona extends Thread {
	Ordenador o;
	private int idPersona;
	public boolean tarjetaIzqd;
	public boolean tarjetaDrch;

	Persona(int id, Ordenador o) {
		this.idPersona = id;
		this.o = o;
	}

	/**
	 * M�todo que se utiliza para intentar coger la tarjeta izquierda durante tres
	 * intentos. Si consigue dicha tarjeta, intenta coger la derecha durante otros
	 * tres intentos. e intenta usar el ordenador.
	 * 
	 * Si la tajeta a su izquierda no se encuentra disponible, entrar� en wait() y
	 * volver� a ejecutarse a s� mismo incrementando el valor de la variable
	 * intento. Si no a logrado encontrar la tajeta, la soltar� para que otra
	 * persona que la necesite la puede coger.
	 * 
	 * @param intentos el n�mero con el que se inicializa el valor del primer
	 *                 intento
	 * 
	 */
	public synchronized void hayTarjetaIzqd() {
		System.out.println("Buscando tarjeta izquierda (Persona: " + this.idPersona + ")");
		int[] posTarjeta = buscaTarjetas(this.idPersona);
		for (int i = 0; i < 3;) {
			try {
				if (o.Tarjetas[posTarjeta[0]] == true) {
					getTarjetaIzqrd(posTarjeta[0]);
				} else {
					wait();
					i++;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.tarjetaIzqd = false;
			o.Tarjetas[posTarjeta[0]] = true;
			this.tarjetaDrch = false;
			o.Tarjetas[posTarjeta[1]] = true;
			notifyAll();
		}
	}

	public void getTarjetaIzqrd(int posTarjeta) {
		this.tarjetaIzqd = true;
		o.Tarjetas[posTarjeta] = false;
		if (this.tarjetaDrch == true) {
			System.out.println("Persona: "+this.idPersona+": Intento usar el pc");
			o.usaOrdenador(this);
		}

	}

	/**
	 * M�todo que se utiliza para intentar coger la tarjeta derecha durante tres
	 * intentos. Si consigue dicha tarjeta, intenta coger la izquierda durante otros
	 * tres intentos e intenta usar el ordenador.
	 * 
	 * Si la tajeta a su derecha no se encuentra disponible, entrar� en wait() y
	 * volver� a ejecutarse a s� mismo incrementando el valor de la variable
	 * intento. Si no a logrado encontrar la tajeta, la soltar� para que otra
	 * persona que la necesite la puede coger.
	 * 
	 * @param intentos el n�mero con el que se inicializa el valor del primer
	 *                 intento
	 * 
	 */
	public synchronized void hayTarjetaDrch() {
		System.out.println("Buscando tarjeta derecha (Persona: " + this.idPersona + ")");
		int[] posTarjeta = buscaTarjetas(this.idPersona);
		while (this.tarjetaDrch == false) {
			try {
				if (o.Tarjetas[posTarjeta[1]] == true) {
					getTarjetaDrch(0, posTarjeta[1]);

				} else {
					wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized void getTarjetaDrch(int intentos, int posTarjeta) {
		this.tarjetaDrch = true;
		o.Tarjetas[posTarjeta] = false;
		if (this.tarjetaIzqd == true) {
			System.out.println("Persona: "+this.idPersona+": Intento usar el pc");
			o.usaOrdenador(this);
		} else {
			hayTarjetaIzqd();
		}

	}

	/**
	 * M�todo que se ejecuta cuando el usuario deja de usar el ordenador establece
	 * los valores de sus tarjetas a falso y los de la colecci�n a verdadero,
	 * realizando as� la acci�n de soltar las tarjetas, para luego ejecutar el
	 * m�todo piensa()
	 */
	public synchronized void sueltaTarjetas() {
		int[] posTarjetas = buscaTarjetas(this.idPersona);
		this.tarjetaDrch = false;
		o.Tarjetas[posTarjetas[0]] = true;
		this.tarjetaIzqd = false;
		o.Tarjetas[posTarjetas[1]] = true;
		System.out.println("La persona " + this.idPersona + " deja de usar el ordenador");
		notifyAll();
		piensa();
	}

	/**
	 * M�todo que simula el pensar de la persona, realizando un sleep sobre hilo con
	 * un tiempo aleatorio de hasta 4000 milisegundos, despu�s vuelve a ejecutar el
	 * run()
	 */
	private void piensa() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep((long) Math.random() * 4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// run();
		System.out.println("Ya termine");
	}

	/**
	 * M�todo utilizado para calcular cual es la posici�n de la tarjeta izquierda y
	 * derecha, ya que la persona con id = 4 deber� coger las tajetas 4 y 0,
	 * mientras que con el resto, la posici�n de sus respectivas tarjetas se puede
	 * controlar f�cilmente seg�n su id.
	 * 
	 * @param idPersona
	 * @return Devuelve un array de enteros de dos valores, la posici�n 0 para la
	 *         tajeta izquierda y la 1 para la derecha
	 *
	 */
	private int[] buscaTarjetas(int idPersona) {
		int[] posTarjeta = new int[2];
		if (idPersona != 4) {
			posTarjeta[0] = idPersona;
			posTarjeta[1] = idPersona + 1;
		} else {
			posTarjeta[0] = idPersona;
			posTarjeta[1] = 0;
		}
		return posTarjeta;

	}

	@Override
	public void run() {
		hayTarjetaDrch();
	}

	public int getIdPersona() {
		return this.idPersona;
	}
}

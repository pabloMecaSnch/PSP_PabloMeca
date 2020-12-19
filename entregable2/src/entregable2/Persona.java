package entregable2;

public class Persona extends Thread {
	Mesa o;
	private int idPersona;
	private Tarjeta tarjetaIzqd;
	private Tarjeta tarjetaDrch;

	public int turno;
	
	/**
	 * Método para ver si la persona tiene en su posesión la tarjeta izquierda.
	 * 
	 * @return <ol>
	 * <li>Objeto Tarjeta</li>
	 * <li>null</li>
	 * </ol>
	 */
	public Tarjeta getTarjetaIzqd() {
		return tarjetaIzqd;
	}

	/**
	 * Método para modificar el valor de la tarjeta izquierda
	 * @param tarjetaIzqd Objeto de case Tarjeta
	 */
	public void setTarjetaIzqd(Tarjeta tarjetaIzqd) {
		this.tarjetaIzqd = tarjetaIzqd;
	}

	/**
	 * Método para ver si la persona tiene en su posesión la tarjeta derecha.
	 * 
	 * @return <ol>
	 * <li>Objeto Tarjeta</li>
	 * <li>null</li>
	 * </ol>
	 */
	public Tarjeta getTarjetaDrch() {
		return tarjetaDrch;
	}

	/**
	 * Método para modificar el valor de la tarjeta derecha
	 * @param tarjetaIzqd Objeto de case Tarjeta
	 */
	public void setTarjetaDrch(Tarjeta tarjetaDrch) {
		this.tarjetaDrch = tarjetaDrch;
	}

	/**
	 * Método para modificar el valor del id
	 * @param tarjetaIzqd id de la persona
	 */
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * Método constructor de la clase Persona
	 * @param id id de la persona
	 * @param o Objeto de la clase Mesa
	 */
	public Persona(int id, Mesa o) {
		this.idPersona = id;
		this.o = o;
	}

	/**
	 * Método de devuelve el valor del id de la persona
	 * @return id
	 */
	public int getIdPersona() {
		return this.idPersona;
	}

	/**
	 * Cuerpo del hilo.</br>
	 * Este hilo se ejecuta 10 veces antes de terminar, al inicio de cada ciclo
	 * piensa un tiempo aleatorio y luego pide turno para entrar en la mesa, para después pedir permiso
	 * al portero.</br>
	 * Después usa el ordenador y suelta las tarjetas.
	 * 
	 */
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			piensa();

			o.pideTurno(this);
			o.pidePermiso(this);
			buscaTarjetaDrch();
			buscaTarjetaIzqrd();
			o.usaOrdenador(this);
			sueltaTarjetas();
		}
	}

	/**
	 * Método que suelta las tarjetas
	 * <ol>
	 * <li>si tiene las tarjetas las suelta</li>
	 * <li>si no tiene las tarjetas, no las suelta</li>
	 * </ol>
	 * Por último libera al portero
	 */
	public synchronized void sueltaTarjetas() {

		int[] posTarjeta = this.getPosicion(this.idPersona);
		if (this.tarjetaDrch != null) {
			this.tarjetaDrch = null;
			o.tarjetas[posTarjeta[1]].p = null;
			System.out.println("La persona :" + this.getIdPersona() + " suelta las tarjetas dr");
		}
		if (this.tarjetaIzqd != null) {
			this.tarjetaIzqd = null;
			o.tarjetas[posTarjeta[0]].p = null;
			System.out.println("La persona :" + this.getIdPersona() + " suelta las tarjetas iz");
		}
		o.portero = true;
		notifyAll();

	}

	/**
	 * Método usado para simular el pensar de la persona
	 */
	private void piensa() {

		System.out.println("Persona: " + this.idPersona + " pensando");
		try {
			Thread.sleep((int) Math.random() * 3000 + 100);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Método que busca la tarjeta de su derecha
	 * <ol>
	 * <li>si no tiene la tarjeta de la derecha la coge</li>
	 * </ol>
	 */
	public void buscaTarjetaDrch() {
		System.out.println("Persona: " + this.idPersona + " buscando tarjeta derecha");
		int[] posicionTarjeta = getPosicion(this.idPersona);

		if (this.tarjetaDrch == null) {
			o.cogeTarjetaDrch(posicionTarjeta[1], this);
		}
	}

	/**
	 * Método que busca la tarjeta de su izquierda
	 * <ol>
	 * <li>si no tiene la tarjeta de la izquierda la coge</li>
	 * </ol>
	 */
	public void buscaTarjetaIzqrd() {
		System.out.println("Persona: " + this.idPersona + " buscando tarjeta izquierda");
		int[] posicionTarjeta = getPosicion(this.idPersona);
		if (this.tarjetaIzqd == null) {
			o.cogeTarjetaIzqrd(posicionTarjeta[0], this);
		}
	}

	/**
	 * Método usado para obtener la posición de la tarjeta en función de la persona
	 * 
	 * @param id Identificador de la persona
	 * @return Array de dos posiciones
	 *         <ol>
	 *         <li>[0]Usada para guardar la tarjeta de la izquierda</li>
	 *         <li>[1]Usada para guardar la tarjeta de la derecha</li>
	 *         </ol>
	 */
	private int[] getPosicion(int id) {
		int[] posTarjeta = new int[2];
		if (id != 4) {
			posTarjeta[0] = id;
			posTarjeta[1] = id + 1;
		} else {
			posTarjeta[0] = id;
			posTarjeta[1] = 0;
		}
		return posTarjeta;
	}
}

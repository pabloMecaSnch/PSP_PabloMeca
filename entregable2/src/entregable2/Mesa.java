package entregable2;

public class Mesa {

	public boolean ordenadorOcupado = false;
	public Tarjeta[] tarjetas;

	public static int turno = 0;
	public static boolean portero = true;

	public int turnoActual = 0;
	
	/**
	 * M�todo constructor de la clase Mesa
	 * @param tarjetas las tarjetas que va a haber en la mesa.
	 */
	public Mesa(Tarjeta[] tarjetas) {
		this.ordenadorOcupado = false;
		this.tarjetas = tarjetas;
	}

	/**
	 * M�todo usado para recoger turno
	 * @param p Persona que pide turno
	 */
	public synchronized void pideTurno(Persona p) {
		p.turno = Mesa.turno;
		this.turnoActual = p.turno;
		Mesa.turno++;
		System.out.println("Persona "+p.getIdPersona()+" coge turno "+Mesa.turno);
	}
	
	/**
	 * M�todo que comprueba si el portero te deja pasar. Una vez entra, aumenta el turno para que pueda entrar el siguiente
	 * @param p
	 */
	public synchronized void pidePermiso(Persona p) {
		while (!portero || p.turno != this.turnoActual) {
			try {

				wait();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		this.turnoActual++;
	}

	/**
	 *M�todo que coge la tarjeta derecha si est� disponible
	 * 
	 * @param pos Posicion de la tarjeta izquierda
	 * @param p   Persona que coge la tarjeta
	 */
	public synchronized void cogeTarjetaDrch(int pos, Persona p) {

		if (this.tarjetas[pos].p == null) {
			p.setTarjetaDrch(this.tarjetas[pos]);
			this.tarjetas[pos].p = p;
			System.out.println("La persona :" + p.getIdPersona() + " pilla la tarjeta dr");
		}
	}

	/**
	 *M�todo que coge la tarjeta izquierda si est� disponible
	 * 
	 * @param pos Posicion de la tarjeta izquierda
	 * @param p   Persona que coge la tarjeta
	 */
	public synchronized void cogeTarjetaIzqrd(int pos, Persona p) {

		if (this.tarjetas[pos].p == null) {
			p.setTarjetaIzqd(this.tarjetas[pos]);
			this.tarjetas[pos].p = p;
			System.out.println("La persona :" + p.getIdPersona() + " pilla la tarjeta iz");
		}
	}

	/**
	 * M�todo para usar el ordenador. Mientras el ordenador est� siendo utilizado,
	 * la siguiente persona que quiera utilizarlo deber� esperar.
	 * 
	 * @param p Persona que utiliza el ordenador
	 */
	public synchronized void usaOrdenador(Persona p) {

		this.ordenadorOcupado = true;
		System.out.println("La persona :" + p.getIdPersona() + " est� usando el ordenador");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Termine de usar el pc");
		this.ordenadorOcupado = false;

	}
}

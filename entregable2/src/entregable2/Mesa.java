package entregable2;

public class Mesa {

	public boolean ordenadorOcupado = false;
	public Tarjeta[] tarjetas;

	public Mesa(Tarjeta[] tarjetas) {
		this.ordenadorOcupado = false;
		this.tarjetas = tarjetas;
	}

	/**
	 * Método que coge la tarjeta derecha
	 * 
	 * <ol>
	 * <li>En caso de no tener la tarjeta a su disponibilidad
	 * 	<ol>
	 * 		<li>comprueba si tiene la tarjeta de la Izquierda
			 *<ol>
			 * 		<li>si la tiene la suelta</li>
			 * <li>si no la tiene, espera</li>
			 * 	</ol>
	 * 		</li>
	 * 	</ol>
	 * </li>
	 * </ol>
	 * @param pos Posicion de la tarjeta derecha
	 * @param p   Persona que coge la tarjeta
	 */
	public synchronized void cogeTarjetaDrch(int pos, Persona p) {

		if (this.tarjetas[pos].p == null) {
			p.setTarjetaDrch(this.tarjetas[pos]);
			this.tarjetas[pos].p = p;
			System.out.println("La persona :" + p.getIdPersona() + " pilla la tarjeta dr");
		} else {
			if (p.getTarjetaIzqd() == null) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * Método que coge la tarjeta izquierda
	 * 
	 * <ol>
	 * <li>En caso de no tener la tarjeta a su disponibilidad
	 * 	<ol>
	 * 		<li>comprueba si tiene la tarjeta de la Derecha
			 *<ol>
			 * 		<li>si la tiene la suelta</li>
			 * <li>si no la tiene, espera</li>
			 * 	</ol>
	 * 		</li>
	 * 	</ol>
	 * </li>
	 * </ol>
	 * @param pos Posicion de la tarjeta izquierda
	 * @param p   Persona que coge la tarjeta
	 */
	public synchronized void cogeTarjetaIzqrd(int pos, Persona p) {

		if (this.tarjetas[pos].p == null) {
			p.setTarjetaIzqd(this.tarjetas[pos]);
			this.tarjetas[pos].p = p;
			System.out.println("La persona :" + p.getIdPersona() + " pilla la tarjeta iz");
		} else {
			if (p.getTarjetaDrch() == null) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Método para usar el ordenador. Mientras el ordenador esté siendo utilizado,
	 * la siguiente persona que quiera utilizarlo deberá esperar.
	 * 
	 * @param p Persona que utiliza el ordenador
	 */
	public synchronized void usaOrdenador(Persona p) {
		while (ordenadorOcupado) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.ordenadorOcupado = true;
		System.out.println("La persona :" + p.getIdPersona() + " está usando el ordenador");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Termine de usar el pc");
		this.ordenadorOcupado = false;
		notifyAll();
	}
}

package entregable2;

public class Ordenador {

	public boolean ocupado = false;
	public Tarjeta[] tarjetas;

	public Ordenador(Tarjeta[] tarjetas) {
		this.ocupado = false;
		this.tarjetas = tarjetas;
	}

	/**
	 * Método que coge la tarjeta derecha
	 * @param pos Posicion de la tarjeta derecha
	 * @param p Persona que coge la tarjeta
	 */
	public synchronized void cogeTarjetaDrch(int pos,Persona p) {
		
		if (this.tarjetas[pos].p == null) {	
			p.setTarjetaDrch(this.tarjetas[ pos ]);
			this.tarjetas[ pos ].p = p;
			System.out.println("La persona :" + p.getIdPersona() + " pilla la tarjeta dr");
		} else {
			try {
				wait(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Método que coge la tarjeta izquierda
	 * @param pos Posicion de la tarjeta izquierda
	 * @param p Persona que coge la tarjeta
	 */
	public synchronized void cogeTarjetaIzqrd(int pos,Persona p) {
		
		if (this.tarjetas[pos].p == null) {	
			p.setTarjetaIzqd(this.tarjetas[ pos ]);
			this.tarjetas[ pos ].p = p;
			System.out.println("La persona :" + p.getIdPersona() + " pilla la tarjeta iz");
		} else {
			try {
				wait(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Método para usar el ordenador.
	 * Mientras el ordenador esté siendo utilizado, la siguiente persona que quiera utilizarlo deberá esperar.
	 * @param p Persona que utiliza el ordenador
	 */
	public synchronized void usaOrdenador(Persona p) {
		while (ocupado) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.ocupado = true;
		System.out.println("La persona :" + p.getIdPersona() + " está usando el ordenador");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Termine de usar el pc");
		this.ocupado = false;
		notifyAll();
	}
}

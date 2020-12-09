package entregable2;

import javax.sql.rowset.spi.SyncResolver;

public class Persona extends Thread {
	Ordenador o;
	private int idPersona;
	private Tarjeta tarjetaIzqd;
	private Tarjeta tarjetaDrch;

	public Tarjeta getTarjetaIzqd() {
		return tarjetaIzqd;
	}

	public void setTarjetaIzqd(Tarjeta tarjetaIzqd) {
		this.tarjetaIzqd = tarjetaIzqd;
	}

	public Tarjeta getTarjetaDrch() {
		return tarjetaDrch;
	}

	public void setTarjetaDrch(Tarjeta tarjetaDrch) {
		this.tarjetaDrch = tarjetaDrch;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	Persona(int id, Ordenador o) {
		this.idPersona = id;
		this.o = o;
	}

	public int getIdPersona() {
		return this.idPersona;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			int contador = 0;
			piensa();
			while (this.tarjetaDrch == null || this.tarjetaIzqd == null) {
				// contador utilizado para controlar que una persona no acapare las tarjetas
				// mucho tiempo
				if (contador == 2) {
					sueltaTarjetas();
					contador = 0;
				}
				buscaTarjetaDrch();
				buscaTarjetaIzqrd();
				contador++;
			}
			o.usaOrdenador(this);
			sueltaTarjetas();
		}
	}

	/**
	 * Método que suleta las tarjetas
	 * <ol>
	 * <li>si tiene las tarjetas las suelta</li>
	 * <li>si no tiene las tarjetas, no las suelta</li>
	 * </ol>
	 */
	private synchronized void sueltaTarjetas() {

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
		notifyAll();
	}

	/**
	 * Método usado para simular el pensar de la persona
	 */
	private void piensa() {
		
		System.out.println("Persona: " + this.idPersona + " pensando");
		try {
			Thread.sleep((int) Math.random() * 1000);
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

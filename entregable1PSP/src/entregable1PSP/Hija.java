package entregable1PSP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hija {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int n = 1;
		int n2 = 0;
		String entrada;
		// variables booleanas creadas para mejorar el control durante el proceso
		boolean fecha = false,mesas=false,correcto;
		/*
		 * correcto: la uso para que el programa sepa que se ha introducido una opcion correcta, de no ser así, se enviria un mensaje.
		 * mesa: la uso para que el programa sepa que ya se ha introducido el primer comando y ahora debe preguntar por el numero de mesas.
		 * fecha: la uso para que el programa sepa ya se ha introducido un numero de mesas soportado y ahora debe preguntar por una fecha de reserva.
		 * */
		//Aqui programo el input de la clase para que recoja los datos que la clase Padre le envia
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Date f = null;
		//Inicio de la clase
		System.out.println(
				"Bienvenido a central de reservas del restaurante Delicias de Pablo \n" + "¿En qué puedo ayudarle?");
		//Linea informativa para que el usuario sepa como empezar a usar el programa.
		System.out.println("Teclee ,ayuda, para ver los comandos disponibles actualmente.");
		//Creacion de un bucle infinito para que la clase no muera hasta que n2 sea mayor o igual que n
		while (n > n2) {
			//Si hay datos enviados a esta clase, se ejecuta el if
			if (br.ready()) {
				correcto = false;
				//Leo la informacion que ha introducido la clase Padre
				entrada = br.readLine();
				if (entrada.equalsIgnoreCase("Reservar mesa")) {
					fecha=false;//Cambio la variable fecha a false para que el proceso de reserva se reinicie si vuelves a introducir este comando.
					System.out.println("Perfecto, ¿cuántas personas asistirán a la comida?");
					correcto = true;
					mesas=true;
				}
				//Compruebo si ya debo recoger los datos de la fecha con el booleano fecha
				if(fecha) {
					try {
						f = sdf.parse(entrada);
						System.out.println("fecha: "+sdf.format(f).toString()+"\nLa reserva se ha realizado con éxito, ¡muchas gracias!");
						n2=2;
						correcto= true;
					}catch(ParseException e) {
						System.out.println("Fecha mal introducida");
					}
				}else if(mesas) {
					//Compruebo si el dato introducido es un numero entero
				try {
					//Si lo es, recojo el dato y dependiendo de el numero introducido, muestro un mesaje u otro.
					int i = Integer.parseInt(entrada);
					int nMesas = calcNumeMesas(i);
					if (nMesas == -1) {
						System.out.println("Lo sentimos, no podemos hacer reservas tan grandes");
					}else if(nMesas==0) {
						System.out.println("Lo sentimos, no podemos hacer reservas para 0 personas o menos.");
					}else {
						System.out.println("De acuerdo, para una comida con " + i + " personas, hemos reservado "
								+ nMesas + " mesas.");
						System.out.println("Introduzca una fecha con el siguiente formato: dd/mm/yyyy");
						fecha = true;
					}
					correcto = true;
					//Si el dato no es un numero recojo la excepcion generada por el metodo parseInt y dejo que el programa siga su ejecución
				} catch (NumberFormatException e) {
					// TODO: handle exception
				}
				}
				if(entrada.equalsIgnoreCase("ayuda")) {
					System.out.println("-Reservar mesa:  Comienza el proceso para reservar mesa");
					correcto=true;
				}

				if (!correcto) {
					System.out.println("lo siento, no te entendido");
				}
			}

		}
	}
//Metodo que maneja el dato del numero de personas para devolver el numero de mesas reservadas
	private static int calcNumeMesas(int i) {
		// TODO Auto-generated method stub
		int num = 1;
		if (i >= 6 && i <= 12) {
			num = 2;
		}
		if (i > 12) {
			num = -1;
		}
		if(i<=0 ) {
			num=0;
		}
		return num;
	}

}

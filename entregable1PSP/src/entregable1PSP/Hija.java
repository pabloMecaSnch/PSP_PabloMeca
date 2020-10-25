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
		boolean fecha = false;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Date f = null;
		boolean correcto;
		System.out.println(
				"Bienvenido a central de reservas del restaurante Delicias de Pablo \n" + "¿En qué puedo ayudarle?");
		while (n > n2) {
			if (br.ready()) {
				correcto = false;
				entrada = br.readLine();
				if (entrada.equalsIgnoreCase("Reservar mesa")) {
					System.out.println("Perfecto, ¿cuántas personas asistirán a la comida?");
					correcto = true;
				}
				if(fecha) {
					try {
						f = sdf.parse(entrada);
						System.out.println("fecha: "+sdf.format(f).toString()+"\nLa reserva se ha realizado con éxito, ¡muchas gracias!");
						n2=2;
						correcto= true;
					}catch(ParseException e) {
						System.out.println("Fecha mal introducida");
					}
				}
				try {
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

				} catch (NumberFormatException e) {
					// TODO: handle exception
				}

				if (!correcto) {
					System.out.println("lo siento, no te entendido");
				}
			}

		}
	}

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

package runProcess;

import java.io.IOException;
import java.util.Arrays;

public class RunPProcess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length<=0){
			System.err.println("Se necesita un programa a ejecutar");
			System.exit(-1);
		}
		ProcessBuilder pb = new ProcessBuilder(args);
		try {
			Process process = pb.start();
			int retorno = process.waitFor();
			System.out.println("La ejecución de "+Arrays.toString(args)+" devuelve "+retorno);
		} catch (IOException ex) {
			System.err.println("Excepción de E/S");
			System.exit(-1);
		}catch(InterruptedException ex) {
			System.err.println("El proceso hijo finalizó de forma incorrecta");
			Thread.currentThread().interrupt();
			System.exit(-1);
		}
	}
	
	
	
}

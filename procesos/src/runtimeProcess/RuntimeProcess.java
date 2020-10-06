package runtimeProcess;

import java.io.IOException;

public class RuntimeProcess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length<=0) {
			System.err.println("Se necesita un programa a ejecutar");
			System.exit(-1);
		}
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec(args);
			process.destroy();
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println("Excepción de E/S");
			System.exit(-1);
		}
	}

}

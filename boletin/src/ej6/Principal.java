package ej6;

public class Principal {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		CuentaVocal hilos[];
		char vocales[]= {'a','e','i','o','u'};
		hilos = new CuentaVocal[vocales.length];
		for (int i = 0; i < vocales.length; i++) {
			hilos[i]=  new CuentaVocal(vocales[i]);
			hilos[i].run();
		}
		for (int i = 0; i < hilos.length; i++) {
			hilos[i].join();
		}
		
		System.out.println(ContadorGlobal.totalVocales);
		
	}

}

package ejercicio5;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ProcessComunication {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Process process = Runtime.getRuntime().exec("javac -cp src src/ejercicio5/Hija.java");
		Process pr = Runtime.getRuntime().exec("java -cp src ejercicio5/Hija");
		InputStream is = pr.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		
		while((line = br.readLine()) !=null) {
			System.out.println(line);
		}
	}

}

package ejHilo1;

public class Fibonacci implements Runnable{

	Thread t;
	int n;
	Fibonacci(){
		t = new Thread(this,"hilo fibonacci");
	}
	public Fibonacci(int n) {
		t = new Thread(this, "hola");
		this.n=n;
		t.start();
	}
	@Override
	public void run() {
		int n1=1;
		int n2=0;
		int res=0;
		for (int i = 0; i <= n; i++) {
			res=n1+n2;
			System.out.print(res+",");
			n2=n1;
			n1=res;
		}
	}
}

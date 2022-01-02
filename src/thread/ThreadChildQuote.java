package thread;

import java.io.IOException;

import test.Main;

public class ThreadChildQuote extends Thread {

	String filename;
	public ThreadChildQuote(String s) {
		filename=s;
	}
	@Override
	public void run() {
	     	try {
				Main.inputQuote(filename);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

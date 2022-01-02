package thread;
import java.io.IOException;

import test.Main;

public class ThreadChildOrder extends Thread {

	private String filename;
	public ThreadChildOrder(String s) {
		filename=s;
	}
	@Override
	public void run() {
		try {
			Main.inputOrder(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package files.stock.desis;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
abstract public class WriteOrderFile {
      
	public static void addOrder(String s) {
		try {
		      FileWriter fw = new FileWriter("src/files/stock/desis/orderList.txt", true);
		      fw.write(s);
		      fw.close();
		      
		    } catch (IOException e) {
		      System.out.println(e.getMessage());
		      e.printStackTrace();
		    }
		  
	}
	
}

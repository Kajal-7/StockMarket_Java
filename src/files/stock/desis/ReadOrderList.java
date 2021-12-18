
package files.stock.desis;

import java.io.FileReader;
import java.io.IOException;

abstract public class ReadOrderList {
       
	 static public void readFile(){
	        try 
	        {   FileReader fr = new FileReader("src/files/stock/desis/orderList.txt");
	            int content;
	            while ((content = fr.read()) != -1) {
	                System.out.print((char) content);
	            }
	            fr.close(); 
	        } catch (IOException e) {
	        	System.out.println(e.getMessage());
	            e.printStackTrace();
	        }
	   
}
}

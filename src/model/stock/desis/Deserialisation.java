package model.stock.desis;
import java.io.*;


public abstract class Deserialisation {
	private final static String file ="src/model/stock/desis/quoteobjFile.txt";
	
	public static void deserialiseAndBuildList() {
		 
		try {
			
			FileInputStream fin=new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fin);
			
			while((fin.available())!=0) {
				
				Quote obj=(Quote)in.readObject();
				Quote.makeQuotemap(obj);
					
			}
		    in.close();
	    fin.close();
		     
		}catch(EOFException e) {
			e.getMessage();
		}catch (FileNotFoundException e) {
			
			e.getMessage();
			e.printStackTrace();
		} catch (IOException e) {
			
			e.getMessage();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.getMessage();
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	        
	}
	

}

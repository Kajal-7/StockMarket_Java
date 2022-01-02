
package model.stock.desis;

import java.io.*;
import java.util.TreeMap;
import java.util.Map;


public abstract class Serialisation {
	
	private final static String file ="src/model/stock/desis/quoteobjFile.txt";
	 
	 static void serialiseObject(TreeMap<String, Quote> map) {
		
		 try
	        {   
	           
	              FileOutputStream fs = new FileOutputStream(file,true);
	              ObjectOutputStream out = new ObjectOutputStream(fs);
	              for (Map.Entry<String, Quote> e : map.entrySet())
	             {
	            	 out.writeObject(e.getValue());
	            	 out.reset();
	             }
	            
	            out.close();
	            fs.close();
	              
	            System.out.println("Objects have been serialized");
	  
	        }
		 catch (StreamCorruptedException e) {
			 e.printStackTrace();
		 }
		    catch (FileNotFoundException e) {
				
				e.getMessage();
				e.printStackTrace();
			}  
	        catch(IOException e)
	        {
	            System.out.println("IOException is caught");
	            
	        }
		    catch (Exception e) {
				e.printStackTrace();
			}
	}

}

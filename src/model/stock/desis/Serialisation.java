package model.stock.desis;

import java.io.*;
import java.util.ArrayList;


public abstract class Serialisation {
	
	private final static String file ="src/model/stock/desis/quoteobjFile.txt";
	 
	public static void serialiseObject(ArrayList<Quote> Qobj) {
		
		 try
	        {   
	           
	              FileOutputStream fs = new FileOutputStream(file,true);
	              ObjectOutputStream out = new ObjectOutputStream(fs);
	             for(Quote obj:Qobj)
	             {
	            	 out.writeObject(obj);
	            	 out.reset();
	             }
	            
	            out.close();
	            fs.close();
	              
	            System.out.println("Objects have been serialized");
	  
	        }
	          
	        catch(IOException e)
	        {
	            System.out.println("IOException is caught");
	            
	        }
	    
	}

}

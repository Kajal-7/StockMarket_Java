package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import files.stock.desis.ReadOrderList;
import thread.*;
import model.stock.desis.*;
import java.lang.Thread;

public class Main {
	//Input files
	private final static String Orderfile ="src/test/orderinput.txt";
	private final static String Quotefile ="src/test/quoteinput.txt";
	private final static String Orderfile2 ="src/test/orderinput2.txt";
	
	
	public static  void inputQuote(String quotefile)  throws IOException {
		BufferedReader bre = new BufferedReader(new FileReader(quotefile));
	     String content;
	     while ((content = bre.readLine()) != null) 
	    	 {   
	    		 Quote obj=new Quote(content,0.0,0.0); //Constructors are by default synchronized
	    	 }
        
	}
	public static  void inputOrder(String orderfile) throws IOException{
		 BufferedReader br = new BufferedReader(new FileReader(orderfile));
	     String line;

	     while ((line = br.readLine()) != null) {
	    	 String[] data = line.split("\\s+");
	    	 
	          if(data[0].equals("Sleep") && data.length==2) {
	    		 
	    		 try {
					Thread.sleep(Integer.parseInt(data[1]));
				} catch (NumberFormatException | InterruptedException e) {
					e.printStackTrace();
				}
	    	 }
	          else if(Order.checkInput(data))
	    		 Order.placeOrder(data);
	    	 
	     }
	}
	public static void main(String args[]) throws IOException, InterruptedException {
		
		//create list of Quote objects already present in serialised Objects file
		Deserialisation.deserialiseAndBuildList();
		System.out.println("These stocks already existes");
		Quote.printStocks();
		System.out.println("<-------------------------------------------------->");
		
		//Creating new Quote objects using input file
		 
	     ThreadChildQuote t1=new ThreadChildQuote(Quotefile);
	     
	     t1.start();
	     
	     t1.join();
	    
	     
          Quote.serialise();
          System.out.println("The list of all avaiable stocks");
          Quote.printStocks();
        
		
        System.out.println("<------------------------------------------------->");
        
        
	//Creating Orders
       ThreadChildOrder t3=new ThreadChildOrder(Orderfile);
        ThreadChildOrder t4=new ThreadChildOrder(Orderfile2);
	     t3.start();
         t4.start();
         t3.join();
         t4.join();
      
     
     System.out.println("<------------------------------------------------->\n Trade");
     Trade.printTrade();
     
     System.out.println("<------------------------------------------------->");
     System.out.println("The updated Stocks.");
     Quote.printStocks();
     
     
     //<---------Can call underlying method to read all the orders placed till now---------------->
     //ReadFile.readFile(); 
     
     
     //done to update serialized object file containing Quote objects
     try {
     PrintWriter writer = new PrintWriter("src/model/stock/desis/quoteobjFile.txt");
     writer.print("");
     writer.close();
     System.out.println("The serialised list of quote objects is updated now!");
     Quote.serialiseWholeList();
     }catch(FileNotFoundException e) {
    	 e.printStackTrace();
     }catch(Exception e) {
    	 e.printStackTrace();
     }
   
   }
}

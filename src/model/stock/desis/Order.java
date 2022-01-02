package model.stock.desis;
import java.util.*;
import files.stock.desis.WriteOrderFile;

public class Order {
     
	static protected ArrayList<Order> buyorderlist= new  ArrayList<Order>();
	static protected ArrayList<Order> sellorderlist = new  ArrayList<Order>();

	
	//default 
	 String stockname;
	 double price;
	 String tradingPartyName;
	 String typeOfOrder;
	 String directionOfOrder;

	//Constructor
	public Order(String stockname,double price, String tradingPartyName,String directionOfOrder,String typeOfOrder){
	   this.stockname=stockname;
	   this.price=price;
	   this.tradingPartyName=tradingPartyName;
	   this.typeOfOrder=typeOfOrder;
	   this.directionOfOrder=directionOfOrder;
	   
	   String s=stockname+" "+String.valueOf(price)+" "+tradingPartyName+" "+typeOfOrder+" "+directionOfOrder+"\n";
	   //used to keep record of all orders as text file
	   WriteOrderFile.addOrder(s);
	      
	}

	//Getters
			public String getStockName() {
				return this.stockname;
			}
			public String getTradingPartyName() {
				return this.tradingPartyName;
			}
			public String getTypeOfOrder() {
				return this.typeOfOrder;
			}
			public String getDirectionOfOrder() {
				return this.directionOfOrder;
			}
			public double getPrice() {
				return this.price;
			}
			
	//Setters
			public void setStockName(String stockName) {
				 this.stockname=stockName;
			}
			public void setTradingPartyName(String tradingPartyName) {
				this.tradingPartyName=tradingPartyName;
			}
			public void setTypeOfOrder(String typeOfOrder) {
				 this.typeOfOrder=typeOfOrder;
			}
			public void setDirectionOfOrder(String directionOfOrder) {
				this.directionOfOrder=directionOfOrder;
			}
			public void setPrice(double price) {
				this.price=price;
			}
	
	
	
	//--------------------Methods---------------
	
	//to check is order credentials fine
	static public boolean checkInput(String [] data) {
		if(data.length!=5)
		{
			System.out.println("Please enter all the details of Order");
			return false;
		}
		if(Quote.findStock(data[0])==null) {
			System.out.println("Stock not available!");
			return false;
		}
		if(!(data[3].equals("Buy")) && !(data[3].equals("Sell")))
		{
			System.out.println("Wrong direction of order.");
			return false;
		}
		if(!(data[4].equals("Limit")) && !(data[4].equals("Market")) && !(data[4].equals("IOC"))) {
			System.out.println("Wrong type of Order.");
			return false;
		}
		return true;
	}
	static public  synchronized void placeOrder(String [] data) {
		Order obj=new Order(data[0],Double.parseDouble(data[1]),data[2],data[3],data[4]);
		
		if(data[4].equals("Market")) {
			MarketOrder.placeSpecificTypeOrder(obj);
		}
		else if(data[4].equals("IOC")) {
			IocOrder.placeSpecificTypeOrder(obj);
		}
		else if(data[4].equals("Limit")) {
			LimitOrder.placeSpecificTypeOrder(obj);
		}
		else {
			System.out.println("Invalid type of Order");
			return;
		}

		System.out.println("Order Placed");
		return;
	}
	//to update required Quote object corresponding to Order made 
	protected static void update(String stockName, String direction) {
		//check current max buy and min sell if it is equal to val then update else not
		double val=0;
		
		if(direction.equals("Buy"))
		{
			for(Order obj:buyorderlist) {
				if(obj.getStockName().equals(stockName)&& obj.getPrice()>val)
					val=obj.getPrice();
			}
		}
		else
		{
			val=Double.MAX_VALUE;
			for(Order obj:sellorderlist) {
				if(obj.getStockName().equals(stockName)&& obj.getPrice()<val)
					val=obj.getPrice();
			}
			if(val==Double.MAX_VALUE)
				val=0;
		}
		
		Quote.updatePrice(stockName,direction,val);
	}
	static public void removeObjects( Order obj2) {
		
		if(obj2.directionOfOrder.equals("Buy"))
		{
			buyorderlist.remove(obj2);
		}
		else
		{
			sellorderlist.remove(obj2);
		}
	}
}

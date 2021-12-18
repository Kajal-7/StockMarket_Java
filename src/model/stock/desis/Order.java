package model.stock.desis;
import files.stock.desis.WriteOrderFile;

public class Order {

	private String stockname;
	private double price;
	private String tradingPartyName;
	private String typeOfOrder;
	private String directionOfOrder;

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
	   
	   //to update corresponding quote objects
	   this.update(this.stockname,this.directionOfOrder,this.price);
		   
	 
	}
	
	//Getters
	public String getStockName(Order obj) {
		return obj.stockname;
	}
	public String getTradingPartyName(Order obj) {
		return obj.tradingPartyName;
	}
	public String getTypeOfOrder(Order obj) {
		return obj.typeOfOrder;
	}
	public String getDirectionOfOrder(Order obj) {
		return obj.directionOfOrder;
	}
	public double getPrice(Order obj) {
		return obj.price;
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
			System.out.println("Please enter al the details of Order");
			return false;
		}
		if(Quote.findStock(data[0])==null) {
			System.out.println("Stock not available!");
			return false;
		}
		return true;
	}
	static public void placeOrder(String [] data) {
		Order obj=new Order (data[0],Double.parseDouble(data[1]),data[2],data[3],data[4]);
		System.out.println("Order Placed");
	}
	//to update required Quote object corresponding to Order made 
	private void update(String stockName, String direction, double val) {
		Quote.updatePrice(stockName,direction,val);
		
	}
}

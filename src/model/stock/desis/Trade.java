package model.stock.desis;

import java.util.ArrayList;

public class Trade {
     
	private double price;
	private String stockName;
	private String buyerTradingParty;
	private String sellerTradingParty;
	private static ArrayList<Trade> tradelist=new ArrayList<Trade>();
	
	//Constructor
	public Trade(double price, String stockName,String buyerTradingParty, String sellerTradingParty ) {
		this.price=price;
		this.stockName=stockName;
		this.buyerTradingParty=buyerTradingParty;
		this.sellerTradingParty=sellerTradingParty;
		
	}
	//introduction
	private void introduce() {
		System.out.println("Price: "+this.price+
				"\nStockname: "+this.stockName+
				"\nBuyer Trading Party Name: "+this.buyerTradingParty+
				"\nSeller Trading Party Name: "+this.sellerTradingParty+"\n");
	}
	//Getters
	public double getPrice() {
		return this.price;
	}
	public String getStockNmae() {
		return this.stockName;
	}
	public String getBuyerTradingParty() {
		return this.buyerTradingParty;
	}
	public String getSellerTradingParty() {
		return this.sellerTradingParty;
	}
	//Setters
	public void setPrice(double price) {
		this.price=price;
	}
	public void setStockName(String stockName) {
		this.stockName=stockName;
	}
	public void setBuyerTradingParty(String buyerTradingParty) {
		this.buyerTradingParty=buyerTradingParty;
	}
	public void setSellerTradingParty(String sellerTradingParty) {
		this.sellerTradingParty=sellerTradingParty;
	}
	//Methods
	static protected void createTrade(Order obj1,Order obj2) {
		
		double tradeprice;
		if(obj1.getTypeOfOrder().equals("Market"))
		tradeprice=obj2.getPrice();
		else
			tradeprice=(obj1.getPrice()+obj2.getPrice())/2;
		
		String buyparty;
		String sellparty;
		if(obj1.getDirectionOfOrder().equals("Buy"))
		{
			buyparty=obj1.getTradingPartyName();
			sellparty=obj2.getTradingPartyName();
		}
		else
		{
			buyparty=obj2.getTradingPartyName();
			sellparty=obj1.getTradingPartyName();
		}
		
		
			
			Trade tradeobj=new Trade(tradeprice,obj1.getStockName(),buyparty,sellparty);
			tradelist.add(tradeobj);
			
			Order.removeObjects(obj2);
			Order.update(obj2.stockname,obj2.directionOfOrder);
		
	}	
	
	static public void printTrade() {
		for(Trade obj: tradelist) {
			obj.introduce();
		}
	}
}

package model.stock.desis;
import java.util.*;


public class LimitOrder extends Order{
	
	
	public LimitOrder(String stockname, double price, String tradingPartyName, String directionOfOrder,
			String typeOfOrder) {
		super(stockname, price, tradingPartyName, directionOfOrder, typeOfOrder);
		
	}
		
    //if order is possible this method returns the second trade party object else returns null
	static Order isTradePossible(Order firstTradeParty) {
		
		Order secondTradeParty=null;
		ArrayList<Order> orderlist;
		
		if(firstTradeParty.getDirectionOfOrder().equals("Buy")) {
			orderlist=Order.sellorderlist;
		}
		else {
			orderlist=Order.buyorderlist;}
		
		for(Order obj: orderlist) {
			 if(obj.getStockName().equals(firstTradeParty.getStockName())&&
					 !(firstTradeParty.getTradingPartyName().equals(obj.getTradingPartyName()))) {
				 if(firstTradeParty.getDirectionOfOrder().equals("Buy") && firstTradeParty.getPrice()>obj.getPrice())
					 secondTradeParty=obj;
				 else if(firstTradeParty.getDirectionOfOrder().equals("Sell") && firstTradeParty.getPrice()<obj.getPrice())
					 secondTradeParty= obj;
			 }
		 }
		
		
		return secondTradeParty;
	}
	
	static void placeSpecificTypeOrder(Order obj) {
	
		  Order secondTradingParty=isTradePossible(obj);
		  if(secondTradingParty!=null) {
			  Trade.createTrade(obj,secondTradingParty);
		  }
		  else {
			  if(obj.getDirectionOfOrder().equals("Buy")) {
				  buyorderlist.add(obj);
				  }
				else
				{
					sellorderlist.add(obj);
				}		  
			  Quote.updatePriceAddOrder(obj.stockname,obj.directionOfOrder,obj.price);	
		  }
	}
}
